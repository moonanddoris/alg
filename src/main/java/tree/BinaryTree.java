package tree;

import java.util.Deque;
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
     * 按层Z字形遍历 使用双向队列作为辅助存储空间
     * flag=true 从ping左侧读取 写入pong队列
     * flag=false 从pong右侧读取 写入ping队列
     * 写入时，不需要更换方向
     */
    public static void zShapeLevelOut(TreeNode treeNode){
        if(treeNode == null){return;}

        Deque<TreeNode> pingQueue = new LinkedList<>();
        Deque<TreeNode> pongQueue = new LinkedList<>();

        pingQueue.offerFirst(treeNode);
        boolean flag=true;

        while (!pingQueue.isEmpty() || !pongQueue.isEmpty()) {

            if (flag) {
                while (!pingQueue.isEmpty()){
                    TreeNode tmpNode = pingQueue.pollFirst();
                    System.out.println(tmpNode.getData());

                    if(tmpNode.getLeft() != null){
                        pongQueue.offerFirst(tmpNode.getLeft());
                    }
                    if(tmpNode.getRight() != null){
                        pongQueue.offerFirst(tmpNode.getRight());
                    }
                }

                flag=false;

            } else {
                while (!pongQueue.isEmpty()){
                    TreeNode tmpNode = pongQueue.pollLast();
                    System.out.println(tmpNode.getData());

                    if(tmpNode.getLeft() != null){
                        pingQueue.offerFirst(tmpNode.getLeft());
                    }
                    if(tmpNode.getRight() != null){
                        pingQueue.offerFirst(tmpNode.getRight());
                    }
                }

                flag=true;
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


    //TODO 二叉查找树 的查找 插入 删除

    //TODO 分裂细胞 若干时间后的细胞个数

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


        System.out.println("-------------------zShape");
        zShapeLevelOut(testTree);
    }


}
