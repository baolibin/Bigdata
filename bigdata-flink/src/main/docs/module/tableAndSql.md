

* Flnik Table API & SQL
  - [Flnik Table API & SQL原理]()
  - [Flnik Table API & SQL使用场景]()
  - [Flnik Table API & SQL使用API]()
  - [Flnik Table API & SQL调优]()
  - [Flnik Table API & SQL源码]()


###### [1、Flnik Table API & SQL概述？是什么？]()
    Apache Flink提供了两种顶层的关系型API，分别为Table API和SQL，Flink通过Table API&SQL实现了批流统一。
    Table API是用于Scala和Java的语言集成查询API，它允许以非常直观的方式组合关系运算符（例如select，where和join）的查询。
    Flink SQL基于Apache Calcite 实现了标准的SQL，用户可以使用标准的SQL处理数据集。
    Table API和SQL与Flink的DataStream和DataSet API紧密集成在一起，用户可以实现相互转化，比如可以将DataStream或者DataSet注册为table进行操作数据。


###### [2、Flnik Table API & SQL的planner？]()
    从Flink1.9开始，Flink为Table & SQL API提供了两种planner,分别为Blink planner和old planner，其中old planner是在Flink1.9之前的版本使用。
    

###### [3、Flnik Table API & SQL的编程模板？]()
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




