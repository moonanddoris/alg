package leetcode;

/**
 * leetcode236 medium 二叉树的最近公共祖先
 * @author lixinhai
 * @date 2023/9/22
 */
public class Solution236 {


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

    /**
     * 左右子树递归寻找 关键是判断递归计算的返回值，返回值有以下情况 1.公共祖先(非p和q) 2.p节点（子树包含p） 3.q节点（子树包含q）
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(null == root){
            return null;
        }

        TreeNode resultLeft = lowestCommonAncestor(root.left, p, q);
        TreeNode resultRight = lowestCommonAncestor(root.right, p, q);

        if(root.val != p.val && root.val!=q.val){

            if(null == resultLeft && null == resultRight){
                return null;
            }

            if(null != resultLeft && null != resultRight){
                return root;
            }

            if(null != resultLeft){
                return resultLeft;
            }

            return resultRight;
        }
        else if(root.val == p.val){

            return root;

        }
        else if(root.val == q.val){
            return root;
        }

        return null;
    }


}
