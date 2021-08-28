package com.sdmctech.flink.util;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommand;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommandDescription;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisMapper;

import java.util.Optional;

/**
 * @Author: Larkin
 * @Date: 2021/7/8 10:23
 */
public class MyRedisMapper implements RedisMapper<Tuple2<String, String>> {
    /**
     * 表示从接收的数据中获取需要操作的redis key
     */
    @Override
    public String getKeyFromData(Tuple2<String, String> data) {
        return data.f0; //第一个元素
    }

    /**
     * 表示从接收的数据中获取需要操作的redis value
     */
    @Override
    public String getValueFromData(Tuple2<String, String> data) {
        return data.f1; //第二个元素
    }

    @Override
    public RedisCommandDescription getCommandDescription() {
        return new RedisCommandDescription(RedisCommand.SADDEX, 3*60 * 60);
    }

    @Override
    public Optional<String> getAdditionalKey(Tuple2<String, String> data) {
        return Optional.empty();
    }

    @Override
    public Optional<Integer> getAdditionalTTL(Tuple2<String, String> data) {
        return Optional.of(3*60 * 60);
    }
}
