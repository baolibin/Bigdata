package com.libin.etl.utils

import scala.io.Source

/**
 * Copyright (c) 2018/7/1. xixi Inc. All Rights Reserved.
 * Authors: libin <2578858653@qq.com>
 *
 * Purpose :
 */

object LoadUtils {

    case class stu(name: String, age: Int, height: Int)

    /**
     * 读取配置中的文件
     *
     * @param fileName 配置文件名
     * @return 返回所有行记录的集合数据
     */
    def readResourceFile(fileName: String): Array[String] = {
        val inputStream = this.getClass.getClassLoader.getResourceAsStream(fileName)
        Source.fromInputStream(inputStream).getLines().toArray
    }


    /**
     * 读取配置中的文件,按照制定分隔符返回Map
     *
     * @param fileName 配置文件名
     * @return 返回所有行记录的集合数据
     */
    def readResourceFile(fileName: String, delimit: String): Map[String, String] = {
        val inputStream = this.getClass.getClassLoader.getResourceAsStream(fileName)
        Source.fromInputStream(inputStream).getLines()
                .map {
                    line =>
                        val sp = line.split(delimit)
                        (sp(0), sp(1))
                }.toMap
    }
}
