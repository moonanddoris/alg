package stack;

/**
 * @author lixinhai
 * @date 2022/3/16
 */
public class StackOpt {

    public static void main(String[] args){
        MyStack testStack = new MyStack(10);

        testStack.pushIn(8);
        testStack.pushIn(9);
        testStack.pushIn(99);

        testStack.popOut();
        testStack.popOut();

        testStack.printInfo();
    }

}
