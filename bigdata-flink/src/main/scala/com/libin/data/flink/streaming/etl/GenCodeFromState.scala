package com.libin.data.flink.streaming.etl

import com.libin.data.flink.base.streaming.FlinkStreamingTrait
import com.libin.data.flink.streaming.loader.FlinkStreamingLoader
import org.apache.commons.lang3.StringUtils
import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.KeyedProcessFunction
import org.apache.flink.streaming.api.scala.{DataStream, OutputTag, createTypeInformation}
import org.apache.flink.util.Collector

/**
  * Copyright (c) 2020/9/3. libin Inc. All Rights Reserved.
  * Authors: libin <libin>
  * <p>
  * Purpose : 统计实时流中某一单词出现的总个数（eg：比如一天某商品被点击的PV）
  *
  * before staring ：
  * nc
  * -l -p 9000
  */
object GenCodeFromState extends FlinkStreamingTrait[String] {
    implicit val inTypeInfo: TypeInformation[String] = createTypeInformation[String]

    def main(args: Array[String]): Unit = {
        val dataStream = FlinkStreamingLoader.getSourceDataBySocket(env)
        process(dataStream, OutputTag("GenCodeFromState"))
        env.execute("GenCodeFromState")
    }

    /**
      * 数据处理方法
      */
    override def process(dataStream: DataStream[String],
                         outputTag: OutputTag[String]): Unit = {
        val out = dataStream.filter(x => x.contains("a"))
                .keyBy(x => x.head.toString)
                .process(new mapStateFunction(outputTag))
        out.print()
    }

}

/**
  * map处理函数逻辑类
  *
  * @param outputTag 分流标识
  */
class mapStateFunction(outputTag: OutputTag[String]) extends StateKeyedProcessFunction[String, String, Long] {
    override def processElement(value: String,
                                context: KeyedProcessFunction[String, String, Long]#Context,
                                collector: Collector[Long]): Unit = {
        if (StringUtils.isNoneBlank(value)) {
            val counts = accumulatorOperator()
            collector.collect(counts)
        }
    }
}

/**
  * 按分区状态state
  *
  * @tparam K 分区Key
  * @tparam I 输入类型
  * @tparam O 输出类型
  */
abstract class StateKeyedProcessFunction[K, I, O] extends KeyedProcessFunction[K, I, O] {
    var counts = 0L

    /**
      * 调用前初始化执行一次
      */
    override def open(parameters: Configuration): Unit = {
        println(s"init counts is ${counts}")
    }

    /**
      * 每次调用执行一次
      */
    def accumulatorOperator(): Long = {
        counts = counts + 1L
        println(s"current count is ${counts}")
        counts
    }
}
