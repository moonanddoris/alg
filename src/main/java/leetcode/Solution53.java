package leetcode;

import java.util.Arrays;

/**
 * leecode53 easy 最大连续子序列和
 * @author lixinhai
 * @date 2022/5/21
 */
public class Solution53 {

    // 暴力破解法 遍历所有子序列组合 记录最大值 时间复杂度O(n^3)

    /**
     * 分析连续子序列和的计算规律 前面和小于0的子序列放弃
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {

        int maxSum = Integer.MIN_VALUE;

        int curSum = 0;

        for (int i=0; i<nums.length; i++){

            // 初始
            if(i == 0){
                maxSum = curSum = nums[i];
            }
            else {
                // 如果前面的和 小于0，则抛弃 重新累加
                if(curSum < 0){
                    curSum = 0;
                }

                curSum += nums[i];

                if(curSum > maxSum){
                    maxSum = curSum;
                }
            }
        }

        return maxSum;
    }

    // 归并算法 时间复杂度O(n*logn)
    // F(0,n-1) = max{F(0,n/2-1), F(n/2,n-1), merge(最大连续子序列横跨左右两空间，分别向左和向右找最大子序列和) }

    // 动态规划 类似0-1背包问题 F(i)=F(i-1)+nums[i]（以第i个元素结尾的子序列和，等于当前值 组合 第i-1个元素结尾的子序列的所有情况。特殊的，F(i)为0也是一种情况，即抛弃之前的子序列）
    // 在以上所有情况中，找最大值。时间复杂度O(n) 空间复杂度 O(k) 遍历状态最后一行的长度

    public static void main(String[] args){
        System.out.println("---------start--------");

        //int[] test = {-1, 0, 1, -3};
        int[] test = {-2,1,-3,4,-1,2,1,-5,4};
        int re = maxSubArray(test);

        System.out.println("-----------------result:" + re);

        System.out.println("---------end--------");
    }
}
