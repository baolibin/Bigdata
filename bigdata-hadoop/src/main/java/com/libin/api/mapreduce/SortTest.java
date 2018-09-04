package com.libin.api.mapreduce;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

/**
 * Copyright (c) 2015/04/14. xixi Inc. All Rights Reserved.
 * Authors: libin <2578858653@qq.com>
 * <p>
 * Purpose : mapreduce编程自定义排序
 */
public class SortTest {
    public static class NewkWritable implements WritableComparable<NewkWritable>{
        long first;
        long second;

        public NewkWritable(){

        }

        public NewkWritable(long first,long second){
            this.set(first, second);
        }

        public void set(long first,long second){
            this.first=first;
            this.second=second;
        }

        @Override
        public void readFields(DataInput in) throws IOException {
            this.first=in.readLong();
            this.second=in.readLong();
        }

        @Override
        public void write(DataOutput out) throws IOException {
            out.writeLong(first);
            out.writeLong(second);
        }

        @Override
        public int compareTo(NewkWritable o) {
            //return (int) ((this.first+this.second)-(o.first+o.second));
            //按照降序和排序
            return (int) ((o.first+o.second)-(this.first+this.second));
        }

        @Override
        public String toString() {
            return first+"";
        }

    }

    public static class MyMapper extends Mapper<LongWritable, Text, NewkWritable, LongWritable>{
        NewkWritable k2=new NewkWritable();
        LongWritable V2=new LongWritable();
        @Override
        protected void map(LongWritable key,Text value,Mapper<LongWritable, Text, NewkWritable, LongWritable>.Context context)throws IOException, InterruptedException {
            String line=value.toString();
            String[] splited=line.split("\t");
            k2.set(Long.parseLong(splited[0]),Long.parseLong(splited[1]));
            V2.set(Long.parseLong(splited[1]));
            //k2的toString方法，只输出了first，输出的结果正常。
            context.write(k2, V2);
        }
    }


    public static class MyReducer extends Reducer<NewkWritable, LongWritable, NewkWritable, LongWritable>{
        @Override
        protected void reduce(NewkWritable k2,Iterable<LongWritable> v2s,Reducer<NewkWritable, LongWritable, NewkWritable, LongWritable>.Context context)throws IOException, InterruptedException {
            Iterator<LongWritable> iterator=v2s.iterator();
            iterator.hasNext();
            LongWritable v2=iterator.next();
            context.write(k2, v2);
        }
    }

    public static void main(String[] args) throws Exception {
        //String INPUT_PATH=args[0];
        //String OUTPUT_PATH=args[1];

        String INPUT_PATH="/input/haha";
        String OUTPUT_PATH="/sort_out2";

        Configuration conf=new Configuration();
        Job job=Job.getInstance(conf, SortTest.class.getSimpleName());

        job.setJarByClass(SortTest.class);
        //1.1
        FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.1.100:9000"+INPUT_PATH));
        job.setInputFormatClass(TextInputFormat.class);
        //1.2
        job.setMapperClass(MyMapper.class);
        job.setMapOutputKeyClass(NewkWritable.class);
        job.setMapOutputValueClass(LongWritable.class);

        job.setReducerClass(MyReducer.class);
        job.setOutputKeyClass(NewkWritable.class);
        job.setOutputValueClass(LongWritable.class);

        FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.1.100:9000"+OUTPUT_PATH));
        job.setOutputFormatClass(TextOutputFormat.class);

        job.waitForCompletion(true);
    }
}
