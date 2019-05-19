package com.libin.data.flink.streaming;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

/**
 * Copyright (c) 2019/05/17. xixi Inc. All Rights Reserved.
 * Authors: libin <2578858653@qq.com>
 * <p>
 * Purpose : 服务器上执行 nc -l 9000  , 运行代码
 */
public class WordCount {
    public static void main(String[] args) throws Exception {
        // 获取flink运行的环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        String hostname = "39.98.57.193";
        int port = 9000;
        // 连接socket获取输入的数据
        DataStreamSource<String> stream = env.socketTextStream(hostname, port, "\n");

        SingleOutputStreamOperator<WordCountCount> word = stream.flatMap(new FlatMapFunction<String, WordCountCount>() {
            public void flatMap(String s, Collector<WordCountCount> collector) throws Exception {
                String[] sp = s.split("\t");
                for (String word : sp) {
                    collector.collect(new WordCountCount(word, 1L));
                }
            }
        }).keyBy("word")
                //.timeWindow(Time.seconds(2), Time.seconds(1)) //指定时间窗口大小为2秒，指定时间间隔为1秒
                .timeWindow(Time.seconds(2))
                //.sum("count");
                .reduce(new ReduceFunction<WordCountCount>() {
                    public WordCountCount reduce(WordCountCount t1, WordCountCount t2) throws Exception {
                        return new WordCountCount(t1.word, t1.count + t2.count);
                    }
                });
        // 将数据打印到控制台，并且设置并行度
        word.print().setParallelism(1);

        // 执行程序
        env.execute("socket wc");
    }

    public static class WordCountCount {
        public String word;
        public long count;

        public WordCountCount() {
        }

        public WordCountCount(String word, long count) {
            this.word = word;
            this.count = count;
        }

        @Override
        public String toString() {
            return word + "\t" + count;
        }
    }
}
