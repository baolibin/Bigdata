package com.libin.common

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession
import org.joda.time.DateTime
import org.slf4j.{Logger, LoggerFactory}

/**
 * Copyright (c) 2018/7/1. xixi Inc. All Rights Reserved.
 * Authors: libin <2578858653@qq.com>
 *
 * Purpose :
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
     * 创建SparkSession对象
     */
    def createSparkSession(): SparkSession = {
        SparkSession.builder().appName(appName).getOrCreate()
    }

    /**
     * 创建本地的SparkSession对象
     */
    def createSparkSessionLocal(): SparkSession = {
        SparkSession.builder().appName(appName).master("local[2]").getOrCreate()
    }

    /**
     * 创建本地的SparkContext对象
     */
    def createSparkContextLocal(): SparkContext = {
        SparkContext.getOrCreate(new SparkConf().setAppName(appName).setMaster("local[2]"))
    }

    /**
     * 初始化
     *
     * @return
     */
    def initContext: SparkSession = createSparkSessionLocal()

    /**
     * 停掉一个作业
     *
     */
    def destroyJob(): Unit = {
        createSparkSessionLocal().stop()
        logger.info(s"$jobName stopped at ${new DateTime()}")
    }
}
