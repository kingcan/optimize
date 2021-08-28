package com.sdmctech.hiveUDF;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DuplicateRemove extends UDF {
    public String evaluate(Text text){
        List<String> throughLines = Arrays.stream(text.toString().split(",")).collect(Collectors.toList());
        //利用stream流将集合去重
        List<String> throughLineList = throughLines.stream().distinct().collect(Collectors.toList());
        //然后再用Stringuitls.join将集合变成逗号分开的字符串
       return StringUtils.join(throughLineList, ",");
    }
}
