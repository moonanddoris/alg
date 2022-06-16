package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * leetcode94 easy 二叉树中序遍历
 * @author lixinhai
 * @date 2022/6/14
 */
public class Solution94 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> re = new ArrayList<>();

        //inorderTraversal(root, re);
        inorderTraversalV2(root, re);

        return re;
    }

    /**
     * 常规的递归解法
     * @param root
     * @param re
     */
    public static void inorderTraversal(TreeNode root, List<Integer> re) {

        if(null == root){ return; }

        inorderTraversal(root.left, re);
        re.add(root.val);
        inorderTraversal(root.right, re);
    }

    /**
     * 迭代版本
     * @param root
     * @param re
     */
    public static void inorderTraversalV2(TreeNode root, List<Integer> re) {
        if(null == root){return;}

        Stack<TreeNode> tmpSt = new Stack<>();
        TreeNode cur = root;

        while (true){

            // 不是叶子节点
            while (cur != null && (cur.left != null || cur.right != null)){
                tmpSt.push(cur);
                cur = cur.left;
            }

            // 左侧节点为空的情况
            if(cur != null){
                re.add(cur.val);
            }

            if(tmpSt.empty()){break;}

            cur = tmpSt.pop();
            re.add(cur.val);
            cur = cur.right;
        }
    }


    public static void main(String[] args) {

        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);

        n1.left = n2;
        n1.right = n3;

        n2.left = n4;
        n2.right = n5;
        n3.right = n6;

        System.out.println("---------------" + inorderTraversal(n1));

    }

}
