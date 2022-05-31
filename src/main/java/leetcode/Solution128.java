package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * leetcode128 medium 最长连续序列
 * @author lixinhai
 * @date 2022/5/31
 */
public class Solution128 {

    /**
     *
     * @param nums
     * @return
     */
    public static int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        int count = 0;

        for(int x : nums){
            numSet.add(x);
        }

        for(int i=0; i<nums.length; i++){
            // 只有连续数组的一个元素 会进入内循环，进入之后，也只会累计循环n次（加上第一个元素的外层循环），复杂度O(n)
            if(numSet.contains(nums[i]-1)){
                continue;
            }

            int innerCnt = 1;

            int j=1;
            while (numSet.contains(nums[i]+j)){
                innerCnt += 1;
                j++;
            }

            /*
            for(int j=1; j<= nums.length-1 ;j++){
                if(numSet.contains(nums[i]+j)){
                    innerCnt += 1;
                }
                else {
                    break;
                }
            }
            */

            count = Math.max(count, innerCnt);
        }

        return count;
    }

    public static void main(String[] args){

        //int[] test = {100,4,200,1,3,2};
        int[] test = {100,4,200,101,0,2};

        System.out.println("------------------"+ longestConsecutive(test));

    }
}
