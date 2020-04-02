package com.libin.data.flink

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.scala._

/**
 * Copyright (c) 2020/4/2 libin Inc. All Rights Reserved.
 * Authors: libin<2578858653@qq.com>
 *
 * Purpose : nc -lk 9999
 */
object GenCodeFromWindow {
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

// 滚动: countWindowAll(long size): Windows this DataStream into tumbling count windows.
// 滑动: countWindowAll(long size, long slide): Windows this DataStream into sliding count windows.

// 滚动: timeWindowAll(Time size): Windows this DataStream into tumbling time windows.
// 滑动: timeWindowAll(Time size, Time slide): Windows this DataStream into sliding time windows.


/**
 * Input:
 * a
 * a
 * a
 * a
 * a
 * b
 * b
 * b
 * b
 * b
 * a
 * b
 * c
 * d
 * e
 * Output:
 * 6> (a,5)
 * 2> (b,4)
 * 2> (b,1)
 * 4> (c,1)
 * 2> (b,1)
 * 6> (a,1)
 * 5> (d,1)
 * 1> (e,1)
 */
