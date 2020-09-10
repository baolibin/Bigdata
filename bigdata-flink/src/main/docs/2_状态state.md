
##### 1、State和CheckPoint
    State一般指一个具体的Task/Operator的状态，State数据默认保存在Java的堆内存中。
    而CheckPoint（可以理解为CheckPoint是把State数据持久化存储了）则表示了一个Flink Job在一个特定时刻的一份
    全局状态快照，即包含了所有Task/Operator的状态。
    
    Task是Flink中执行的基本单位，Operator是算子（Transformation）。

##### 2、Flink state类型
    State可以被记录，在失败的情况下数据还可以恢复。Flink中有以下两种基本类型的State。
    Keyed State。
    Operator State。
    Keyed State和Operator State以两种形式存在。

    原始状态（Raw State）：由用户自行管理状态具体的数据结构，框架在做CheckPoint的时候，使用byte[]读写状态内容，对其内部数据结构一无所知。
    托管状态（Managed State）：由Flink框架管理的状态。通常在DataStream上推荐使用托管状态，当实现一个用户自定义的Operator时使用到原始状态。
