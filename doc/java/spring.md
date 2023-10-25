# Spring相关

## Spring数据库事务管理

* Spring支持两种数据库事务管理，一种是编程式事务TransactionTemplate，另一种是声明式事务管理
* 声明式事务管理也有两种方式，一种是基于tx和aop名字空间的配置文件，另一种是@Transactional注解

### @Transactional注解使用

* 默认rollbackFor=RuntimeException.class，仅发生RuntimeException或Error时，才进行回滚
* 若要捕获所有的Exception，修改为rollbackFor=Exception.class
* 因为是Spring框架下的事务管理，通过注解生成的代理类进行事务拦截。因此需要以bean的方式调用才可以生效
* 需要应用在public方法上才可以生效
* 传播行为默认为Propagation.REQUIRED，如有已有事务则加入，否则新建事务
* Propagation.REQUIRES_NEW会建立新的事务，使用时需要明确场景。否则可能会读取不到新数据

## Spring循环依赖

* final+构造函数的bean注入方式，易引发循环依赖问题 可以使用@Lazy来解决

## maven包版本冲突问题

* 命令 mvn dependency:tree -Dverbose -Dincludes=junit:junit 来查找jar包是被谁引入的
* 有以下几个处理原则
  * 短路径优先
  * 第一优先，如果路径相同，先被引入的生效
  * 排除原则，如果确定不使用jar，可以exclude掉
  * 版本锁定原则，在最外层的pom中，dependencyManagement中指定版本，这样后续所有引用都使用当前版本。这种方式比较好

## 问题Process finished with exit code 1

* 配置文件错误导致，如pom文件 mapper文件 yml配置文件中有语法错误 或者jar包版本冲突
* SpringBean加载过程中有问题，比如依赖的bean没有被先注入 使用@Lazy等解决
* 排查方法，在程序启动处 catch Throwable 打印出来查看具体错误

## 注解

* @ConditionalOnProperty 控制配置类是否生效
* @DependsOn 依赖于另一个组件，被依赖的组件会先注册到spring容器内

## SpringSession的结构

* session是会话，并不等同于登录态，只是经常用session来保存登录态。如果一个请求没有携带SESSION，spring会创建一个session（这时不一定有登录态）
* spring:session:sessions:sessionId  数据类型是hash类型,其中保存着session相关信息和session的数据。默认TTL是35分钟
* spring:session:sessions:expires:sessionId  值为空，此键存在表明，对应的sessions还未过期 (这种机制是因为redis处理过期的优先级很低，无法及时处理，sessions比此键存活时间多5分钟)
* spring:session:expirations:时间戳 表示未来1分钟内可能过期的expires，会有定时任务不停的更新每分钟的expirations spring也有定时任务不停的遍历expirations内的键，通过redis的惰性删除进行真正清理

```bash
#sessions详细内容
redis 127.0.0.1:6379> hgetall "spring:session:sessions:d70c8cc7-4cdd-4a7f-b371-16783eb81f3c"
1) "maxInactiveInterval"
2) "\xac\xed\x00\x05sr\x00\x11java.lang.Integer\x12\xe2\xa0\xa4\xf7\x81\x878\x02\x00\x01I\x00\x05valuexr\x00\x10java.lang.Number\x86\xac\x95\x1d\x0b\x94\xe0\x8b\x02\x00\x00xp\x00'\x8d\x00"
3) "creationTime"
4) "\xac\xed\x00\x05sr\x00\x0ejava.lang.Long;\x8b\xe4\x90\xcc\x8f#\xdf\x02\x00\x01J\x00\x05valuexr\x00\x10java.lang.Number\x86\xac\x95\x1d\x0b\x94\xe0\x8b\x02\x00\x00xp\x00\x00\x01\x83\xd5\x9f\xd1v"
5) "lastAccessedTime"
6) "\xac\xed\x00\x05sr\x00\x0ejava.lang.Long;\x8b\xe4\x90\xcc\x8f#\xdf\x02\x00\x01J\x00\x05valuexr\x00\x10java.lang.Number\x86\xac\x95\x1d\x0b\x94\xe0\x8b\x02\x00\x00xp\x00\x00\x01\x83\xd5\x9b\xc9\x1b"

redis 127.0.0.1:6379> smembers spring:session:expirations:1557389100000
1) "\xac\xed\x00\x05t\x00,expires:1b8b2340-da25-4ca6-864c-4af28f033327"

#相差5分钟
redis 127.0.0.1:6379> ttl spring:session:sessions:expires:d70c8cc7-4cdd-4a7f-b371-16783eb81f3c
(integer) 1060225
redis 127.0.0.1:6379> ttl spring:session:sessions:d70c8cc7-4cdd-4a7f-b371-16783eb81f3c
(integer) 1060524
```
* SpringSession的Cookie名默认为"SESSION"
* 浏览器的Cookie值，Base64解码就是服务端的seesionId


## 线程池相关

https://blog.csdn.net/qq_27579471/article/details/119397934
https://blog.csdn.net/qq_27579471/article/details/106839223

### 关于请求AOP的方式

* org.springframework.web.servlet.HandlerInterceptor的接口preHandle  org.springframework.web.filter.OncePerRequestFilter的接口doFilterInternal都可以作为请求的aop入口
* 以上二者的区别参考 https://blog.csdn.net/qq_37855368/article/details/126886667 注意：拦截器可以注入spring的bean，过滤器则不可以
* org.springframework.web.servlet.HandlerInterceptor的接口preHandle，传入的参数handler，无法获取到入参
  * 想尝试用以下方法获取((HandlerMethod) handler).getMethodParameters(); 比较困难
  * 可能的方法，通过解析 ServleRequest来获取参数 getParameterNames and getParameterValues, or getParameterMap
  * 直接使用AOP注解，JoinPoint.getArgs 即可直接映射到入参并已经反序列化了

### 关于Bean加载

* @Configuration和@Bean 往往是需要注入第三方jar包的类，因为无法写@Component等注解（或者不在程序扫描目录下）。入参也会作为被依赖的Bean进行加载
* 注入的方式有三种，构造器注入 setter注入  成员变量注解
* SpringBoot支持自动配置组件，形式都是 xxxAutoConfiguration 如RedisAutoConfiguration 对应的yml配置是spring.redis.xx。自动按照配置进行配置
  * 但是SpringBoot遵循 习惯优于配置，所以往往会带着注解 @Conditionalxxx 如@ConditionalOnMissingBean
  * 如果程序没有自定义Bean，才会执行自动config 参考<springboot 自动配置 autoConfig 全流程> https://blog.51cto.com/u_8224502/3725882?b=totalstatistic
  * @EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
  * spring-boot-autoconfigure支持自定义自动配置；其中可以指定加载顺序如 @AutoConfigureAfter @AutoConfigureBefore
  * 自动加载的原理 @SpringBootApplication -> @EnableAutoConfiguration -> @import -> EnableAutoConfigurationImportSelector -> SpringFactoriesLoader.loadFactoryNames方法进行扫描具有META-INF/spring.factories文件的jar包
  * 举个例子，如果未使用自动加载，需要在启动类上使用@MapperScan，如果使用了自动加载，则在自动配置类的地方 使用@MapperScan即可
  * @EnableConfigurationProperties 指定配置类，可以手动指定不在扫描范围内的配置类

### 关于Spring生命周期

* ApplicationContextAware 当一个类实现了这个接口，当前bean创建时会自动调用setApplicationContext方法，把容器上下文传递进来，就可以获取到容器内所有的bean。常用来底层进行自动配置、bean初始化的操作
* BeanFactoryAware 类似于 ApplicationContextAware，不同点是，BeanFactoryAware需要指定bean才能获取到。（而ApplicationContextAware是透明的，可以拿到所有的Bean） ApplicationContext是BeanFactory的子类
* InitializingBean 当一个类实现了这个接口，当前bean创建时会自动调用afterPropertiesSet方法，完成自定的一些操作
* WebMvcAutoConfiguration 源码学习 了解初始化加载过程 https://www.cnblogs.com/ruber/p/17601665.html
  * 以下是chatGpt的回答，加深理解mvc相关配置的源码
  ```agsl
  org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.EnableWebMvcConfiguration
  org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration
  org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport

  这三个类之间的关系是什么，以及为什么这么设计的原因是什么？
  
  这三个类都与Spring MVC框架中的配置有关，具体的关系如下：

  org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.EnableWebMvcConfiguration：这个类是Spring Boot中用于配置Spring MVC框架的类。它继承了WebMvcConfigurationSupport类，并重写了DelegatingWebMvcConfiguration中的一些方法，从而对Spring MVC进行了自定义配置。简单来说，这个类是Spring Boot中配置Spring MVC框架的入口。

  org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration：这个类是Spring MVC中的一个重要类，它实现了WebMvcConfigurer接口，并扩展了WebMvcConfigurationSupport类。它的主要作用是将用户自定义的Spring MVC配置和默认的Spring MVC配置合并在一起，从而生成最终的配置。简单来说，这个类承担了Spring MVC配置的委派和合并工作。

  org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport：这个类是Spring MVC框架中的一个基础配置类，它实现了WebMvcConfigurer接口，并为Spring MVC框架提供了一些基本的默认配置。简单来说，这个类是Spring MVC框架中基础配置的实现。

  这么设计的原因是，Spring MVC框架提供了很多配置选项，这些选项可以通过继承WebMvcConfigurationSupport类进行自定义配置。为了方便用户进行自定义配置，Spring Boot提供了EnableWebMvcConfiguration类，它继承了WebMvcConfigurationSupport类并进行了一些定制化配置，用户可以通过继承EnableWebMvcConfiguration进行自定义配置。而DelegatingWebMvcConfiguration类则承担了将用户自定义的配置和默认配置进行合并的责任，从而生成最终的配置。这种设计方式可以方便用户进行自定义配置，并保证了默认配置和自定义配置的兼容性。
  ```

* RequestMappingHandlerAdapter 适配器模式，把不同的请求适配给对应的endpoint处理


### 其它
* @ServletComponentScan可以指定类加载到servlet容器中