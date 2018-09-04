package com.libin.api.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * Copyright (c) 2015/10/30. xixi Inc. All Rights Reserved.
 * Authors: libin <2578858653@qq.com>
 * <p>
 * Purpose :
 * 问题场景：当有很多个小文件，需要把每个小文件的目录名加进小文件内容中并转换输出，用一个map类的话可以处理每一行数据的时候读取这行数据的目录名加到第一个字段输出。
 */
public class GetInputSplit {
    public static class MapClass extends Mapper<LongWritable, Text, Text, IntWritable> {
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, NullWritable>.Context context)
                throws IOException, InterruptedException {
            FileSplit fileSplit = (FileSplit) context.getInputSplit();
            //获得当前子目录名
            String pathName = fileSplit.getPath().getName();
            //获得全路径
            String path = fileSplit.getPath().toString();
            //获得父目录的全路径
            String parentPath = fileSplit.getPath().getParent().toString();
            //获取父目录的目录名
            String parentPathName = fileSplit.getPath().getParent().getName();
            //整和Mapkey
            String mapkey = pathName + "|" + path + "|" + parentPath + "|" + parentPathName;
            context.write(new Text(mapkey), NullWritable.get());
        }
    }
}
