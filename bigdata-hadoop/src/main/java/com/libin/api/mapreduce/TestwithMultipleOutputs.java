package com.libin.api.mapreduce;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

/**
 * Copyright (c) 2015/10/08. xixi Inc. All Rights Reserved.
 * Authors: libin <2578858653@qq.com>
 * <p>
 * Purpose :
 */
public class TestwithMultipleOutputs extends Configured {
    public static class MapClass extends Mapper<LongWritable, Text, Text, IntWritable> {

        private MultipleOutputs<Text, Text> mos;

        @Override
        protected void setup(Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
            mos = new MultipleOutputs(context);
        }

        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] tokens = line.split("\t");
            if (tokens[0].equals("hadoop")) {
                mos.write("hadoop", new Text(tokens[0]), new Text(tokens[1]));
            } else if (tokens[0].equals("hive")) {
                mos.write("hive", new Text(tokens[0]), new Text(tokens[1]));
            } else if (tokens[0].equals("hbase")) {
                mos.write("hbase", new Text(tokens[0]), new Text(tokens[1]));
            } else if (tokens[0].equals("spark")) {
                mos.write("spark", new Text(tokens[0]), new Text(tokens[1]));
            }

        }

        protected void cleanup(Context context) throws IOException, InterruptedException {
            mos.close();
        }

    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "MultipleOutput");
        job.setJarByClass(TestwithMultipleOutputs.class);
        Path in = new Path(args[0]);
        Path out = new Path(args[1]);
        FileInputFormat.setInputPaths(job, in);
        FileOutputFormat.setOutputPath(job, out);
        job.setMapperClass(MapClass.class);
        job.setNumReduceTasks(0);
        MultipleOutputs.addNamedOutput(job, "hadoop", TextOutputFormat.class, Text.class, Text.class);
        MultipleOutputs.addNamedOutput(job, "hive", TextOutputFormat.class, Text.class, Text.class);
        MultipleOutputs.addNamedOutput(job, "hbase", TextOutputFormat.class, Text.class, Text.class);
        MultipleOutputs.addNamedOutput(job, "spark", TextOutputFormat.class, Text.class, Text.class);
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
