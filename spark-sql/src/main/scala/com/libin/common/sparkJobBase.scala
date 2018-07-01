package com.libin.common

import org.apache.spark.sql.SparkSession

/**
  * Copyright (c) 2018/7/1. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  *
  * Purpose :
  */

trait sparkJobBase {

  val appName = this.getClass.getSimpleName

  def createSparkSession(): SparkSession = {
    SparkSession.builder().appName(appName).getOrCreate()
  }

  def createSparkSessionLocal(): SparkSession = {
    SparkSession.builder().appName(appName).master("local[2]").getOrCreate()
  }

}
