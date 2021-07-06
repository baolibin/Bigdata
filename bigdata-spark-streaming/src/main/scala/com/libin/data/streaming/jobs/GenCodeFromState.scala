package com.libin.data.streaming.jobs

import com.libin.data.streaming.base.client.SocketSparkStreamingTrait
import com.libin.data.streaming.utils.StreamingExamples

/**
  * Copyright (c) 2019/02/16. libin Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  * <p>
  * Purpose :
  */
object GenCodeFromState extends SocketSparkStreamingTrait {
    def main(args: Array[String]): Unit = {
        val ssc = createStreamContext(5)
        StreamingExamples.setStreamingLogLevels()
        val lines = ssc.socketTextStream("localhost", 9999)

        ssc.checkpoint("~/2021_github/Bigdata/bigdata-spark-streaming/src/main/resources/updatestateByKey")

        lines.map((_, 1L))
            .reduceByKey(_ + _)
            .updateStateByKey(updateFunction)
    }

    def updateFunction(newData: Seq[Long], state: Option[Long]): Option[Long] = {
        val newState = state.getOrElse(0L) + newData.sum
        Some(newState)
    }

}
