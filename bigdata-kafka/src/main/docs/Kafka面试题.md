

##### Kafka面试题

###### 1. Kafka消息是采用Pull模式,还是Push模式？
    Kafka的customer应该从brokes拉取消息还是brokers将消息推送到consumer,也就是pull还push。
    Kafka遵循了一种大部分消息系统共同的传统的设计：producer将消息推送到broker,consumer从broker拉取消息。

    消息系统都致力于让consumer以最大的速率最快速的消费消息,push模式下,当broker推送的速率远大于consumer消费的速率时,
    consumer恐怕就要崩溃了。最终 Kafka还是选取了传统的pull模式。
    Pull模式中consumer可以自主决定是否批量的从broker拉取数据。
    
    Pull有个缺点是,如果broker没有可供消费的消息,将导致consumer不断在循环中轮询,
    直到新消息到达。为了避免这点,Kafka 有个参数可以让consumer阻塞知道新消息到达。

###### 2. Kafka一致性语义
    At Least Once：消息不丢,但可能重复
    At Most Once：消息会丢,但不会重复
    Exactly Once：消息不丢,也不重复。

###### 3. Kafka数据顺序
    一个消费者组里它的内部是有序的。
    消费者组与消费者组之间是无序的。

###### 4. Kafka中的ISR、AR代表什么？
    分区中的所有副本统称为AR（Assigned Replicas）。
    所有与leader副本保持一定程度同步的副本（包括leader副本在内）组成ISR（In-Sync Replicas），ISR集合是AR集合中的一个子集。
    与leader副本同步滞后过多的副本（不包括leader副本）组成OSR（Out-of-Sync Replicas），由此可见，AR=ISR+OSR。

###### 5. Kafka中的HW、LEO、LSO、LW等分别代表什么？


###### 6. Kafka中的分区器、序列化器、拦截器是否了解？它们之间的处理顺序是什么？


###### 7. 有哪些情况会造成重复消费?


###### 8. 有哪些情况会造成漏消费?


###### 9. 消费者提交消费位移时提交的是当前消费到的最新消息的offset还是offset+1?
 
###### 10. KafkaConsumer是非线程安全的，那么怎么样实现多线程消费？


###### 11. 当你使用kafka-topics.sh创建（删除）了一个topic之后，Kafka背后会执行什么逻辑？




