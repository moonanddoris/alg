package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * leetcode160 easy 相交链表
 * @author lixinhai
 * @date 2022/6/4
 */
public class Solution160 {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * 最容易想到的解法 两个set进行保存历史节点
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB){

        Set<ListNode> setA = new HashSet<>();
        Set<ListNode> setB = new HashSet<>();

        while (headA != null || headB != null){
            if(headA != null){

                if(setB.contains(headA)){
                    return headA;
                }else {
                    setA.add(headA);
                }

                headA = headA.next;
            }

            if(headB != null){

                if(setA.contains(headB)){
                    return headB;
                }else {
                    setB.add(headB);
                }

                headB = headB.next;
            }
        }

        return null;

    }

    /**
     * 先计算两个链表的长度差 然后一起遍历，找第一个相遇节点
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode2(ListNode headA, ListNode headB){
        if(headA == null || headB == null){
            return null;
        }
        if(headA == headB){return headA;}

        int lenA=0, lenB=0;
        ListNode tmpA = headA, tmpB = headB;
        while (tmpA != null){
            lenA++;
            tmpA=tmpA.next;
        }

        while (tmpB != null){
            lenB++;
            tmpB=tmpB.next;
        }

        int cnt = 0;
        if(lenA > lenB){
            cnt = lenA - lenB;
            while (cnt-- > 0 ){
                headA=headA.next;
            }
        }
        else {
            cnt = lenB - lenA;
            while (cnt-- > 0 ){
                headB=headB.next;
            }
        }

        while (headA != headB){
            headA=headA.next;
            headB=headB.next;
        }

        return headA;
    }

    public static void main(String[] args){

        ListNode n5 = new ListNode(5);
        ListNode n4 = new ListNode(4, n5);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode n1 = new ListNode(1, n2);

        ListNode head = n1;

        ListNode headA = n1;

        ListNode m1 = new ListNode(10, n3);
        head = m1;


        ListNode t = getIntersectionNode2(n1, m1);

        while (head != null){
            System.out.println("================="+head.val);
            System.out.println("=================+++++++++"+head.hashCode());
            System.out.println("=================---------"+head);
            head=head.next;
        }

    }
}
