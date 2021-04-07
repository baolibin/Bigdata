* [Spark Core知识点]()
    - [1）、Spark作业提交流程？](#1)
    - [2）、Spark的内存模型？](#2)
    - [3）、SparkContext创建流程？源码级别？](#3)
    - [4）、简述Spark个版本区别？1.x与2.x？](#4)
    - [5）、使用Spark中遇到过哪些问题？如何解决的？](#5)
    - [6）、Spark的Shuffle过程？ 和MR Shuffle区别？](#6)
    - [7）、Spark中的数据倾斜问题有啥好的解决方案？](#7)
    - [8）、Spark有哪些聚合类的算子，我们应该怎么避免使用这些算子？ReduceByKey和GroupByKey的区别？](#8)
    - [9）、Spark On Yarn作业执行流程？yarn-client和yarn-cluster的区别？](#9)
    - [10）、Spark中Job、Task、RDD、DAG、Stage的理解？](#10)
    - [11）、Spark中RDD如何通过记录更新的方式容错？](#11)
    - [12）、Spark常用调优方法？](#12)
    - [13）、Spark中宽依赖和窄依赖如何理解？](#13)
    - [14）、Spark中Job和Task如何理解？](#14)
    - [15）、Spark中Transformation和action区别是什么？列举出常用的方法？](#15)
    - [16）、Spark中persist()和cache()的区别？](#16)
    - [17）、Spark中map和mapPartitions的区别？](#17)
    - [18）、Spark中Worker和Executor的异同？](#18)
    - [19）、Spark中提供的2中共享变量是啥？](#19)
    - [20）、菲波那切数列可以用Spark做出来么？](#20)
    - [21）、看过哪些Spark源码？](#21)
    - [22）、Spark通信机制？](#22)
    - [23）、Spark的存储级别有哪些？](#23)
    - [24）、Spark序列化模式有哪些？](#24)
    - [25）、Spark使用到的安全协议有哪些？](#25)
    - [26）、Spark部署模式有哪些？](#26)
    - [27）、Spark的cache后能不能接其它算子？是不是action操作?](#27)
    - [28）、Spark中reduceByKey是action算子不？reduec呢?](#28)
    - [29）、Spark中数据本地性是哪个阶段确定的？](#29)
    - [30）、Spark中RDD的弹性提现在哪里？](#30)
    - [31）、Spark中容错机制？](#31)
    - [32）、Spark中RDD的缺陷？](#32)
    - [33）、Spark中有哪些聚合类的算子？应该避免什么类型的算子?](#33)
    - [34）、Spark中并行度怎么设置比较合理一些？](#34)
    - [35）、Spark中数据的位置由谁来管理？](#35)
    - [36）、Spark中数据本地性有哪几种？](#36)
    - [37）、Spark如何处理不被序列化的数据？](#37)
    - [38）、Spark中collect功能是啥？其底层是如何实现的?](#38)
    - [39）、Spark作业在没有获得足够资源就开始启动了,可能会导致什么问题？](#39)
    - [40）、Spark中map和flatmap有啥区别？](#40)
    - [41）、介绍一下join操作优化经验？](#41)
    - [42）、Spark有哪些组件？](#42)
    - [43）、Spark的工作机制？](#43)
    - [44）、Spark中的宽窄依赖？](#44)
    - [45）、Spark如何划分stage？](#45)
    - [46）、spark-submit时候如何引用外部的jar包？](#46)
    - [47）、Spark中RDD有哪些特性？](#47)
    - [48）、Spark的一个工作流程？](#48)
    - [49）、Spark on yarn与standalone区别？](#49)
    - [50）、Spark优化之内存管理？](#50)
    - [51）、Spark优化之广播变量？](#51)
    - [52）、Spark优化之数据本地性？](#52)
    - [53）、Spark中task有几种类型？](#53)

###### <span id="1">1.Spark作业提交流程？</span>
    
###### <span id="2">2.Spark的内存模型？</span>

###### <span id="3">3.SparkContext创建流程？源码级别？</span>

###### <span id="4">4.简述Spark个版本区别？1.x与2.x？</span>

###### <span id="5">5.使用Spark中遇到过哪些问题？如何解决的？</span>

###### <span id="6">6.Spark的Shuffle过程？ 和MR Shuffle区别？</span>

###### <span id="7">7.Spark中的数据倾斜问题有啥好的解决方案？</span>

###### <span id="8">8.Spark有哪些聚合类的算子，我们应该怎么避免使用这些算子？ReduceByKey和GroupByKey的区别？</span>

###### <span id="9">9.Spark On Yarn作业执行流程？yarn-client和yarn-cluster的区别？</span>

###### <span id="10">10.Spark中Job、Task、RDD、DAG、Stage的理解？</span>

###### <span id="11">11.Spark中RDD如何通过记录更新的方式容错？</span>

###### <span id="12">12.Spark常用调优方法？</span>

###### <span id="13">13.Spark中宽依赖和窄依赖如何理解？</span>

###### <span id="14">14.Spark中Job和Task如何理解？</span>

###### <span id="15">15.Spark中Transformation和action区别是什么？列举出常用的方法？</span>

###### <span id="16">16.Spark中persist()和cache()的区别？</span>

###### <span id="17">17.Spark中map和mapPartitions的区别？</span>

###### <span id="18">18.Spark中Worker和Executor的异同？</span>

###### <span id="19">19.Spark中提供的2中共享变量是啥？</span>

###### <span id="20">20.菲波那切数列可以用Spark做出来么？</span>

###### <span id="21">21.看过哪些Spark源码？</span>

###### <span id="22">22.Spark通信机制？</span>

###### <span id="23">23.Spark的存储级别有哪些？</span>

###### <span id="24">24.Spark序列化模式有哪些？</span>

###### <span id="25">25.Spark使用到的安全协议有哪些？</span>

###### <span id="26">26.Spark部署模式有哪些？</span>

###### <span id="27">27.Spark的cache后能不能接其它算子？是不是action操作?</span>

###### <span id="28">28.Spark中reduceByKey是action算子不？reduec呢?</span>

###### <span id="29">29.Spark中数据本地性是哪个阶段确定的？</span>

###### <span id="30">30.Spark中RDD的弹性提现在哪里？</span>

###### <span id="31">31.Spark中容错机制？</span>

###### <span id="32">32.Spark中RDD的缺陷？</span>

###### <span id="33">33.Spark中有哪些聚合类的算子？应该避免什么类型的算子?</span>

###### <span id="34">34.Spark中并行度怎么设置比较合理一些？</span>

###### <span id="35">35.Spark中数据的位置由谁来管理？</span>

###### <span id="36">36.Spark中数据本地性有哪几种？</span>

###### <span id="37">37.Spark如何处理不被序列化的数据？</span>

###### <span id="38">38.Spark中collect功能是啥？其底层是如何实现的?</span>

###### <span id="39">39.Spark作业在没有获得足够资源就开始启动了,可能会导致什么问题？</span>

###### <span id="40">40.Spark中map和flatmap有啥区别？</span>

###### <span id="41">41.介绍一下join操作优化经验？</span>

###### <span id="42">42.Spark有哪些组件？</span>

###### <span id="43">43.Spark的工作机制？</span>

###### <span id="44">44.Spark中的宽窄依赖？</span>

###### <span id="45">45.Spark如何划分stage？</span>

###### <span id="46">46.spark-submit时候如何引用外部的jar包？</span>

###### <span id="47">47.Spark中RDD有哪些特性？</span>

###### <span id="48">48.Spark的一个工作流程？</span>

###### <span id="49">49.Spark on yarn与standalone区别？</span>

###### <span id="50">50.Spark优化之内存管理？</span>

###### <span id="51">51.Spark优化之广播变量？</span>

###### <span id="52">52.Spark优化之数据本地性？</span>

###### <span id="53">53.Spark中task有几种类型？</span>


