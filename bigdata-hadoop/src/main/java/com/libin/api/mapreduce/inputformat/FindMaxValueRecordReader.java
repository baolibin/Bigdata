package com.libin.api.mapreduce.inputformat;

import java.io.IOException;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

/**
 * Copyright (c) 2015/09/06. xixi Inc. All Rights Reserved.
 * Authors: libin <2578858653@qq.com>
 * <p>
 * Purpose : 这个自定义 RecordReader 类，定义输入到map函数的输入格式
 * Key为偏移量
 * Value为float数组，长度为50
 */
public class FindMaxValueRecordReader extends RecordReader<IntWritable, ArrayWritable> {
    private int m_End;
    private int m_Index;
    private int m_Start;
    private IntWritable key = null;
    private ArrayWritable value = null;
    private FindMaxValueInputSplit fmvsplit = null;

    @Override
    public void initialize(InputSplit split, TaskAttemptContext context)
            throws IOException, InterruptedException {
        fmvsplit = (FindMaxValueInputSplit) split;
        this.m_Start = fmvsplit.getM_StartIndex();
        this.m_End = fmvsplit.getM_EndIndex();
        this.m_Index = this.m_Start;
    }

    /**
     * 输出的key为  IntWritable
     * 输出的value为  ArrayWritable
     */
    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        if (key == null) {
            key = new IntWritable();
        }
        if (value == null) {
            value = new ArrayWritable(FloatWritable.class);
        }
        if (m_Index <= m_End) {
            key.set(m_Index);
            value = fmvsplit.getM_FloatArray();
            m_Index = m_End + 1;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public IntWritable getCurrentKey() throws IOException, InterruptedException {
        return key;
    }

    @Override
    public ArrayWritable getCurrentValue() throws IOException,
            InterruptedException {
        return value;
    }

    @Override
    public float getProgress() throws IOException, InterruptedException {

        if (this.m_Index == this.m_End) {
            return 0.0f;
        } else {
            return Math.min(1.0f, (this.m_Index - this.m_Start) / (float) (this.m_End - this.m_Start));
        }
    }

    @Override
    public void close() throws IOException {
    }
}
