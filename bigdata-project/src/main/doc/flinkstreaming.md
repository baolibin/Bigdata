### [9.4、Flink Streaming]()

---
###### [1）、Flink如何保证数据仅且消费一次？]()
    Flink是由source --> transformation --> sink三部分组成；也就是说Flink要实现ExactlyOnce语义与这三者脱不了干系；
    
    数据源Source，首先要支持ExactlyOnce，比如Kafka;
    接着需要对Flink开启checkpoint机制；即开启状态后端，保存其偏移量及中间状态数据；
    Sink端需要支持写覆盖也就是我们经常说的幂等性或者支持事务（两阶段提升）
    
    Flink读取Kafka数据中的checkpoint具体操作如下:
         使用FlinkKafkaConsumer，开启CheckPointing，偏移量会保存通过CheckPoint保存到StateBackend中，并且默认会将偏移量写入Kafka的特殊topic中，即：__consumer_offsets
         FlinkKafkaConsumr的setCommitOffsetsOnCheckpoints参数默认true，即将偏移量写入到Kafka特殊的Topic中，
         目的是为了监控或重启任务没有指定savePoint时可以接着一起的偏移量继续消费并且设置CheckpointingMode.EXACTLY_ONCE
    Barrier【隔离带】可以保证一个流水线中的所有算子都处理成功了，才会对该条数据做CheckPoint
    存储系统不支持覆盖的话，就得要支持事务，成功了提交事务和更新偏移量，如果失败可以回滚且不更新偏移量
    
###### [2）、Flink如何做checkPoint检查点？分布式快照原理是啥?]()
    Flink使用的检查点算法是分布式快照算法（基于Chandy-Lamport算法的分布式快照），将检查点的保存和数据分开处理，不需要暂停整个应用。
    
    检查点分界线(checkpoint barrier):
    Flink检查点算法用到了一种称为分界线(barrier)的特殊数据格式,用来把一条流上数据按照不同的检查点分开.
    分界线之前到来的数据导致的状态修改,都被包含在当前分界线所属的检查点中,而基于分界线之后的数据导致的所有修改,都被包含在之后的检查点中.

###### [3）、Flink程序消费过慢如何解决？]()
###### [4）、统计实时流中某一单词出现的总个数（eg：比如一天某商品被点击的PV）？](bigdata-flink/src/main/scala/com/libin/data/flink/streaming/etl/GenCodeFromState.scala)
###### [5）、Flink中时间有几种？]()
    对于流式数据处理，最大的特点就是数据具有时间的属性，Flink根据时间的产生位置分为三种类型:
    1)事件生成时间（Event Time）:
        事件时间是每个独立事件在产生它的设备上发生的时间，这个时间在事件进入Flink之前就已经嵌入到事件中，时间顺序取决于事件产生的地方，和下游数据处理系统的时间无关。
    2)事件注入时间（Ingestion Time）、
        注入时间是数据进入Flink系统的时间，接入时间依赖Source Operator 所在主机的系统时钟。
        因为接入时间在数据接入过程生成后，时间戳不在发生变化，和后续处理数据的Operator所在机器的时钟没有关系，所以不会因为某台机器时钟不同步或网络延迟而导致计算结果不准确的问题。
    3)事件处理时间(Processing Time)。
        处理时间是指数据在操作算子计算过程中获取到的所在主机时间。当用户选择使用Processing Time 时，所有和时间相关的计算算子，
        例如Linux计算,在当前的任务中所有算子将直接使用其所在主机的系统时间。
    在Flink 中默认情况下使用Processing Time ,如果想用其它的时间类型，则在创建StreamExecutionEnvironment 中调用setStreamTimeCharacteristic()设定时间概念。

###### [6）、Flink中窗口有几种？]()
    滑动窗口（Tumbling Window，无重叠），滚动窗口（Sliding Window，有重叠），和会话窗口（Session Window，活动间隙）。
    
    1)、滚动时间窗口（Tumbling Time Window）
        翻滚窗口能将数据流切分成不重叠的窗口，每一个事件只能属于一个窗口。
        比如：统计每一分钟中用户购买的商品的总数，需要将用户的行为事件按每一分钟进行切分。
    2)、滚动计数窗口（Tumbling Count Window）
        滚动计数窗口会对窗口进行计算。
        比如：想要每100个用户购买行为事件统计购买总数，那么每当窗口中填满100个元素了，就会对窗口进行计算。   
    3)、滑动时间窗口（Sliding Time Window）
        滑动时间窗口是不间断的，需要平滑地进行窗口聚合，一个元素可以对应多个窗口。
        比如：我们可以每30秒计算一次最近一分钟用户购买的商品总数。 
    4)、滑动计数窗口（Sliding Count Window）
        每次滑动指定步数，然后做聚合统计。
        例如：计算每10个元素计算一次最近100个元素的购买总数。
    5)、会话窗口
        在用户交互事件流中，将事件聚合到会话窗口中（一段用户持续活跃的周期），由非活跃的间隙分隔开。
        比如：需要计算每个用户在活跃期间总共购买的商品数量，如果用户30秒没有活动则视为会话断开。
   
###### [7）、Flink中state如何理解？状态机制?]()
###### [8）、Flink中Operator是啥？]()
###### [9）、Flink中StreamExecutionEnvironment初始化流程？]()
###### [10）、用过DataStream里面的哪些方法？]()
###### [11）、Flink程序调优？]()
###### [12）、Flink如何解决数据乱序问题？Watermark使用过么?EventTime+Watermark可否解决数据乱序问题?]()
###### [13）、Flink的checkpoint存储有哪些(状态存储)？]()
###### [14）、Flink如何实现exactly-once？]()
###### [15）、海量key去重,双十一场景,滑动窗口长度为1小时,滑动距离为10s,亿级别用户,如何计算UV？]()
###### [16）、Flink的checkpoint和spark streaming比较？]()
###### [17）、Flink CEP编程中当状态没有达到时候,数据会保存在哪里？]()
###### [18）、3种时间语义？]()
###### [19）、Flink面对高峰数据如何处理？]()
###### [20）、Flink程序运行慢如何优化处理？]()
###### [21）、Flink程序延迟高如何解决？]()
###### [22）、Flink如何做容错？]()
###### [23）、Flink有没有重启策略？说说有哪几种?]()
###### [24）、Flink分布式快照原理是什么?]()
###### [25）、Flink的Kafka连接器有什么特别的地方?]()
###### [26）、Flink的内存管理?]()
###### [27）、Flink序列化都有哪些?怎么实现的?]()
###### [28）、Flink的window出现了数据倾斜,如何解决?]()
###### [29）、Flink在使用聚合函数GroupBy、KeyBy、Distinct等函数出现数据热点如何解决?]()
###### [30）、Flink如何处理反压?和spark streaming和storm区别有了解么?]()
###### [31）、Flink的Operator Chains算子链了解么?]()
###### [32）、Flink什么时候会把Operator Chain在一起行程算子链?]()
###### [33）、Flink1.7特性?Flink1.9特性]()
###### [34）、Flink组件栈有哪些?]()
###### [35）、Flink运行需要依赖哪些组件?必须依赖Hadoop么?]()
###### [36）、Flink基础编程模型?]()
###### [37）、Flink集群有哪些角色?各有什么作用?]()
###### [38）、Flink中Task Slot概念?Slot和parallelism区别?]()
###### [39）、Flink中常用算子有哪些?]()
###### [40）、Flink分区策略?]()
###### [41）、Flink并行度如何设置?]()
###### [42）、Flink分布式缓存用过没?如何使用?]()
###### [43）、Flink广播变量,使用时候需要注意什么?]()
###### [44）、Flink Table&SQL熟悉不?TableEnvironment这个类有什么作用?]()
###### [45）、Flink SQL实现原理是什么?如何实现SQL的解析?]()
###### [46）、Flink如何支持流批一体的?]()
###### [47）、Flink如何支如何做到高效的数据转换?]()
###### [48）、Flink如何做内存管理?]()
###### [49）、Flink Job提交流程?]()
###### [50）、Flink的三层图结构是哪几个图?]()
###### [51）、Flink中JobManager在集群中扮演的角色?]()
###### [52）、Flink中JobManager在集群启动中扮演的角色?]()
###### [53）、Flink中TaskManager在集群中扮演的角色?]()
###### [54）、Flink中TaskManager在集群启动时候扮演的角色?]()
###### [55）、Flink计算资源的调度是如何实现的?]()
###### [56）、简述Flink的数据抽象以及数据交换过程?]()
###### [57）、FlinkSQL的实现原理?]()
###### [58）、Flink压测和监控?]()
###### [59）、有了Spark为啥还要用Flink?]()
###### [60）、Flink的应用架构有哪些?]()
###### [61）、Flink Barrier对齐?]()
