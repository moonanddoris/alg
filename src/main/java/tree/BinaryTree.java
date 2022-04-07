package tree;

import linked.MyNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lixinhai
 * @date 2022/4/2
 */
public class BinaryTree {

    /**
     * 二叉树的构建
     * 按层遍历的方法 进行构建；同样需要队列作为辅助 类似于按层输出的实现
     * @param inputData
     * @return
     */
    public static TreeNode buildBTree(int[] inputData){
        Queue<TreeNode> myQueue = new LinkedList<TreeNode>();
        TreeNode rootNode = new TreeNode(inputData[0]);

        myQueue.offer(rootNode);
        int tmpIndex = 1;

        while (tmpIndex < inputData.length  && !myQueue.isEmpty()){
            TreeNode treeNode = myQueue.poll();

            int leftData = inputData[tmpIndex++];
            TreeNode leftNode = new TreeNode(leftData);
            treeNode.setLeft(leftNode);
            myQueue.offer(leftNode);

            if(tmpIndex >= inputData.length){
                break;
            }

            int rightData = inputData[tmpIndex++];
            TreeNode rightNode = new TreeNode(rightData);
            treeNode.setRight(rightNode);
            myQueue.offer(rightNode);
        }

        return rootNode;
    }

    /**
     * 前序
     */
    public static void preOut(TreeNode treeNode){
        if(treeNode == null){return;}

        System.out.println(treeNode.getData());

        preOut(treeNode.getLeft());
        preOut(treeNode.getRight());
    }

    /**
     * 中序
     * @param treeNode
     */
    public static void midOut(TreeNode treeNode){
        if(treeNode == null){return;}

        midOut(treeNode.getLeft());
        System.out.println(treeNode.getData());
        midOut(treeNode.getRight());
    }

    /**
     * 后序
     */
    public static void postOut(TreeNode treeNode){
        if(treeNode == null){return;}

        postOut(treeNode.getLeft());
        postOut(treeNode.getRight());

        System.out.println(treeNode.getData());
    }

    /**
     * 按层遍历 使用队列作为辅助存储空间
     */
    public static void levelOut(TreeNode treeNode){
        if(treeNode == null){return;}

        Queue<TreeNode> myQueue = new LinkedList<TreeNode>();
        myQueue.offer(treeNode);


        while (!myQueue.isEmpty()) {
            TreeNode tmpNode = myQueue.poll();

            System.out.println(tmpNode.getData());

            if (tmpNode.getLeft() != null) {
                myQueue.offer(tmpNode.getLeft());
            }

            if (tmpNode.getRight() != null) {
                myQueue.offer(tmpNode.getRight());
            }
        }
    }

    /**
     * 包含n个节点的二叉树 有多少种形态
     * @param dataNum
     * @return
     */
    public static int getTreeCnt(int dataNum){
        //TODO 使用哈希表进行缓存优化
        if(dataNum < 0){
            return 0;
        }

        if(dataNum ==0 || dataNum == 1){
            return 1;
        }

        int re = 0;

        int cnt = dataNum -1;
        while (cnt >= 0){
            re += getTreeCnt(dataNum-1 -cnt) * getTreeCnt(cnt);
            cnt --;
        }

        return re;
    }

    public static void main(String[] args){


        int[] test = {1, 2, 3, 4, 5, 6, 7};
        TreeNode testTree = buildBTree(test);
        System.out.println("-------------------");
        preOut(testTree);
        System.out.println("-------------------");
        midOut(testTree);
        System.out.println("-------------------");
        postOut(testTree);
        System.out.println("-------------------");
        levelOut(testTree);

        //System.out.println(getTreeCnt(6));

    }


}
