package com.libin.etl

/**
  * Copyright (c) 2018/7/1. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  *
  * Purpose :
  */

class testProcessor {

  val SEPARATOR: String = "\t"

  val PARTITIONNUM: Int = 100
}

object testProcessor {

  def apply() = new testProcessor()

  def main(args: Array[String]) {
    val processor: testProcessor = apply()
    println(processor.PARTITIONNUM)

    println(this.getClass.getClassLoader.getResource("stu.json"))
  }
}

