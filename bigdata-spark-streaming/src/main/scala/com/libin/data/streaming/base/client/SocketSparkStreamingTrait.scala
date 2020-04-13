package com.libin.data.streaming.base.client

import com.libin.data.streaming.base.SparkStreamingTrait
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * Copyright (c) 2020/4/11. libin Inc. All Rights Reserved.
 * Authors: libin <2578858653@qq.com>
 * <p>
 * Purpose :  SparkStreaming + Socket
 */
trait SocketSparkStreamingTrait extends SparkStreamingTrait {

    /**
     * 根据指定的batch间隔时间，生成StreamingContext对象
     *
     * @param interval batch间隔时间
     * @return StreamingContext对象
     */
    def createStreamContext(interval: Int): StreamingContext = {
        val conf = new SparkConf().setAppName(appName).setIfMissing("spark.master", "local")
        new StreamingContext(conf, Seconds(interval))
    }
}
