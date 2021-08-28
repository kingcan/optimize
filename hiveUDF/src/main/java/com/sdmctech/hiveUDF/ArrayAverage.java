package com.sdmctech.hiveUDF;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.IntWritable;

import java.util.ArrayList;

public class ArrayAverage extends UDF {

    public IntWritable evaluate(ArrayList<Integer> list) {
        IntWritable result = new IntWritable(-1);
        if (list == null || list.size() < 1) {
            return result;
        }
        int sum = 0;
        for (Integer i : list) {
            sum += i;
        }
        result.set((int)sum/list.size());
        return result;
    }
}
