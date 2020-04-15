package com.libin.utils

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

/**
 * Copyright (c) 2020/4/14. libin Inc. All Rights Reserved.
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
    
    /**
     * DateTime转为日期字符串
     *
     * @param input 日期
     */
    def parseDateTimeToStr(input: DateTime): Option[String] =
        try {
            Some(input.toString(DATE_FORMAT))
        } catch {
            case e: Exception => None
        }
}
