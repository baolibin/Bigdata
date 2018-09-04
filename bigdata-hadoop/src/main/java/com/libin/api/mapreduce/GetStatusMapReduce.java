package com.libin.api.mapreduce;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

/**
 * Copyright (c) 2016/04/01. xixi Inc. All Rights Reserved.
 * Authors: libin <2578858653@qq.com>
 * <p>
 * Purpose : Hadoop的FileStatus简单使用
 */
public class GetStatusMapReduce {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length != 2) {
            System.err.println("Usage databaseV1 <inputpath> <outputpath>");
        }
        conf.set("path", otherArgs[0]);

        Job job = Job.getInstance(conf, GetStatusMapReduce.class.getSimpleName() + "1");
        job.setJarByClass(GetStatusMapReduce.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        job.setMapperClass(MyMapper1.class);
        job.setNumReduceTasks(0);
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
        job.waitForCompletion(true);
    }

    public static class MyMapper1 extends Mapper<LongWritable, Text, Text, NullWritable> {
        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, NullWritable>.Context context)
                throws IOException, InterruptedException {
            FileSystem fileSystem = FileSystem.get(context.getConfiguration());
            FileStatus[] fileStatus = fileSystem.listStatus(new Path(context.getConfiguration().get("path")));
            int length = fileStatus.length;
            context.write(new Text("==================== 一次map函数 =============================="), NullWritable.get());
            context.write(new Text("length--" + length), NullWritable.get());
            for (FileStatus fs : fileStatus) {
                long accessTime = fs.getAccessTime();
                long blockSize = fs.getBlockSize();
                Class<? extends FileStatus> class1 = fs.getClass();
                String group = fs.getGroup();
                long len = fs.getLen();
                long modificationTime = fs.getModificationTime();
                String owner = fs.getOwner();
                Path path = fs.getPath();
                FsPermission permission = fs.getPermission();
                short replication = fs.getReplication();
                //Path symlink = fs.getSymlink();
                String string = fs.toString();
                boolean directory = fs.isDirectory();
                boolean encrypted = fs.isEncrypted();
                boolean file = fs.isFile();

                context.write(new Text("===================================================================================="), NullWritable.get());
                context.write(new Text("accessTime--" + accessTime), NullWritable.get());
                context.write(new Text("blockSize--" + blockSize), NullWritable.get());
                context.write(new Text("class1--" + class1), NullWritable.get());
                context.write(new Text("group--" + group), NullWritable.get());
                context.write(new Text("len--" + len), NullWritable.get());
                context.write(new Text("modificationTime--" + modificationTime), NullWritable.get());
                context.write(new Text("owner--" + owner), NullWritable.get());
                context.write(new Text("path--" + path), NullWritable.get());
                context.write(new Text("permission--" + permission), NullWritable.get());
                context.write(new Text("replication--" + replication), NullWritable.get());
                //context.write(new Text("symlink--"+symlink), NullWritable.get());
                context.write(new Text("string--" + string), NullWritable.get());
                context.write(new Text("directory--" + directory), NullWritable.get());
                context.write(new Text("encrypted--" + encrypted), NullWritable.get());
                context.write(new Text("file--" + file), NullWritable.get());
            }
        }
    }
}
