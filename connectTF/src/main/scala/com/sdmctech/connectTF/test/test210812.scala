package com.sdmctech.connectTF.test
import org.apache.spark.SparkEnv
import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.sql.functions._
/**
	* @Author: Larkin
	* @Date: 2021/8/12 17:34
	*/
object test210812 {
	def main(args: Array[String]): Unit = {
		// launch spark-shell with the following command:
		val spark = SparkSession.builder()
		  .master("local[*]")
			.getOrCreate()
		import spark.implicits._
		val df = Seq((8, "bat"), (8, "abc"), (1, "xyz"), (2, "aaa")).toDF("number", "word")
		df.show
		// scala> df.show
		// +------+----+
		// |number|word|
		// +------+----+
		// |     8| bat|
		// |     8| abc|
		// |     1| xyz|
		// |     2| aaa|
		// +------+----+
		val tf_output_dir = "/tmp/tfrecord-test"
		// dump the tfrecords to files.
//		df.repartition(3, col("number"))
//			.write.mode(SaveMode.Overwrite).partitionBy("number")
//			.format("tfrecord").option("recordType", "Example")
//			.save(tf_output_dir)
		df.write.mode(SaveMode.Overwrite)
			.format("tfrecord").option("recordType", "Example")
			.save(tf_output_dir)
		// ls /tmp/tfrecord-test
		// _SUCCESS        number=1        number=2        number=8
		// read back the tfrecords from files.
		val new_df = spark.read.format("tfrecord").option("recordType", "Example").load(tf_output_dir)
		new_df.show
		// scala> new_df.show
		// +----+------+
		// |word|number|
		// +----+------+
		// | bat|     8|
		// | abc|     8|
		// | xyz|     1|
		// | aaa|     2|
	}
}
