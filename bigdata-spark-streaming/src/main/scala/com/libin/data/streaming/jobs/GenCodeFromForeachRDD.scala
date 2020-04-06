package com.libin.data.streaming.jobs

import com.libin.data.streaming.utils.StreamingExamples
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * Copyright (c) 2020/4/3 libin Inc. All Rights Reserved.
 * Authors: libin<2578858653@qq.com>
 *
 * Purpose : Spark Streaming的foreachRDD算子使用
 *
 * Linux：		nc -lk 9999
 * windows：  nc -l -p 9999
 */
object GenCodeFromForeachRDD {
	def main(args: Array[String]): Unit = {
		val conf = new SparkConf().setMaster("local[2]").setAppName("GenCodeFromForeachRDD")
		val ssc = new StreamingContext(conf, Seconds(5))
		
		StreamingExamples.setStreamingLogLevels()
		val lines = ssc.socketTextStream("localhost", 9999)
		
		lines.map((_, 1))
			.foreachRDD {
				rdd =>
					val saveRdd: RDD[(String, Int)] = rdd.mapPartitions {
						iter =>
							val result = iter.map {
								line =>
									line
							}.toList
							result.toIterator
					}
					println(saveRdd.count())
			}
		ssc.start() // Start the computation
		ssc.awaitTermination() // Wait for the computation to terminate
	}
}
