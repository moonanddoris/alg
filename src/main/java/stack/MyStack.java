package stack;

/**
 * @author lixinhai
 * @date 2022/3/16
 */
public class MyStack {

    /**
     * 栈大小
     */
    private int size;

    /**
     * 栈顶 数组下标
     */
    private int top = -1;

    private int[] datas;

    public MyStack(int size) {
        this.size = size;
        this.datas = new int[size];
    }

    /**
     *  入栈
     * @param data
     */
    public void pushIn(int data){

        if(top+1 >= size){
            System.out.println("栈溢出");
        }

        top++;
        datas[top] = data;
    }

    /**
     * 出栈
     * @return
     */
    public int popOut(){
        if(top < 0){
            System.out.println("已经到达栈底");
        }

        int tmp = datas[top];
        top--;
        return tmp;
    }

    public void printInfo(){
        System.out.println("栈大小：" + this.size);
        System.out.println("栈顶位置：" + this.top);

        System.out.println("栈内元素：" );
        for(int i = 0;i<=this.top ;i++){
            System.out.println(this.datas[i]);
        }
    }
}
