## 一个OOM分析案例

* 生产环境的某个后台服务，过一段时间会产生rocketMq的消息积压，即没有及时消费
* 查看服务日志，发现有频繁的 java.lang.OutOfMemoryError:Java heap space
* 表明此程序中存在内存泄漏，导致jvm的堆区被使用殆尽
* jmap -dump:format=b,live,file=1.hprof PID 导出内存dump文件，下载到本地，使用VisualVM进行分析
* 点击"类"查看占用量大的对象。一般排在前面的都是底层的对象，它们都是被引用的，不太容易看出来程序引发的问题
* 可以挑几个查看，根引用是什么有可能的类
* 从大向小，继续寻找程序中的类，这些才是真正引起问题的根源。可以看到很多OkHttpClient的实例，再追查代码，发现很多地方滥用OkHttpClient的使用
* 可以粘贴出来 可疑对象的引用链，结构类似如下
```bash
this     - value: java.util.LinkedHashMap$Entry #1
 <- before     - class: java.util.LinkedHashMap$Entry, value: java.util.LinkedHashMap$Entry #1
  <- before     - class: java.util.LinkedHashMap$Entry, value: java.util.LinkedHashMap$Entry #3
   <- before     - class: java.util.LinkedHashMap$Entry, value: java.util.LinkedHashMap$Entry #5
    <- before     - class: java.util.LinkedHashMap$Entry, value: java.util.LinkedHashMap$Entry #7
     <- before     - class: java.util.LinkedHashMap$Entry, value: java.util.LinkedHashMap$Entry #9
      <- before     - class: java.util.LinkedHashMap$Entry, value: java.util.LinkedHashMap$Entry #11
       <- before     - class: java.util.LinkedHashMap$Entry, value: java.util.LinkedHashMap$Entry #13
        <- before     - class: java.util.LinkedHashMap$Entry, value: java.util.LinkedHashMap$Entry #15
         <- before     - class: java.util.LinkedHashMap$Entry, value: java.util.LinkedHashMap$Entry #17
          <- before     - class: java.util.LinkedHashMap$Entry, value: java.util.LinkedHashMap$Entry #19
           <- before     - class: java.util.LinkedHashMap$Entry, value: java.util.LinkedHashMap$Entry #21
            <- before     - class: java.util.LinkedHashMap$Entry, value: java.util.LinkedHashMap$Entry #23
             <- before     - class: java.util.LinkedHashMap$Entry, value: java.util.LinkedHashMap$Entry #25
              <- before     - class: java.util.LinkedHashMap$Entry, value: java.util.LinkedHashMap$Entry #27
               <- before     - class: java.util.LinkedHashMap$Entry, value: java.util.LinkedHashMap$Entry #29
                <- before     - class: java.util.LinkedHashMap$Entry, value: java.util.LinkedHashMap$Entry #31
                 <- before     - class: java.util.LinkedHashMap$Entry, value: java.util.LinkedHashMap$Entry #33
                  <- next     - class: java.util.LinkedHashMap$Entry, value: java.util.LinkedHashMap$Entry #35
                   <- [39]     - class: java.util.HashMap$Node[], value: java.util.LinkedHashMap$Entry #12840427
                    <- table     - class: java.util.LinkedHashMap, value: java.util.HashMap$Node[] #38
                     <- subjectToCaCerts     - class: okhttp3.internal.tls.BasicTrustRootIndex, value: java.util.LinkedHashMap #6760448
                      <- trustRootIndex     - class: okhttp3.internal.tls.BasicCertificateChainCleaner, value: okhttp3.internal.tls.BasicTrustRootIndex #69477
                       <- certificateChainCleaner     - class: okhttp3.CertificatePinner, value: okhttp3.internal.tls.BasicCertificateChainCleaner #69477
                        <- certificatePinner     - class: okhttp3.OkHttpClient, value: okhttp3.CertificatePinner #1
                         <- key     - class: java.util.HashMap$Node, value: okhttp3.OkHttpClient #69477
                          <- [18723]     - class: java.util.HashMap$Node[], value: java.util.HashMap$Node #1
                           <- table     - class: java.util.HashMap, value: java.util.HashMap$Node[] #298297
                            <- retrofitMap     - class: c.h.f.common.Retrofit.RetrofitApiHttpGenerator, value: java.util.HashMap #101397
```