/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.rocketmq.flink;

import org.apache.commons.lang.Validate;
import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.typeutils.ResultTypeQueryable;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.runtime.state.FunctionInitializationContext;
import org.apache.flink.runtime.state.FunctionSnapshotContext;
import org.apache.flink.streaming.api.checkpoint.CheckpointedFunction;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;
import org.apache.rocketmq.client.consumer.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.flink.common.serialization.KeyValueDeserializationSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import static org.apache.rocketmq.flink.RocketMQConfig.*;
import static org.apache.rocketmq.flink.RocketMQUtils.getInteger;
import static org.apache.rocketmq.flink.RocketMQUtils.getLong;

/**
 * The RocketMQSource is based on RocketMQ pull consumer mode,
 * and provides exactly once reliability guarantees when checkpoints are enabled.
 * Otherwise, the source doesn't provide any reliability guarantees.
 */
public class RocketMQSource<OUT> extends RichParallelSourceFunction<OUT>
        implements CheckpointedFunction, ResultTypeQueryable<OUT> {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(RocketMQSource.class);
    private static final String OFFSETS_STATE_NAME = "topic-partition-offset-states";
    private transient MQPullConsumerScheduleService pullConsumerScheduleService;
    private DefaultMQPullConsumer consumer;
    private KeyValueDeserializationSchema<OUT> schema;
    private RunningChecker runningChecker;
    private transient ListState<Tuple2<MessageQueue, Long>> unionOffsetStates;
    private Map<MessageQueue, Long> offsetTable;
    private Map<MessageQueue, Long> restoredOffsets;
    private Properties props;
    private String topic;
    private String group;
    private transient volatile boolean restored;

    public RocketMQSource(KeyValueDeserializationSchema<OUT> schema, Properties props) {
        this.schema = schema;
        this.props = props;
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        LOG.debug("source open....");
        Validate.notEmpty(props, "Consumer properties can not be empty");
        Validate.notNull(schema, "KeyValueDeserializationSchema can not be null");

        this.topic = props.getProperty(RocketMQConfig.CONSUMER_TOPIC);
        this.group = props.getProperty(RocketMQConfig.CONSUMER_GROUP);

        Validate.notEmpty(topic, "Consumer topic can not be empty");
        Validate.notEmpty(group, "Consumer group can not be empty");

        if (offsetTable == null) {
            offsetTable = new ConcurrentHashMap<>();
        }
        if (restoredOffsets == null) {
            restoredOffsets = new ConcurrentHashMap<>();
        }

        runningChecker = new RunningChecker();

        pullConsumerScheduleService = new MQPullConsumerScheduleService(group);
        consumer = pullConsumerScheduleService.getDefaultMQPullConsumer();

        consumer.setInstanceName(String.valueOf(getRuntimeContext().getIndexOfThisSubtask()));
        RocketMQConfig.buildConsumerConfigs(props, consumer);
    }

    @Override
    public void run(SourceContext context) throws Exception {
        LOG.debug("source run....");
        // The lock that guarantees that record emission and state updates are atomic,
        // from the view of taking a checkpoint.
        final Object lock = context.getCheckpointLock();

        int delayWhenMessageNotFound = getInteger(props, RocketMQConfig.CONSUMER_DELAY_WHEN_MESSAGE_NOT_FOUND,
                RocketMQConfig.DEFAULT_CONSUMER_DELAY_WHEN_MESSAGE_NOT_FOUND);

        String tag = props.getProperty(RocketMQConfig.CONSUMER_TAG, RocketMQConfig.DEFAULT_CONSUMER_TAG);

        int pullPoolSize = getInteger(props, RocketMQConfig.CONSUMER_PULL_POOL_SIZE,
                RocketMQConfig.DEFAULT_CONSUMER_PULL_POOL_SIZE);

        int pullBatchSize = getInteger(props, RocketMQConfig.CONSUMER_BATCH_SIZE,
                RocketMQConfig.DEFAULT_CONSUMER_BATCH_SIZE);

        pullConsumerScheduleService.setPullThreadNums(pullPoolSize);
        pullConsumerScheduleService.registerPullTaskCallback(topic, new PullTaskCallback() {

            @Override
            public void doPullTask(MessageQueue mq, PullTaskContext pullTaskContext) {
                try {
                    long offset = getMessageQueueOffset(mq);
                    if (offset < 0) {
                        return;
                    }

                    PullResult pullResult = consumer.pull(mq, tag, offset, pullBatchSize);
                    boolean found = false;
                    switch (pullResult.getPullStatus()) {
                        case FOUND:
                            List<MessageExt> messages = pullResult.getMsgFoundList();
                            for (MessageExt msg : messages) {
                                byte[] key = msg.getKeys() != null ? msg.getKeys().getBytes(StandardCharsets.UTF_8) : null;
                                byte[] value = msg.getBody();
                                OUT data = schema.deserializeKeyAndValue(key, value);

                                // output and state update are atomic
                                synchronized (lock) {
                                    context.collectWithTimestamp(data, msg.getBornTimestamp());
                                }
                            }
                            found = true;
                            break;
                        case NO_MATCHED_MSG:
                            LOG.debug("No matched message after offset {} for queue {}", offset, mq);
                            break;
                        case NO_NEW_MSG:
                            break;
                        case OFFSET_ILLEGAL:
                            LOG.warn("Offset {} is illegal for queue {}", offset, mq);
                            break;
                        default:
                            break;
                    }

                    synchronized (lock) {
                        putMessageQueueOffset(mq, pullResult.getNextBeginOffset());
                    }

                    if (found) {
                        pullTaskContext.setPullNextDelayTimeMillis(0); // no delay when messages were found
                    } else {
                        pullTaskContext.setPullNextDelayTimeMillis(delayWhenMessageNotFound);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        try {
            pullConsumerScheduleService.start();
        } catch (MQClientException e) {
            throw new RuntimeException(e);
        }

        runningChecker.setRunning(true);

        awaitTermination();

    }

    private void awaitTermination() throws InterruptedException {
        while (runningChecker.isRunning()) {
            Thread.sleep(50);
        }
    }

    private long getMessageQueueOffset(MessageQueue mq) throws MQClientException {
        Long offset = offsetTable.get(mq);
        // restoredOffsets(unionOffsetStates) is the restored global union state;
        // should only snapshot mqs that actually belong to us
        if (restored && offset == null) {
            offset = restoredOffsets.get(mq);
        }
        if (offset == null) {
            offset = consumer.fetchConsumeOffset(mq, false);
            if (offset < 0) {
                String initialOffset = props.getProperty(RocketMQConfig.CONSUMER_OFFSET_RESET_TO, CONSUMER_OFFSET_LATEST);
                switch (initialOffset) {
                    case CONSUMER_OFFSET_EARLIEST:
                        offset = consumer.minOffset(mq);
                        break;
                    case CONSUMER_OFFSET_LATEST:
                        offset = consumer.maxOffset(mq);
                        break;
                    case CONSUMER_OFFSET_TIMESTAMP:
                        offset = consumer.searchOffset(mq, getLong(props,
                                RocketMQConfig.CONSUMER_OFFSET_FROM_TIMESTAMP, System.currentTimeMillis()));
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown value for CONSUMER_OFFSET_RESET_TO.");
                }
            }
        }
        offsetTable.put(mq, offset);
        return offsetTable.get(mq);
    }

    private void putMessageQueueOffset(MessageQueue mq, long offset) throws MQClientException {
        offsetTable.put(mq, offset);
        consumer.updateConsumeOffset(mq, offset);
    }

    @Override
    public void cancel() {
        LOG.debug("cancel ...");
        runningChecker.setRunning(false);

        if (pullConsumerScheduleService != null) {
            pullConsumerScheduleService.shutdown();
        }

        offsetTable.clear();
        restoredOffsets.clear();
    }

    @Override
    public void close() throws Exception {
        LOG.debug("close ...");
        // pretty much the same logic as cancelling
        try {
            cancel();
        } finally {
            super.close();
        }
    }

    @Override
    public void snapshotState(FunctionSnapshotContext context) throws Exception {
        // called when a snapshot for a checkpoint is requested

        if (!runningChecker.isRunning()) {
            LOG.debug("snapshotState() called on closed source; returning null.");
            return;
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("Snapshotting state {} ...", context.getCheckpointId());
        }

        unionOffsetStates.clear();

        if (LOG.isDebugEnabled()) {
            LOG.debug("Snapshotted state, last processed offsets: {}, checkpoint id: {}, timestamp: {}",
                    offsetTable, context.getCheckpointId(), context.getCheckpointTimestamp());
        }

        for (Map.Entry<MessageQueue, Long> entry : offsetTable.entrySet()) {
            unionOffsetStates.add(Tuple2.of(entry.getKey(), entry.getValue()));
        }
    }

    @Override
    public void initializeState(FunctionInitializationContext context) throws Exception {
        // called every time the user-defined function is initialized,
        // be that when the function is first initialized or be that
        // when the function is actually recovering from an earlier checkpoint.
        // Given this, initializeState() is not only the place where different types of state are initialized,
        // but also where state recovery logic is included.
        LOG.debug("initialize State ...");

        this.unionOffsetStates = context.getOperatorStateStore().getUnionListState(new ListStateDescriptor<>(
                OFFSETS_STATE_NAME, TypeInformation.of(new TypeHint<Tuple2<MessageQueue, Long>>() {
        })));

        this.restored = context.isRestored();

        if (restored) {
            if (restoredOffsets == null) {
                restoredOffsets = new ConcurrentHashMap<>();
            }
            for (Tuple2<MessageQueue, Long> mqOffsets : unionOffsetStates.get()) {
                // unionOffsetStates is the restored global union state;
                // should only snapshot mqs that actually belong to us
                restoredOffsets.put(mqOffsets.f0, mqOffsets.f1);
            }
            LOG.info("Setting restore state in the consumer. Using the following offsets: {}", restoredOffsets);
        } else {
            LOG.info("No restore state for the consumer.");
        }
    }

    @Override
    public TypeInformation<OUT> getProducedType() {
        return schema.getProducedType();
    }
}
