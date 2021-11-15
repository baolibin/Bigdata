

* Flnik Table API & SQL
  - [Flnik Table API & SQL原理]()
  - [Flnik Table API & SQL使用场景]()
  - [Flnik Table API & SQL使用API]()
  - [Flnik Table API & SQL调优]()
  - [Flnik Table API & SQL源码]()


###### [Flnik Table API & SQL原理]()
    Apache Flink提供了两种顶层的关系型API，分别为Table API和SQL，Flink通过Table API&SQL实现了批流统一。
    Table API是用于Scala和Java的语言集成查询API，它允许以非常直观的方式组合关系运算符（例如select，where和join）的查询。
    Flink SQL基于Apache Calcite 实现了标准的SQL，用户可以使用标准的SQL处理数据集。
    Table API和SQL与Flink的DataStream和DataSet API紧密集成在一起，用户可以实现相互转化，比如可以将DataStream或者DataSet注册为table进行操作数据。

