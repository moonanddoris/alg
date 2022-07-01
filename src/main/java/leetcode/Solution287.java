package leetcode;

/**
 * leetcode287 medium 寻找重复数
 * @author lixinhai
 * @date 2022/6/20
 */
public class Solution287 {
    public static int findDuplicate(int[] nums) {
        if(null == nums || nums.length <= 1){
            return -1;
        }

        int i = 0;
        for(; i< nums.length; i++){

            while (nums[i] > i+1){
                // 交换合适的位置
                int swapIndex = nums[i] -1;

                // 避免交换的是相同数字
                if(nums[i] == nums[swapIndex]){return nums[i];}

                int tmp = nums[i];
                nums[i] = nums[swapIndex];
                nums[swapIndex] = tmp;
            }

            if(nums[i] < i+1){
                return nums[i];
            }
        }

        return -1;
    }

    public static void main(String[] args){
        //int[] test = {1,3,4,2,2};
        int[] test = {1,1};
        int re = findDuplicate(test);
        System.out.println("---------------:" + re);
    }
}
