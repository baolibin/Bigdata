package com.libin.utils

import java.net.URI
import java.text.SimpleDateFormat

import org.apache.commons.lang3.StringUtils
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileStatus, FileSystem, Path}
import org.apache.hadoop.io.{LongWritable, Text}
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.joda.time.DateTime
import org.slf4j.{Logger, LoggerFactory}
import shaded.parquet.org.apache.thrift.TBase

import scala.collection.mutable
import scala.reflect.ClassTag
import scala.util.matching.Regex

/**
 * Copyright (c) 2020/4/22 libin Inc. All Rights Reserved.
 * Authors: libin<2578858653@qq.com>
 *
 * Purpose :
 */
object SparkLogUtils {
	
	val logger: Logger = LoggerFactory.getLogger("SparkUtils")
	
	val regularType: Regex = "org.apache.([0-9a-zA-Z.]+):".trim.r //匹配读取方式
	val regularTable: Regex = "/user/xxx/xxx/([0-9a-zA-Z/_]+)/dat".trim.r // 匹配数据表
	val regularTime: Regex = "([0-9-:, ]+)INFO".trim.r // 匹配读取日期
	val regularField: Regex = "StructField\\(([0-9a-zA-Z_]+),".trim.r // 匹配读取字段
	val regularParquetVer: Regex = "init,([0-9a-zA-Z-.]+)".trim.r
	
	val oneG = 1048576000L // 1G的size大小
	val tenG = 10485760000L // 10G的size大小
	val twentyG = 20971520000L // 10G的size大小
	
	val LOG_READ_TIME_FORMAT_COMMA = "yyyy-MM-dd,HH:mm:ss,SSS" // 格式化读取日期
	val LOG_READ_TIME_FORMAT_SPACE = "yyyy-MM-dd HH:mm:ss,SSS" // 格式化读取日期
	
	val splitSpace = " " // 空格字符
	val splitComma = "," // 空格字符
	val splitEqual = "=" // 空格字符
	val splitSemicolon = ";" // 分号字符
	val splitPoint = "\\." // 点字符
	val splitLine = "\\|" // 竖线字符
	val splitLeftSlash = "/" //左斜杠
	val splitRightSlash = "\\" //右斜杠
	val splitEmpty = "" // 空格字符
	
	val sparkSqlFieldType = "StructField" // 解析spark sql字段
	val sparkCoreMrFieldType = "filter_column" // 解析mr和spark core字段
	val sparkSqlReadType = "spark.sql"
	val sparkCoreReadType = "spark.rdd"
	val pigReadType = "pig"
	val hiveReadType = "hive"
	val mrReadType = "hadoop.mapred"
	val sparkSqlSplitInfo = "INFO"
	val parquetVer = "filter_column"
	
	val sparkSqlLogTable = 0
	val sparkSqlLogField = 1
	
	val DATE_FORMAT = "yyyyMMdd"
	
	/**
	 * 返回指定目录下面的所有子目录
	 *
	 * @param conf        Configuration
	 * @param yarnLogPath /yarn/XXX/log/
	 * @return (path, owner, time)
	 */
	def listLogPath(conf: Configuration, yarnLogPath: String): Array[(String, String, Long, String)] = {
		val fs: FileSystem = FileSystem.get(URI.create(yarnLogPath), conf)
		val fsStatus: Array[FileStatus] = fs.listStatus(new Path(yarnLogPath))
		val pathInfo: Array[(String, String, Long, String)] = fsStatus
			.map {
				x =>
					val time = if (x.getAccessTime == 0L) x.getModificationTime else x.getAccessTime
					// logger.info(s"${x.getPath.toString}, ${x.getPath}, ${x.getPath.getName}, ${x.getOwner} , ${x.toString}")
					(x.getPath.toString, x.getOwner, time, x.getPath.getName)
			}
		pathInfo
	}
	
	/**
	 * 返回解析的时间信息
	 *
	 * @param originTime 待解析的时间字符串
	 * @param format     SimpleDateFormat
	 * @return 返回时间戳
	 */
	def getDateFormat(originTime: String, format: SimpleDateFormat): Long = {
		try {
			format.parse(originTime).getTime
		} catch {
			case _: Throwable => logger.info(s"originTime=$originTime")
				0
		}
	}
	
	/**
	 * 获取指定目录下最大的那个文件
	 * 策略:
	 * 如果app作业目录下最大Log小文件小于20G, 则取最大的Log文件
	 * 如果app作业目录下最大Log小文件大于20G,  若存在20G以下Log小文件,则取20G以下Log小文件最大的,否则取所有Log小文件最小的
	 *
	 * @param conf        Configuration
	 * @param yarnLogPath 目录
	 */
	def getMaxLogPath(conf: Configuration, yarnLogPath: String): (Long, String) = {
		val fsStatus: Array[FileStatus] = getFsStatusArr(conf, yarnLogPath)
		val arr = fsStatus.map(x => (x.getLen, x.getPath.toString))
		if (!arr.isEmpty) {
			val sortArr = arr.sortBy(_._1)
			val max = sortArr.last
			if (max._1 > tenG) {
				val filterSize = sortArr.filter(x => x._1 <= tenG)
				if (filterSize.isEmpty) {
					val min = sortArr.minBy(_._1)
					min
				} else {
					val maxFilter = filterSize.maxBy(_._1)
					maxFilter
				}
			} else {
				max
			}
		} else {
			logger.info(s"get path failed=$yarnLogPath")
			null
		}
	}
	
	/**
	 * 返回指定作业路径下日志小文件大小topN的路径集合
	 * 策略:
	 * 如果app作业目录下最大Log小文件小于10G, 若个数大于topN,返回topN, 否则返回top1
	 * 如果app作业目录下最大Log小文件大于10G, 则返回大于10G的log小文件最小的那个
	 *
	 * @param conf        Configuration
	 * @param yarnLogPath 指定作业的路径
	 * @param topN        大小topN
	 * @return 返回topN的路径集合
	 */
	def getTopNLogPath(conf: Configuration, yarnLogPath: String, topN: Int): Array[(Long, String)] = {
		val fsStatus: Array[FileStatus] = getFsStatusArr(conf, yarnLogPath)
		val arr = fsStatus.map(x => (x.getLen, x.getPath.toString))
		if (!arr.isEmpty) {
			val sortArr = arr.sortBy(_._1).reverse
			val max = sortArr.head
			if (max._1 < tenG) {
				if (sortArr.length >= topN) {
					logger.info(s"sortArr.take(topN)=${sortArr.take(topN).mkString("|")}")
					sortArr.take(topN)
				} else {
					logger.info(s"sortArr.take(1)=${sortArr.take(1).mkString("|")}")
					sortArr.take(1)
				}
			} else {
				val minFilter = sortArr.filter(x => x._1 > tenG)
				logger.info(s"sortArr.minFilter.minBy(_._1)=${minFilter.minBy(_._1)}")
				Array(minFilter.minBy(_._1))
			}
		} else {
			logger.info(s"get topN path failed=$yarnLogPath")
			null
		}
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
	 * 读取指定目录的数据
	 *
	 * @param sc          SparkContext
	 * @param yarnLogPath 目录
	 * @param hadoopConf  Configuration
	 * @return RDD[String]
	 */
	def getLogData(sc: SparkContext, yarnLogPath: String, hadoopConf: Configuration): RDD[String] = {
		val inputRdd = sc.newAPIHadoopFile(yarnLogPath,
			classOf[CombineTextInputFormat],
			classOf[LongWritable],
			classOf[Text],
			hadoopConf)
		inputRdd.map(_._2.toString)
	}
	
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
	 * 从文本中匹配出指定内容的数据
	 *
	 * @param line    文本信息
	 * @param regular 模式串
	 * @return
	 */
	def regularUpField(line: String, regular: Regex): String = {
		val data = regular.findAllIn(line).matchData
		if (data.nonEmpty) {
			data.next().group(1).trim
		} else null
	}
	
	/**
	 * 返回匹配的所有信息
	 *
	 * @param line    文本信息
	 * @param regular 模式串
	 * @return
	 */
	def regularUpFieldAll(line: String, regular: Regex): String = {
		val data = regular.findAllIn(line).matchData
		val sb = new StringBuilder
		if (data.nonEmpty) sb.append(data.next().group(1).trim)
		while (data.hasNext) {
			sb.append("|" + data.next().group(1).replaceAll("\\\\", "").trim)
		}
		sb.toString()
	}
	
	/**
	 * 判断一个字段是否存在指定的表中
	 *
	 * @param thriftClass 表数据格式
	 * @param fieldName   字段名
	 * @return 返回true或false
	 */
	def isTableFieldName[T <: TBase[_, _] : ClassTag](thriftClass: Class[T], fieldName: String): Boolean = {
		// println(thriftClass.getDeclaredField(fieldName))
		val fields = thriftClass.getFields.map(x => x.toString.split(splitPoint).last)
		println(fields.mkString(splitComma))
		if (fields.contains(fieldName)) true else false
	}
	
	/**
	 * 字段与表进行匹配,如果字段和表内任一一个字段都不匹配,则抛弃该字段
	 *
	 * @param tableNameFields 表字段
	 * @param LogFields       要匹配的字段
	 * @return 返回这些字段是否为表的字段
	 */
	def isTableFields(tableNameFields: String, LogFields: String, splitType: String): String = {
		val tableFieldList = tableNameFields.split(splitComma).toList
		val res = LogFields.split(splitType).toSet.filter {
			x =>
				// 设置column的作业会有嵌套(struct1_Field1/struct2_Field1)
				if (tableFieldList.exists(field => x.contains(field))) true else false
		}.mkString(splitComma)
		res
	}
	
	def isHiveTableFields(tableNameFields: String, LogFields: String, splitType: String): String = {
		val tableFieldList = tableNameFields.split(splitComma).toList
		val res = LogFields.split(splitType).toSet.flatMap {
			line: String =>
				val matchFieldList = tableFieldList.filter(field => line.contains(field))
				if (matchFieldList.nonEmpty) matchFieldList else None
		}.filter(x => StringUtils.isNoneBlank(x)).mkString(splitComma)
		res
	}
	
	/**
	 * 强制匹配是否为该表字段
	 *
	 * @param tableNameFields 表字段
	 * @param LogFields       待匹配字段
	 */
	def isTableMatchFields(tableNameFields: String, LogFields: String): Boolean = {
		val tableFieldList = tableNameFields.split(splitComma).toList
		val realLogField = LogFields.split(SparkLogUtils.splitLeftSlash).head
		tableFieldList.exists { line => if (line.equals(realLogField)) true else false }
	}
	
	/**
	 * 返回读取模式Map
	 */
	def getReadMode: mutable.HashMap[String, String] = {
		val readModeMap = new mutable.HashMap[String, String]()
		readModeMap.put(sparkSqlReadType, "sparkSql")
		readModeMap.put(sparkCoreReadType, "sparkCore")
		readModeMap.put(pigReadType, "pig")
		readModeMap.put(mrReadType, "mr")
		readModeMap
	}
	
	def getDayHour(time: Long): (Int, Int) = {
		if (time > 0) {
			val dateTime = new DateTime(time)
			(dateTime.toString(SparkLogUtils.DATE_FORMAT).toInt, dateTime.getHourOfDay)
		} else {
			(0, 0)
		}
	}
	
}
