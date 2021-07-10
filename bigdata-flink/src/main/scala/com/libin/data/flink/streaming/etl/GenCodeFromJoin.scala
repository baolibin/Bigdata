package com.libin.data.flink.streaming.etl

import com.libin.data.flink.streaming.etl.GenCodeFromState.env
import com.libin.data.flink.streaming.loader.FlinkStreamingLoader
import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.streaming.api.scala.{DataStream, OutputTag, createTypeInformation}
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.util.Collector

/**
  * Copyright (c) 2021/7/10. libin Inc. All Rights Reserved.
  * Authors: libin <libin>
  * <p>
  * Purpose : 多流join
  */
object GenCodeFromJoin extends {

    implicit val inTypeInfo: TypeInformation[String] = createTypeInformation[String]

    def main(args: Array[String]): Unit = {
        val dataStream1 = FlinkStreamingLoader.getSourceDataBySocketFromPort(env, "localhost", 9001)
                .map(x => (x.substring(0, 1), x))
        val dataStream2 = FlinkStreamingLoader.getSourceDataBySocketFromPort(env, "localhost", 9002)
                .map(x => (x.substring(0, 1), x))

        process(dataStream1, dataStream2, OutputTag("join"))

        env.execute("GenCodeFromJoin")
    }

    /**
      * 多流join
      *
      * @param dataStream1 第一条流
      * @param dataStream2 第二条流
      * @param outputTag   OutputTag
      */
    def process(dataStream1: DataStream[(String, String)],
                dataStream2: DataStream[(String, String)],
                outputTag: OutputTag[String]): DataStream[(String, String)] = {
        val joinStream: DataStream[(String, String)] = dataStream1.join(dataStream2)
                .where(_._1)
                .equalTo(_._1)
                .window(TumblingEventTimeWindows.of(Time.milliseconds(10)))
                .apply {
                    (d1, d2, out: Collector[(String, String)]) =>
                        out.collect((d1._2, d2._2))
                }.name("res")

        joinStream.print()
        joinStream
    }

}
