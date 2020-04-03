package com.libin.data.streaming.base

import org.slf4j.{Logger, LoggerFactory}

/**
 * Copyright (c) 2020/4/3 libin Inc. All Rights Reserved.
 * Authors: libin<2578858653@qq.com>
 *
 * Purpose :
 */
trait SparkStreamingTrait {
	
	/**
	 * Application Name
	 */
	def appName: String = this.getClass.getSimpleName
	
	/**
	 * logger
	 */
	def logger: Logger = LoggerFactory.getLogger(appName)
	
	
	
}
