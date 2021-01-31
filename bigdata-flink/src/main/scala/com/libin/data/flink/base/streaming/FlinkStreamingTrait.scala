package com.libin.data.flink.base.streaming

import org.apache.flink.streaming.api.scala.{DataStream, OutputTag, StreamExecutionEnvironment}

/**
  * Copyright (c) 2020/4/3 libin Inc. All Rights Reserved.
  * Authors: libin<2578858653@qq.com>
  *
  * Purpose :
  */
trait FlinkStreamingTrait[T] {

    /**
      * 获取执行环境入口
      */
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    /**
      * Application Name
      */
    def appName: String = this.getClass.getSimpleName

    /**
      * 数据处理方法
      */
    def process(dataStream: DataStream[T], outputTag: OutputTag[String])

}
