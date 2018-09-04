package com.libin.api.mapreduce.inputformat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.*;

/**
 * Copyright (c) 2015/09/06. xixi Inc. All Rights Reserved.
 * Authors: libin <2578858653@qq.com>
 * <p>
 * Purpose :
 */
public class FindMaxValueInputFormat extends InputFormat<IntWritable, ArrayWritable> {
    public static float[] floatvalues;

    /**
     * 返回一个InputSplit 集合
     * 这个例子一共有两个InputSplit，两个map
     * 随机产生100个 0-1 的数组，放到float数组里面
     */
    @Override
    public List<InputSplit> getSplits(JobContext context) throws IOException,
            InterruptedException {
        int NumOfValues = context.getConfiguration().getInt("NumOfValues", 100);
        floatvalues = new float[NumOfValues];
        Random rand = new Random();

        for (int i = 0; i < NumOfValues; i++) {
            floatvalues[i] = rand.nextFloat();
        }
        int NumSplits = context.getConfiguration().getInt("mapred.map.tasks", 2);
        int beg = 0;
        int length = (int) Math.floor(NumOfValues / NumSplits);

        ArrayList<InputSplit> splits = new ArrayList<InputSplit>();
        int end = length - 1;

        for (int i = 0; i < NumSplits - 1; i++) {
            FindMaxValueInputSplit split = new FindMaxValueInputSplit(beg, end);
            splits.add(split);

            beg = end + 1;
            end = end + length - 1;
        }

        FindMaxValueInputSplit split = new FindMaxValueInputSplit(beg, NumOfValues - 1);
        splits.add(split);

        return splits;
    }

    /**
     * 自定义 RecordReader
     */
    @Override
    public RecordReader<IntWritable, ArrayWritable> createRecordReader(
            InputSplit split, TaskAttemptContext context) throws IOException,
            InterruptedException {
        return new FindMaxValueRecordReader();
    }
}
