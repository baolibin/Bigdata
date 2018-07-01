package com.libin.common

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession
import org.slf4j.{LoggerFactory, Logger}

/**
  * Copyright (c) 2018/7/1. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  *
  * Purpose :
  */

trait SparkJobBase {

  val logger: Logger = LoggerFactory.getLogger(appName)

  def appName: String = this.getClass.getSimpleName

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

}
