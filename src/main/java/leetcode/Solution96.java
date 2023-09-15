package leetcode;

/**
 * leetcode96 medium 不同的二叉搜索树
 * @author lixinhai
 * @date 2023/9/14
 */
public class Solution96 {

    // 二叉搜索树满足以下条件：对于任一节点，它的左子树节点值都比它小，右子树节点值都比它大
    // 因为是1-n连续的数，所以和具体的数值并没关系 [1, 2, 3]和[4, 5, 6] 形态数量是一样的，只和数组长度有关系
    // 遍历1-n的数组，以每个元素作为根节点，它左侧的元素构建左子树，右侧节点构建右子数 F(k)=f(0~k-1)*f(k+1~n)
    // 最终结果为 S(n)=F(0) + F(1) + ... + F(n)
    // 很容易想到递归来实现，因为计算过程中有很多重复计算，可以使用map来缓存进行优化
    // 另外，可以想到，F(k)=F(n-k) 即有对称性，遍历到中间节点时就不需要继续遍历了


    // 解法二
    public static int numTrees(int n) {

        if(n <= 1){ return 1; }
        int re = 0;
        for(int x=1; x<=n/2; x++){
            System.out.print("-------计算过程-----" + n + "   ");
            System.out.println((x-1) + "乘以" + (n-x));
            re += numTrees(x-1) * numTrees(n-x);
        }

        if(n%2 ==  0){
            return re*2;
        }
        else {
            return re*2 + numTrees(n/2 +1-1) * numTrees(n - (n/2 +1));
        }
    }

    // 解法一
//    public static int numTrees(int n) {
//
//        Map<Integer, Integer> result = new HashMap<>(n);
//        return numTrees(n, result);
//    }
//
//    public static int numTrees(int n, Map<Integer, Integer> result) {
//        if(result.containsKey(n)){
//            return result.get(n);
//        }
//
//        if(n <= 1){ return 1; }
//        int re = 0;
//        for(int x=1; x<=n; x++){
//            System.out.print("-------计算过程-----" + n + "   ");
//            System.out.println((x-1) + "乘以" + (n-x));
//            re += numTrees(x-1, result) * numTrees(n-x, result);
//        }
//
//        result.put(n, re);
//        return re;
//    }


    public static void main(String[] args){
        int[] test = {1,2,3};

        System.out.println(numTrees(3));
    }
}
