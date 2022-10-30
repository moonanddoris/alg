package leetcode;

/**
 * leetcode543 easy 二叉树的直径
 * @author lixinhai
 * @date 2022/10/27
 */
public class Solution543 {

    public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
    }


    public int diameterOfBinaryTree(TreeNode root) {
        if(null == root){
            return 0;
        }

        int leftDeepth = treeDeepth(root.left);
        int rightDeepth = treeDeepth(root.right);

        int leftDiameter = diameterOfBinaryTree(root.left);
        int rightDiameter = diameterOfBinaryTree(root.right);

        return Math.max(leftDeepth+rightDeepth, Math.max(leftDiameter, rightDiameter));
    }

    public int treeDeepth(TreeNode root){
        if(root == null){return 0;}

        return 1+ Math.max(treeDeepth(root.left), treeDeepth(root.right));
    }


}
