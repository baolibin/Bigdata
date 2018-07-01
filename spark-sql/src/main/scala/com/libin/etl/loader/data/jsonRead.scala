package com.libin.etl.loader.data

import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * Copyright (c) 2018/7/1. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  *
  * Purpose :
  */

object jsonRead {

  /**
    * 读取配置文件中的数据
    * @param ss  SparkSession
    * @param fileName  文件名字
    * @return  返回df数据格式
    */
  def readJsonToDf(ss: SparkSession, fileName: String): DataFrame = {
    val url: String = this.getClass.getClassLoader.getResource(fileName).toString
    ss.read.json(url)
  }
}
