package com.libin.api.mapreduce;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;

/**
 * Copyright (c) 2015/02/22. xixi Inc. All Rights Reserved.
 * Authors: libin <2578858653@qq.com>
 * <p>
 * Purpose : Hadoop自定义数据类型编程练习
 */
public class KpiApp {
    static final String INPUT_PATH = "hdfs://192.168.1.100:9000/input/HTTP_20130313143750.dat";
    static final String OUT_PATH = "hdfs://192.168.1.100:9000/output/out02";

    public static void main(String[] args) throws Exception {
        final Job job = new Job(new Configuration(), KpiApp.class.getSimpleName());
        //1.1指定输入文件路径
        FileInputFormat.setInputPaths(job, INPUT_PATH);
        //指定哪个类用来格式化输入文件
        job.setInputFormatClass(TextInputFormat.class);

        //1.2指定自定义的Mapper类
        job.setMapperClass(MyMapper.class);
        //指定输出<k2,v2>的类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(KpiWritable.class);

        //1.3指定分区类
        job.setPartitionerClass(HashPartitioner.class);
        job.setNumReduceTasks(1);

        //1.4TODO 排序、分区

        //1.5  TODO （可选）合并

        //2.2指定自定义的reduce类
        job.setReducerClass(MyReducer.class);
        //指定输出<k3,v3>的类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(KpiWritable.class);

        //2.3指定输出到哪里
        FileOutputFormat.setOutputPath(job, new Path(OUT_PATH));
        //设定输出文件的格式化类
        job.setOutputFormatClass(TextOutputFormat.class);

        //把代码提交给JobTracker执行
        job.waitForCompletion(true);
    }

    static class MyMapper extends Mapper<LongWritable, Text, Text, KpiWritable> {
        protected void map(LongWritable key, Text
                value, org.apache.hadoop.mapreduce.Mapper<LongWritable, Text, Text, KpiWritable>.Context context) throws
                IOException, InterruptedException {
            final String[] splited = value.toString().split("\t");
            final String msisdn = splited[1];
            final Text k2 = new Text(msisdn);
            final KpiWritable v2 = new KpiWritable(splited[6], splited[7], splited[8], splited[9]);
            context.write(k2, v2);
        }
    }

    static class MyReducer extends Reducer<Text, KpiWritable, Text, KpiWritable> {
        /**
         * @param k2  表示整个文件中不同的手机号码
         * @param v2s 表示该手机号在不同时段的流量的集合
         */
        protected void reduce(Text k2, java.lang.Iterable<KpiWritable> v2s, org.apache.hadoop.mapreduce.Reducer<Text, KpiWritable, Text, KpiWritable>.
                Context context) throws IOException, InterruptedException {
            long upPackNum = 0L;
            long downPackNum = 0L;
            long upPayLoad = 0L;
            long downPayLoad = 0L;

            for (KpiWritable kpiWritable : v2s) {
                upPackNum += kpiWritable.upPackNum;
                downPackNum += kpiWritable.downPackNum;
                upPayLoad += kpiWritable.upPayLoad;
                downPayLoad += kpiWritable.downPayLoad;
            }

            final KpiWritable v3 = new KpiWritable(upPackNum + "", downPackNum + "", upPayLoad + "", downPayLoad + "");
            context.write(k2, v3);
        }
    }
}

class KpiWritable implements Writable {
    long upPackNum;
    long downPackNum;
    long upPayLoad;
    long downPayLoad;

    public KpiWritable() {
    }

    public KpiWritable(String upPackNum, String downPackNum, String upPayLoad, String downPayLoad) {
        this.upPackNum = Long.parseLong(upPackNum);
        this.downPackNum = Long.parseLong(downPackNum);
        this.upPayLoad = Long.parseLong(upPayLoad);
        this.downPayLoad = Long.parseLong(downPayLoad);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.upPackNum = in.readLong();
        this.downPackNum = in.readLong();
        this.upPayLoad = in.readLong();
        this.downPayLoad = in.readLong();
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(upPackNum);
        out.writeLong(downPackNum);
        out.writeLong(upPayLoad);
        out.writeLong(downPayLoad);
    }

    @Override
    public String toString() {
        return upPackNum + "\t" + downPackNum + "\t" + upPayLoad + "\t" + downPayLoad;
    }
}
