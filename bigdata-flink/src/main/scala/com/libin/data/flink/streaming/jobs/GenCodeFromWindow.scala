package com.libin.data.flink.streaming.jobs

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment, createTypeInformation}
import org.apache.flink.streaming.api.windowing.time.Time

/**
 * Copyright (c) 2020/4/2 libin Inc. All Rights Reserved.
 * Authors: libin<2578858653@qq.com>
 *
 * Purpose : nc -lk 9999
 */
object GenCodeFromWindow {
	
	implicit val inTypeInfo = createTypeInformation[String]
	
	def main(args: Array[String]) {
		// create env
		val env = StreamExecutionEnvironment.getExecutionEnvironment
		// data source
		val text = env.socketTextStream("localhost", 9999)
		
		val counts: DataStream[(String, Int)] = text
			.flatMap {
				line =>
					line.toLowerCase.split("\\W+") filter {
						x => x.nonEmpty
					}
			}
			.map {
				x =>
					(x, 1)
			}
			.keyBy(0)
			.timeWindow(Time.seconds(5))
			//.countWindowAll(5, 2)
			//.timeWindowAll(Time.minutes(1),Time.seconds(30))
			.sum(1)
		
		counts.print()
		
		env.execute("Window Stream WordCount")
	}
}
