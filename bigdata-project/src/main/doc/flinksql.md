

* [FlinkSQL]()
    - [1）、Flink sql简介？]()
    - [2）、Flink sql join？]()
    - [3）、Flink内部的SQL引擎模块？]()


###### [1）、Flink sql简介？]()
    Flink Table & SQL API是在DataStream和DataSet之上封装的一层高级API。

###### [2）、Flink sql join？]()


###### [3）、Flink内部的SQL引擎模块？]()
    Calcite作为一个强大的SQL计算引擎，在Flink内部的SQL引擎模块就是基于Calcite。
    
    Table & SQL API基于scala和java编写，内部基于calcite实现标准sql的解析和校验。跟spark不一样，flink直接基于开源的calcite编写。
    calcite本身是一个apache的开源项目，它独立于存储和执行，专门负责sql的解析优化、语法树的校验等，并且通过插件的方式可以很方便的扩展优化规则，广泛的应用在hive、solr、flink等中。

###### [4）、]()


