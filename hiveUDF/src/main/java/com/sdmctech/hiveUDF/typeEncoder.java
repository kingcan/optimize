package com.sdmctech.hiveUDF;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

public class typeEncoder extends UDF {

 public int evaluate(Text text){
if(text.toString().equals("null")||text.toString().equals("NULL")){
return -1;
}else if(text.toString().equals("normal")){
return 1;
}else if(text.toString().equals("employee")){
    return 2;
}else if(text.toString().equals("test")){
    return 3;
}else{
return 0;
}
 }

}