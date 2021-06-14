* [9.11、Java](java.md)
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
    - [25）、什么是值传递和引用传递？]()
    - [26）、可以在static环境中访问非static变量吗？]()
    - [27）、Java支持多继承么,为什么？]()
    - [28）、构造器是否可被重写？]()
    - [29）、char型变量中能不能存贮一个中文汉字，为什么？]()
    - [30）、如何实现对象克隆？]()
    - [31）、object中定义了哪些方法？]()
    - [32）、hashCode的作用是什么？]()
    - [34）、列举出JAVA中6个比较常用的包？]()
    - [35）、JDK 7有哪些新特性？]()
    - [36）、JDK 和 JRE 有什么区别？]()
    - [37）、说说你熟悉的设计模式有哪些？]()
    - [38）、在自己的代码中，如果创建一个java.lang.String类，这个类是否可以被类加载器加载？为什么？]()
    - [39）、java8的新特性?]()
    - [40）、switch是否能作用在byte 上，是否能作用在long 上，是否能作用在String上?]()
    - [41）、是否可以从一个静态（static）方法内部发出对非静态（non-static）方法的调用?]()
    
https://zhuanlan.zhihu.com/p/159331453

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
    Java为数据结构中的映射定义了一个接口java.util.Map，此接口主要有四个常用的实现类，分别是HashMap、Hashtable、LinkedHashMap和TreeMap。
    
    java1.7中hashMap底层实现为数组+链表
    java1.8中对上面进行优化，底层实现为数组+链表+红黑树
    
    HashMap的初始容量为16，Hashtable初始容量为11，两者的填充因子默认都是0.75。
    HashMap扩容时是当前容量翻倍即:capacity*2，Hashtable扩容时是容量翻倍+1即:capacity*2+1。
    
    Hashtable是线程安全，而HashMap则非线程安全。
    Hashtable的实现方法里面都添加了synchronized关键字来确保线程同步。
    平时使用时若无特殊需求建议使用HashMap，在多线程环境下若使用HashMap需要使用Collections.synchronizedMap()方法来获取一个线程安全的集合。

###### [7）、描述动态代理的几种实现方式？]()
    JDK原生动态代理：
        java动态代理是利用反射机制生成一个实现代理接口的匿名类，在调用具体方法前调用InvokeHandler来处理。
        JDK原声动态代理时java原声支持的、不需要任何外部依赖、但是它只能基于接口进行代理
    CGLIB动态代理：
        利用asm开源包，对代理对象类的class文件加载进来，通过修改其字节码生成子类来处理。
        CGLIB通过继承的方式进行代理、无论目标对象没有没实现接口都可以代理，但是无法处理final的情况

###### [8）、Java的反射中，Class.forName和ClassLoader区别？]()
    java类装载过程分3步：加载、链接(校验、准备、解析)、初始化。
    
    1、加载
    Jvm把class文件字节码加载到内存中，并将这些静态数据装换成运行时数据区中方法区的类型数据，
    在运行时数据区堆中生成一个代表这个类的java.lang.Class对象，作为方法区类数据的访问入口。
    *注：方法区不仅仅是存放方法，它存放的是类的类型信息。
    
    2、链接：执行下面的校验、准备和解析步骤，其中解析步骤是可选的
    a：校验：检查加载的class文件的正确性和安全性
    b：准备：为类变量分配存储空间并设置类变量初始值，类变量随类型信息存放在方法区中,生命周期很长，使用不当和容易造成内存泄漏。
    *注：类变量就是static变量；初始值指的是类变量类型的默认值而不是实际要赋的值
    c：解析：jvm将常量池内的符号引用转换为直接引用
    
    3、初始化：执行类变量赋值和静态代码块
    -----------------------------------------------
    在了解了类装载过程之后我们继续比较二者区别：
    Classloder.loaderClass(String name)
    其实该方法内部调用的是：Classloder. loadClass(name, false)
    方法：Classloder. loadClass(String name, boolean resolve)
    a：参数name代表类的全限定类名
    b：参数resolve代表是否解析，resolve为true是解析该类
    
    Class.forName(String name)
    其实该方法内部调用的是：Class.forName(className, true, ClassLoader.getClassLoader(caller))
    方法：Class.forName0(String name, boolean initialize, ClassLoader loader)
    参数name代表全限定类名
    参数initialize表示是否初始化该类，为true是初始化该类
    参数loader 对应的类加载器
    
    两者最大的区别
    Class.forName得到的class是已经初始化完成的
    Classloder.loaderClass得到的class是还没有链接的

###### [9）、Java中nio和bio区别？]()
    NIO的核心：Channel（通道），Buffer（缓存）， Selector（选择器）
    （1）BIO是面向流（Stream）的，NIO是面向缓存区（Buffer）的。


###### [10）、写出三种单例模式实现方式？]()
    7种：
    第一种（懒汉，线程不安全）
    第二种（懒汉，线程安全）
    第三种（饿汉）
    第四种（饿汉，变种）
    第五种（静态内部类）
    第六种（枚举）：
    第七种（双重校验锁）
    
    /**
     * 1、饿汉单例
     */
    class Single {
        private static Single s = new Single();
    
        private Single() {
        }
    
        public static Single getSingle() {
            return s;
        }
    }
    
    /**
     * 2、懒汉单例
     */
    class Single2 {
        private static Single2 single2;
    
        private Single2() {
        }
    
        public static Single2 getSingle2() {
            if (single2 == null) {
                single2 = new Single2();
            }
            return single2;
        }
    }
    
    /**
     * 3、双重检查
     */
    class Single3 {
        private static Single3 single3;
    
        private Single3() {
        }
    
        public static Single3 getSingle2() {
            if (single3 == null) {
                synchronized (Single3.class) {
                    if (single3 == null) {
                        single3 = new Single3();
                    }
                }
            }
            return single3;
        }
    }

###### [11）、垃圾回收机制？]()
    典型的垃圾收集算法：
    1、Mark-Sweep（标记-清除）算法
    标记-清除算法分为两个阶段：标记阶段和清除阶段。标记阶段的任务是标记出所有需要被回收的对象，清除阶段就是回收被标记的对象所占用的空间。
    有一个比较严重的问题就是容易产生内存碎片，碎片太多可能会导致后续过程中需要为大对象分配空间时无法找到足够的空间而提前触发新的一次垃圾收集动作。
    2、Copying（复制）算法
    将可用内存按容量划分为大小相等的两块，每次只使用其中的一块。当这一块的内存用完了，就将还存活着的对象复制到另外一块上面，
    然后再把已使用的内存空间一次清理掉，这样一来就不容易出现内存碎片的问题。
    很显然，Copying算法的效率跟存活对象的数目多少有很大的关系，如果存活对象很多，那么Copying算法的效率将会大大降低。
    3、Mark-Compact（标记-整理）算法
    在完成标记之后，它不是直接清理可回收对象，而是将存活对象都向一端移动，然后清理掉端边界以外的内存。
    4、Generational Collection（分代收集）算法
    分代收集算法是目前大部分JVM的垃圾收集器采用的算法。
    核心思想是根据对象存活的生命周期将内存划分为若干个不同的区域。一般情况下将堆区划分为老年代（Tenured Generation）和新生代（Young Generation），
    老年代的特点是每次垃圾收集时只有少量对象需要被回收，而新生代的特点是每次垃圾回收时都有大量的对象需要被回收，那么就可以根据不同代的特点采取最适合的收集算法。
    目前大部分垃圾收集器对于新生代都采取Copying算法，因为新生代中每次垃圾回收都要回收大部分对象，也就是说需要复制的操作次数较少。
    老年代的特点是每次回收都只回收少量对象，一般使用的是Mark-Compact算法。
    
    在堆区之外还有一个代就是永久代（Permanet Generation），它用来存储class类、常量、方法描述等。
    对永久代的回收主要回收两部分内容：废弃常量和无用的类。
    
    典型的垃圾收集器：
    1、Serial/Serial Old
    Serial/Serial Old收集器是最基本最古老的收集器，它是一个单线程收集器，并且在它进行垃圾收集时，必须暂停所有用户线程。
    2、ParNew
    ParNew收集器是Serial收集器的多线程版本，使用多个线程进行垃圾收集。
    3、Parallel Scavenge
    Parallel Scavenge收集器是一个新生代的多线程收集器（并行收集器），它在回收期间不需要暂停其他用户线程
    4、Parallel Old
    Parallel Old是Parallel Scavenge收集器的老年代版本（并行收集器），使用多线程和Mark-Compact算法。
    5、CMS
    CMS（Current Mark Sweep）收集器是一种以获取最短回收停顿时间为目标的收集器，它是一种并发收集器，采用的是Mark-Sweep算法。
    6、G1
    G1收集器是当今收集器技术发展最前沿的成果，它是一款面向服务端应用的收集器，它能充分利用多CPU、多核环境。
    因此它是一款并行与并发收集器，并且它能建立可预测的停顿时间模型。
    
###### [12）、Java的内存模型？]()
    程序计数器:程序计数器是一块较小的内存空间，它的作用可以看做是当前线程所执行的字节码的行号指示器。
    Java 虚拟机栈:虚拟机栈描述的是 Java 方法执行的内存模型：每个方法被执行的时候都会同时创建一个栈帧用于存储局部变量表、操作栈、动态链接、方法出口等信息。
    本地方法栈:本地方法栈为虚拟机使用到的 native 方法服务。
    Java 堆:Java 堆是被所有线程共享的一块内存区域，在虚拟机启动时创建。此内存区域的唯一目的就是存放对象实例，几乎所有的对象实例以及数组都要在堆上分配内存。
    方法区:各个线程共享的内存区域，它用于存储已被虚拟机加载的类信息、常量、静态变量、即时编译器编译后的代码等数据。
    运行时常量池:运行时常量池是方法区的一部分。Class 文件中除了有类的版本、字段、方法、接口等描述等信息外，还有一项信息是常量池，用于存放编译期生成的各种字面量和符号引用，这部分内容将在类加载后存放到方法区的运行时常量池中。

###### [13）、数组和链表数据结构描述？各自时间复杂度]()
    两种数据结构都是线性表，在排序和查找等算法中都有广泛的应用
    数组:是将元素在内存中连续存放，由于每个元素占用内存相同，可以通过下标迅速访问数组中任何元素。
    链表:中的元素在内存中不是顺序存储的，而是通过存在元素中的指针联系到一起。
    
    数组静态分配内存，链表动态分配内存；
    数组在内存中连续，链表不一定连续；
    数组元素在栈区，链表元素在堆区；(数组的值在栈中保存，数组变量本身在堆中保存。)
    
    数组利用下标定位，时间复杂度为O(1)，链表定位元素时间复杂度O(n)；
    数组插入或删除元素的时间复杂度O(n)，链表的时间复杂度O(1)。
    
    栈内存存储的是局部变量而堆内存存储的是实体；
    栈内存的更新速度要快于堆内存，因为局部变量的生命周期很短；
    栈内存存放的变量生命周期一旦结束就会被释放，而堆内存存放的实体会被垃圾回收机制不定时的回收。

###### [14）、Java1.5中引入了泛型，泛型的存在用来解决什么问题？]()
    Java泛型（generics）是 JDK 5中引入的一个新特性，其本质是参数化类型，解决不确定具体对象类型的问题。

###### [15）、Java的HashSet内部如何实现的？]()


###### [16）、Java中List、Set和Map之间的区别？]()
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

###### [18）、Java中synchronized和volatile区别？]()
###### [19）、Jvm中一次完整的GC流程？]()
    Java堆内存划分:
    Java堆 = 老年代 + 新生代
    新生代 = Eden + s0 + s1
    当 Eden 区的空间满了， Java虚拟机会触发一次 Minor GC，以收集新生代的垃圾，存活下来的对象，则会转移到 Survivor区。大对象直接进入老年代
    如果对象在Eden出生，并经过第一次Minor GC后仍然存活，并且被Survivor容纳的话，年龄设为1，每熬过一次Minor GC，年龄+1，若年龄超过一定限制（15），则被晋升到老年态。
    即长期存活的对象进入老年态。
    老年代满了而无法容纳更多的对象，Minor GC 之后通常就会进行Full GC，Full GC 清理整个内存堆 – 包括年轻代和年老代。
    Major GC 发生在老年代的GC，清理老年区，经常会伴随至少一次Minor GC，比Minor GC慢10倍以上。

###### [20）、Java中CurrentHashMap和HashMap的区别？]()
###### [21）、Java中int和Integer区别？]()
    1、Integer是int的包装类，int则是java的一种基本数据类型 
    2、Integer变量必须实例化后才能使用，而int变量不需要 
    3、Integer实际是对象的引用，当new一个Integer时，实际上是生成一个指针指向此对象；而int则是直接存储数据值 
    4、Integer的默认值是null，int的默认值是0

###### [22）、Java中内存溢出和内存泄漏区别？]()
    内存泄漏(Memory Leak)：指程序在申请内存后，无法释放已申请的内存空间，内存泄露堆积会导致内存被占光。
    内存溢出(out of memory)：指程序在申请内存时，没有足够的内存空间供其使用，出现 out of memory。

###### [23）、Java中进程和线程的区别？]()
    进程是具有一定独立功能的程序关于某个数据集合上的一次运行活动,进程是系统进行资源分配和调度的一个独立单位。
    线程是进程的一个实体,是CPU调度和分派的基本单位,它是 比进程更小的能独立运行的基本单位。

###### [24）、深拷贝和浅拷贝区别？]()
    深拷贝：除了对象本身被复制外，对象所包含的所有成员变量都会被复制，包括引用类型的成员对象。
    浅拷贝：只复制对象其中包含的值类型的成员变量，而引用类型的成员对象没有被复制。

###### [25）、什么是值传递和引用传递？]()
    值传递:是对基本型变量而言的,传递的是该变量的一个副本，改变副本不影响原变量.
    引用传递:一般是对于对象型变量而言的,传递的是该对象地址的一个副本, 并不是原对象本身 。所以对引用对象进行操作会同时改变原对象.

###### [26）、可以在static环境中访问非static变量吗？]()
    static变量在Java中是属于类的，它在所有的实例中的值是一样的。
    当类被Java虚拟机载入的时候，会对static变量进行初始化。
    因为静态的成员属于类，随着类的加载而加载到静态方法区内存，当类加载时，此时不一定有实例创建，没有实例，就不可以访问非静态的成员。
    类的加载先于实例的创建，因此静态环境中，不可以访问非静态！

###### [27）、Java支持多继承么,为什么？]()
    不支持多继承
    安全性的考虑:如果子类继承的多个父类里面有相同的方法或者属性，子类将不知道具体要继承哪个。
    Java提供了接口和内部类:以达到实现多继承功能，弥补单继承的缺陷。

###### [28）、构造器是否可被重写？]()
    构造器是不能被继承的，因为每个类的类名都不相同，而构造器名称与类名相同，所以谈不上继承。
    又由于构造器不能被继承，所以相应的就不能被重写了。

###### [29）、char型变量中能不能存贮一个中文汉字，为什么？]()
    在Java中，char类型占2个字节，而且Java默认采用Unicode编码，一个Unicode码是16位
    所以一个Unicode码占两个字节，Java中无论汉子还是英文字母都是用Unicode编码来表示的。
    所以，在Java中，char类型变量可以存储一个中文汉字。

###### [30）、如何实现对象克隆？]()
    实现 Cloneable 接口，重写 clone() 方法。
    Object 的 clone() 方法是浅拷贝，即如果类中属性有自定义引用类型，只拷贝引用，不拷贝引用指向的对象。
    对象的属性的Class 也实现 Cloneable 接口，在克隆对象时也手动克隆属性，完成深拷贝

###### [31）、object中定义了哪些方法？]()
    getClass(); 获取类结构信息
    hashCode() 获取哈希码
    equals(Object) 默认比较对象的地址值是否相等，子类可以重写比较规则
    clone() 用于对象克隆
    toString() 把对象转变成字符串
    notify() 多线程中唤醒功能
    notifyAll() 多线程中唤醒所有等待线程的功能
    wait() 让持有对象锁的线程进入等待
    wait(long timeout) 让持有对象锁的线程进入等待，设置超时毫秒数时间
    wait(long timeout, int nanos) 让持有对象锁的线程进入等待，设置超时纳秒数时间
    finalize() 垃圾回收前执行的方法

###### [32）、hashCode的作用是什么？]()
    hashCode的存在主要是用于查找的快捷性，如Hashtable，HashMap等，hashCode是用来在散列存储结构中确定对象的存储地址的；
    如果两个对象相同，就是适用于equals(java.lang.Object) 方法，那么这两个对象的hashCode一定要相同；
    如果对象的equals方法被重写，那么对象的hashCode也尽量重写
    两个对象的hashCode相同，并不一定表示两个对象就相同，也就是不一定适用于equals方法，只能够说明这两个对象在散列存储结构中.

###### [34）、列举出JAVA中6个比较常用的包？]()
    java.lang;
    java.util;
    java.io;
    java.sql;
    java.awt;
    java.net;

###### [35）、JDK 7有哪些新特性？]()
    Try-with-resource语句
    NIO2 文件处理Files
    switch可以支持字符串判断条件
    泛型推导
    多异常统一处理

###### [36）、JDK 和 JRE 有什么区别？]()
    JDK：Java Development Kit 的简称，Java 开发工具包，提供了 Java 的开发环境和运行环境。
    JRE：Java Runtime Environment 的简称，Java 运行环境，为 Java 的运行提供了所需环境。

###### [37）、说说你熟悉的设计模式有哪些？]()
    设计模式分为三大类：
    创建型模式：工厂方法模式、抽象工厂模式、单例模式、建造者模式、原型模式（5种）
    结构型模式：适配器模式、装饰者模式、代理模式、外观模式、桥接模式、组合模式、享元模式。（7种）
    行为型模式：策略模式、模板方法模式、观察者模式、迭代子模式、责任链模式、命令模式、备忘录模式、状态模式、访问者模式、中介者模式、解释器模式。（11种）

###### [38）、在自己的代码中，如果创建一个java.lang.String类，这个类是否可以被类加载器加载？为什么？]()
    不可以。
    因为JDK处于安全性的考虑，基于双亲委派模型，优先加载JDK的String类，如果java.lang.String已经加载，便不会再次被加载。

###### [39）、java8的新特性?]()
    Lambda 表达式：Lambda允许把函数作为一个方法的参数
    Stream API ：新添加的Stream API（java.util.stream） 把真正的函数式编程风格引入到Java中
    方法引用：方法引用提供了非常有用的语法，可以直接引用已有Java类或对象（实例）的方法或构造器。
    默认方法：默认方法就是一个在接口里面有了一个实现的方法。
    Optional 类 ：Optional 类已经成为 Java 8 类库的一部分，用来解决空指针异常。
    Date Time API ：加强对日期与时间的处理。

###### [40）、switch是否能作用在byte 上，是否能作用在long 上，是否能作用在String上?]()
    switch可作用于char byte short int
    switch可作用于char byte short int对应的包装类
    switch不可作用于long double float boolean，以及他们的包装类

###### [41）、是否可以从一个静态（static）方法内部发出对非静态（non-static）方法的调用?]()
    不可以。
    非static方法是要与对象实例息息相关的，必须在创建一个对象后，才可以在该对象上进行非static方法调用，
    而static方法跟类相关的，不需要创建对象，可以由类直接调用。
    
    当一个static方法被调用时，可能还没有创建任何实例对象，如果从一个static方法中发出对非static方法的调用，
    那个非static方法是关联到哪个对象上的呢？这个逻辑是不成立的

    一个static方法内部不可以发出对非static方法的调用。
