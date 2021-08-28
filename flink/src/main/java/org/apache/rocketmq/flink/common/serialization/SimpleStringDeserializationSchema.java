package org.apache.rocketmq.flink.common.serialization;

import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.nio.charset.StandardCharsets;

/**
 * @Author: Larkin
 * @Date: 2021/7/6 14:36
 */
public class SimpleStringDeserializationSchema implements KeyValueDeserializationSchema<String> {

    private static final long serialVersionUID = 1L;

    @Override
    public String deserializeKeyAndValue(byte[] key, byte[] value) {

        String v = value != null ? new String(value, StandardCharsets.UTF_8) : "";
        return v;
    }

    @Override
    public TypeInformation<String> getProducedType() {
        return TypeInformation.of(String.class);
    }
}
