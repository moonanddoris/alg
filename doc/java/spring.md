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
* Propagation.REQUIRED会建立新的事务，使用时需要明确场景。否则可能会读取不到新数据

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