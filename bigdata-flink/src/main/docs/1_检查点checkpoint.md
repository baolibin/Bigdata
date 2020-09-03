
##### 1、FLink Checkpoint是什么？
    checkpoint 是一种分布式快照，把state数据持久化存储了。
    可以理解为在某一时刻一个Flink Job在一个特定时刻的一份全局状态快照，即包含了所有task/operator的状态，
    这样，在任务进行故障恢复的时候，就可以还原到任务故障前最近一次检查点的状态，从而保证数据的一致性。当然，
    为了保证exactly-once/at-least-once 的特性，还需要数据源支持数据回放。
    Flink的checkpoint机制基于chandy-lamda算法。

#####2、Barriers是什么？
    flink 分布式快照的核心元素是 stream barriers,这些barriers被注入到流中，并作为流的一部分，随着流流动。
    barriers将数据流的记录分为进入当前快照的记录和进入下一个快照的记录，每个barriers都携带了快照的ID，
    快照的数据在barriers的前面推送。barriers非常轻量级，不会中断流的流动。同一时间，会有多个checkpoint在并发进行。
    2.1、单流的barrier
![单流的barrier](images/flink检查点.png)
    

