* [9.11、Java](java.md)
    - [1）、说说Java中有哪些数据结构？](#1java)
    - [2）、Java中有几种基本类型，各占多少字节？](#2java)
    - [3）、Java中String可以被继承不？String、StringBuffer和StringBuilder有什么区别？](#3javastringstringstringbufferstringbuilder)
    - [4）、Java中ArrayList和LinkedList有什么区别？](#4javaarraylistlinkedlist)
    - [5）、Java中类的初始化顺序？](#5java)
    - [6）、Java中HashMap内部实现原理？如何扩容？线程安全不？](#6javahashmap)
    - [7）、描述动态代理的几种实现方式？](#7)
    - [8）、Java的反射中，Class.forName和ClassLoader区别？](#8javaclassfornameclassloader)
    - [9）、Java中nio和bio区别？](#9javaniobio)
    - [10）、写出三种单例模式实现方式？](#10)
    - [11）、垃圾回收机制？](#11)
    - [12）、Java的内存模型？](#12java)
    - [13）、数组和链表数据结构描述？各自时间复杂度](#13)
    - [14）、Java1.5中引入了泛型，泛型的存在用来解决什么问题？](#14java15)
    - [15）、Java的HashSet内部如何实现的？](#15javahashset)
    - [16）、Java中List、Set和Map之间的区别？](#16javalistsetmap)
    - [17）、Java中哪些集合类是线程安全的？](#17java)
    - [18）、Java中synchronized和volatile区别？](#18javasynchronizedvolatile)
    - [19）、Jvm中一次完整的GC流程？](#19jvmgc)
    - [20）、CurrentHashMap、HashMap、HashTable、TreeMap的区别？](#20javacurrenthashmaphashmaphashtabletreemap)
    - [21）、Java中int和Integer区别？](#21javaintinteger)
    - [22）、Java中内存溢出和内存泄漏区别？](#22-java)
    - [23）、Java中进程和线程的区别？](#23java)
    - [24）、深拷贝和浅拷贝区别？](#24)
    - [25）、什么是值传递和引用传递？](#25)
    - [26）、可以在static环境中访问非static变量吗？](#26staticstatic)
    - [27）、Java支持多继承么,为什么？](#27java)
    - [28）、构造器是否可被重写？](#28)
    - [29）、char型变量中能不能存贮一个中文汉字，为什么？](#29char)
    - [30）、如何实现对象克隆？](#30)
    - [31）、object中定义了哪些方法？](#31object)
    - [32）、hashCode的作用是什么？](#32hashcode)
    - [34）、列举出JAVA中6个比较常用的包？](#34java6)
    - [35）、JDK 7有哪些新特性？](#35jdk-7)
    - [36）、JDK 和 JRE 有什么区别？](#36jdk--jre-)
    - [37）、说说你熟悉的设计模式有哪些？](#37)
    - [38）、在自己的代码中，如果创建一个java.lang.String类，这个类是否可以被类加载器加载？为什么？](#38javalangstring)
    - [39）、java8的新特性?](#39java8)
    - [40）、switch是否能作用在byte 上，是否能作用在long 上，是否能作用在String上?](#40switchbyte-long-string)
    - [41）、是否可以从一个静态（static）方法内部发出对非静态（non-static）方法的调用?](#41staticnon-static)
    - [42）、equals与==的区别?](#42equals)
    - [43）、final, finally, finalize 的区别?](#43final-finally-finalize-)
    - [44）、重载和重写的区别?](#44)
    - [45）、BIO、NIO、AIO 有什么区别?](#45bionioaio-)
    - [46）、String，StringBuffer，StringBuilder的区别?](#46stringstringbufferstringbuilder)
    - [47）、Comparator与Comparable有什么区别?](#47comparablecomparator)
    - [48）、说说反射的用途及实现原理，Java获取反射的三种方法?](#48java)
    - [49）、Java中IO流分为几种?](#49javaio)
    - [50）、Java创建对象有几种方式?](#50java)
    - [51）、守护线程是什么？用什么方法实现守护线程?](#51)
    - [52）、String s与new String与有什么区别?](#52string-snew-string)
    - [53）、反射中，Class.forName和ClassLoader的区别?](#53classfornameclassloader)
    - [54）、Java的类加载机制?]()
    - [55）、JVM调优?]()
    - [56）、数据库3范式?]()
    - [57）、rpc服务和http服务区别?]()
    - [58）、CurrentHashMap底层结构?]()
    - [59）、单例模式双重检测机制?]()
    - [60）、java字符串contains实现原理?]()
    - [61）、Java map的contains实现?]()
    - [62）、java1.8 currentHashMap原理?]()
    - [63）、java CAS机制?]()
    - [64）、java中的悲观锁与乐观锁?]()

---
###### [1）、说说Java中有哪些数据结构？]()
    Java中数据结构主要体现在集合类中。
    Collection和Map接口
    Collection包括：List和Set接口
    
    Collection 接口的接口 对象的集合（单列集合） 
    ├——-List 接口：元素按进入先后有序保存，可重复 
    │—————-├ LinkedList（双向循环链表） 接口实现类， 链表， 插入删除， 没有同步， 线程不安全 
    │—————-├ ArrayList（动态数组） 接口实现类， 数组， 随机访问， 没有同步， 线程不安全 
    │—————-└ Vector（动态数组） 接口实现类 数组， 同步， 线程安全 
    │ ———————-└ Stack 是Vector类的实现类 
    └——-Set 接口： 仅接收一次，不可重复
    ├—————-└HashSet（数组+单链表/红黑树） 使用hash表（数组）存储元素 
    │————————└ LinkedHashSet（哈希表+双向链表 链表维护元素的插入次序 
    └ —————-TreeSet 底层实现为红黑树，元素排好序
    
    Map 接口 键值对的集合 （双列集合） 
    ├———Hashtable（数组+单链表/红黑树） 接口实现类， 同步， 线程安全 
    ├———HashMap（数组+单链表/红黑树） 接口实现类 ，没有同步， 线程不安全- 
    │—————–├ LinkedHashMap（哈希表+双向链表） 双向链表和哈希表实现 
    │—————–└ WeakHashMap 
    ├ ——TreeMap 红黑树对所有的key进行排序 
    └———IdentifyHashMap
    
    基于双向循环链表实现：LinkedList
    基于动态数组实现：ArrayList、Vector
    基于数组+单链表/红黑树实现：HashMap、HashSet、Hashtable、currentHashMap
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
    扩容每次是2的幂，原因是(n-1)二进制都为1，更好的散列性
    
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
    是基于HashMap实现的，默认构造函数是构建一个初始容量为16，负载因子为0.75 的HashMap。
    封装了一个HashMap对象来存储所有的集合元素，所有放入HashSet中的集合元素实际上由HashMap的key来保存，而HashMap的value则存储了一个PRESENT它是一个静态的Object对象。

    例如：如果内部容量为16，装载因子为0.75，那么当表中有12个元素的时候，桶的数量就会自动增长。
    装载因子和初始化容量是影响HashSet操作的两个主要因素。装载因子为0.75的时候可以提供关于时间和空间复杂度方面更有效的性能。
    如果我们加大这个装载因子，那么内存的上限就会减小（因为它减少了内部重建的操作），但是将影响哈希表中的add与查询的操作。
    为了减少再哈希操作，我们应该选择一个合适的初始化大小。

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
    	ConcurrentHashMap不允许为null，AbstractMap 分段锁技术 
    	TreeMap不允许为null，AbstractMap线程不安全

    集合可以看作是一种容器，用来存储对象信息。
    所有集合类都位于java.util包下，但支持多线程的集合类位于java.util.concurrent包下。
    1）数组长度不可变化而且无法保存具有映射关系的数据；集合类用于保存数量不确定的数据，以及保存具有映射关系的数据。
    2）数组元素既可以是基本类型的值，也可以是对象；集合只能保存对象。

###### [18）、Java中synchronized和volatile区别？]()
    1、Java语言为了解决并发编程中存在的原子性、可见性和有序性问题，提供了一系列和并发处理相关的关键字，比如synchronized、volatile、final、concurren包等。
    2、synchronized通过加锁的方式，使得其在需要原子性、可见性和有序性这三种特性的时候都可以作为其中一种解决方案，看起来是“万能”的。
    3、volatile通过在volatile变量的操作前后插入内存屏障的方式，保证了变量在并发场景下的可见性和有序性。
    4、volatile关键字是无法保证原子性的，而synchronized通过monitorenter和monitorexit两个指令，可以保证被synchronized修饰的代码在同一时间只能被一个线程访问
    即可保证不会出现CPU时间片在多个线程间切换，即可保证原子性。

###### [19）、Jvm中一次完整的GC流程？]()
    Java堆内存划分:
    Java堆 = 老年代 + 新生代
    新生代 = Eden + s0 + s1
    当 Eden 区的空间满了， Java虚拟机会触发一次 Minor GC，以收集新生代的垃圾，存活下来的对象，则会转移到 Survivor区。大对象直接进入老年代
    如果对象在Eden出生，并经过第一次Minor GC后仍然存活，并且被Survivor容纳的话，年龄设为1，每熬过一次Minor GC，年龄+1，若年龄超过一定限制（15），则被晋升到老年态。
    即长期存活的对象进入老年态。
    老年代满了而无法容纳更多的对象，Minor GC 之后通常就会进行Full GC，Full GC 清理整个内存堆 – 包括年轻代和年老代。
    Major GC 发生在老年代的GC，清理老年区，经常会伴随至少一次Minor GC，比Minor GC慢10倍以上。

###### [20）、Java中CurrentHashMap、HashMap、HashTable、TreeMap的区别？]()
    四者均实现了Map接口，存储的内容是基于key-value的键值对映射，一个映射不能有重复的键，一个键最多只能映射一个值。
    
    从元素特性讲:
    1)、HashTable的key、value都不能为null；
    2）、HashMap中的key、value可以为null;
    3）、TreeMap中当未实现 Comparator 接口时，key 不可以为null；当实现 Comparator 接口时，若未对null情况进行判断，则key不可以为null，反之亦然；
    
    顺序特性:
    1）、HashTable、HashMap具有无序特性;
    2）、TreeMap是利用红黑树来实现的（树中的每个节点的值，都会大于或等于它的左子树种的所有节点的值，并且小于或等于它的右子树中的所有节点的值）,
    实现了SortMap接口，能够对保存的记录根据键进行排序。所以一般需要排序的情况下是选择TreeMap来进行，默认为升序排序方式（深度优先搜索），可自定义实现Comparator接口实现排序方式。
    
    初始化与增长方式：
    1）、初始化时：
    HashTable在不指定容量的情况下的默认容量为11，且不要求底层数组的容量一定要为2的整数次幂；
    HashMap默认容量为16，且要求容量一定为2的整数次幂。
    2）、扩容时：
    Hashtable将容量变为原来的2倍加1；
    HashMap扩容将容量变为原来的2倍。    
    
    线程安全：
    1）、HashTable其方法函数都是同步的（采用synchronized修饰），不会出现两个线程同时对数据进行操作的情况，因此保证了线程安全性；
    2）、HashMap不支持线程的同步，即任一时刻可以有多个线程同时写HashMap;可能会导致数据的不一致。
    3）、HashMap如果需要同步
    （1）、可以用 Collections的synchronizedMap方法；
    （2）、使用ConcurrentHashMap类，相较于HashTable锁住的是对象整体， ConcurrentHashMap基于lock实现锁分段技术。
    首先将Map存放的数据分成一段一段的存储方式，然后给每一段数据分配一把锁，当一个线程占用锁访问其中一个段的数据时，其他段的数据也能被其他线程访问。
    ConcurrentHashMap不仅保证了多线程运行环境下的数据访问安全性，而且性能上有长足的提升。
    
    CurrentHashMap和HashMap：
    HashMap是线程不安全的，后来在jdk1.5的时候就出来了CurrentHashMap来弥补HashMap的线程不安全的特性；
    CurrentHashMap，是将HashMap分成了很多个片（一般默认是16片），引入了分段锁的概念，然后对每一片加锁，
    具体可以理解成一把大的Map分解成N个小的HashTable，根据key.hashCode()来决定放到哪一个片上；
    
    效率的比较：
    由于安全机制的原因，HashMap的效率比HashTable，CurrentHashMap的效率高；
    但是由于CurrentHashMap加锁的高效性,HashTable是整个加锁，他的效率比HashTable高； 
    总的来说 HashMap>CurrentHashMap>HashTable;

###### [21）、Java中int和Integer区别？]()
    1、Integer是int的包装类，int则是java的一种基本数据类型 
    2、Integer变量必须实例化后才能使用，而int变量不需要 
    3、Integer实际是对象的引用，当new一个Integer时，实际上是生成一个指针指向此对象；而int则是直接存储数据值 
    4、Integer的默认值是null，int的默认值是0

###### [22） 、Java中内存溢出和内存泄漏区别？]()
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

###### [42）、equals与==的区别?]()
    最大的区别是，==是运算符，equal是方法
    1.java基本类型（short，int，long，byte，char，float，double，boolean）
    比较基本类型，只能用==，不能用equal，这里的==比较的是两个变量的值
    2.比较包装类型
    ==比较的是内存地址，因为a和b是new出来的，是两个不同的对象，所以地址肯定是不同的，而equal比较的是值
    3.比较String类型
    ==比较的是内存地址，equal比较的是值
    4.比较对象
    ==和equal比较的都是内存地址，因为equal没有被重写，没有被重写的equal都是object的equal方法

###### [43）、final, finally, finalize 的区别?]()
    final 用于修饰属性,方法和类, 分别表示属性不能被重新赋值, 方法不可被覆盖, 类不可被继承.
    finally 是异常处理语句结构的一部分，一般以ty-catch-finally出现，finally代码块表示总是被执行.
    finalize 是Object类的一个方法，该方法一般由垃圾回收器来调用，当我们调用System.gc() 方法的时候，由垃圾回收器调用finalize()方法，回收垃圾，JVM并不保证此方法总被调用.

###### [44）、重载和重写的区别?]()
    重写必须继承，重载不用。
    重载表示同一个类中可以有多个名称相同的方法，但这些方法的参数列表各不相同（即参数个数或类型不同）
    重写表示子类中的方法与父类中的某个方法的名称和参数完全相同啦，通过子类实例对象调用这个方法时，将调用子类中的定义方法，这相当于把父类中定义的那个完全相同的方法给覆盖了。

###### [45）、BIO、NIO、AIO 有什么区别?]()
    BIO：线程发起 IO 请求，不管内核是否准备好 IO 操作，从发起请求起，线程一直阻塞，直到操作完成。
    NIO：线程发起 IO 请求，立即返回；内核在做好 IO 操作的准备之后，通过调用注册的回调函数通知线程做 IO 操作，线程开始阻塞，直到操作完成。
    AIO：线程发起 IO 请求，立即返回；内存做好 IO 操作的准备之后，做 IO 操作，直到操作完成或者失败，通过调用注册的回调函数通知线程做 IO 操作完成或者失败。
    
    BIO 是一个连接一个线程。,NIO 是一个请求一个线程。,AIO 是一个有效请求一个线程。
    BIO：同步并阻塞，服务器实现模式为一个连接一个线程，即客户端有连接请求时服务器端就需要启动一个线程进行处理，如果这个连接不做任何事情会造成不必要的线程开销，当然可以通过线程池机制改善。
    NIO：同步非阻塞，服务器实现模式为一个请求一个线程，即客户端发送的连接请求都会注册到多路复用器上，多路复用器轮询到连接有I/O请求时才启动一个线程进行处理。
    AIO：异步非阻塞，服务器实现模式为一个有效请求一个线程，客户端的 IO 请求都是由 OS 先完成了再通知服务器应用去启动线程进行处理。

###### [46）、String，StringBuffer，StringBuilder的区别?]()
    String：
    String类是一个不可变的类，一旦创建就不可以修改。
    String是final类，不能被继承
    String实现了equals()方法和hashCode()方法
    
    StringBuffer：
    继承自AbstractStringBuilder，是可变类。
    StringBuffer是线程安全的
    可以通过append方法动态构造数据。

    StringBuilder：
    继承自AbstractStringBuilder，是可变类。
    StringBuilder是非线性安全的。
    执行效率比StringBuffer高。

###### [47）、Comparable与Comparator有什么区别?]()
    Comparable & Comparator 都是用来实现集合中元素的比较、排序的，只是 Comparable 是在集合内部定义的方法实现的排序，Comparator 是在集合外部实现的排序，
    所以，如想实现排序，就需要在集合内实现 Comparable 接口的方法或在集合外定义 Comparator 接口的方法。

###### [48）、说说反射的用途及实现原理，Java获取反射的三种方法?]()
    Java获取反射的三种方法：
    第一种，使用 Class.forName 静态方法。
    第二种，使用类的.class 方法
    第三种，使用实例对象的 getClass() 方法。

###### [49）、Java中IO流分为几种?]()
    Java中的流分为两种：一种是字节流，另一种是字符流。
    IO流分别由四个抽象类来表示（两输入两输出）:InputStream，OutputStream，Reader，Writer。

###### [50）、Java创建对象有几种方式?]()
    Java创建对象有5种方式
    1、用new语句创建对象。
    2、使用反射，使用Class.newInstance()创建对象/调用类对象的构造方法——Constructor
    3、调用对象的clone()方法。
    4、运用反序列化手段，调用java.io.ObjectInputStream对象的readObject()方法.
    5、使用Unsafe

###### [51）、守护线程是什么？用什么方法实现守护线程?]()
    守护线程是运行在后台的一种特殊进程。
    它独立于控制终端并且周期性地执行某种任务或等待处理某些发生的事件。
    在 Java 中垃圾回收线程就是特殊的守护线程。

###### [52）、String s与new String与有什么区别?]()
    1、String str ="sss";
    先在常量池中查找有没有"sss" 这个对象,如果有，就让str指向那个"sss".如果没有，在常量池中新建一个“sss”对象，并让str指向在常量池中新建的对象"sss"。
    2、String newStr =new String ("sss"); 
    是在堆中建立的对象"sss" ,在栈中创建堆中"sss" 对象的内存地址。

###### [53）、反射中，Class.forName和ClassLoader的区别?]()
    Class.forName和ClassLoader都可以对类进行加载。
    ClassLoader负责加载 Java 类的字节代码到 Java 虚拟机中，Class.forName其实是调用了ClassLoader。
    Class.forName和ClassLoader的区别，就是在类加载的时候，class.forName有参数控制是否对类进行初始化。

###### [54）、Java的类加载机制?]()
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

###### [55）、JVM调优?]()
    对JVM内存的系统级的调优主要的目的是减少GC的频率和Full GC的次数。
    
    Full GC会对整个堆进行整理，包括Young、Tenured和Perm。Full GC因为需要对整个堆进行回收，所以比较慢，因此应该尽可能减少Full GC的次数。
    导致Full GC的原因
    1）、年老代（Tenured）被写满
    2）、持久代Pemanet Generation空间不足
    3）、System.gc()被显示调用
    
    JVM性能调优方法和步骤
    1）、监控GC的状态
    2）、生成堆的dump文件，分析dump文件，分析结果，判断是否需要优化
    3）、调整GC类型和内存分配
    
    针对JVM堆的设置，一般可以通过-Xms -Xmx限定其最小、最大值，
    为了防止垃圾收集器在最小、最大之间收缩堆而产生额外的时间，通常把最大、最小设置为相同的值;

###### [56）、数据库3范式?]()
    第一范式（1NF）:列不可再分： 每一列属性都是不可再分的属性值，确保每一列的原子性
    第二范式（2NF）属性完全依赖于主键：要求数据库表中的每个实例或行必须可以被惟一地区分。
    第三范式（3NF）属性不依赖于其它非主属性 ，属性直接依赖于主键：数据不能存在传递关系，即每个属性都跟主键有直接关系而不是间接关系。
    
###### [57）、rpc服务和http服务区别?]()
    两种形式的C/S架构
    RPC主要是基于TCP/IP协议的，而HTTP服务主要是基于HTTP协议的，
    我们都知道HTTP协议是在传输层协议TCP之上的，所以效率来看的话，RPC当然是要更胜一筹。

    在说RPC和HTTP的区别之前，我觉的有必要了解一下OSI的七层网络结构模型（虽然实际应用中基本上都是五层），它可以分为以下几层： （从上到下）
    第一层：应用层。定义了用于在网络中进行通信和传输数据的接口；
    第二层：表示层。定义不同的系统中数据的传输格式，编码和解码规范等；
    第三层：会话层。管理用户的会话，控制用户间逻辑连接的建立和中断；
    第四层：传输层。管理着网络中的端到端的数据传输；
    第五层：网络层。定义网络设备间如何传输数据；
    第六层：链路层。将上面的网络层的数据包封装成数据帧，便于物理层传输；
    第七层：物理层。这一层主要就是传输这些二进制数据。
    HTTP是应用层协议，而TCP是传输层协议。

###### [58）、CurrentHashMap底层结构?]()

###### [59）、单例模式双重检测机制?]()
    public static Singleton getInstanceDC() {
         if (_instance == null) {                // Single Checked
             synchronized (Singleton.class) {
                 if (_instance == null) {        // Double checked
                     _instance = new Singleton();
                 }
             }
         }
         return _instance;
    }


###### [60）、Java map的containsKey实现?]()

###### [61）、java字符串contains实现原理?]()
    使用的是暴力解法，时间复杂度为o(n2)，空间复杂度为o(1)。


###### [62）、java1.8 currentHashMap原理?]()
    1.8中放弃了Segment臃肿的设计，取而代之的是采用Node + CAS + Synchronized+红黑树 来保证并发安全进行实现

###### [63）、java CAS机制?]()
    CAS是英文单词Compare And Swap的缩写，翻译过来就是比较并替换。
    CAS机制当中使用了3个基本操作数：内存地址V，旧的预期值A，要修改的新值B。
    更新一个变量的时候，只有当变量的预期值A和内存地址V当中的实际值相同时，才会将内存地址V对应的值修改为B。

###### [64）、java中的悲观锁与乐观锁?]()
    悲观锁：Synchronzied 
    乐观锁：CAS

###### [65）、Java volatile关键字，为什么会出现指令重排?]()
    指令重排序是JVM为了优化指令、提高程序运行效率，在不影响单线程程序执行结果的前提下，尽可能地提高并行度。
    指令重排序包括编译器重排序和运行时重排序。
    volatile关键字提供内存屏障的方式来防止指令被重排，编译器在生成字节码文件时，会在指令序列中插入内存屏障来禁止特定类型的处理器重排序。
