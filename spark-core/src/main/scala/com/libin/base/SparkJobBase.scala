package com.libin.base

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession
import org.joda.time.DateTime
import org.slf4j.{Logger, LoggerFactory}

/**
 * Copyright (c) 2020/4/13 libin Inc. All Rights Reserved.
 * Authors: libin<2578858653@qq.com>
 *
 * Purpose : Spark操作的一些公共方法
 */
trait SparkJobBase {
	/**
	 * 得到app名称
	 */
	def appName: String = this.getClass.getSimpleName.stripSuffix("$")
	
	/**
	 * 对数据处理的一些常用分隔符,使用时需要被重写
	 */
	val separator: String = "\t"
	
	/**
	 * 对数据的填充值
	 */
	val fillValue: String = ""
	
	/**
	 * 对数据处理的分区数,使用时需要被重写
	 */
	val partitionNum: Int = 400
	
	/**
	 * 获取作业名字,使用时需要被重写
	 */
	def jobName: String = this.getClass.getSimpleName
	
	/**
	 * 日志对象
	 */
	val logger: Logger = LoggerFactory.getLogger(jobName)
	
	/**
	 * 获取一个SparkContext对象,依赖被重写的 @jobName 参数
	 */
	def getSparkContext: SparkContext = {
		val conf = new SparkConf().setAppName(jobName)
		SparkContext.getOrCreate(conf)
	}
	
	/**
	 * 获取一个SparkSession对象,依赖被重写的 @jobName 参数
	 */
	def getSparkSession: SparkSession = {
		SparkSession.builder().appName(jobName).getOrCreate()
	}
	
	/**
	 * 初始化
	 *
	 * @return
	 */
	def initContext: SparkContext = getSparkContext
	
	/**
	 * 停掉一个作业
	 *
	 */
	def destroyJob(): Unit = {
		getSparkContext.stop()
		logger.info(s"$jobName stopped at ${new DateTime()}")
	}
}
