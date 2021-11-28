* [SpringBoot]()
    - [1）、什么是微服务？]()
    - [2）、SpringBoot是什么？]()
    - [3）、SpringBoot与Spring区别？]()
    - [4）、SpringBoot与SpringCloud区别？]()

---
###### [1、什么是微服务？]()
    微服务是一种用于构建应用的架构方案。微服务架构有别于更为传统的单体式方案，可将应用拆分成多个核心功能。
    每个功能都被称为一项服务，可以单独构建和部署，这意味着各项服务在工作（和出现故障）时不会相互影响。

###### [2、SpringBoot是什么？创建方式？]()
    Spring Boot 是 Pivotal 团队在 Spring 的基础上提供的一套全新的开源框架，其目的是为了简化 Spring 应用的搭建和开发过程。
    Spring Boot 去除了大量的 XML 配置文件，简化了复杂的依赖管理。    
    Spring Boot的核心原理是基于SpringMVC无配置文件完全注解化 + 内置Tomcat实现SpringBoot框架,使用Main函数启动。
* [2.1）、网页创建方式](https://start.spring.io/) 
* [2.2）、Spring Quickstart Guide](https://spring.io/quickstart)   

###### [3、SpringBoot与Spring区别？]()
    Spring Boot基本上是Spring框架的扩展，它消除了设置Spring应用程序所需的XML配置，
    为更快，更高效的开发生态系统铺平了道路。
    
    Spring Boot中的一些特征：
    1）、创建独立的Spring应用。
    2）、嵌入式Tomcat、Jetty、 Undertow容器（无需部署war文件）。
    3）、提供的starters 简化构建配置
    4）、尽可能自动配置spring应用。
    5）、提供生产指标,例如指标、健壮检查和外部化配置
    6）、完全没有代码生成和XML配置要求

###### [4、SpringBoot与SpringCloud区别？]()
    Spring Boot的核心原理是基于SpringMVC无配置文件完全注解化 + 内置Tomcat实现SpringBoot框架,使用Main函数启动。

    SpringCloud是关注全局的微服务协调整理治理框架，它将SpringBoot开发的一个个单体微服务整合并管理起来，
    为各个微服务之间提供，配置管理、服务发现、断路器、路由、等集成服务。
    
    SpringBoot不依赖于SpringCloud,SpringCloud依赖于SpringBoot,属于依赖关系。

###### [5、Spring Boot、Spring MVC和Spring有什么区别？]()
    spring是一个一站式的轻量级的java开发框架，核心是控制反转（IOC）和面向切面（AOP），
    针对于开发的WEB层(springMvc)、业务层(Ioc)、持久层(jdbcTemplate)等都提供了多种配置解决方案；
    
    springMvc属于一个企业WEB开发的MVC框架，涵盖面包括前端视图开发、文件配置、后台接口逻辑开发等，
    XML、config等配置相对比较繁琐复杂；
    
    spring boot使用了默认大于配置的理念，集成了快速开发的spring多个插件，同时自动过滤不需要配置的多余的插件，
    简化了项目的开发配置流程，一定程度上取消xml配置，是一套快速配置开发的脚手架，能快速开发单个微服务；

###### [6、什么是Spring Boot Stater？]()



