package com.libin.utils

import java.net.URI

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileStatus, FileSystem, Path}
import org.joda.time.DateTime

/**
 * Copyright (c) 2020/4/14. libin Inc. All Rights Reserved.
 * Authors: libin <2578858653@qq.com>
 * <p>
 * Purpose : 
 */
object PathUtils {

    /**
     * 路径拼接DateTime
     *
     * @param root 数据根目录
     * @param date 读取的数据日期
     * @return 完整数据路径
     */
    def pathAssemble(root: String, date: DateTime): String = s"$root/date=${date.toString(DateUtils.DATE_FORMAT)}"

    def pathAssembleAll(root: String, date: DateTime): String = s"$root/date=${date.toString(DateUtils.DATE_FORMAT)}/*"

    /**
     * 获得Hadoop输入配置
     */
    def getHadoopConfig: Configuration = {
        val hadoopConf = new Configuration()
        hadoopConf.set("mapreduce.input.fileinputformat.split.maxsize", "512000000")
        hadoopConf.set("mapreduce.input.fileinputformat.split.minsize", "268435456")
        hadoopConf
    }

    /**
     * 返回目录下所有文件FileStatus信息
     *
     * @param conf        Configuration
     * @param yarnLogPath 指定作业的路径
     */
    def getFsStatusArr(conf: Configuration, yarnLogPath: String): Array[FileStatus] = {
        val fs: FileSystem = FileSystem.get(URI.create(yarnLogPath), conf)
        fs.listStatus(new Path(yarnLogPath))
    }

    /**
     * 检测带分区的数据表，返回指定路径下面有数据最新分区的数据日期.
     * 可以用于判断天、周、月、年作业，不用关注上游数据表生成周期日期，取最近有数据的分区数据与指定天做对比，判断是否是需要读取的数据。
     *
     * @param path 数据父路径
     * @return
     */
    def getLatestDate(path: String): String = {
        val fsStatus: Array[FileStatus] = getFsStatusArr(getHadoopConfig, path)

        fsStatus
                .map(_.getPath.toString.trim)
                // 合格的数据目录
                .filter(_.contains(SeparatorUtils.SEPARATOR_EQUAL))
                .filter {
                    x =>
                        // 当前分区数据目录下面存在SUCCESS文件，确保数据已经正常生成完毕
                        val pathArr = getFsStatusArr(getHadoopConfig, x).map {
                            x =>
                                x.getPath.toString
                        }
                        pathArr.exists(_.contains(SeparatorUtils.pathSuccess))
                }
                // 取出路径中数据分区信息
                .map(_.split(SeparatorUtils.SEPARATOR_EQUAL).last)
                .filter(_.length == 8)
                // 取最近的一份数据
                .maxBy(_.toLong)
    }

}
