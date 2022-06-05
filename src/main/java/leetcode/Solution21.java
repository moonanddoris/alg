package leetcode;

/**
 * leetcode21 easy 合并有序链表
 * @author lixinhai
 * @date 2022/6/5
 */
public class Solution21 {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null){ return list2; }
        if(list2 == null){ return list1; }

        ListNode mainList, subList; // subList 合并到 mainList

        if(list1.val <= list2.val){
            mainList = list1;
            subList = list2;
        }
        else {
            mainList = list2;
            subList = list1;
        }

        while (mainList.next != null && subList != null){

            if(mainList.val <= subList.val ){
                if(mainList.next.val >= subList.val){
                    // 插入
                    ListNode tmp = mainList.next;
                    ListNode tmp1 = subList.next;

                    mainList.next = subList;
                    subList.next = tmp;

                    subList = tmp1;
                    mainList = mainList.next;
                }
                else {
                    // 向后
                    mainList = mainList.next;
                }
            }
            else {

            }
        }

        if(subList != null){
            mainList.next = subList;
        }

        return list1.val<= list2.val ? list1 : list2;
    }

    public static void main(String[] args){

        ListNode n5 = new ListNode(5);
        ListNode n4 = new ListNode(4, n5);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode n1 = new ListNode(1, n2);

        ListNode m6 = new ListNode(4);
        ListNode m4 = new ListNode(4, m6);
        ListNode m2 = new ListNode(3, m4);



        ListNode head = mergeTwoLists(n1, m2);

        while (head != null){
            System.out.println("================="+head.val);
            head=head.next;
        }

    }
}
