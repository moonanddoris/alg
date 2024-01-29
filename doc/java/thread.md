### ThreadLocal InheritableThreadLocal TransmittableThreadLocal

* ThreadLocal存在内存泄露，用完之后要手动释放掉；内存泄露的原理 https://blog.csdn.net/yh4494/article/details/128544068
* InheritableThreadLocal可以在传递父线程变量，但是在线程池中无法传递
* 这时可以使用TransmittableThreadLocal
* MDC底层使用的是InheritableThreadLocal；手动传递变量 MDC.getCopyOfContextMap()  MDC.setContextMap(previous);