package leetcode;

import java.util.Arrays;

/**
 * leetcode121 easy 买卖股票最佳时机
 * @author lixinhai
 * @date 2022/9/22
 */
public class Solution121 {
    /**
     * 求一个子区间，右边界和左边界 差值最大
     * 分治法，二分为左右两个子区间，目标区间可能出现在左侧或者右侧，也可能横跨
     * 整体的复杂度是 On*logn
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        return maxProfitInner(prices, 0, prices.length-1);
    }

    public static int maxProfitInner(int[] prices, int left, int right) {

        if(left == right){return 0;}
        if(right - left == 1){return Math.max(0,prices[right] - prices[left]);}

        int leftMax = maxProfitInner(prices, left, (right+left)/2);
        int rightMax = maxProfitInner(prices, (right+left)/2 + 1, right);

        int[] a = Arrays.copyOfRange(prices, left, (right+left)/2 + 1);
        int[] b = Arrays.copyOfRange(prices, (right+left)/2 +1, right + 1);

        int midMax;
        try {
            midMax = Arrays.stream(b).max().getAsInt() - Arrays.stream(a).min().getAsInt();
        }
        catch (Exception e){
            midMax = 0;
        }

        return Math.max(Math.max(leftMax, rightMax), midMax);
    }

    public static void main(String[] args){
        int[] test = {1, 0, 11, 4, 7, 3, 10};

        int re = maxProfit(test);

        System.out.println(re);

    }

}


