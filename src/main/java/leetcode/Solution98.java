package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode98 medium 验证二叉搜索树
 * @author lixinhai
 * @date 2023/9/15
 */
public class Solution98 {
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
     * 中序遍历 应该是升序队列
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {

        List<TreeNode> list = new ArrayList<>();
        return midTraversal(root, list);
    }

    public boolean midTraversal(TreeNode node, List<TreeNode> nodeList){

        if(node.left != null){
            boolean b = midTraversal(node.left, nodeList);
            if(!b){
                return false;
            }
        }

        if(nodeList.size() > 0){
            if(nodeList.get(nodeList.size()-1).val >= node.val){
                return false;
            }
        }
        nodeList.add(node);

        if(node.right != null){
            boolean b = midTraversal(node.right, nodeList);
            if(!b){
                return false;
            }
        }

        return true;
    }




    public static void main (String[] args){

    }

}
