package com.libin.etl.utils

import org.joda.time.DateTime

/**
 * Copyright (c) 2020/4/15. libin Inc. All Rights Reserved.
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
     * 路径拼接String
     *
     * @param root 数据根目录
     * @param date 读取的数据日期
     * @return 完整数据路径
     */
    def pathAssemble(root: String, date: String): String = pathAssemble(root, DateUtils.parseDate(date).get)
}
