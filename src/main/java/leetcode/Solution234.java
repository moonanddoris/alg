package leetcode;

import java.util.Stack;

/**
 * leetcode234 easy 回文链表
 * @author lixinhai
 * @date 2022/10/12
 */
public class Solution234 {

 public class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

    /**
     * 先计算长度 将前半部分入栈 和后半部分进行比对 时间复杂度On 空间复杂度On
     * 其它的解法 1.转化为数组 通过双向指针遍历  2.将后半部分反转 和前半部分比较，然后恢复原样 时间复杂度On 空间复杂度O1
     * @param head
     * @return
     */
   public boolean isPalindrome(ListNode head) {
     if(null == head){return false;}

     ListNode p = head;
     int len = 0;
     while (p != null){
         len++;
         p=p.next;
     }

     if(len == 1){return true;}

     Stack<ListNode> firstHalfStack = new Stack<>();
     int mid = len/2;

     int cursor = 0;
     ListNode tmp = head;
     while (tmp != null){
         if(cursor < mid){
             firstHalfStack.push(tmp);
         }
         else {

             if(len%2 == 0) {
                 if (tmp.val != firstHalfStack.pop().val) {
                     return false;
                 }
             }
             else {
                 if(cursor > mid){
                     if (tmp.val != firstHalfStack.pop().val) {
                         return false;
                     }
                 }
             }
         }

         tmp=tmp.next;
         cursor++;
     }
     return true;
   }
}
