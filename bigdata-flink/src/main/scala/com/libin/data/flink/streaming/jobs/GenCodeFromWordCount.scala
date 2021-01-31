package com.libin.data.flink.streaming.jobs

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.windowing.time.Time

/**
  * Copyright (c) 2019/05/18. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  * <p>
  * Purpose :  服务器上执行 nc -l 9000  , 运行代码
  */
object GenCodeFromWordCount {

    /**
      * 经典的WordCount代码
      */
    def main(args: Array[String]): Unit = {
        val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
        val hostname = "localhost"
        val port = 9000
        val stream = env.socketTextStream(hostname, port, '\n')

        import org.apache.flink.api.scala._
        val wcStream: DataStream[wc] =
            stream
                    .flatMap(x => x.split("\t"))
                    .map(w => wc(w, 1))
                    .keyBy("word")
                    .timeWindow(Time.seconds(2), Time.seconds(1))
                    //.sum("count")
                    .reduce((a, b) => wc(a.word, a.count + b.count))

        wcStream.print().setParallelism(1)
        env.execute("socket wc")
    }

    case class wc(word: String, count: Long)

}
