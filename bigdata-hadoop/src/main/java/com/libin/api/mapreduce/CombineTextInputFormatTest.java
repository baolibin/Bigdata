package com.libin.api.mapreduce;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;

/**
 * Copyright (c) 2015/04/16. xixi Inc. All Rights Reserved.
 * Authors: libin <2578858653@qq.com>
 * <p>
 * Purpose :
 * 处理的数据源是多个小文件
 * 会把多个小文件合并处理，合并的大小如果小于128M，就当成一个InputSplit处理。
 * 与SequenceFileInputFormat不同的是，SequenceFileInputFormat处理的数据源是合并好的SequencceFile类型的数据。
 */
public class CombineTextInputFormatTest {
    public static class MyMapper extends
            Mapper<LongWritable, Text, Text, LongWritable> {
        final Text k2 = new Text();
        final LongWritable v2 = new LongWritable();

        protected void map(LongWritable key, Text value,
                           Mapper<LongWritable, Text, Text, LongWritable>.Context context)
                throws InterruptedException, IOException {
            final String line = value.toString();
            final String[] splited = line.split("\\s");
            for (String word : splited) {
                k2.set(word);
                v2.set(1);
                context.write(k2, v2);
            }
        }
    }

    public static class MyReducer extends
            Reducer<Text, LongWritable, Text, LongWritable> {
        LongWritable v3 = new LongWritable();

        protected void reduce(Text k2, Iterable<LongWritable> v2s,
                              Reducer<Text, LongWritable, Text, LongWritable>.Context context)
                throws IOException, InterruptedException {
            long count = 0L;
            for (LongWritable v2 : v2s) {
                count += v2.get();
            }
            v3.set(count);
            context.write(k2, v3);
        }
    }

    public static void main(String[] args) throws Exception {
        final Configuration conf = new Configuration();
        final Job job = Job.getInstance(conf, CombineTextInputFormatTest.class.getSimpleName());
        // 1.1
        FileInputFormat.setInputPaths(job,
                "hdfs://192.168.1.10:9000/input");

        //这里改了一下
        job.setInputFormatClass(CombineTextInputFormat.class);


        // 1.2
        job.setMapperClass(MyMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);
        // 1.3 默认只有一个分区
        job.setPartitionerClass(HashPartitioner.class);
        job.setNumReduceTasks(1);
        // 1.4省略不写
        // 1.5省略不写

        // 2.2
        job.setReducerClass(MyReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);
        // 2.3
        FileOutputFormat.setOutputPath(job, new Path(
                "hdfs://192.168.1.10:9000/out2"));
        job.setOutputFormatClass(TextOutputFormat.class);
        // 执行打成jar包的程序时，必须调用下面的方法
        job.setJarByClass(CombineTextInputFormatTest.class);
        job.waitForCompletion(true);
    }

}
