package leetcode;

import java.util.Arrays;

/**
 * leetcode75 medium 颜色分类
 * @author lixinhai
 * @date 2023/9/8
 */
public class Solution75 {


    /**
     * 简化版的快排 先以1进行分区，再以0进行分区
     * @param nums
     */
    public static void sortColors(int[] nums) {

        int re = qSort(nums, 0, nums.length-1, 1);
        qSort(nums, 0, re, 0);
    }

    /**
     *
     * @param nums
     * @param left
     * @param right
     * @param pivot
     * @return
     */
    public static int qSort(int[] nums, int left, int right, int pivot){
        while (left < right) {
            while (right > left && nums[right] > pivot) {right--;}

            while (right > left && nums[left] <= pivot) {left++;}

            if(right > left) {
                int tmp = nums[right];
                nums[right] = nums[left];
                nums[left] = tmp;
            }
        }

        return left;
    }

    public static void main(String[] args){

        //int[] test = {2,1,2,1,0};
        //int[] test = {2,0,2,1,1,0};
        int[] test = {0,1,2};

//        int i = qSort(test, 0, test.length - 1, 1);
//        System.out.println(Arrays.toString(Arrays.stream(test).toArray()));
//        System.out.println(i);

        sortColors(test);
        System.out.println(Arrays.toString(Arrays.stream(test).toArray()));

    }


}
