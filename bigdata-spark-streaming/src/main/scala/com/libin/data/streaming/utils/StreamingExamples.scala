package com.libin.data.streaming.utils

import org.apache.spark.internal.Logging
import org.apache.log4j.{Level, Logger}

/**
 * Copyright (c) 2020/4/3 libin Inc. All Rights Reserved.
 * Authors: libin<2578858653@qq.com>
 *
 * Purpose : 只显示WARN日志,大量的INFO日志都可以被屏蔽掉
 */
object StreamingExamples extends Logging {
	/** Set reasonable logging levels for streaming if the user has not configured log4j. */
	def setStreamingLogLevels() {
		val log4jInitialized = Logger.getRootLogger.getAllAppenders.hasMoreElements
		// if (!log4jInitialized) {
		if (log4jInitialized) {
			// We first log something to initialize Spark's default logging, then we override the
			// logging level.
			logInfo("Setting log level to [WARN] for streaming example." +
				" To override add a custom log4j.properties to the classpath.")
			Logger.getRootLogger.setLevel(Level.WARN)
		}else{
			Logger.getRootLogger.setLevel(Level.WARN)
		}
	}
}
