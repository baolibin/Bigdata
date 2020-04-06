package com.libin.data.streaming.jobs

import com.libin.data.streaming.utils.StreamingExamples
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Copyright (c) 2020/04/04. libin Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  * <p>
  * Purpose : checkpoint
  * Linux：		nc -lk 9999
  * windows：  nc -l -p 9999
  */
object GenCodeFromCheckpoint {
    def main(args: Array[String]): Unit = {
        val conf = new SparkConf().setMaster("local[2]").setAppName("GenCodeFromCheckpoint")
        val ssc = new StreamingContext(conf, Seconds(5))

        StreamingExamples.setStreamingLogLevels()
        val lines = ssc.socketTextStream("localhost", 9999)
        ssc.checkpoint("E:\\2020_github\\checkout\\GenCodeFromCheckpoint")

        lines.print()
        ssc.start() // Start the computation
        ssc.awaitTermination() // Wait for the computation to terminate
    }
}
