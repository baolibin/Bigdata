

##### Kafka面试题

1. Kafka消息是采用Pull模式,还是Push模式？
    Kafka的customer应该从brokes拉取消息还是brokers将消息推送到consumer,也就是pull还push。
    Kafka遵循了一种大部分消息系统共同的传统的设计：producer将消息推送到broker,consumer从broker拉取消息。

    消息系统都致力于让consumer以最大的速率最快速的消费消息,push模式下,当broker推送的速率远大于consumer消费的速率时,
    consumer恐怕就要崩溃了。最终 Kafka还是选取了传统的pull模式。
    Pull模式中consumer可以自主决定是否批量的从broker拉取数据。
    
    Pull有个缺点是,如果broker没有可供消费的消息,将导致consumer不断在循环中轮询,
    直到新消息到达。为了避免这点,Kafka 有个参数可以让consumer阻塞知道新消息到达。

2. Kafka一致性语义



