package leetcode;

import java.util.Arrays;

/**
 * leetcode152 medium 乘积最大子数组
 * @author lixinhai
 * @date 2022/5/22
 */
public class Solution152 {


    // 比较容易想到 回溯算法 穷举所有情况 O(n^2)

    public static int maxProduct(int[] nums) {

        int[] status = new int[nums.length];
        int result = nums[0];

        for(int i=0;i<nums.length;i++){

            int j=0;
            for(;j<i;j++){
                status[j] = status[j] * nums[i];

                result = Math.max(status[j], result);
            }
            status[j] = nums[i];
            result = Math.max(status[j], result);

        }

        return result;
    }


    // 使用动态规划进行优化，每次遍历 仅保存最大值和最小值；保存最小值的意义在于负数相乘，最小值会变成最大值
    public static int maxProductPro(int[] nums) {

        int imax, imin, result;
        result=imin=imax=nums[0];

        for(int i=1;i<nums.length;i++){
            if(nums[i] > 0){
                imax = Math.max(nums[i], imax * nums[i]);
                imin = Math.min(nums[i], imin * nums[i]);
            }
            else {
                // 负数逻辑比较特殊
                int tmpMax = imax; // 这里相互依赖，需要中间临时变量
                imax = Math.max(nums[i], imin * nums[i]);
                imin = Math.min(nums[i], tmpMax * nums[i]);
            }

            System.out.println("---------imin"+imin);
            System.out.println("---------imax"+imax);

            result = Math.max(imax, result);
        }

        return result;
    }

    public static void main(String[] args){

        int[] test = {2,3,-2,-4};
        System.out.println("----------:"+ maxProduct(test));
        System.out.println("----------:"+ maxProductPro(test));

    }
}
