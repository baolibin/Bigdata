package com.libin.api.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2016/04/01. xixi Inc. All Rights Reserved.
 * Authors: libin <2578858653@qq.com>
 * <p>
 * Purpose : Hadoop的PathFilter使用
 */
public class TextPathFilterDemo {
    static class TextPathFilter extends Configured implements PathFilter {
        @Override
        public boolean accept(Path path) {
            FileSystem fs;
            try {
                fs = FileSystem.get(getConf());
                FileStatus fstatus = fs.getFileStatus(path);
                List<String> lstName = new ArrayList<String>();
                lstName.add("input1");
                lstName.add("input2");
                lstName.add("input3");
                lstName.add("input4");
                if (fstatus.isDirectory()) {   //是目录的话返回true
                    return true;
                }
                if (fstatus.isFile() && lstName.contains(fstatus.getPath().getParent().getName())) {  //是文件的话且满足过滤条件返回true
                    return true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return false;
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length != 2) {
            System.err.println("Usage databaseV1 <inputpath> <outputpath>");
        }
        Job job = Job.getInstance(conf, TextPathFilterDemo.class.getSimpleName());
        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));      //输入路径
        FileInputFormat.setInputDirRecursive(job, true);// 递归输入
        FileInputFormat.setInputPathFilter(job, TextPathFilter.class);   //指定pathfilter类
    }
}


