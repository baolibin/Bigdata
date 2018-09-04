package com.libin.api.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.chain.ChainMapper;
import org.apache.hadoop.mapreduce.lib.chain.ChainReducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;

/**
 * Copyright (c) 2016/03/17. xixi Inc. All Rights Reserved.
 * Authors: libin <2578858653@qq.com>
 * <p>
 * Purpose : Hadoop的ChainMapper/ChainReducer
 */
public class ChainMapperChainReducer {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length != 2) {
            System.err.println("Usage <Input> <Output>");
        }

        Job job = Job.getInstance(conf, ChainMapperChainReducer.class.getSimpleName());
        job.setJarByClass(ChainMapperChainReducer.class);

        ChainMapper.addMapper(job, MyMapper1.class, LongWritable.class, Text.class, Text.class, IntWritable.class, new Configuration(false));
        ChainReducer.setReducer(job, MyReducer1.class, Text.class, IntWritable.class, Text.class, IntWritable.class, new Configuration(false));
        ChainMapper.addMapper(job, MyMapper2.class, Text.class, IntWritable.class, Text.class, IntWritable.class, new Configuration(false));

        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
        job.waitForCompletion(true);
    }

    public static class MyMapper1 extends Mapper<LongWritable, Text, Text, IntWritable> {
        IntWritable in = new IntWritable();

        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
                throws IOException, InterruptedException {
            String[] spl = value.toString().split("\\|");
            if (spl.length == 2) {
                in.set(Integer.parseInt(spl[1].trim()));
                context.write(new Text(spl[0].trim()), in);
            }
        }
    }

    public static class MyReducer1 extends Reducer<Text, IntWritable, Text, IntWritable> {
        IntWritable in = new IntWritable();

        @Override
        protected void reduce(Text k2, Iterable<IntWritable> v2s, Reducer<Text, IntWritable, Text, IntWritable>.Context context)
                throws IOException, InterruptedException {
            Integer uv = 0;
            for (IntWritable v2 : v2s) {
                uv += Integer.parseInt(v2.toString().trim());
            }
            in.set(uv);
            context.write(k2, in);
        }
    }

    public static class MyMapper2 extends Mapper<Text, IntWritable, Text, IntWritable> {
        @Override
        protected void map(Text key, IntWritable value, Mapper<Text, IntWritable, Text, IntWritable>.Context context)
                throws IOException, InterruptedException {
            if (Long.parseLong(value.toString().trim()) >= 5) {
                context.write(new Text(key.toString().trim()), value);
            }
        }
    }
}
