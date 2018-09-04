package com.libin.api.mapreduce;

import java.io.IOException;
import java.net.URI;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobID;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.TaskAttemptID;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

/**
 * Copyright (c) 2016/03/29. xixi Inc. All Rights Reserved.
 * Authors: libin <2578858653@qq.com>
 * <p>
 * Purpose : hadoop的Context简单使用
 */
public class GetIDMapReduce {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length != 2) {
            System.err.println("Usage databaseV1 <inputpath> <outputpath>");
        }
        Job job = Job.getInstance(conf, GetIDMapReduce.class.getSimpleName() + "1");
        job.setJarByClass(GetIDMapReduce.class);
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

            FileSplit fileSplit = (FileSplit) context.getInputSplit();
            String pathname = fileSplit.getPath().getName();
            JobID jobid = context.getJobID();    //获取jobid
            LongWritable keyout = context.getCurrentKey(); //获取key偏移量
            Text valueout = context.getCurrentValue();  //获取一行的值
            String jobname = context.getJobName();    //获得job名字
            TaskAttemptID taskid = context.getTaskAttemptID();    //获得taskid
            float progress = context.getProgress();    //获取任务执行进度
            String jar = context.getJar();    //作业运行之前，往集群拷贝的作业资源jar
            //String status = context.getStatus();
            String user = context.getUser(); //获取当前用户
            //String[] fileTimestamps = context.getFileTimestamps();
            int numReduceTasks = context.getNumReduceTasks(); //获得reduce的数量
            //Path[] fileClassPaths = context.getFileClassPaths();
            Configuration configuration = context.getConfiguration();    //获得作业配置文件
            //RawComparator<?> groupingComparator = context.getGroupingComparator();
            boolean jobSetupCleanupNeeded = context.getJobSetupCleanupNeeded(); //Get whether job-setup and job-cleanup is needed for the job
            int maxMapAttempts = context.getMaxMapAttempts(); //the max number of attempts per map task
            int maxReduceAttempts = context.getMaxReduceAttempts(); //he max number of attempts per reduce task.
            //@SuppressWarnings("deprecation")
            //Path[] localCacheFiles = context.getLocalCacheFiles();
            //OutputCommitter outputCommitter = context.getOutputCommitter();
            Path workingDirectory = context.getWorkingDirectory(); //工作目录
            boolean nextKeyValue = context.nextKeyValue();    //下一个键值对
            //URI[] cacheFiles = context.getCacheFiles();
            URI[] cacheArchives = context.getCacheArchives(); //Get cache archives set in the Configuration
            Path[] archiveClassPaths = context.getArchiveClassPaths();//Get the archive entries in classpath as an array of Path
            boolean profileEnabled = context.getProfileEnabled();//Get whether the task profiling is enabled.
            //String profileParams = context.getProfileParams();
            @SuppressWarnings("deprecation")
            boolean symlink = context.getSymlink();// Originally intended to check if symlinks should be used, but currently symlinks cannot be disabled
            //RawComparator<?> sortComparator = context.getSortComparator();
            //int hashCode = context.hashCode();
            context.write(new Text("===================================================================================="), NullWritable.get());
            context.write(new Text("pathname--" + pathname), NullWritable.get());
            context.write(new Text("jobid--" + jobid.toString()), NullWritable.get());
            context.write(new Text("keyout--" + keyout.toString()), NullWritable.get());
            context.write(new Text("keyout--" + valueout), NullWritable.get());
            context.write(new Text("jobname--" + jobname), NullWritable.get());
            context.write(new Text("taskid--" + taskid.toString()), NullWritable.get());
            context.write(new Text("progress--" + progress), NullWritable.get());
            context.write(new Text("jar--" + jar.toString()), NullWritable.get());
            //context.write(new Text("status--"+status), NullWritable.get());
            context.write(new Text("user--" + user), NullWritable.get());
            //context.write(new Text("fileTimestamps--"+fileTimestamps), NullWritable.get());
            context.write(new Text("numReduceTasks--" + numReduceTasks), NullWritable.get());
            //context.write(new Text("fileClassPaths--"+fileClassPaths), NullWritable.get());
            context.write(new Text("configuration--" + configuration), NullWritable.get());
            //context.write(new Text("groupingComparator--"+groupingComparator), NullWritable.get());
            context.write(new Text("jobSetupCleanupNeeded--" + jobSetupCleanupNeeded), NullWritable.get());
            context.write(new Text("maxMapAttempts--" + maxMapAttempts), NullWritable.get());
            context.write(new Text("maxReduceAttempts--" + maxReduceAttempts), NullWritable.get());
            //context.write(new Text("localCacheFiles--"+localCacheFiles), NullWritable.get());
            //context.write(new Text("outputCommitter--"+outputCommitter), NullWritable.get());
            context.write(new Text("workingDirectory--" + workingDirectory), NullWritable.get());
            context.write(new Text("nextKeyValue--" + nextKeyValue), NullWritable.get());
            //context.write(new Text("cacheFiles--"+cacheFiles), NullWritable.get());
            context.write(new Text("cacheArchives--" + cacheArchives), NullWritable.get());
            context.write(new Text("archiveClassPaths--" + archiveClassPaths), NullWritable.get());
            context.write(new Text("profileEnabled--" + profileEnabled), NullWritable.get());
            //context.write(new Text("profileParams--"+profileParams), NullWritable.get());
            context.write(new Text("symlink--" + symlink), NullWritable.get());
            //context.write(new Text("sortComparator--"+sortComparator), NullWritable.get());
            //context.write(new Text("hashCode--"+hashCode), NullWritable.get());
        }
    }
}
