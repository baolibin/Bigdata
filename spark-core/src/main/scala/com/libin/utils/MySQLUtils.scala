package com.libin.utils

import java.sql.{Connection, DriverManager, ResultSet, SQLException}

import com.typesafe.config.ConfigFactory
import org.slf4j.{Logger, LoggerFactory}

import scala.collection.mutable.ArrayBuffer

/**
 * Copyright (c) 2020/4/22 libin Inc. All Rights Reserved.
 * Authors: libin<2578858653@qq.com>
 *
 * Purpose :
 */
object MySQLUtils {
	val logger: Logger = LoggerFactory.getLogger("MySQLUtils")
	val splitTable = "\t" // 制表符字符
	/**
	 * 获取MySQL访问链接
	 *
	 * @param config 数据库配置信息
	 */
	def getMySQLConn(config: String): Connection = {
		try {
			def dbConf = ConfigFactory.load(config).getConfig("db.default")
			
			Class.forName(dbConf.getString("driver"))
			DriverManager.getConnection(dbConf.getString("url"),
				dbConf.getString("user"),
				dbConf.getString("password"))
		} catch {
			case e: SQLException => e.printStackTrace(); null
			case _: Throwable => null
		}
	}
	
	/**
	 * 查询指定Sql语句，返回指定字段内容
	 *
	 * @param sql  语句
	 * @param conn Connection
	 * @param num  读取几个字段,把查询出来的字段每一行拼接在一起
	 */
	def executeSql(sql: String, conn: Connection, num: Int): Array[String] = {
		val stmt = conn.createStatement();
		try {
			val rs: ResultSet = stmt.executeQuery(sql)
			var arr = new ArrayBuffer[String]
			val sb = new StringBuilder
			while (rs.next()) {
				sb.clear()
				for (i <- 1 to (num)) {
					sb.append(rs.getString(i))
					// 查询中有多个字段之间用制表符分割开
					if (i < num) sb.append(splitTable)
				}
				arr += sb.toString()
			}
			rs.close()
			arr.toArray
		} catch {
			case e: SQLException => e.printStackTrace(); null
			case _: Throwable => null
		} finally {
			stmt.close()
		}
	}
	
	/**
	 * 关闭MySQL链接
	 *
	 * @param conn Connection链接
	 */
	def close(conn: Connection): Unit = {
		try {
			conn.close()
		} catch {
			case a: SQLException => a.printStackTrace(); null
			case _: Throwable => null
		}
	}
}
