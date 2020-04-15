package com.libin.etl.utils

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

/**
 * Copyright (c) 2020/4/15. libin Inc. All Rights Reserved.
 * Authors: libin <2578858653@qq.com>
 * <p>
 * Purpose : 
 */
object DateUtils {
    val DATE_FORMAT = "yyyyMMdd"

    /**
     * 日期字符串转为DateTime
     *
     * @param input 日期
     * @return
     */
    def parseDate(input: String): Option[DateTime] =
        try {
            Some(DateTimeFormat.forPattern(DATE_FORMAT).parseDateTime(input))
        } catch {
            case e: Exception => None
        }
}