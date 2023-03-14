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
