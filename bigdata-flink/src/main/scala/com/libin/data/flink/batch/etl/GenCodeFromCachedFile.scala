package com.libin.data.flink.batch.etl

import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment}
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.scala.createTypeInformation

import scala.io.Source

/**
  * Copyright (c) 2021/4/15. libin Inc. All Rights Reserved.
  * Authors: libin <libin>
  * <p>
  * Purpose : 分布式缓存
  *
  * before staring ：
  * nc
  * -l -p 9000
  */
object GenCodeFromCachedFile {
    implicit val inTypeInfo: TypeInformation[String] = createTypeInformation[String]

    def main(args: Array[String]): Unit = {
        val env = ExecutionEnvironment.getExecutionEnvironment
        env.setParallelism(3)

        /**
          * 注册缓存的文件
          * cachePath:
          * register a file from HDFS
          * register a local executable file (script, executable, ...)
          */
        env.registerCachedFile("cachePath", "cacheName")
        val stream: DataSet[String] = env.fromElements("this", "is", "flink", "distribute", "cache")

        stream
            .flatMap(_.split(","))
            .map(new RichMapFunction[String, String] {
                var list: List[(String)] = _

                override def open(parameters: Configuration): Unit = {
                    super.open(parameters)
                    val file = getRuntimeContext.getDistributedCache.getFile("")
                    val iterator = Source.fromFile(file.getAbsoluteFile).getLines()
                    list = iterator.toList
                }

                override def map(value: String): String = {
                    var middle: String = ""
                    if (list.head.contains(value)) {
                        middle = value
                    }
                    middle
                }
            })
            .map((_, 1L))
            .filter(_._1.nonEmpty)
            .groupBy(0)
            .sum(1)
            .print()
    }

}
