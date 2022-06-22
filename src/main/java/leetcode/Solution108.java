package leetcode;

/**
 * 有序数组转化二叉搜索树
 * @author lixinhai
 * @date 2022/6/20
 */
public class Solution108 {

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

    public static TreeNode sortedArrayToBST(int[] nums) {
        if(null==nums || nums.length==0){ return null; }
        return sortedArrayToBST(nums, 0, nums.length-1);
    }

    public static TreeNode sortedArrayToBST(int[] nums, int left, int right) {
        if(right < 0 || left<0){return null;}
        if(left > nums.length-1 || right> nums.length-1){return null;}
        if(left > right){return null;}

        if(left == right){
            return new TreeNode(nums[left]);
        }

        int mid = left + (right-left)/2;

        TreeNode midNode = new TreeNode(nums[mid]);

        TreeNode leftNode = sortedArrayToBST(nums, left, mid-1);
        TreeNode rightNode = sortedArrayToBST(nums, mid+1, right);

        midNode.left = leftNode;
        midNode.right = rightNode;

        return midNode;
    }

    public static void main(String[] args){
        int[] nums = new int[] {-10,-3,0,5,9};

        TreeNode re = sortedArrayToBST(nums);

        System.out.println(re);
    }
}
