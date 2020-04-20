package com.libin.utils

import org.apache.commons.lang3.StringUtils

import scala.io.Source

/**
 * Copyright (c) 2020/4/20 XiaoMi Inc. All Rights Reserved.
 * Authors: libin<baolibin@xiaomi.com>
 *
 * Purpose : 读取resource下面的数据
 */
object ResourceUtils {
	/**
	 * 1. 读取resources文件夹下的配置文件，返回Map形式,以第一个元素为key,第二个元素为value;
	 * 2. 以#开头的行为注释行,不加入map
	 *
	 * @param fileName :文件名称
	 * @param delimit  :分割符
	 */
	def readFileAsMap(fileName: String, delimit: String): Map[String, String] = {
		val inputStream = this.getClass.getClassLoader.getResourceAsStream(fileName)
		Source.fromInputStream(inputStream).getLines().filter {
			line =>
				!line.startsWith("#")
		}.map {
			line =>
				//使用delimit将该行分成两部分，key与value
				val parts = line.split(delimit, 2)
				(parts(0), parts(1))
		}.toMap
	}
	
	/**
	 * 1. 读取resources文件夹下的配置文件，返回Set形式
	 * 2. 以#开头的行为注释行或者空行,不加入set
	 *
	 * @param fileName :文件名称
	 */
	def readFileAsSet(fileName: String): Set[String] = {
		val inputStream = this.getClass.getClassLoader.getResourceAsStream(fileName)
		Source.fromInputStream(inputStream).getLines().filter {
			line =>
				!line.startsWith("#") && StringUtils.isNotBlank(line)
		}.map(line => StringUtils.trim(line)).toSet
	}
	
	/**
	 * 1. 读取resources文件夹下的配置文件，每行作为List的一个元素
	 * 2. 以#开头的行为注释，不加入List
	 *
	 * @param fileName :文件名称
	 */
	def readFileAsList(fileName: String): List[String] = {
		val inputStream = this.getClass.getClassLoader.getResourceAsStream(fileName)
		Source.fromInputStream(inputStream).getLines().filter {
			line =>
				!line.startsWith("#")
		}.toList
	}
}
