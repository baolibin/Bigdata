package com.libin.data.flink.streaming.jobs.config

import org.apache.flink.streaming.api.scala.DataStream
import org.apache.flink.streaming.connectors.fs.StringWriter
import org.apache.flink.streaming.connectors.fs.bucketing.{BucketingSink, DateTimeBucketer}

/**
 * Copyright (c) 2020/4/2 libin Inc. All Rights Reserved.
 * Authors: libin<2578858653@qq.com>
 *
 * Purpose : 使用BucketingSink对存储的数据进行输出
 */
object GenCodeFromBucketingSink {
	def main(args: Array[String]): Unit = {

		val resultDS: DataStream[Long] = null

		val sink = new BucketingSink[Long]("output path")
		sink.setBucketer(new DateTimeBucketer[Long]("yyyy-MM-dd--HHmm"))
		sink.setWriter(new StringWriter[Long]())
		// sink.setBatchSize(1024 * 1024 * 100) // this is 100 MB,
		sink.setBatchSize(1024 * 1024 * 1) // this is 1 MB,
		// sink.setBatchRolloverInterval( 60 * 1000); // this is 30 seconds
		resultDS.addSink(sink)
	}
}
