package leetcode;

/**
 * leetcode226 easy 反转二叉树
 * @author lixinhai
 * @date 2022/10/8
 */
public class Solution226 {

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


    /**
     * 递归解法 相当于深度优先遍历
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if(null == root){return null;}

        TreeNode invertLeft = invertTree(root.left);
        TreeNode invertRight = invertTree(root.right);

        root.left = invertRight;
        root.right = invertLeft;

        return root;
    }

    //TODO(lxh) 使用辅助队列进行 广度/按层优先遍历
}
