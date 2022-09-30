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