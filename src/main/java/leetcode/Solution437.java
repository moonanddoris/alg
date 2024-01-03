package leetcode;

/**
 * leetcode437 medium 路径之和3
 * @author lixinhai
 * @date 2023/10/25
 */
public class Solution437 {
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

    public static int pathSum(TreeNode root, int targetSum) {
        if(null == root){
            return 0;
        }
        int re=0;
        re += rootSum(root, targetSum);

        re += pathSum(root.left, targetSum);
        re += pathSum(root.right, targetSum);
        return re;
    }

    /**
     * 包含根节点
     * @param root
     * @param targetSum
     * @return
     */
    public static int rootSum(TreeNode root, long targetSum) {
        if(null == root){
            return 0;
        }

        int re=0;

        if(root.val == targetSum){
            re +=1; // 包括重复路径
//            return 1; // 不包括重复路径
        }

        re += rootSum(root.left, targetSum - root.val);
        re += rootSum(root.right, targetSum - root.val);

        return re;
    }


    public static void main(String[] args){

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        root.right = new TreeNode(1);

        System.out.println("------------" +  pathSum(root, 1));

    }
}
