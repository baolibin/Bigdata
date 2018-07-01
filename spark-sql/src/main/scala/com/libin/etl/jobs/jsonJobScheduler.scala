package com.libin.etl.jobs

import com.libin.common.sparkJobBase
import com.libin.etl.loader.data.jsonRead

/**
  * Copyright (c) 2018/7/1. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  *
  * Purpose :
  */

class jsonJobScheduler extends sparkJobBase {

  override val appName = "jsonJobScheduler"

  val ss = createSparkSessionLocal()
}


object jsonJobScheduler {

  def apply() = new jsonJobScheduler

  def main(args: Array[String]) {
    val jobScheduler: jsonJobScheduler = apply()
    // 测试读取json配置数据
    // loadUtils.readResourceFile("stu.json").foreach(println)

    // 读取df操作
    jsonRead.readJsonToDf(jobScheduler.ss, "stu.json").show()
  }
}
