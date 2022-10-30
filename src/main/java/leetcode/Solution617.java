package leetcode;

/**
 * leetcode617 easy 合并二叉树
 * @author lixinhai
 * @date 2022/10/28
 */
public class Solution617 {

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

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {

        if(root1 == null && root2 == null){return null;}

        TreeNode newNode = new TreeNode();
        TreeNode newLeft,newRight;

        if(root1 != null && root2 != null){
            newNode.val = root1.val + root2.val;

            newLeft = mergeTrees(root1.left, root2.left);
            newRight = mergeTrees(root1.right, root2.right);
        }
        else if(root2 != null){
            newNode.val = root2.val;

            newLeft = mergeTrees(null, root2.left);
            newRight = mergeTrees(null, root2.right);

        }
        else {
            newNode.val = root1.val;

            newLeft = mergeTrees(root1.left, null);
            newRight = mergeTrees(root2.right, null);
        }

        newNode.left = newLeft;
        newNode.right = newRight;
        return newNode;
    }
}
