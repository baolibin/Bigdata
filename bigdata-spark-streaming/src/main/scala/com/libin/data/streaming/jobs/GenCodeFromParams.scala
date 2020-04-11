package com.libin.data.streaming.jobs

import com.libin.data.streaming.base.client.SocketSparkStreamingTrait
import org.apache.spark.SparkConf

/**
 * Copyright (c) 2020/4/11. libin Inc. All Rights Reserved.
 * Authors: libin <2578858653@qq.com>
 * <p>
 * Purpose : Spark Streaming一些配置参数
 */
object GenCodeFromParams extends SocketSparkStreamingTrait {
    def main(args: Array[String]): Unit = {
        // val ssc = createStreamContext(5)
        val conf = new SparkConf().setMaster("local[2]").setAppName(appName)

        /**
         * 16个分区，5分钟一个batch，则一个batch消费处理的数据量是 5000 * 16 * 5 * 60 = 24000000
         */
        // 启用反压机制,开启后spark自动根据系统负载选择最优消费速率
        conf.set("spark.streaming.backpressure.enabled", "true")
        // 限制第一次批处理应该消费的数据，因为程序冷启动队列里面有大量积压，防止第一次全部读取，造成系统阻塞
        conf.set("spark.streaming.backpressure.initialRate", "24000000")
        // 限制每秒每个消费线程读取每个kafka分区最大的数据量
        conf.set("spark.streaming.kafka.maxRatePerPartition", "5000") // 一般用在反压，限流上

        // 确保在kill任务时，能够处理完最后一批数据，再关闭程序，不会发生强制kill导致数据处理中断，没处理完的数据丢失
        conf.set("spark.streaming.stopGracefullyOnShutdown", "true")


    }
}
