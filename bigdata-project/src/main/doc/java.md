* [9.11、Java](bigdata-project/src/main/doc/java.md)
    - [1）、说说Java中有哪些数据结构？]()
    - [2）、Java中有几种基本类型，各占多少字节？]()
    - [3）、Java中String可以被继承不？String、StringBuffer和StringBuilder有什么区别？]()
    - [4）、Java中ArrayList和LinkedList有什么区别？]()
    - [5）、Java中类的初始化顺序？]()
    - [6）、Java中HashMap内部实现原理？如何扩容？线程安全不？]()
    - [7）、描述动态代理的几种实现方式？]()
    - [8）、Java的反射中，Class.forName和ClassLoader区别？]()
    - [9）、Java中nio和bio区别？]()
    - [10）、写出三种单例模式实现方式？]()
    - [11）、垃圾回收机制？]()
    - [12）、Java的内存模型？]()
    - [13）、数组和链表数据结构描述？各自时间复杂度]()
    - [14）、Java1.5中引入了泛型，泛型的存在用来解决什么问题？]()
    - [15）、Java的HashSet内部如何实现的？]()
    - [16）、Java中List、Set和Map之间的区别？]()
    - [17）、Java中哪些集合类是线程安全的？]()
    - [18）、Java中synchronized和volatile区别？]()
    - [19）、Jvm中一次完整的GC流程？]()
    - [20）、Java中CurrentHashMap和HashMap的区别？]()
    - [21）、Java中int和Integer区别？]()
    - [22）、Java中内存溢出和内存泄漏区别？]()
    - [23）、Java中进程和线程的区别？]()
    - [24）、深拷贝和浅拷贝区别？]()
---
###### [1）、说说Java中有哪些数据结构？]()
    Java中数据结构主要体现在集合类中。
    Collection和Map接口
    Collection包括：List和Set接口
    
    Collection 接口的接口 对象的集合（单列集合） 
    ├——-List 接口：元素按进入先后有序保存，可重复 
    │—————-├ LinkedList 接口实现类， 链表， 插入删除， 没有同步， 线程不安全 
    │—————-├ ArrayList 接口实现类， 数组， 随机访问， 没有同步， 线程不安全 
    │—————-└ Vector 接口实现类 数组， 同步， 线程安全 
    │ ———————-└ Stack 是Vector类的实现类 
    └——-Set 接口： 仅接收一次，不可重复，并做内部排序 
    ├—————-└HashSet 使用hash表（数组）存储元素 
    │————————└ LinkedHashSet 链表维护元素的插入次序 
    └ —————-TreeSet 底层实现为二叉树，元素排好序
    
    Map 接口 键值对的集合 （双列集合） 
    ├———Hashtable 接口实现类， 同步， 线程安全 
    ├———HashMap 接口实现类 ，没有同步， 线程不安全- 
    │—————–├ LinkedHashMap 双向链表和哈希表实现 
    │—————–└ WeakHashMap 
    ├ ——TreeMap 红黑树对所有的key进行排序 
    └———IdentifyHashMap
    
    基于双向循环链表实现：LinkedList
    基于动态数组实现：ArrayList、Vector
    基于数组+单链表实现：HashMap、HashSet、Hashtable、currentHashMap
    基于红黑树实现：TreeMap、TreeSet
    基于哈希表+双向链表实现：LinkedHashMap、LinkedHashSet
    基于最小堆实现：PriorityQueue   
    

###### [2）、Java中有几种基本类型，各占多少字节？]()
    Java中有8种基本数据类型，分别为：
    6种数字类型 ：byte、short、int、long、float、double
    1种字符类型：char
    1种布尔型：boolean。
    这八种基本类型都有对应的包装类分别为：Byte、Short、Integer、Long、Float、Double、Character、Boolean

###### [3）、Java中String可以被继承不？String、StringBuffer和StringBuilder有什么区别？]()
    String、StringBuffer和StringBuilder都是final类,不允许被继承。
    
    String：适用于少量的字符串操作的情况
    StringBuilder：适用于单线程下在字符缓冲区进行大量操作的情况
    StringBuffer：适用多线程下在字符缓冲区进行大量操作的情况
    
    StringBuffer是线程安全，可以不需要额外的同步用于多线程中。
    StringBuilder是非同步,运行于多线程中就需要使用着单独同步处理，但是速度就比StringBuffer快多了。
    1）、速度快慢：StringBuilder > StringBuffer > String
    2）、在线程安全上，StringBuilder是线程不安全的，而StringBuffer是线程安全的
    如果一个StringBuffer对象在字符串缓冲区被多个线程使用时，StringBuffer中很多方法可以带有synchronized关键字，所以可以保证线程是安全的，
    但StringBuilder的方法则没有该关键字，所以不能保证线程安全，有可能会出现一些错误的操作。
    所以如果要进行的操作是多线程的，那么就要使用StringBuffer，但是在单线程的情况下，还是建议使用速度比较快的StringBuilder。
    

###### [4）、Java中ArrayList和LinkedList有什么区别？]()
    ArrayList底层存储是动态数组，线程不安全，可以根据下标直接查询元素，查询效率高 O(1)，添加和删除元素需要移动数组，效率低 O(n)。
    向数组尾部添加元素效率很快0(1),但是向数组中间添加和删除，会调用native方法System.arraycopy()进行数组的大量拷贝操作，效率很低O(n)。
    称之为动态数组原因为当存储元素超过线性表长度时候可以自动进行扩容，扩容后容量是扩容前1.5倍。
    Arraylist源码中最大的数组容量是Integer.MAX_VALUE-8，对于空出的8位，目前解释是 ：
    ①存储Headerwords；
    ②避免一些机器内存溢出，减少出错几率，所以少分配
    ③最大还是能支持到Integer.MAX_VALUE（当Integer.MAX_VALUE-8依旧无法满足需求时）。

    LinkedList底层是双向链表，数据添加和删除效率0(1)，只需改变prev和next指针，查询效率低O(n)，需要遍历数组。
    总结：
    1、对于查询get和修改set操作，ArrayList比LinkedList性能好，ArrayList根据下标直接操作O(1)，LinkedList需要遍历O(n)。
    2、对于新增add和删除remove操作，LinkedList比ArrayList性能好，ArrayList需要移动数据O(n)，LinkedList只需移动指针O(1)。

    public static native void arraycopy(Object src,int srcPos,Object dest, int destPos,int length)；
    * @param      src      the source array. 源数组
    * @param      srcPos   starting position in the source array. 源数组的起始位置
    * @param      dest     the destination array. 目标数组
    * @param      destPos  starting position in the destination data. 目标数组的起始位置
    * @param      length   the number of array elements to be copied. 复制的长度

###### [5）、Java中类的初始化顺序？]()
    1、静态变量
    2、静态变量初始化块
    3、变量
    4、初始化块
    5、构造器

###### [6）、Java中HashMap内部实现原理？如何扩容？线程安全不？]()
###### [7）、描述动态代理的几种实现方式？]()
###### [8）、Java的反射中，Class.forName和ClassLoader区别？]()
###### [9）、Java中nio和bio区别？]()
    NIO的核心：Channel（通道），Buffer（缓存）， Selector（选择器）
    （1）BIO是面向流（Stream）的，NIO是面向缓存区（Buffer）的。


###### [10）、写出三种单例模式实现方式？]()


###### [11）、垃圾回收机制？]()


###### [12）、Java的内存模型？]()


###### [13）、数组和链表数据结构描述？各自时间复杂度]()


###### [14）、Java1.5中引入了泛型，泛型的存在用来解决什么问题？]()


###### [15）、Java的HashSet内部如何实现的？]()


###### [16）、Java中List、Set和Map之间的区别？]()
###### [17）、Java中哪些集合类是线程安全的？]()
    1.集合类实现
    基于双向循环链表实现：LinkedList
    基于动态数组实现：ArrayList、Vector
    基于数组+单链表实现：HashMap、HashSet、Hashtable、currentHashMap
    基于红黑树实现：TreeMap、TreeSet
    基于哈希表+双向链表实现：LinkedHashMap、LinkedHashSet
    基于最小堆实现：PriorityQueue
    2.集合类是否线程安全
    线程安全：Hashtable(线程安全的HashMap)、Vector(线程安全的ArrayList)、currentHashMap(替代Hashtable)
    允许存放null值：
    list、map、set、vector等集合类型都是可以存放null的。
    不允许存放null值：
    Hashtable不允许null，Dictionary 线程安全 
    	ConcurrentHashMa不允许为null，AbstractMap 分段锁技术 
    	TreeMap不允许为null，AbstractMap线程不安全

    集合可以看作是一种容器，用来存储对象信息。
    所有集合类都位于java.util包下，但支持多线程的集合类位于java.util.concurrent包下。
    1）数组长度不可变化而且无法保存具有映射关系的数据；集合类用于保存数量不确定的数据，以及保存具有映射关系的数据。
    2）数组元素既可以是基本类型的值，也可以是对象；集合只能保存对象。
    Java集合类主要由两个根接口Collection和Map派生出来的，
    1）Collection派生出了三个子接口：List、Set、Queue（Java5新增的队列）:
    List代表了有序可重复集合，可直接根据元素的索引来访问；
    Set代表无序不可重复集合，只能根据元素本身来访问；
    Queue是队列集合；
    2）Map代表的是存储key-value对的集合，可根据元素的key来访问value。
    HashMap：无序存放，key不允许重复；
    TreeMap：有序存放，按照集合key值进行排序，key值不允许重复；
    IdentityHashMap：key值可以重复。

###### [18）、Java中synchronized和volatile区别？]()
###### [19）、Jvm中一次完整的GC流程？]()
###### [20）、Java中CurrentHashMap和HashMap的区别？]()
###### [21）、Java中int和Integer区别？]()
    1、Integer是int的包装类，int则是java的一种基本数据类型 
    2、Integer变量必须实例化后才能使用，而int变量不需要 
    3、Integer实际是对象的引用，当new一个Integer时，实际上是生成一个指针指向此对象；而int则是直接存储数据值 
    4、Integer的默认值是null，int的默认值是0

###### [22）、Java中内存溢出和内存泄漏区别？]()
###### [23）、Java中进程和线程的区别？]()
###### [24）、深拷贝和浅拷贝区别？]()
