package com.libin.api.mapreduce;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBInputFormat;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * Copyright (c) 2015/04/14. xixi Inc. All Rights Reserved.
 * Authors: libin <2578858653@qq.com>
 * <p>
 * Purpose : DBInputFormat用法
 */
public class DBInputFormatTest {
    public static class MyMapper extends
            Mapper<LongWritable, MyDBWritable, LongWritable, Text> {
        final Text v2 = new Text();

        protected void map(LongWritable key, MyDBWritable value,Mapper<LongWritable, MyDBWritable, LongWritable, Text>.Context context)throws InterruptedException, IOException {
            v2.set(value.toString());
            context.write(key, v2);
        }
    }

    public static class MyDBWritable implements Writable,DBWritable{
        int id;
        String name;
        public void write(PreparedStatement statement) throws SQLException {
            statement.setInt(1, id);
            statement.setString(2, name);
        }

        public void readFields(ResultSet resultSet) throws SQLException {
            this.id=resultSet.getInt(1);
            this.name=resultSet.getString(2);
        }

        public void write(DataOutput out) throws IOException {
            out.write(id);
            out.writeUTF(name);
        }

        public void readFields(DataInput in) throws IOException {
            this.id=in.readInt();
            this.name=in.readUTF();
        }
        public String toString(){
            return "MyDBWritable[id="+id+",\t"+"name="+name+"]";
        }
    }

    public static void main(String[] args) throws Exception {
        final Configuration conf = new Configuration();
        DBConfiguration.configureDB(conf, "com.mysql.jdbc.Driver", "jdbc:mysql://192.168.1.1:3306/oled", "root", "xxx");
        final Job job = Job.getInstance(conf, DBInputFormatTest.class.getSimpleName());
        // 1.1
        //FileInputFormat.setInputPaths(job,"hdfs://192.168.1.10:9000/input/hehe");
        job.setInputFormatClass(DBInputFormat.class);
        job.setMapperClass(MyMapper.class);
        //job.setPartitionerClass(HashPartitioner.class);
        //job.setNumReduceTasks(1);
        job.setOutputKeyClass(LongWritable.class);
        job.setOutputValueClass(Text.class);
        DBInputFormat.setInput(job, MyDBWritable.class, "select id,name from DB", "select count(1) from DB");
        // 2.3
        FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.1.100:9000/DBout1"));
        //job.setOutputFormatClass(TextOutputFormat.class);
        job.setJarByClass(DBInputFormatTest.class);
        job.waitForCompletion(true);
    }
}
