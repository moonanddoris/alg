package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * leetcode101 easy 对称二叉树
 * @author lixinhai
 * @date 2022/9/16
 */
public class Solution101 {

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
     * 1.中序遍历结果是回文数
     * 这种依赖二叉树是完全二叉树的情况
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        List<TreeNode> re = new ArrayList();
        midOrder(root, re);

        if(re.size() %2 == 0){return false;}

        int i = 0, j= re.size()-1;
        while (j > i && re.get(i++).val == re.get(j--).val){ }

        return j==i;
    }

    /**
     * 中序遍历
     * @return
     */
    public static void midOrder(TreeNode root, List<TreeNode> result){
        if(root == null){return;}

        if(root.left != null){midOrder(root.left, result);}

        result.add(root);

        if(root.right != null){midOrder(root.right, result);}
    }

    /**
     * 2.按层遍历 每一层是回文数，中间如果有不符的，提前结束
     * 以上两种方法都有缺陷，依赖于完全二叉数，且如果有重复值，可能会误判
     * 所以应该补全为完全二叉树，在空节点填充null
     * @param root
     * @return
     */
    public boolean isSymmetricPro(TreeNode root) {
        if(root==null){return false;}

        Queue<TreeNode> pingQueue = new LinkedList<>();
        pingQueue.offer(root);
        boolean pingHasChild = true;

        //pingQueue.poll();
        Queue<TreeNode> pongQueue = new LinkedList<>();
        boolean pongHasChild = false;

        while (!pingQueue.isEmpty() || !pongQueue.isEmpty()){

            if(!pingQueue.isEmpty()){
                if(!innerIsSymmetric((LinkedList<TreeNode>) pingQueue)){return false;}

                // 继续下层遍历
                if(pingHasChild){
                    pongHasChild = false;
                    while (!pingQueue.isEmpty()){
                        TreeNode tmp = pingQueue.poll();
                        if(tmp != null){
                            if(tmp.left!=null || tmp.right!=null){pongHasChild=true;}
                            pongQueue.offer(tmp.left);
                            pongQueue.offer(tmp.right);
                        }
                        else {
                            pongQueue.offer(null);
                            pongQueue.offer(null);
                        }
                    }
                }
                else {return true;}
            }

            if(!pongQueue.isEmpty()){
                if(!innerIsSymmetric((LinkedList<TreeNode>) pongQueue)){return false;}

                // 继续下层遍历
                if(pongHasChild){
                    pingHasChild = false;
                    while (!pongQueue.isEmpty()){
                        TreeNode tmp = pongQueue.poll();
                        if(tmp != null){
                            if(tmp.left!=null || tmp.right!=null){pingHasChild=true;}
                            pingQueue.offer(tmp.left);
                            pingQueue.offer(tmp.right);
                        }
                        else {
                            pingQueue.offer(null);
                            pingQueue.offer(null);
                        }
                    }
                }
                else {return true;}
            }
        }
        return true;
    }

    public boolean innerIsSymmetric(LinkedList<TreeNode> list){
        int i = 0, j= list.size()-1;
        while (j > i){
            if(list.get(i) != null && list.get(j)!=null){
                if(list.get(i).val == list.get(j).val){
                    i++;j--;
                }
                else {
                    return false;
                }
            }
            else if(list.get(i) == null && list.get(j)==null){
                i++;j--;
            }
            else {
                return false;
            }
        }

        return true;
    }

    /**
     * 递归解法
     * @param root
     * @return
     */
    public boolean isSymmetricRecursion(TreeNode root) {
        if(root==null){return false;}
        return compare(root.left, root.right);
    }

    public boolean compare(TreeNode left, TreeNode right) {
        if(null == left && null ==right){
            return true;
        }
        else if(null!=left && null!=right){
            if(left.val == right.val){
                return compare(left.left, right.right) && compare(left.right, right.left);
            }
            else {
                return false;
            }

        }
        else {
            return false;
        }
    }

    /**
     * 迭代解法
     * @param root
     * @return
     */
    public boolean isSymmetricIteration(TreeNode root) {
        if(root==null){return false;}
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);

        while (!queue.isEmpty()){
            TreeNode tmp1 = queue.poll();
            TreeNode tmp2 = queue.poll();

            if(null == tmp1 && null == tmp2){

            }
            else if(null != tmp1 && null != tmp2){
                if(tmp1.val == tmp2.val){
                    queue.offer(tmp1.left);
                    queue.offer(tmp2.right);

                    queue.offer(tmp1.right);
                    queue.offer(tmp2.left);
                }
                else {
                    return false;
                }

            }
            else {
                return false;
            }
        }

        return true;
    }

}
