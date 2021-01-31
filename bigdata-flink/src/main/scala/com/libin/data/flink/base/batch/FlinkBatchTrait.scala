package com.libin.data.flink.base.batch

import org.apache.flink.api.scala.ExecutionEnvironment
import org.slf4j.{Logger, LoggerFactory}

/**
  * Copyright (c) 2021/1/31. libin Inc. All Rights Reserved.
  * Authors: libin <libin>
  * <p>
  * Purpose : Flink批处理公共接口
  */
trait FlinkBatchTrait {
    /**
      * 获取执行环境入口
      */
    val env: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment
    /**
      * 对数据处理的一些常用分隔符,使用时需要被重写
      */
    val separator: String = ","
    /**
      * 对数据的填充值
      */
    val fillValue: String = ""
    /**
      * 对数据处理的分区数,使用时需要被重写
      */
    val partitionNum: Int = 400
    /**
      * 日志对象
      */
    val logger: Logger = LoggerFactory.getLogger(jobName)

    /**
      * 获取作业名字,使用时需要被重写
      */
    def jobName: String = this.getClass.getSimpleName

    /**
      * 得到app名称
      */
    def appName: String = this.getClass.getSimpleName.stripSuffix("$")
}
