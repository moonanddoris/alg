package queue;

/**
 * 循环队列
 * @author lixinhai
 * @date 2022/3/18
 */
public class MyQueueLoop {

    // volatile 关键字只能保证 多线程可见性和有序性，无法保证原子性
    private int head;

    private int tail;

    private int size = 0;

    private int[] datas;

    public MyQueueLoop(int size) {
        this.size = size;
        head = tail = 0;
        datas = new int[size];
    }

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public int getTail() {
        return tail;
    }

    public void setTail(int tail) {
        this.tail = tail;
    }

    public boolean pushIn(int data){
        if( (this.tail+1)%size == this.head){
            System.out.println("已到列尾");
            return false;
        }

        datas[tail] = data;
        tail++;
        tail = tail % size;
        return true;
    }

    public int popOut(){
        if(head == tail){
            System.out.println("无法取出更多数据");
            return -999999;
        }
        int re = this.datas[this.head];
        head = ++head % size;
        return re;
    }

    public void printInfo(){
        System.out.println("队列大小：" + this.size);
        System.out.println("队列头位置：" + this.head);
        System.out.println("队列尾位置：" + this.tail);

        System.out.println("队列元素：" );
        for(int i = this.head; i%size != this.tail ;i++){
            System.out.println(this.datas[i%size]);
        }
    }

    public static void testThreadSafe(){
        MyQueueLoop testQueue = new MyQueueLoop(100);

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
        if(testQueue.getTail() != 50){
            System.out.println("队列头位置：" + testQueue.getHead());
            System.out.println("队列尾位置：" + testQueue.getTail());
            System.out.println("队列元素：" );
            for(int i = testQueue.getHead(); i% testQueue.size != testQueue.getTail() ;i++){
                System.out.println(testQueue.datas[i% testQueue.size]);
            }
        }
    }

    public static void main(String[] args){

        // 无论是否 volatile变量，在高并发的场景下，总会发生线程安全的问题
        for(int i = 0; i < 100000; i++) {
            testThreadSafe();
        }

        /*
        MyQueueLoop testQueue = new MyQueueLoop(4);

        testQueue.pushIn(0);
        testQueue.pushIn(1);
        testQueue.pushIn(2);

        testQueue.popOut();

        testQueue.pushIn(3);

        testQueue.printInfo();
        */

    }

}
