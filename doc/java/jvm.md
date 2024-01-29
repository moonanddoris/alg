## JVM

* java8默认的内存大小为物理内存的1/4或者1GB，取二者最小值；xms xmx来指定。对应的错误 java.lang.OutOfMemoryError:Java heap space
 
* 非堆内存默认为内存的1/4

* Java虚拟机中的非堆内存（Non-Heap Memory）用于存储Java虚拟机自己管理的一些内存，例如Java类、方法区（Metaspace），线程堆栈等。
对应的错误 java.lang.StackOverflowError java.lang.OutOfMemoryError:PermGen space
 
* 查看默认内存大小 java -XX:+PrintFlagsFinal -version | grep -iE 'heapsize|PermSize|ThreadStackSize'
 
* 查看程序gc情况 jstat -gcutil PID
```bash
  S0     S1     E      O      M     CCS    YGC     YGCT    FGC    FGCT     GCT
  0.00   0.00  86.51 100.00  88.69  87.69    646   13.345  2358 2291.506 2304.851
```

* 查看内存占用最大的对象 jmap -histo:live PID| head -n 100
* 查看jvm配置参数 jinfo -flags PID
* jstack -F PID > tmp.log 导出堆栈信息到文件
* jmap -dump:format=b,live,file=1.hprof PID 导出内存dump文件
* 使用jhat jvisualvm 来分析内存
* jstat -gccapacity PID 查看gc详情

### 如何查看目标线程
* top -Hp pid 查看疑似的线程
* printf "%x\n" tid 将线程id转化为16进制 8f7
* jstack pid | grep 8f7  在堆栈信息中查看线程具体信息

### jvm优化思路

* 本质上是减少GC的次数。
* 如果是频繁创建对象的应用，可以适当增加新生代大小。常量较多可以增加持久代大小。对于单例较多的对象可以增加老生代大小。比如spring应用中。
* GC选择，在JDK5.0以后，JVM会根据当前 系统配置进行判断。一般执行-Server命令便可以。gc包括三种策略：串行，并行，并发。
* 吞吐量大大应用，一般采用并行收集，开启多个线程，加快gc的是否。
* 响应速度高的应用，一般采用并发收集，比如应用服务器。