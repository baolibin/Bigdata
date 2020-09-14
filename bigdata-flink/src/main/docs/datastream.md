
##### 1、DataStream简介
    DataStream提供了实时处理的API。
    DataStream API主要分为3块：DataSource、Transformation、Sink。

##### 1、DataStream Transformation算子  
    Flink针对DataStream提供了大量的已经实现的算子。
    Map：输入一个元素，然后返回一个元素，中间可以进行清洗转换等操作。
    FlatMap：输入一个元素，可以返回零个、一个或者多个元素。
    Filter：过滤函数，对传入的数据进行判断，符合条件的数据会被留下。
    KeyBy：根据指定的Key进行分组，Key相同的数据会进入同一个分区。KeyBy的两种典型用法如下。


