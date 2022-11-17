package leetcode;

import java.util.Arrays;

/**
 * leetcode31 medium 下一个排列
 * @author lixinhai
 * @date 2022/11/8
 */
public class Solution31 {

    public static void nextPermutation(int[] nums) {
        if(null == nums){return;}
        int len = nums.length;
        if(len <= 1){
            return;
        }

        // 寻找分界点
        int index = -1;
        for (int x=len-1; x>0; x--){

            if(nums[x] <= nums[x-1]){

            }
            else {
                index=x-1;
                break;
            }
        }

        if(index < 0){
            // 重新排序
            Arrays.sort(nums);
        }
        else {
            // 找第一个大数
            Integer firstLargeIndex = null;
            for(int x=len-1; x>index; x--){
                if(nums[x] > nums[index]){
                    firstLargeIndex = x;
                    break;
                }
            }

            // 交换
            int tmp = nums[index];
            nums[index] = nums[firstLargeIndex];
            nums[firstLargeIndex] = tmp;

            // index 后面升序
            Arrays.sort(nums, index+1, len);
        }
    }

    public static void main(String args[]){
        int [] test = { 3, 2, 1};

        for (int x=0; x<10; x++){
            nextPermutation(test);
            System.out.println(Arrays.toString(test));
        }

    }

}
