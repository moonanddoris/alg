package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode15 medium 三数之和
 * @author lixinhai
 * @date 2022/5/22
 */
public class Solution15 {

    //比较容易想到 转化为 两数之和：先确定第一个数x，然后在剩余的序列中求，两数之和为taget-x的组合
    //但是这样做的话，有个比较难解决的问题  就是解的重复问题

    //经典的解决方案是，先排序+双指针遍历，重复解的问题，可以在排序后 判断相邻数相等来避免
    public static List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        if(nums.length < 3){
            return result;
        }

        // 先排序
        Arrays.sort(nums);

        for(int i=0; i<=nums.length-3; i++){

            // 重复元素之前已经计算过，直接跳过，避免重复解
            if(i>0 && nums[i]==nums[i-1]){
                continue;
            }

            for(int m=i+1,n= nums.length-1; m<n;){
                if(nums[m] + nums[n] == 0-nums[i]){
                    result.add(Arrays.asList(nums[i], nums[m], nums[n]));
                    // 继续寻找

                    //这里 避免两数之和的重复解
                    while (nums[m++] == nums[m] && m<n){}
                    //m++;
                    n--;
                }
                else if(nums[m] + nums[n] > 0-nums[i]){
                    // 右指针向左
                    n--;
                }
                // nums[m] + nums[n] < target
                else {
                    // 左指针向右
                    m++;
                }
            }
        }

        return result;
    }

    public static void main(String[] args){
        System.out.println("---------start--------");

        //int[] test = {-1,0,1,2,-1,-4};
        int[] test = {-2,0,0,2,2};

        System.out.println("-----------------result:" + threeSum(test));

        System.out.println("---------end--------");
    }
}

