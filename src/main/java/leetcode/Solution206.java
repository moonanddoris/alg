package leetcode;

/**
 * leetcode206 easy 反转链表
 * @author lixinhai
 * @date 2022/6/3
 */
public class Solution206 {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static ListNode reverseList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        ListNode curr = head;
        ListNode pre = null;
        ListNode next = head.next;

        while (next != null){

            ListNode tmp = curr.next.next;

            curr.next = pre;

            pre = curr;
            curr = next;
            next = tmp;
        }

        curr.next = pre;

        return curr;
    }

    // 这种写法更简洁一些
    public static ListNode reverseList2(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        ListNode curr = head;
        ListNode pre = null;

        while (curr != null){

            ListNode tmp = curr.next;
            curr.next = pre;

            pre = curr;
            curr = tmp;
        }

        return pre;
    }
    public static void main(String[] args){

        ListNode n5 = new ListNode(5);
        ListNode n4 = new ListNode(4, n5);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode n1 = new ListNode(1, n2);

        ListNode head = reverseList2(n1);

        while (head != null){
            System.out.println("================="+head.val);
            head=head.next;
        }

    }

}
