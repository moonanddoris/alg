package leetcode;

/**
 * leetcode2 medium 两数相加
 * @author lixinhai
 * @date 2022/7/11
 */
public class Solution2 {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null){return null;}
        if(l1 == null){return l2;}
        if(l2 == null){return l1;}

        ListNode re = null, result = null;

        // 进位
        int carry = 0;

        while (!(carry==0 && l1==null && l2==null)){
            int s1 = null==l1 ? 0 : l1.val;
            int s2 = null==l2 ? 0 : l2.val;

            if(l1!=null){ l1 = l1.next;}
            if(l2!=null){ l2 = l2.next;}

            int tmp = (s1 + s2 + carry) %10;
            carry = (s1 + s2 + carry) /10;

            ListNode tmpNode = new ListNode();
            tmpNode.val = tmp;

            if(re == null){
                re = tmpNode;
                result = re;
            }
            else {
                re.next = tmpNode;
                re = tmpNode;
            }
        }

        return result;
    }

    public static void main(String[] args){

        ListNode x3 = new ListNode(3);
        ListNode x4 = new ListNode(4, x3);
        ListNode x2 = new ListNode(2, x4);


        ListNode y4 = new ListNode(4);
        ListNode y6 = new ListNode(6, y4);
        ListNode y5 = new ListNode(5, y6);

        addTwoNumbers(x2, y5);

    }
}
