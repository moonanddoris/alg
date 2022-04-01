package linked;

/**
 * @author lixinhai
 * @date 2022/3/10
 */
public class NodeOpt {
    /**
     *
     * @param listLen
     * @return head node
     */
    public static MyNode buildNodeList(int listLen){
        if(listLen <= 0){
            return null;
        }
        MyNode headNode = null;
        MyNode curNode = null;
        for(int i=0; i < listLen; i++){

            MyNode tmp = new MyNode(i+1, null);
            if(i == 0){
                headNode = tmp;
            }
            if(curNode != null) {
                curNode.setNext(tmp);
            }
            curNode=tmp;
        }

        // create a loop
        //curNode.setNext(headNode);

        return headNode;
    }

    /**
     *
     * @param headNode
     * @return
     */
    public static MyNode reverseNodeList(MyNode headNode){
        // empty or only one
        if(headNode == null || headNode.getNext() == null){
            return headNode;
        }

        MyNode i, j, tmp;

        i = headNode;
        j = headNode.getNext();

        while (j != null){
            tmp = j.getNext();
            j.setNext(i);

            i=j;
            j = tmp;
        }

        // 头节点特殊处理
        //headNode.setNext(null);

        return i;
    }

    /**
     * 快慢指针  节点路径计算  标记法
     * @param head
     * @return
     */
    public static boolean checkLoop(MyNode head){
        MyNode slowOne, fastOne;
        slowOne = fastOne = head;

        while (fastOne !=null && fastOne.getNext()!=null){
            slowOne = slowOne.getNext();
            fastOne = fastOne.getNext().getNext();

            if(slowOne == fastOne){
                return true;
            }
        }

        return false;
    }

    public static MyNode getMidNode(MyNode head){
        MyNode slowOne, fastOne;
        slowOne = fastOne = head;

        while (fastOne !=null && fastOne.getNext()!=null){
            slowOne = slowOne.getNext();
            fastOne = fastOne.getNext().getNext();
        }
        return slowOne;
    }

    public static MyNode delBackN(MyNode head, int num){
        if(num <= 0){
            return head;
        }

        // pre 和 cur之间长度 num
        MyNode cur, pre, tmp;
        tmp = pre = cur = head;

        // i 不是从0开始
        for (int i=1; i<num; i++){
            cur = cur.getNext();
            if(cur == null){
                System.out.println("长度异常");
                return head;
            }
        }

        while (cur.getNext()!=null){
            tmp = pre;
            pre = pre.getNext();
            cur = cur.getNext();
        }

        //System.out.println(tmp.getData());
        //System.out.println(pre.getData());

        if(tmp == head){
            MyNode tt = head.getNext();
            head.setNext(null);
            head = tt;
        }
        else {
            tmp.setNext(pre.getNext());
            pre.setNext(null);
        }

        return head;
    }

    public static MyNode mergeTowSortedList(MyNode head1, MyNode head2){
        MyNode mainNodeCur, subNodeCur, nodeTmp;

        if(head1.getData() <= head2.getData()){
            mainNodeCur = head1;
            subNodeCur = head2;
        }
        else {
            mainNodeCur = head2;
            subNodeCur = head1;
        }

        MyNode re = mainNodeCur;

        while (mainNodeCur.getNext() != null && subNodeCur !=null) {

            // 找见主链表的 开始插入节点
            while (mainNodeCur.getNext() != null && subNodeCur.getData() > mainNodeCur.getNext().getData() ) {
                mainNodeCur = mainNodeCur.getNext();
            }

            nodeTmp = mainNodeCur.getNext();
            mainNodeCur.setNext(subNodeCur);
            mainNodeCur = nodeTmp;

            // 找见子链表的 结束节点
            while (subNodeCur.getNext()!=null && subNodeCur.getNext().getData() <= mainNodeCur.getData()) {
                subNodeCur = subNodeCur.getNext();
            }

            nodeTmp = subNodeCur.getNext();
            subNodeCur.setNext(mainNodeCur);
            subNodeCur = nodeTmp;
        }

        return re;
    }

    public static void printList(MyNode head){
        MyNode a = head;
        do{
            System.out.println(a.getData());
            a = a.getNext();
        }while (a != null);
    }


    public static void main(String[] args){

        MyNode test = buildNodeList(10);

        //printList(test);

        MyNode test1 = buildNodeList(3);

        MyNode newOne = mergeTowSortedList(test, test1);
        printList(newOne);

        //MyNode newOne = delBackN(test, 10);

        //printList(newOne);

        //System.out.println(getMidNode(test).getData());

        //System.out.println(checkLoop(test));

        //MyNode rTest = reverseNodeList(test);
        //printList(rTest);
    }
}
