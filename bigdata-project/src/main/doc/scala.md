* [9.12、Scala](scala.md)
    - [1）、伴生对象Object和伴生类Class区别？](#1objectclass)
    - [2）、var、val和def三个关键字之间的区别？](#2varvaldef)
    - [3）、trait和abstract class区别？](#3traitabstract-class)
    - [4）、case class是什么？](#4case-class)
    - [5）、apply和unapply区别？以及各自的使用场景？](#5applyunapply)
    - [6）、Nil、Null、None和Nothing四个类型的区别？](#6nilnullnonenothing)
    - [7）、Unit类型是什么？](#7unit)
    - [8）、Option类型的定义和使用场景？](#8option)
    - [9）、yield如何工作？](#9yield)
    - [10）、Scala隐士转换如何理解？什么场景下使用？](#10scala)
    - [11）、什么是偏函数？](#11)
    - [12）、什么是柯里化？](#12)
    - [13）、什么是闭包？](#13)
    - [14）、Array和ArrayBuffer区别？](#14arrayarraybuffer)
    - [15）、Scala中协变和逆变区别？](#15scala)
    - [16）、Scala中有break么？](#16scalabreak)
    - [17）、Scala中上界和下界区别？](#17scala)
    - [18）、Scala中Any AnyRef AnyVal区别？](#18scalaany-anyref-anyval)

---
###### [1）、伴生对象Object和伴生类Class区别？]()
    当Object单例对象和Class类名一样情况，Object修饰的为类的伴生对象，Class修饰的是类的伴生类。
    相当于一个用来写静态方法，静态变量，一个用来写非静态方法和非静态变量，两者可以互相访问对象的私有成员。
    
    Class类使用方法和Java差不多，都是面向对象的作用，封装属性和方法。
    Object常常用来写入main方法，用来提供程序的入口。

###### [2）、var、val和def三个关键字之间的区别？]()
    var是变量声明关键字，类似于Java中的变量，变量值可以更改，但是变量类型不能更改。
    val常量声明关键字。
    def 关键字用于创建方法。
    lazy val（惰性val）声明，意思是当需要计算时才使用，避免重复计算。

###### [3）、trait和abstract class区别？]()
    （1）一个类只能集成一个抽象类，但是可以通过with关键字继承多个特质；
    （2）抽象类有带参数的构造函数，特质不行（如 trait t（i：Int）{} ，这种声明是错误的）

###### [4）、case class是什么？]()
    Scala没有break操作，但是可以实现break原理，需要创建Breaks对象实现内部的break方法就可以像java一样跳出语句，但是在模式匹配过程中不需要跳出匹配模式，因为模式匹配只能匹配其中一个结果值。
    // 导入以下包
    import scala.util.control._
    // 创建 Breaks 对象
    val loop = new Breaks;
    // 在 breakable 中循环
    loop.breakable{
        // 循环
        for(...){
           ....
           // 循环中断
           loop.break;
       }
    }
    
    case class代表样例类，它和class类比较来说，可以不需要序列化，而class需要序列化操作，和object很类似

###### [5）、apply和unapply区别？以及各自的使用场景？]()
    构造器：apply方法是为了自动实现样本类的对象，无需new关键字。
    提取器：unapply方法，从对象中提取出构造该对象的参数

###### [6）、Nil、Null、None和Nothing四个类型的区别？]()
    Nil代表一个List空类型，等同List[Nothing]
    Null是一个trait（特质），是所以引用类型AnyRef的一个子类型，null是Null唯一的实例。
    None是Option的空标识
    Nothing也是一个trait（特质），是所有类型Any（包括值类型和引用类型）的子类型，它不在有子类型，它也没有实例，实际上为了一个方法抛出异常，通常会设置一个默认返回类型。

###### [7）、Unit类型是什么？]()
    Scala中的Unit类型类似于java中的void，无返回值。
    主要的不同是在Scala中可以有一个Unit类型值，也就是（），然而java中是没有void类型的值的。
    除了这一点，Unit和void是等效的。一般来说每一个返回void的java方法对应一个返回Unit的Scala方法。

###### [8）、Option类型的定义和使用场景？]()
    在Scala语言中，Option类型是一个特殊的类型，它是代表有值和无值的体现，内部有两个对象，一个是Some一个是None，
    Some代表有返回值，内部有值，而None恰恰相反，表示无值，
    比如，我们使用Map集合进行取值操作的时候，当我们通过get取值，返回的类型就是Option类型，而不是具体的值。

###### [9）、yield如何工作？]()
    yield用于循环迭代中生成新值

###### [10）、Scala隐士转换如何理解？什么场景下使用？]()
    隐式转换函数是以implicit关键字声明的带有单个参数的函数。这种函数将会自动应用，将值从一种类型转换为另一种类型。

###### [11）、什么是偏函数？]()
    偏函数表示用{}包含用case进行类型匹配的操作，这种操作一般用于匹配唯一的属性值，在Spark中的算子内经常会遇到
    val rdd = sc.textFile(路径)
    rdd.map{
        case (参数)=>{返回结果}
    }

###### [12）、什么是柯里化？]()
    定义：柯里化指的是将原来接受两个参数的函数变成新的接受一个参数的函数的过程。
    新的函数返回一个以原有的第二个参数作为参数的函数。
    eg：
        def mul(x:Int,y:Int) = x * y  //该函数接受两个参数
        def mulOneAtTime(x:Int) = (y:Int) => x * y  //该函数接受一个参数生成另外一个接受单个参数的函数
        这样的话，如果需要计算两个数的乘积的话只需要调用：
        mulOneAtTime(5)(4)
        这就是函数的柯里化

###### [13）、什么是闭包？]()
     一个函数把外部的那些不属于自己的对象也包含(闭合)进来。

###### [14）、Array和ArrayBuffer区别？]()
    Array是不可变的,不能直接删除元素,可重新赋值或生成新的Array.
    ArrayBuffer是可变的,提供了删除元素操作
    Array和ArrayBuffer互相转换,可以分别调用toArray()或toBuffer()方法即可.

###### [15）、Scala中协变和逆变区别？]()
    使用+ 来表示协变类型；使用-表示逆变类型；非转化类型不需要添加标记。
    函数的参数必须是逆变的，而返回值必须是协变的
    假如我们定义一个class C[+A] {} ,这里A的类型参数是协变的，这就意味着在方法需要参数是C[AnyRef]的时候，我们可以是用C[String]来代替。
    同样的道理如果我们定义一个class C[-A] {}, 这里A的类型是逆变的，这就意味着在方法需要参数是C[String]的时候，我们可以用C[AnyRef]来代替。
    
    对于一个带类型参数的类型，比如 List[T]：
    如果对A及其子类型B,满足 List[B]也符合 List[A]的子类型，那么就称为covariance(协变)；
    如果对A及其子类型B,满足 List[A]是 List[B]的子类型，即与原来的父子关系正相反，则称为contravariance(逆变)。

###### [16）、Scala中有break么？]()
    Scala没有break操作，但是可以实现break原理，需要创建Breaks对象实现内部的break方法就可以像java一样跳出语句，
    但是在模式匹配过程中不需要跳出匹配模式，因为模式匹配只能匹配其中一个结果值。

###### [17）、Scala中上界和下界区别？]()
    Scala的上下边界特性允许泛型类型是某个类的子类，或者是某个类的父类；
    (1) U >: T
    这是类型下界的定义，也就是U必须是类型T的父类(或本身，自己也可以认为是自己的父类)。
    (2) S <: T
    这是类型上界的定义，也就是S必须是类型T的子类（或本身，自己也可以认为是自己的子类)。

##### [18）、Scala中Any AnyRef AnyVal区别？]()
    Any : 是abstract类，它是Scala类继承结构中最底层的。所有运行环境中的Scala类都是直接或间接继承自Any这个类，它就是其它语言（.Net，Java等）中的Object。
    从Scala2.10 开始，对于一般性的traits（特性，特质）可以直接继承Any。一般性traits是指，继承自Any，并且只有defs成员，不需要初始化。
    
    AnyRef : 是所有引用类型的基类。除了值类型，所有类型都继承自AnyRef 。

    AnyVal : 是所有值类型的基类， 它描述的是值，而不是代表一个对象。 
    包括 9 个 AnyVal 子类型：
    scala.Double 
    scala.Float 
    scala.Long 
    scala.Int 
    scala.Char 
    scala.Short 
    scala.Byte 
    上面是数字类型。
    scala.Unit 和 scala.Boolean 是非数字类型。
