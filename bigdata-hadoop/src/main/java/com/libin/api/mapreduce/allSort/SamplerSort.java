package com.libin.api.mapreduce.allSort;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Partitioner;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * Copyright (c) 2015/10/08. xixi Inc. All Rights Reserved.
 * Authors: libin <2578858653@qq.com>
 * <p>
 * Purpose :
 */
@SuppressWarnings("deprecation")
public class SamplerSort extends Configured implements Tool {
    // 自定义的Partitioner
    public static class TotalOrderPartitioner implements Partitioner<Text, Text> {

        private Text[] splitPoints;

        public TotalOrderPartitioner() {
        }

        @Override
        public int getPartition(Text arg0, Text arg1, int arg2) {
            // TODO Auto-generated method stub
            return findPartition(arg0);
        }

        public void configure(JobConf arg0) {
            try {
                FileSystem fs = FileSystem.getLocal(arg0);
                Path partFile = new Path(SamplerInputFormat.PARTITION_FILENAME);
                splitPoints = readPartitions(fs, partFile, arg0); // 读取采集文件
            } catch (IOException ie) {
                throw new IllegalArgumentException("can't read paritions file", ie);
            }

        }

        public int findPartition(Text key) // 分配可以到多个reduce
        {
            int len = splitPoints.length;
            for (int i = 0; i < len; i++) {
                int res = key.compareTo(splitPoints[i]);
                if (res > 0 && i < len - 1) {
                    continue;
                } else if (res == 0) {
                    return i;
                } else if (res < 0) {
                    return i;
                } else if (res > 0 && i == len - 1) {
                    return i + 1;
                }
            }
            return 0;
        }

        private static Text[] readPartitions(FileSystem fs, Path p, JobConf job) throws IOException {
            SequenceFile.Reader reader = new SequenceFile.Reader(fs, p, job);
            List<Text> parts = new ArrayList<Text>();
            Text key = new Text();
            NullWritable value = NullWritable.get();
            while (reader.next(key, value)) {
                parts.add(key);
            }
            reader.close();
            return parts.toArray(new Text[parts.size()]);
        }

    }

    @Override
    public int run(String[] args) throws Exception {
        JobConf job = (JobConf) getConf();
        // job.set(name, value);
        Path inputDir = new Path(args[0]);
        inputDir = inputDir.makeQualified(inputDir.getFileSystem(job));
        Path partitionFile = new Path(inputDir, SamplerInputFormat.PARTITION_FILENAME);

        URI partitionUri = new URI(partitionFile.toString() + "#" + SamplerInputFormat.PARTITION_FILENAME);

        SamplerInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setJobName("SamplerTotalSort");
        job.setJarByClass(SamplerSort.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        job.setInputFormat(SamplerInputFormat.class);
        job.setOutputFormat(TextOutputFormat.class);
        job.setPartitionerClass(TotalOrderPartitioner.class);
        job.setNumReduceTasks(4);

        SamplerInputFormat.writePartitionFile(job, partitionFile); // 数据采集并写入文件

        DistributedCache.addCacheFile(partitionUri, job);
        DistributedCache.createSymlink(job);
        // SamplerInputFormat.setFinalSync(job, true);
        JobClient.runJob(job);
        return 0;
    }

    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new JobConf(), new SamplerSort(), args);
        System.exit(res);
    }
}
