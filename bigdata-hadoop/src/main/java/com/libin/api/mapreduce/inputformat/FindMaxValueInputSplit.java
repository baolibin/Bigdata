package com.libin.api.mapreduce.inputformat;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.InputSplit;

/**
 * Copyright (c) 2015/09/06. xixi Inc. All Rights Reserved.
 * Authors: libin <2578858653@qq.com>
 * <p>
 * Purpose :
 */
public class FindMaxValueInputSplit extends InputSplit implements Writable {
    private int m_StartIndex;
    private int m_EndIndex;
    private ArrayWritable m_FloatArray = new ArrayWritable(FloatWritable.class);

    public FindMaxValueInputSplit() {
    }

    /**
     * 这个自定义分类主要记录了Map函数的开始索引和结束索引，第一个map处理前50个小数，第二个map后50个小数
     *
     * @param start 开始位置
     * @param end   结束位置
     */
    public FindMaxValueInputSplit(int start, int end) {
        m_StartIndex = start;
        m_EndIndex = end;
        int len = m_EndIndex - m_StartIndex + 1;
        int index = m_StartIndex;

        FloatWritable[] result = new FloatWritable[len];

        for (int i = 0; i < result.length; i++) {
            float f = FindMaxValueInputFormat.floatvalues[index];
            FloatWritable fW = new FloatWritable();
            fW.set(f);
            result[i] = fW;
            index++;
        }
        m_FloatArray.set(result);
    }

    public void write(DataOutput out) throws IOException {
        out.writeInt(this.m_StartIndex);
        out.writeInt(this.m_EndIndex);
        this.m_FloatArray.write(out);
    }


    public void readFields(DataInput in) throws IOException {
        this.m_StartIndex = in.readInt();
        this.m_EndIndex = in.readInt();
        this.m_FloatArray.readFields(in);
    }

    @Override
    public long getLength() throws IOException, InterruptedException {
        return (this.m_EndIndex - this.m_StartIndex + 1);
    }

    @Override
    public String[] getLocations() throws IOException, InterruptedException {
        return new String[]{"hadoop-2", "hadoop-1"};
    }

    public int getM_StartIndex() {
        return m_StartIndex;
    }

    public int getM_EndIndex() {
        return m_EndIndex;
    }

    public ArrayWritable getM_FloatArray() {
        return m_FloatArray;
    }

}
