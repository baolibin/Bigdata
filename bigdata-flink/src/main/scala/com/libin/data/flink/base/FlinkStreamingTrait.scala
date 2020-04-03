package com.libin.data.flink.base

/**
 * Copyright (c) 2020/4/3 libin Inc. All Rights Reserved.
 * Authors: libin<2578858653@qq.com>
 *
 * Purpose :
 */
trait FlinkStreamingTrait {
	
	/**
	 * Application Name
	 */
	def appName: String = this.getClass.getSimpleName
}
