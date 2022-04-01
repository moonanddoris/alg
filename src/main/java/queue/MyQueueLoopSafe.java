package queue;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 循环队列基础上 添加CAS原子操作 最大程度保证线程安全
 * @author lixinhai
 * @date 2022/3/18
 */
public class MyQueueLoopSafe {

    private AtomicInteger head;

    private AtomicInteger tail;

    private int size = 0;

    private int[] datas;

    public MyQueueLoopSafe(int size) {
        this.size = size;
        head = new AtomicInteger(0);
        tail = new AtomicInteger(0);
        datas = new int[size];
    }

    public AtomicInteger getHead() {
        return head;
    }

    public void setHead(AtomicInteger head) {
        this.head = head;
    }

    public AtomicInteger getTail() {
        return tail;
    }

    public void setTail(AtomicInteger tail) {
        this.tail = tail;
    }

    public boolean pushIn(int data){
        while (true) {
            if ((this.tail.get() + 1) % size == this.head.get()) {
                System.out.println("已到列尾");
                return false;
            }

            int oldV = tail.get();
            int tmp = (oldV + 1) % size;
            boolean casRe = tail.compareAndSet(oldV, tmp);
            if (casRe) {
                datas[oldV] = data;
                return casRe;
            } else {
                // 在高并发场景下，cas并不能完全解决安全问题  但是提供了返回值，如果false，调用方可以进行容错或者重试
                // 反映了线程安全导致的失败。总好过，默默的操作错误 上层应用不知情
                // 因此 可以使用while循环，直至插入成功
                System.out.println("cas 操作失败" + oldV + "to" + tmp);
            }
        }
    }

    public int popOut(){
        while (true) {
            if (head.get() == tail.get()) {
                System.out.println("无法取出更多数据");
                return -999999;
            }

            int oldV = head.get();
            int tmp = (oldV + 1) % size;
            boolean casRe = head.compareAndSet(oldV, tmp);
            if (casRe) {
                int re = this.datas[oldV];
                return re;
            } else {
                System.out.println("cas 操作失败" + oldV + "to" + tmp);
            }
        }
    }


    public static void testThreadSafe(){
        MyQueueLoopSafe testQueue = new MyQueueLoopSafe(100);

        // 5个线程 每个线程插入10个数据  最终的tail等于50  若不是50说明有线程安全问题
        Thread[] threads = new Thread[5];
        for(int i=0; i< threads.length; i++){
            threads[i] = new Thread(() -> {
                try {
                    for (int j=0; j< 10; j++) {
                        testQueue.pushIn(11);
                    }
                    // System.out.println(testQueue.getTail());
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            });
            threads[i].start();
        }

        // 保证子线程都执行完毕
        for(int i=0; i < threads.length; i++){
            try {
                threads[i].join();
            }
            catch (Exception e){}
        }

        // 5个线程 每个线程插入10个数据  最终的tail等于50  若不是50说明有线程安全问题
        if(testQueue.getTail().get() != 50){
            System.out.println("队列头位置：" + testQueue.getHead());
            System.out.println("队列尾位置：" + testQueue.getTail());
        }
    }

    public static void main(String[] args){

        // 无论是否 volatile变量，在高并发的场景下，总会发生线程安全的问题
        for(int i = 0; i < 100000; i++) {
            testThreadSafe();
        }
    }

}
