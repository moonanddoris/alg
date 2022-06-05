package leetcode;

/**
 * leetcode141 easy 环形链表
 * @author lixinhai
 * @date 2022/6/4
 */
public class Solution141 {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * 检测环
     * @param head
     * @return
     */
    public static boolean hasCycle(ListNode head){
        if(head == null || head.next==null){
            return false;
        }

        ListNode slow=head, fast=head;

        do{
            slow = slow.next;
            fast = fast.next.next;

        }while (slow != fast && fast!=null && fast.next!=null);

        if(fast == null){
            return false;
        }
        else {
            return true;
        }
    }

    public static void main(String[] args){

        ListNode n5 = new ListNode(5);
        ListNode n4 = new ListNode(4, n5);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode n1 = new ListNode(1, n2);

        ListNode head = n1;

        n5.next = n2;

        head.next=head;
        boolean a = hasCycle(head);

        while (head != null){
            System.out.println("================="+head.val);
            head=head.next;
        }

    }
}
