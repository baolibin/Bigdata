package com.libin.api.mapreduce.allSort;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.LineRecordReader;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.util.IndexedSortable;
import org.apache.hadoop.util.QuickSort;

/**
 * Copyright (c) 2015/10/08. xixi Inc. All Rights Reserved.
 * Authors: libin <2578858653@qq.com>
 * <p>
 * Purpose :
 */
@SuppressWarnings("deprecation")
public class SamplerInputFormat extends FileInputFormat<Text, Text> {
    static final String PARTITION_FILENAME = "_partition.lst";
    static final String SAMPLE_SIZE = "terasort.partitions.sample";
    private static JobConf lastConf = null;
    private static InputSplit[] lastResult = null;

    static class TextSampler implements IndexedSortable {
        ArrayList<Text> records = new ArrayList<Text>();

        @Override
        public int compare(int arg0, int arg1) {
            Text right = records.get(arg0);
            Text left = records.get(arg1);

            return right.compareTo(left);
        }

        @Override
        public void swap(int arg0, int arg1) {
            Text right = records.get(arg0);
            Text left = records.get(arg1);

            records.set(arg0, left);
            records.set(arg1, right);
        }

        void addKey(Text key) {
            records.add(new Text(key));
        }

        Text[] createPartitions(int numPartitions) {
            int numRecords = records.size();
            if (numPartitions > numRecords) {
                throw new IllegalArgumentException("Requested more partitions than input keys (" + numPartitions + " > " + numRecords + ")");
            }
            new QuickSort().sort(this, 0, records.size());
            float stepSize = numRecords / (float) numPartitions;
            Text[] result = new Text[numPartitions - 1];
            for (int i = 1; i < numPartitions; ++i) {
                result[i - 1] = records.get(Math.round(stepSize * i));
            }
            return result;
        }
    }

    public static void writePartitionFile(JobConf conf, Path partFile) throws IOException {
        SamplerInputFormat inputFormat = new SamplerInputFormat();
        TextSampler sampler = new TextSampler();
        Text key = new Text();
        Text value = new Text();

        int partitions = conf.getNumReduceTasks(); // Reducer任务的个数
        long sampleSize = conf.getLong(SAMPLE_SIZE, 100); // 采集数据-键值对的个数
        InputSplit[] splits = inputFormat.getSplits(conf, conf.getNumMapTasks());// 获得数据分片
        int samples = Math.min(10, splits.length);// 采集分片的个数
        long recordsPerSample = sampleSize / samples;// 每个分片采集的键值对个数
        int sampleStep = splits.length / samples; // 采集分片的步长
        long records = 0;

        for (int i = 0; i < samples; i++) {
            RecordReader<Text, Text> reader = inputFormat.getRecordReader(splits[sampleStep * i], conf, null);
            while (reader.next(key, value)) {
                sampler.addKey(key);
                records += 1;
                if ((i + 1) * recordsPerSample <= records) {
                    break;
                }
            }
        }
        FileSystem outFs = partFile.getFileSystem(conf);
        if (outFs.exists(partFile)) {
            outFs.delete(partFile, false);
        }
        SequenceFile.Writer writer = SequenceFile.createWriter(outFs, conf, partFile, Text.class, NullWritable.class);
        NullWritable nullValue = NullWritable.get();
        for (Text split : sampler.createPartitions(partitions)) {
            writer.append(split, nullValue);
        }
        writer.close();

    }

    static class TeraRecordReader implements RecordReader<Text, Text> {

        private LineRecordReader in;
        private LongWritable junk = new LongWritable();
        private Text line = new Text();
        // private static int KEY_LENGTH = 10;

        TeraRecordReader(Configuration job, FileSplit split) throws IOException {
            in = new LineRecordReader(job, split);
        }

        @Override
        public void close() throws IOException {
            in.close();
        }

        @Override
        public Text createKey() {
            // TODO Auto-generated method stub
            return new Text();
        }

        @Override
        public Text createValue() {
            return new Text();
        }

        @Override
        public long getPos() throws IOException {
            // TODO Auto-generated method stub
            return in.getPos();
        }

        @Override
        public float getProgress() throws IOException {
            // TODO Auto-generated method stub
            return in.getProgress();
        }

        @Override
        public boolean next(Text arg0, Text arg1) throws IOException {
            if (in.next(junk, line)) {
                // if (line.getLength() < KEY_LENGTH) {
                arg0.set(line);
                arg1.clear();
//                } else {
//                    byte[] bytes = line.getBytes(); // 默认知道读取要比较值的前10个字节 作为key
//                                                    // 后面的字节作为value；
//                    arg0.set(bytes, 0, KEY_LENGTH);
//                    arg1.set(bytes, KEY_LENGTH, line.getLength() - KEY_LENGTH);
//                }
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public InputSplit[] getSplits(JobConf conf, int splits) throws IOException {
        if (conf == lastConf) {
            return lastResult;
        }
        lastConf = conf;
        lastResult = super.getSplits(lastConf, splits);
        return lastResult;
    }

    public org.apache.hadoop.mapred.RecordReader<Text, Text> getRecordReader(InputSplit arg0, JobConf arg1, Reporter arg2) throws IOException {
        return new TeraRecordReader(arg1, (FileSplit) arg0);
    }
}
