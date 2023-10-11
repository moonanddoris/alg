package leetcode;

/**
 * leetcode114 medium 二叉树展开为链表
 * @author lixinhai
 * @date 2023/9/21
 */
public class Solution114 {

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

    public static void flatten(TreeNode root) {
        if(null == root){
            return;
        }

        flatten(root.left);
        flatten(root.right);

        TreeNode n = root.left;
        if(n != null){
            while (n.right!=null){n=n.right;}
        }

        TreeNode tmp = root.right;
        if(null != root.left) {
            root.right = root.left;
        }
        if(n != null){
            n.right = tmp;
        }
        root.left = null;
    }

    public static void main(String[] args){
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);

//        node1.left=node2;
        node1.right=node3;

        flatten(node1);

        System.out.println("888888888888888888888");

    }
}
