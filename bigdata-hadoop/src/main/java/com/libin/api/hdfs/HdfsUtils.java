package com.libin.api.hdfs;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

/**
 * Copyright (c) 2018/09/16. xixi Inc. All Rights Reserved.
 * Authors: libin <2578858653@qq.com>
 * <p>
 * Purpose : 很久之前，14年时候刚开始学习时候的代码，待整体重新梳理一下
 */
public class HdfsUtils {
    static final String PATH = "hdfs://192.168.1.100:9000/";
    static final String DIR = "hdfs://192.168.1.100:9000/dir";
    static final String FILE = "/dir/xiaobaozi";

    public static void main(String args[]) throws Exception {
        FileSystem fileSystem = getFileStstem();
        //创建文件夹
        //     mkdir(fileSystem);
        //上传文件
        //     putData(fileSystem);
        //下载文件
        //     getData(fileSystem);
        //浏览文件夹
        //     list(fileSystem);
        //删除文件夹
        remove(fileSystem);
    }

    private static FileSystem getFileStstem() throws Exception {
        //TODO Auto-generated method stub
        Configuration conf = new Configuration();
        URI uri = new URI(PATH);
        final FileSystem fileSystem = FileSystem.get(uri, conf);
        return fileSystem;
    }

    //创建文件夹
    private static void mkdir(FileSystem fileSystem) throws IOException {
        fileSystem.mkdirs(new Path(DIR));
        System.out.println("创建成功！");
    }

    //上传文件
    private static void putData(FileSystem fileSystem) throws IOException {
        final FSDataOutputStream out = fileSystem.create(new Path(FILE));
        final FileInputStream in = new FileInputStream("D:/File_hdfs.txt");
        IOUtils.copyBytes(in, out, 1024, true);
        System.out.println("上传成功！");
    }

    //下载文件
    private static void getData(FileSystem fileSystem) throws IOException {
        final FSDataInputStream in = fileSystem.open(new Path(FILE));
        IOUtils.copyBytes(in, System.out, 1024, true);
        System.out.println("下载文件！");
    }

    //浏览文件夹
    private static void list(FileSystem fileSystem) throws IOException {
        final FileStatus[] listStatus = fileSystem.listStatus(new Path("/"));
        for (FileStatus fileStatus : listStatus) {
            String isDir = fileStatus.isDir() ? "文件夹" : "文件";
            final String permission = fileStatus.getPermission().toString();
            final short replication = fileStatus.getReplication();
            final long len = fileStatus.getLen();
            final String path = fileStatus.getPath().toString();
            System.out.println(isDir + "\t" + permission + "\t" + replication + "\t" + len + "\t" + path);
        }
        System.out.println("浏览文件夹！");
    }

    //删除文件夹
    private static void remove(FileSystem fileSystem) throws IOException {
        fileSystem.delete(new Path(DIR), true);
        System.out.println("删除文件夹！");
    }
}
