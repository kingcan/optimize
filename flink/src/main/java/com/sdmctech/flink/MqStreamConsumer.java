package com.sdmctech.flink;

import com.alibaba.fastjson.JSONObject;
import com.sdmctech.flink.util.MyRedisMapper;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.redis.RedisSink;
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig;
import org.apache.flink.util.Collector;
import org.apache.rocketmq.flink.RocketMQConfig;
import org.apache.rocketmq.flink.RocketMQSource;
import org.apache.rocketmq.flink.common.serialization.KeyValueDeserializationSchema;
import org.apache.rocketmq.flink.common.serialization.SimpleStringDeserializationSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Author: Larkin
 * @Date: 2021/7/7 16:48
 */
public class MqStreamConsumer {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();
        DataStream<String> text = env.addSource(builderTurboMQSource()).name("turboMQSource");
        SingleOutputStreamOperator<Tuple2<String, List<String>>> result = text.flatMap((String line, Collector<Tuple2<String, List<String>>> out) -> {
            JSONObject jsonObject = (JSONObject) JSONObject.parse(line);
            String memberId = jsonObject.getString("memberId");
            String assetId = jsonObject.getJSONObject("collectionContent").getString("assetId");
            List<String> list = new ArrayList<>();
            list.add(assetId);
            out.collect(Tuple2.of(memberId, list));
        }).returns(Types.TUPLE(Types.STRING, Types.LIST(Types.STRING))).keyBy(0).reduce((t1, t2) -> {
            t1.f1.addAll(t2.f1);
            return t1;
        }).returns(Types.TUPLE(Types.STRING, Types.LIST(Types.STRING)));

        result.print();


        //创建redis的配置
        FlinkJedisPoolConfig build = new FlinkJedisPoolConfig.Builder().setHost("10.10.63.213").setPort(6379).build();

        //创建redissink
        RedisSink<Tuple2<String, String>> redisSink = new RedisSink<>(build, new MyRedisMapper());

        //result.addSink(redisSink);

        env.execute("MqStreamConsumer");
    }


    private static RocketMQSource<String> builderTurboMQSource() {
        Properties props = new Properties();
        props.setProperty(RocketMQConfig.NAME_SERVER_ADDR, "10.10.63.234:9876");
        props.setProperty(RocketMQConfig.CONSUMER_TOPIC, "videoPlay");
        props.setProperty(RocketMQConfig.CONSUMER_GROUP, "videoPlayConsumer");
        props.setProperty(RocketMQConfig.CONSUMER_TAG, "videoPlay");
        props.setProperty(RocketMQConfig.CONSUMER_OFFSET_RESET_TO, "latest");

        KeyValueDeserializationSchema<String> schema = new SimpleStringDeserializationSchema();

        RocketMQSource<String> turboMQConsumer = new RocketMQSource<String>(schema, props);
        return turboMQConsumer;
    }
}
