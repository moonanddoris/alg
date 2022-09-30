package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * leetcode104 easy 二叉树最大深度
 * @author lixinhai
 * @date 2022/9/20
 */
public class Solution104 {

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
     * 递归解法
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if(null == root){return 0;}

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    /**
     * 迭代解法 层遍历 计算层数
     * 以下代码可以优化，使用一个队列通过计数来实现 层数计算
     * @param root
     * @return
     */
    public int maxDepthPro(TreeNode root) {
        if(root==null){return 0;}

        Queue<TreeNode> pingQueue = new LinkedList<>();
        pingQueue.offer(root);

        Queue<TreeNode> pongQueue = new LinkedList<>();

        int cnt=0;
        while (!pingQueue.isEmpty() || !pongQueue.isEmpty()){

            if(!pingQueue.isEmpty()){
                while (!pingQueue.isEmpty()){
                    TreeNode tmp = pingQueue.poll();
                    if(null != tmp){
                        if(tmp.left != null){pongQueue.offer(tmp.left);}
                        if(tmp.right != null){pongQueue.offer(tmp.right);}
                    }
                }
                cnt+=1;
            }

            if(!pongQueue.isEmpty()){
                while (!pongQueue.isEmpty()){
                    TreeNode tmp = pongQueue.poll();
                    if(null != tmp){
                        if(tmp.left != null){pingQueue.offer(tmp.left);}
                        if(tmp.right != null){pingQueue.offer(tmp.right);}
                    }
                }
                cnt+=1;
            }
        }

        return cnt;
    }


}
