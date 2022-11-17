package leetcode;

/**
 * leetcode19 medium 删除倒数第n个链表节点
 * @author lixinhai
 * @date 2022/11/17
 */
public class Solution19 {

  public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    /**
     * 双指针方法 还可以使用栈来辅助， 还可以先计算长度length  再删除第 length-n+1个节点
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
      if(null == head){return null;}
      if(n <= 0){return head;}

      ListNode slow = head, fast = head;
      ListNode slowPre = null;

      // 先让fast确定领先距离
      int len=n-1;
      while (len >0 ){
          fast = fast.next;
          len--;
      }

      while (fast.next != null){
          slowPre = slow;
          slow = slow.next;
          fast = fast.next;
      }

      // slow就是要被删除的节点
      if(null == slow.next){
          // 最后一个节点
          if(slowPre == null){
              // 且是第一个节点
              return null;
          }
          slowPre.next = null;
      }
      else {
          if(slowPre == null){
              // 且是第一个节点
              return slow.next;
          }
          slowPre.next = slow.next;
          slow.next = null;
      }

      return head;
    }

    public static void main(String [] args){

        ListNode n5 = new ListNode(5);
        ListNode n4 = new ListNode(4, n5);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode n1 = new ListNode(1, n2);

        ListNode head = n1;

        removeNthFromEnd(head, 2);

        while (head != null){
            System.out.println("================="+head.val);
            head=head.next;
        }


    }

}
