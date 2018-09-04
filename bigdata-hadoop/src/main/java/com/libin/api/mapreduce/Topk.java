package com.libin.api.mapreduce;

import java.io.IOException;
import java.util.TreeMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

/**
 * Copyright (c) 2015/08/30. xixi Inc. All Rights Reserved.
 * Authors: libin <2578858653@qq.com>
 * <p>
 * Purpose :
 */
public class Topk {
    /**
     * map方法
     */
    private static final int k = 10;

    public static class MyMapper extends Mapper<LongWritable, Text, LongWritable, Text> {
        LongWritable lw = new LongWritable();
        Text text = new Text();
        TreeMap<Long, String> treemap_mapper = new TreeMap<Long, String>();

        @Override
        protected void map(LongWritable key, Text value, Context context)
                throws IOException, InterruptedException {
            String line = value.toString();
            String[] splited = line.split("\t");

            treemap_mapper.put(Long.parseLong(splited[0]), line);

            if (treemap_mapper.size() > k) {
                treemap_mapper.remove(treemap_mapper.firstKey());
            }
        }

        @Override
        protected void cleanup(Context context)
                throws IOException, InterruptedException {

            for (Long numLong : treemap_mapper.keySet()) {
                context.write(new LongWritable(numLong), new Text(treemap_mapper.get(numLong)));
            }
        }
    }

    /**
     * reducer方法
     *
     * @author Administrator
     */
    public static class MyReducer extends Reducer<LongWritable, Text, LongWritable, NullWritable> {
        TreeMap<Long, String> treemap_reducer = new TreeMap<Long, String>();

        @Override
        protected void reduce(LongWritable k2, Iterable<Text> v2s, Context context)
                throws IOException, InterruptedException {
            treemap_reducer.put(k2.get(), v2s.iterator().next().toString());

            if (treemap_reducer.size() > k) {
                treemap_reducer.remove(treemap_reducer.firstKey());
            }
        }

        @Override
        protected void cleanup(Context context)
                throws IOException, InterruptedException {
            Long[] outLong = new Long[10];
            int flag = 0;
            for (Long numLong : treemap_reducer.keySet()) {
                outLong[flag] = numLong;
                flag++;
            }

            for (int i = k - 1; i >= 0; i--) {
                context.write(new LongWritable(outLong[i]), NullWritable.get());
            }
        }
    }

    /**
     * 主方法
     */
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, Topk.class.getSimpleName());
        job.setJarByClass(Topk.class);

        job.setNumReduceTasks(1);

        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReducer.class);

        job.setMapOutputKeyClass(LongWritable.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputKeyClass(LongWritable.class);
        job.setOutputValueClass(NullWritable.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.setInputPaths(job, args[0]);
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.waitForCompletion(true);
    }
}
