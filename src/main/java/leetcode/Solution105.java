package leetcode;

/**
 * leetcode105 medium 从前序与中序遍历序列构造二叉树
 * @author lixinhai
 * @date 2023/9/19
 */
public class Solution105 {
    public static class TreeNode {
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

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
    }

    public static TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if(preEnd<preStart || inEnd<inStart){
            return null;
        }

        //根节点
        int rootVal = preorder[preStart];

        TreeNode root = new TreeNode(rootVal);

        //左子树
        int tmp = inStart;
        while (inorder[tmp] != rootVal){tmp++;}
        int len = tmp-inStart;

        TreeNode left = buildTree(preorder, preStart+1, preStart+len, inorder, inStart, tmp-1);
        TreeNode right = buildTree(preorder, preStart+len+1, preEnd, inorder, tmp+1, inEnd);

        root.left = left;
        root.right = right;

        return root;
    }

    public static void main(String[] args){
        int[] pre = {1,2};
        int[] in = {2,1};

        TreeNode treeNode = buildTree(pre, in);

        System.out.println("99999999999999999");

    }

}
