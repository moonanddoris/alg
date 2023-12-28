package leetcode;

import java.util.*;

/**
 * leetcode337 medium 打家劫舍3
 * @author lixinhai
 * @date 2023/10/25
 */
public class Solution337 {

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
     * @param root
     * @return
     */
    public static int robTest(TreeNode root) {
        List<Integer> list = getValList(root);
        return robInner(list);
    }

    public static int robInner(List<Integer> datas) {
        if(datas.size() == 0){
            return 0;
        }
        if(datas.size() == 1){
            return datas.get(0);
        }
        if(datas.size() == 2){
            return Math.max(datas.get(0), datas.get(1));
        }


        int curData = datas.get(0);

        int fisrt = curData + robInner(datas.subList(2, datas.size()));
        int second = robInner(datas.subList(1, datas.size()));

        return Math.max(fisrt, second);
    }

    public static List<Integer> getValList(TreeNode root) {

        List<Integer> result = new ArrayList<>();

        int pingResult=0;
        int pongResult=0;

        Queue<TreeNode> pingQueue = new LinkedList<>();
        Queue<TreeNode> pongQueue = new LinkedList<>();

        int flag = 0; //0=ping 1=pong
        pingQueue.offer(root);

        while (!pingQueue.isEmpty() || !pongQueue.isEmpty()){

            if(flag==0 && !pingQueue.isEmpty()){

                TreeNode pingOne = pingQueue.poll();
                if(null != pingOne){
                    pingResult += pingOne.val;

                    if(null != pingOne.left){
                        pongQueue.offer(pingOne.left);
                    }
                    if(null != pingOne.right){
                        pongQueue.offer(pingOne.right);
                    }
                }

                if(pingQueue.isEmpty()){
                    flag=1;
                    result.add(pingResult);
                    pingResult=0;
                }
            }

            if(flag==1 && !pongQueue.isEmpty()) {

                TreeNode pongOne = pongQueue.poll();
                if(null != pongOne){
                    pongResult += pongOne.val;

                    if(null != pongOne.left){
                        pingQueue.offer(pongOne.left);
                    }
                    if(null != pongOne.right){
                        pingQueue.offer(pongOne.right);
                    }
                }

                if(pongQueue.isEmpty()){
                    flag=0;
                    result.add(pongResult);
                    pongResult=0;
                }
            }
        }
        return result;
    }

    /**
     * 递归+缓存
     * @param root
     * @param cache
     * @return
     */
    public static int robIn(TreeNode root, Map<TreeNode, Integer> cache) {
        if(cache.containsKey(root)){
            return cache.get(root);
        }

        if(null == root){
            cache.put(root, 0);
            return 0;
        }

        // 包含当前节点
        int firstMax = 0;
        int firstLeft = 0;
        if(root.left != null){

            int i = robIn(root.left.left, cache);
            firstLeft+=i;

            i = robIn(root.left.right, cache);
            firstLeft+=i;

        }
        int firstRight=0;
        if(root.right != null){

            int i = robIn(root.right.left, cache);
            firstRight+=i;

            i = robIn(root.right.right, cache);
            firstRight+=i;
        }

        firstMax+=root.val + firstRight +firstLeft;

        // 不包含当前节点
        int secondMax=0;

        int i = robIn(root.left, cache);
        secondMax += i;

        i = robIn(root.right, cache);
        secondMax += i;

        cache.put(root, Math.max(firstMax, secondMax));
        return Math.max(firstMax, secondMax);
    }

    public static int rob(TreeNode root) {
        Map<TreeNode, Integer> cache = new HashMap<>();
        return robIn(root, cache);
    }

    public static void main(String[] args){

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(2);
        root.left.left.left = new TreeNode(3);

        int rob = rob(root);

        System.out.println("------------" +  rob);

    }

}
