package com.libin.data.streaming.jobs

import kafka.serializer.StringDecoder
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka.KafkaUtils

/**
 * Copyright (c) 2020/04/06. libin Inc. All Rights Reserved.
 * Authors: libin <2578858653@qq.com>
 * <p>
 * Purpose : kafka
 */
object GenCodeFromKafka {
    def main(args: Array[String]): Unit = {
        /**
         * 配置kafka参数
         */
        val kafkaParams = Map[String, Object](
            "bootstrap.servers" -> "localhost:port",
            "key.deserializer" -> classOf[StringDeserializer],
            "value.deserializer" -> classOf[StringDeserializer],
            "group.id" -> "group_id",
            "auto.offset.reset" -> "latest",
            "enable.auto.commit" -> (false)
        )

        /**
         * 上游kafka topic
         */
        val topics = Set("topic1", "topic2")

        val conf = new SparkConf().setMaster("local[2]").setAppName("GenCodeFromKafka")
        val ssc = new StreamingContext(conf, Seconds(5))

        /*val stream: InputDStream[(String, String)] = KafkaUtils
                .createDirectStream[String, String, StringDecoder, StringDecoder,(String, String)](
                    ssc, kafkaParams, topics
                )*/

    }
}
