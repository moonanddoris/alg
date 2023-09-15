package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * leetcode102 medium 二叉树的层序遍历
 * @author lixinhai
 * @date 2023/9/15
 */
public class Solution102 {

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

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }

        // 两个队列交替使用
        Queue<TreeNode> pingQueue = new LinkedList<>();
        Queue<TreeNode> pongQueue = new LinkedList<>();

        pingQueue.offer(root);

        Queue<TreeNode> targetQueue = pingQueue; //读队列
        Queue<TreeNode> otherQueue = pongQueue; //写队列

        List<Integer> list = new ArrayList<>();


        while (!pingQueue.isEmpty() || !pongQueue.isEmpty() ){

            TreeNode node = targetQueue.poll();
            //放入list
            list.add(node.val);

            if(null != node.left){
                otherQueue.offer(node.left);
            }
            if(null != node.right){
                otherQueue.offer(node.right);
            }


            if(targetQueue.isEmpty()){
                // 队列空了 切换
                if(targetQueue == pingQueue){
                    targetQueue = pongQueue;
                    otherQueue = pingQueue;
                }
                else {
                    targetQueue = pingQueue;
                    otherQueue = pongQueue;
                }

                result.add(list);
                list = new ArrayList<>();
            }
        }

        return result;
    }
}
