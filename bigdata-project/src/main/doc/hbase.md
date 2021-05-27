* [9.7、HBase](bigdata-project/src/main/doc/hbase.md)
    - [1）、HBase是什么？特点有哪些？]()
    - [2）、HBase和Hive的区别？]()
    - [3）、HBase的rowKey如何设计？]()
    - [4）、简述HBase架构模块？]()
    - [5）、描述HBase中一个cell结构？]()
    - [6）、HBase中compact用途是什么？什么时候触发？分为哪两种？有什么区别？有哪些相关参数？]()
    - [7）、HBase优化？]()
    - [8）、HBase如何建立预分区？]()
    - [9）、HBase中HRegionServer宕机如何处理？]()
    - [10）、HBase中scan和get的功能以及实现的异同？]()
    - [11）、HBase读流程？]()
    - [12）、HBase写流程？]()
    - [13）、HBase内部机制是什么？]()
    - [14）、HBase在进行模型设计时重点在什么地方？一张表定义多个Column Family最合适？为什么？]()
    - [15）、如何提高HBase客户端的读写性能？]()
    - [16）、直接将时间戳作为行键存储在HBase中，在写入单个Region时候会发生热点问题，为什么？]()
    - [17）、请描述如何解决HBase中Region太大和Region太小带来的冲突？]()
    - [18）、简述下布隆过滤器的原理？HBase中如何使用的？]()
    - [19）、简述下LSM树的原理？HBase中如何使用的？]()
    - [20）、HBase中二级索引原理？有使用过么？]()
    - [21）、HBase有put方法，那如何批量进HBase中？用什么方法？]()
    - [22）、访问HBase有哪些方式？]()
    - [23）、HBase中最小存储单元是什么？]()
    - [24）、HBase中的MemStore是用来做什么的？]()
    - [25）、HBase中scan对象的setCache和setBatch方法的使用？]()
    - [26）、每天百亿数据存入HBase，如何保证数据的存储正确以及在规定时间里全部录入完毕，不残留数据？]()
    - [27）、HBase的RowFilter和BloomFilter原理？]()
---
###### [1）、HBase是什么？特点有哪些？]()
    hbase是一个分布式的、面向列的开源数据库，它是一个适合于非结构化数据存储的数据库。
    hbase的特点是：1、海量存储；2、列式存储；3、极易扩展；4、高并发；5、稀疏。
    
    1.hbase是一个分布式的，基于列式存储的数据库，基于hadoop的hdfs存储，zookeeper进行管理。
    2.hbase 适合存储半结构化或非结构化的数据，比如数据结构字段不够确定或者杂乱无章很难按照一个概念去抽取的数据。
    3.hbase的存储效率比较高，为null的数据不会被存储。
    4.hbase的表包含rowKey、列族和列，存储数据的最小单元是单元格，单元格包含数据及其对应的写入时间戳，新写入数据时，附带写入时间戳，可以查询到之前写入的多个版本。

###### [2）、HBase和Hive的区别？]()
###### [3）、HBase的rowKey如何设计？]()
###### [4）、简述HBase架构模块？]()
###### [5）、描述HBase中一个cell结构？]()
    cell：由{row key, column(=<CF> + <CQ>), version}唯一确定的单元，cell中的数据是没有类型的，全部是字节码形式存储。

###### [6）、HBase中compact用途是什么？什么时候触发？分为哪两种？有什么区别？有哪些相关参数？]()
    在 hbase 中每当有 memstore 数据 flush 到磁盘之后，就形成一个 storefile，当 storeFile 的数量达到一定程度后，就需要将storefile文件来进行 compaction 操作。
    
    Compact 的作用：
    1>.合并文件
    2>.清除过期，多余版本的数据
    3>.提高读写数据的效率
    HBase 中实现了两种 compaction 的方式：minor and major。
    
    1、 Minor 操作只用来做部分文件的合并操作以及包括 minVersion=0，并且设置 ttl 的过期版本清理，不做任何删除数据、多版本数据的清理工作。
    2、 Major 操作是对 Region 下的 HStore 下的所有 StoreFile 执行合并操作，最终的结果是整理合并出一个文件。

###### [7）、HBase优化？]()
###### [8）、HBase如何建立预分区？]()
###### [9）、HBase中HRegionServer宕机如何处理？]()
###### [10）、HBase中scan和get的功能以及实现的异同？]()
    Get的功能是精准查找，按指定RowKey 获取唯一一条记录。
    Scan的功能是范围查找，按指定的条件获取一批记录。
    实际上它们的实现是一样的，get操作就是一种特殊的scan（begin和end相同的scan操作）。而且hbase读数据的操作都是scan，代码级别实现的是scan，并没有特别针对get的操作。

###### [11）、HBase读流程？]()
    ① HRegionServer保存着meta表以及表数据，要访问表数据，首先Client先去访问zookeeper，从zookeeper里面获取meta表所在的位置信息，即找到这个meta表在哪个HRegionServer上保存着。
    ② 接着 Client 通过刚才获取到的 IP 访问对应的HRegionServer，获取到 Meta 表中存放的元数据。
    ③ Client 通过元数据中存储的信息，访问对应的 HRegionServer，然后扫描所在HRegionServer 的 Memstore 和 Storefile 来查询数据。
    ④ 最后 HRegionServer 把查询到的数据响应给 Client。

###### [12）、HBase写流程？]()
    ① Client 先访问 zookeeper，找到 Meta 表，并获取 Meta 表元数据。
    ② 确定当前将要写入的数据所对应的 HRegion 和 HRegionServer 服务器。
    ③ Client 向该 HRegionServer 服务器发起写入数据请求，然后 HRegionServer 收到请求并响应。
    ④ Client 先把数据写入到 HLog，以防止数据丢失。
    ⑤ 然后将数据写入到 Memstore。
    ⑥ 如果 HLog 和 Memstore 均写入成功，则这条数据写入成功
    ⑦ 如果 Memstore 达到阈值，会把 Memstore 中的数据 flush 到 Storefile 中。
    ⑧ 当 Storefile 越来越多，会触发 Compact 合并操作，把过多的 Storefile 合并成一个大的 Storefile。
    ⑨ 当 Storefile 越来越大，Region 也会越来越大，达到阈值后，会触发 Split 操作，将Region 一分为二。

###### [13）、HBase内部机制是什么？]()
###### [14）、HBase在进行模型设计时重点在什么地方？一张表定义多个Column Family最合适？为什么？]()
###### [15）、如何提高HBase客户端的读写性能？]()
###### [16）、直接将时间戳作为行键存储在HBase中，在写入单个Region时候会发生热点问题，为什么？]()
###### [17）、请描述如何解决HBase中Region太大和Region太小带来的冲突？]()
###### [18）、简述下布隆过滤器的原理？HBase中如何使用的？]()
###### [19）、简述下LSM树的原理？HBase中如何使用的？]()
###### [20）、HBase中二级索引原理？有使用过么？]()
###### [21）、HBase有put方法，那如何批量进HBase中？用什么方法？]()
###### [22）、访问HBase有哪些方式？]()
###### [23）、HBase中最小存储单元是什么？]()
    HBase 会对表按行进行切分，划分为多个区域块儿，每个块儿名为 HRegion
    HBase 是集群结构，会把这些块儿分散存储到多个服务器中，每个服务器名为 HRegionServer
    HRegion 是 HBase 中分布式存储的最小单元，但并不是存储的最小单元
    HRegion 内部会按照列族进行切分，分为多个 Store，每个 Store 保存一个列族，所以 HRegion 由一个或者多个 Store 组成
    每个 Strore 又由一个 MemStore 和 N个 StoreFile 组成
    MemStore 是内存存储单元，当内存中数据达到阈值后，写入 StoreFile，StoreFile 以 HFile 格式保存

###### [24）、HBase中的MemStore是用来做什么的？]()
###### [25）、HBase中scan对象的setCache和setBatch方法的使用？]()
###### [26）、每天百亿数据存入HBase，如何保证数据的存储正确以及在规定时间里全部录入完毕，不残留数据？]()
###### [27）、HBase的RowFilter和BloomFilter原理？]()
