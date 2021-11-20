

* Flnik Table API & SQL
  - [Flnik Table API & SQL原理]()
  - [Flnik Table API & SQL使用场景]()
  - [Flnik Table API & SQL使用API]()
  - [Flnik Table API & SQL调优]()
  - [Flnik Table API & SQL源码]()


###### [1、Flink Table API & SQL概述？是什么？]()
    Apache Flink提供了两种顶层的关系型API，分别为Table API和SQL，Flink通过Table API&SQL实现了批流统一。
    Table API是用于Scala和Java的语言集成查询API，它允许以非常直观的方式组合关系运算符（例如select，where和join）的查询。
    Flink SQL基于Apache Calcite 实现了标准的SQL，用户可以使用标准的SQL处理数据集。
    Table API和SQL与Flink的DataStream和DataSet API紧密集成在一起，用户可以实现相互转化，比如可以将DataStream或者DataSet注册为table进行操作数据。

###### [2、Flink Table API & SQL的planner？]()
    从Flink1.9开始，Flink为Table & SQL API提供了两种planner,分别为Blink planner和old planner，其中old planner是在Flink1.9之前的版本使用。

###### [3、Flink Table API & SQL的编程模板？]()
    所有的Table API&SQL的程序(无论是批处理还是流处理)都有着相同的形式，下面将给出通用的编程结构形式：
    
    // 创建一个TableEnvironment对象，指定planner、处理模式(batch、streaming)
    TableEnvironment tableEnv = ...; 
    // 创建一个表
    tableEnv.connect(...).createTemporaryTable("table1");
    // 注册一个外部的表
    tableEnv.connect(...).createTemporaryTable("outputTable");
    // 通过Table API的查询创建一个Table 对象
    Table tapiResult = tableEnv.from("table1").select(...);
    // 通过SQL查询的查询创建一个Table 对象
    Table sqlResult  = tableEnv.sqlQuery("SELECT ... FROM table1 ... ");
    // 将结果写入TableSink
    tapiResult.insertInto("outputTable");
    // 执行
    tableEnv.execute("java_job");
    
    Table API & SQL的查询可以相互集成，另外还可以在DataStream或者DataSet中使用Table API & SQL的API，实现DataStreams、 DataSet与Table之间的相互转换。

###### [4、Flink Table API & SQL的TableEnvironment？]()
    TableEnvironment是Table API & SQL程序的一个入口，主要包括如下的功能：
    1).在内部的catalog中注册Table
    2).注册catalog
    3).加载可插拔模块
    4).执行SQL查询
    5).注册用户定义函数
    6).DataStream 、DataSet与Table之间的相互转换
    7).持有对ExecutionEnvironment 、StreamExecutionEnvironment的引用

###### [5、catalog是什么？]()
    
* [apache calcite官网地址](https://calcite.apache.org)


###### [6、catalog中创建临时表与永久表？]()
    表分为临时表和永久表：
        1).永久表需要一个catalog(比如Hive的Metastore)来维护表的元数据信息，一旦永久表被创建，
    只要连接到该catalog就可以访问该表，只有显示删除永久表，该表才可以被删除。
        2).临时表的生命周期是Flink Session，这些表不能够被其他的Flink Session访问，这些表不属于任何的catalog或者数据库，
    如果与临时表相对应的数据库被删除了，该临时表也不会被删除。


###### [7、catalog中创建虚表(Virtual Tables)？]()
    一个Table对象相当于SQL中的视图(虚表)，它封装了一个逻辑执行计划，可以通过一个catalog创建。
    
    // 获取一个TableEnvironment
    TableEnvironment tableEnv = ...; 
    // table对象，查询的结果集
    Table projTable = tableEnv.from("X").select(...);
    // 注册一个表，名称为 "projectedTable"
    tableEnv.createTemporaryView("projectedTable", projTable);

###### [8、catalog中创建外部数据源表(Connector Tables)？]()
    可以把外部的数据源注册成表，比如可以读取MySQL数据库数据、Kafka数据等。
    
    tableEnvironment
    .connect(...)
    .withFormat(...)
    .withSchema(...)
    .inAppendMode()
    .createTemporaryTable("MyTable")

###### [9、查询表Table API方式？]()
    Table API是一个集成Scala与Java语言的查询API，与SQL相比，它的查询不是一个标准的SQL语句，而是由一步一步的操作组成的。
    
    // 获取TableEnvironment
    TableEnvironment tableEnv = ...;
    //注册Orders表
    tableEnv.connect(...).createTemporaryTable("Orders");
    // 查询注册的表
    Table orders = tableEnv.from("Orders");
    // 计算操作
    Table revenue = orders
      .filter("cCountry === 'FRANCE'")
      .groupBy("cID, cName")
      .select("cID, cName, revenue.sum AS revSum");

###### [10、查询表SQL方式？]()
    Flink SQL依赖于Apache Calcite，其实现了标准的SQL语法。
    
    // 获取TableEnvironment
    TableEnvironment tableEnv = ...;
    //注册Orders表
    // 计算逻辑同上面的Table API
    Table revenue = tableEnv.sqlQuery(
        "SELECT cID, cName, SUM(revenue) AS revSum " +
        "FROM Orders " +
        "WHERE cCountry = 'FRANCE' " +
        "GROUP BY cID, cName"
      );
    // 注册"RevenueFrance"外部输出表
    // 计算结果插入"RevenueFrance"表
    tableEnv.sqlUpdate(
        "INSERT INTO RevenueFrance " +
        "SELECT cID, cName, SUM(revenue) AS revSum " +
        "FROM Orders " +
        "WHERE cCountry = 'FRANCE' " +
        "GROUP BY cID, cName"
      );

###### [11、Table API & SQL底层的转换与执行，Old planner？]()
    Flink提供了两种planner，分别为old planner和Blink planner，对于不同的planner而言，Table API & SQL底层的执行与转换是有所不同的。
    Old planner根据是流处理作业还是批处理作业，Table API &SQL会被转换成DataStream或者DataSet程序。
    一个查询在内部表示为一个逻辑查询计划，会被转换为两个阶段:
    1）.逻辑查询计划优化
    2）.转换成DataStream或者DataSet程序

###### [12、Table API & SQL底层的转换与执行 Blink planner？]()
    无论是批处理作业还是流处理作业，如果使用的是Blink planner，底层都会被转换为DataStream程序。
    在一个查询在内部表示为一个逻辑查询计划，会被转换成两个阶段：
    1）.逻辑查询计划优化
    2）.转换成DataStream程序


###### [13、查询优化，Old planner？]()



###### [14、查询优化，Blink planner？]()



