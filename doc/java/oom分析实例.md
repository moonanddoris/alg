## 一个OOM分析案例

* 生产环境的某个后台服务，过一段时间会产生rocketMq的消息积压，即没有及时消费
* 查看服务日志，发现有频繁的 java.lang.OutOfMemoryError:Java heap space
* 表明此程序中存在内存泄漏，导致jvm的堆区被使用殆尽
* jmap -dump:format=b,live,file=1.hprof PID 导出内存dump文件，下载到本地，使用VisualVM进行分析
* 点击"类"查看占用量大的对象。一般排在前面的都是底层的对象，它们都是被引用的，不太容易看出来程序引发的问题
* 可以挑几个查看，根引用是什么有可能的类
* 从大向小，继续寻找程序中的类，这些才是真正引起问题的根源。可以看到很多OkHttpClient的实例，再追查代码，发现很多地方滥用OkHttpClient的使用