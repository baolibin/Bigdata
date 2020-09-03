package com.libin.data.flink.streaming.jobs.config

import org.apache.flink.runtime.state.filesystem.FsStateBackend
import org.apache.flink.streaming.api.environment.CheckpointConfig.ExternalizedCheckpointCleanup
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.{CheckpointingMode, TimeCharacteristic}

/**
 * Copyright (c) 2020/4/2 libin Inc. All Rights Reserved.
 * Authors: libin<2578858653@qq.com>
 *
 * Purpose : Env和Checkpoint 一些常见配置
 */
object GenCodeFromCheckpoint {
	def main(args: Array[String]): Unit = {
		// create env
		val env = StreamExecutionEnvironment.getExecutionEnvironment

		// 设置env属性值
		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
		env.setParallelism(16)
		env.enableCheckpointing(20000)
		env.setStateBackend(new FsStateBackend("checkpoint path")) // kafka offset,确保 exactly-once

		// 设置config属性值
		val config = env.getCheckpointConfig
		config.enableExternalizedCheckpoints(ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION)
		config.setCheckpointingMode(CheckpointingMode.AT_LEAST_ONCE)
		// config.setCheckpointInterval(10000)
		config.setCheckpointInterval(5 * 60 * 1000); // Checkpoint的触发频率；
		config.setMinPauseBetweenCheckpoints(5 * 60 * 1000); // Checkpoint之间的最小间隔；
		config.setCheckpointTimeout(10 * 60 * 1000); // Checkpoint的超时时间；
		config.setTolerableCheckpointFailureNumber(3); // 连续3次checkpoint失败，才会导致作业失败重启；默认值是0 。

	}
}
