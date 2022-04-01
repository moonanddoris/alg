package queue;

/**
 * @author lixinhai
 * @date 2022/3/16
 */
public class MyQueue {

    private int head;

    private int tail;

    private int size = 0;

    private int[] datas;

    public MyQueue(int size) {
        this.size = size;
        head = tail = 0;
        datas = new int[size];
    }

    public boolean pushIn(int data){
        if(tail == size){
            System.out.println("已到列尾");
            return false;
        }

        datas[tail++] = data;
        return true;
    }

    public int popOut(){
        if(head == tail){
            System.out.println("无法取出更多数据");
            return -999999;
        }
        return this.datas[this.head++];
    }

    public void printInfo(){
        System.out.println("队列大小：" + this.size);
        System.out.println("队列头位置：" + this.head);
        System.out.println("队列尾位置：" + this.tail);

        System.out.println("队列元素：" );
        for(int i = this.head; i<this.tail ;i++){
            System.out.println(this.datas[i]);
        }
    }

    public static void main(String[] args){

        MyQueue testQueue = new MyQueue(10);

        testQueue.pushIn(1);
        testQueue.pushIn(2);
        testQueue.pushIn(3);

        testQueue.popOut();

        testQueue.printInfo();

    }
}
