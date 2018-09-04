package com.libin.api.mapreduce;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * Copyright (c) 2015/10/19. xixi Inc. All Rights Reserved.
 * Authors: libin <2578858653@qq.com>
 * <p>
 * Purpose : 分布式缓存
 */

@SuppressWarnings("deprecation")
public class DistributedDemo {
    public static void main(String[] arge) throws IOException, ClassNotFoundException, InterruptedException {

        Configuration conf = new Configuration();
        conf.set("fs.default.name", "hdfs://192.168.1.100:9000");
        FileSystem fs = FileSystem.get(conf);
        fs.delete(new Path("libin/output/distributedout.txt"));

        conf.set("mapred.job.tracker", "192.168.1.100:9001");
        conf.set("mapred.jar", "/home/baozi/blb/distributed.jar");
        Job job = Job.getInstance(conf, DistributedDemo.class.getSimpleName());
        DistributedCache.createSymlink(job.getConfiguration());
        try {
            // HDFS中的libin/input/distributedDemo.txt为分布式缓存
            DistributedCache.addCacheFile(new URI("libin/input/distributedDemo.txt"), job.getConfiguration());
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
        job.setMapperClass(DistributedMaper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job, new Path("libin/input/distributedinput.txt"));
        FileOutputFormat.setOutputPath(job, new Path("libin/output/distributedout.txt"));
        job.waitForCompletion(true);
    }

    public static class DistributedMaper extends Mapper<LongWritable, Text, Text, Text> {
        String[] splitedValue;
        String info;
        private List<String> DistributediList = new ArrayList<String>();

        protected void setup(Context context) throws IOException, InterruptedException {
            try {
                Path[] cacheFiles = DistributedCache.getLocalCacheFiles(context.getConfiguration());
                if (cacheFiles != null && cacheFiles.length > 0) {
                    String line;
                    BufferedReader br = new BufferedReader(new FileReader(cacheFiles[0].toString()));
                    try {
                        line = br.readLine();
                        while ((line = br.readLine()) != null) {
                            DistributediList.add(line);
                        }
                    } finally {
                        br.close();
                    }
                }
            } catch (IOException e) {
                System.err.println("Exception reading DistributedCache: " + e);
            }
        }

        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            try {
                splitedValue = value.toString().split("\\|");
                info = splitedValue[0];
                if (DistributediList.contains(info)) {
                    context.write(new Text(splitedValue[0]), value);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
