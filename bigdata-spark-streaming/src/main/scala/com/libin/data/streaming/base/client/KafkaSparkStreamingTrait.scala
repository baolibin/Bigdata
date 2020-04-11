package com.libin.data.streaming.base.client

import com.libin.data.streaming.base.SparkStreamingTrait
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * Copyright (c) 2020/4/3 libin Inc. All Rights Reserved.
 * Authors: libin<2578858653@qq.com>
 *
 * Purpose : SparkStreaming + Kafka
 */
trait KafkaSparkStreamingTrait extends SparkStreamingTrait {
	
	/**
	 * 获取解析参数
	 *
	 * @param inTopic            上游tpoic
	 * @param outTopic           输出topic
	 * @param outPath            输出路径
	 * @param offsetType         offset读取类型
	 * @param recoveryOffsetType offset恢复类型
	 * @param interval           batch间隔
	 * @param streamKind         上游数据类型
	 * @param partitionNumber    对上游topic重分区
	 * @param repartitionNumber  对结果数据重分区
	 */
	case class Params(inTopic: Set[String] = null,
	                  outTopic: String = "",
	                  outPath: String = "",
	                  offsetType: String = "",
	                  recoveryOffsetType: String = "",
	                  interval: Int = 7,
	                  streamKind: String = "",
	                  partitionNumber: Int = 1,
	                  repartitionNumber: Int = 1)
	
	/**
	 * 参数解析
	 */
	final val parser = new scopt.OptionParser[Params](appName) {
		head("Spark Streaming Kafka Params Parser", "")
		
		opt[String]('t', "inTopic").optional().action {
			(x, c) => c.copy(inTopic = x.split(",").toSet)
		}.text("input topics")
		
		opt[String]('o', "outTopic").optional().action {
			(x, c) => c.copy(outTopic = x.trim)
		}.text("output topics")
		
		opt[String]('s', "outPath").optional().action {
			(x, c) => c.copy(outPath = x.trim)
		}.text("outPath")
		
		opt[String]("ot").optional().action {
			(x, c) => c.copy(offsetType = x.trim)
		}.text("offsetType")
		
		opt[String]("rot").optional().action {
			(x, c) => c.copy(recoveryOffsetType = x.trim)
		}.text("recoveryOffsetType")
		
		opt[String]('i', "interval").optional().action {
			(x, c) => c.copy(interval = x.trim.toInt)
		}.text("interval")
		
		opt[String]("sk").optional().action {
			(x, c) => c.copy(streamKind = x.trim)
		}.text("streamKind")
		
		opt[String]("pn").optional().action {
			(x, c) => c.copy(partitionNumber = x.trim.toInt)
		}.text("partitionNumber")
		
		opt[String]("rpn").optional().action {
			(x, c) => c.copy(repartitionNumber = x.trim.toInt)
		}.text("repartitionNumber")
	}
	
	/**
	 * 创建SparkStreamingContext (ssc)
	 *
	 * @param params 参数
	 * @return StreamingContext
	 */
	def createStreamContext(params: Params): StreamingContext = {
		val conf = new SparkConf().setAppName(appName).setIfMissing("spark.master", "local")
		new StreamingContext(conf, Seconds(params.interval))
	}
	
	/**
	 * 程序执行主入口
	 */
	def main(args: Array[String]): Unit = {
		//参数解析
		val params = parser.parse(args, Params()).get
		// 创建StreamingContext对象
		val ssc = createStreamContext(params)
	}
	
}
