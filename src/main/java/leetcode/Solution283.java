package leetcode;

import java.util.Arrays;

/**
 * leetcode283 easy 移动零
 * @author lixinhai
 * @date 2022/10/13
 */
public class Solution283 {
    /**
     * 两个指针 第一个0位置i 第一个需要移动位置的非0数位置j  j>i
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        if(null == nums || nums.length<=1){
            return;
        }

        int i=-1, j=-1;
        while (true){
            // 寻找第一个0位置 i
            // 寻找第一个0之后 第一个非0位置 j
            for(int x=i+1;x< nums.length;x++){
                if(nums[x] == 0){
                    i = x;
                    break;
                }
            }

            for(int x=i+1;x< nums.length;x++){
                if(nums[x] != 0){
                    j = x;
                    break;
                }
            }

            // 约束 i < j
            // 交换i j 位置  i重新寻找从i+1 j继续寻找从j+1
            // 结束条件j > len
            if(i == -1 || j == -1){
                return;
            }

            if(i < j){
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
            else {
                return;
            }
        }

    }


    /**
     * 优化一下写法
     * @param nums
     */
    public static void moveZeroesV2(int[] nums) {
        if(null == nums || nums.length<=1){
            return;
        }

        int i=0, j=0;
        while (i<nums.length && j<nums.length){

            // 寻找第一个0位置 i
            // 寻找第一个0之后 第一个非0位置 j
            if(nums[i] == 0){
                if(nums[j] != 0 && i < j){
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                }
                else {
                    j++;
                }
            }
            else {
                i++;
                if(j < i){j = i;} //减少遍历j的次数
            }
        }

    }


    public static void main(String[] args){
        //int[] test = {1,2};
        //int[] test = {0, 1, 0, 3, 12};
        //int[] test = {0, 1, 0, 3, 0, 2, 0};
        int[] test = {1, 0, 1};

        //moveZeroes(test);
        moveZeroesV2(test);

        System.out.println(Arrays.toString(test));

    }

}
