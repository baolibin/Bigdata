package com.libin.data.flink.batch

import org.apache.flink.api.scala.{ExecutionEnvironment, _}

/**
  * Copyright (c) 2020/4/3 libin Inc. All Rights Reserved.
  * Authors: libin<2578858653@qq.com>
  *
  * Purpose : Flink的word count代码
  */
object WordCount {
    def main(args: Array[String]): Unit = {
        val env = ExecutionEnvironment.getExecutionEnvironment
        val text = env.fromElements("Who's there?", "I think I hear them. Stand, ho! Who's there?")

        val counts = text.flatMap {
            _.toUpperCase.split("\\s+").filter(_.nonEmpty)
        }.map {
            (_, 1)
        }.groupBy(0).sum(1)

        /**
          * (STAND,,1)
          * (THINK,1)
          * (HEAR,1)
          * (I,2)
          * (THEM.,1)
          * (WHO'S,2)
          * (HO!,1)
          * (THERE?,2)
          **/
        counts.print()
    }
}
