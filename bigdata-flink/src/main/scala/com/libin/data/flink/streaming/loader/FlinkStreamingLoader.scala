package com.libin.data.flink.streaming.loader

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}

/**
  * Copyright (c) 2021/1/31. libin Inc. All Rights Reserved.
  * Authors: libin <libin>
  * <p>
  * Purpose : 一些source输入源
  */
object FlinkStreamingLoader {

    /**
      * 使用Socket获取输入源数据
      *
      * @param env StreamExecutionEnvironment
      * @return Socket数据源
      */
    def getSourceDataBySocket(env: StreamExecutionEnvironment): DataStream[String] = {
        val hostname = "localhost"
        val port = 9000
        env.socketTextStream(hostname, port, '\n')
    }

    def getSourceDataBySocketFromPort(env: StreamExecutionEnvironment, hostname: String, port: Int): DataStream[String] = {
        env.socketTextStream(hostname, port, '\n')
    }

}
