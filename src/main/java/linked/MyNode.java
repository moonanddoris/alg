package linked;

/**
 * @author lixinhai
 * @date 2022/3/10
 */
public class MyNode {
    private int data;
    private MyNode next;

    public MyNode(int data, MyNode next) {
        this.data = data;
        this.next = next;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public MyNode getNext() {
        return next;
    }

    public void setNext(MyNode next) {
        this.next = next;
    }
}
