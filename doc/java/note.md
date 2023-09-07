
## JVM参数的加载顺序

* jvm参数的执行顺序如下解释 java -Xms2048m -Drocketmq.client.logUseSlf4j=true -jar 1.jar --spring.profiles.active=test

```bash
➜  alg git:(master) ✗ java -h
用法: java [-options] class [args...]
           (执行类)
   或  java [-options] -jar jarfile [args...]
           (执行 jar 文件)
其中选项包括:
    -d32          使用 32 位数据模型 (如果可用)
    -d64          使用 64 位数据模型 (如果可用)
    -server       选择 "server" VM
                  默认 VM 是 server,
                  因为您是在服务器类计算机上运行。


    -cp <目录和 zip/jar 文件的类搜索路径>
    -classpath <目录和 zip/jar 文件的类搜索路径>
                  用 : 分隔的目录, JAR 档案
                  和 ZIP 档案列表, 用于搜索类文件。
    -D<名称>=<值>
                  设置系统属性
    -verbose:[class|gc|jni]
                  启用详细输出
    -version      输出产品版本并退出
    -version:<值>
                  警告: 此功能已过时, 将在
                  未来发行版中删除。
                  需要指定的版本才能运行
    -showversion  输出产品版本并继续
    -jre-restrict-search | -no-jre-restrict-search
                  警告: 此功能已过时, 将在
                  未来发行版中删除。
                  在版本搜索中包括/排除用户专用 JRE
    -? -help      输出此帮助消息
    -X            输出非标准选项的帮助
    -ea[:<packagename>...|:<classname>]
    -enableassertions[:<packagename>...|:<classname>]
                  按指定的粒度启用断言
    -da[:<packagename>...|:<classname>]
    -disableassertions[:<packagename>...|:<classname>]
                  禁用具有指定粒度的断言
    -esa | -enablesystemassertions
                  启用系统断言
    -dsa | -disablesystemassertions
                  禁用系统断言
    -agentlib:<libname>[=<选项>]
                  加载本机代理库 <libname>, 例如 -agentlib:hprof
                  另请参阅 -agentlib:jdwp=help 和 -agentlib:hprof=help
    -agentpath:<pathname>[=<选项>]
                  按完整路径名加载本机代理库
    -javaagent:<jarpath>[=<选项>]
                  加载 Java 编程语言代理, 请参阅 java.lang.instrument
    -splash:<imagepath>
                  使用指定的图像显示启动屏幕
```

## Java Bean中boolean属性的坑

* boolean值（小写，大写Boolean没有这个问题）如isDel，getter自动为a.del() setter自动为a.setDel

## 如何限制递归调用最大深度

* 第一种写法，在class层面设置一个static变量，进行递归计数。但是有多线程的问题
* 第二种写法，在递归入参增加一个变量进行计数

## java读取静态文件

* pom文件中 resources配置好静态文件类型
* jar包的启动方式，不能直接读取文件，需要使用 ClassPathResource 或者 getResourceAsStream转化为stream来处理
* 一般读取流需要用byte数组进行转换
```java
    private byte[] read(InputStream inputStream){
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        try{
            while ((length = inputStream.read(buffer)) != -1){
                outStream.write(buffer, 0, length);

            }

        }
        catch (IOException e){
            e.printStackTrace();
        }

        return outStream.toByteArray();
    }

    //创建数据缓冲区
    byte[] b=new byte[1024];
    int length;
    while((length=inputStream.read(b))>0){
    response.getOutputStream().write(b,0,length);
    }
```

## 反射相关

* clazz.getDeclaredMethods仅获取当前类的方法；clazz.getMethods获取当前以及父类的方法

## MyBatis

* Mapper文件中的同名方法无法重载，因为它的原理和参数无关
* springboot下mybatis一级缓存是默认开启的，且缓存级别为session（一个sqlsession有一个缓存）。二级缓存需要在mapper文件加cache标签。https://www.jianshu.com/p/407b4d8cb79b
* 如果使用mybatis一级缓存，就一定小心不能修改mybatis mapper方法的返回值，因为mybatis一级缓存直接缓存的就是mapper方法的返回值，没有深拷贝，所以，如果有修改，后续相同查询拿到的都是修改后的脏数据。对于二级缓存，同样需要注意这个问题。


