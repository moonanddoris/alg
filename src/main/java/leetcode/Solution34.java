package leetcode;

import java.util.Arrays;

/**
 * leetcode34 medium 在排序数组中查找元素的第一个和最后一个位置
 * @author lixinhai
 * @date 2022/5/24
 */
public class Solution34 {

    // 二分查找 找到数组中target出现的位置，然后向前和向后寻找边界 时间复杂度 O(logn)+O(n)
    // 开始并没有想到，寻找最后一个小于目标值的位置 和 第一个大于目标值的位置
    public static int[] searchRange(int[] nums, int target) {

        int startIndex=0, endIndex=nums.length-1;
        int tIndex = -1;


        while (endIndex> startIndex){

            int mid = (endIndex+startIndex)/2 + 1;

            if(nums[mid] == target){
                tIndex = mid;
                break;
            }
            else if(nums[mid] > target){
                endIndex = mid-1;
            }
            else {
                startIndex = mid +1;
            }
        }

        if(tIndex > 0){
            // 向左右分别寻找边界
            int i=tIndex, j=tIndex;

            while (i>=0 && j< nums.length && (nums[i]==target || nums[j]==target)){
                if(nums[i] == target){i--;}
                if(nums[j] == target){j++;}
            }
            return new int[]{i+1, j-1};
        }
        else {
            return new int[]{-1,-1};
        }
    }

    /**
     * 转化为 求小于给定值的坐边界i 求大于给定值的右边界j  结果return [i,j];
     * @param nums
     * @param target
     * @return
     */
    public static int[] searchRange2(int[] nums, int target) {
        int leftIndex = binarySearchLeft(nums, target);
        System.out.println("================="+leftIndex);
        int rightIndex = binarySearchRight(nums, target);
        System.out.println("================="+rightIndex);
        return new int[]{leftIndex, rightIndex};
    }

    /**
     * 假设给定值肯定存在
     * 求小于等于给定值的左边界i
     * @param nums
     * @param target
     * @return
     */
    public static int binarySearchLeft(int[] nums, int target){
        int sIndex = 0;
        int eIndex = nums.length-1;
        int mid = 0;

        while (eIndex >= sIndex){
            mid = sIndex + (eIndex-sIndex)/2;

            if(nums[mid] > target){
                eIndex = mid-1;
            }
            else if(nums[mid] < target){
                sIndex = mid+1;
            }
            else {
                if(mid==0 || nums[mid-1] < target){
                    return mid;
                }
                else {
                    eIndex = mid-1;
                }
            }
        }

        return -1;
    }

    /**
     * 求大于等于给定值的右边界j
     * @param nums
     * @param target
     * @return
     */
    public static int binarySearchRight(int[] nums, int target){
        int sIndex = 0;
        int eIndex = nums.length-1;
        int mid = 0;

        while (eIndex >= sIndex){
            mid = sIndex + (eIndex-sIndex)/2;

            if(nums[mid] < target){
                sIndex = mid+1;

            }
            else if (nums[mid] > target){
                eIndex = mid-1;
            }
            else {
                if(mid == nums.length-1 || nums[mid+1] > target){
                    return mid;
                }
                else {
                    sIndex = mid+1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args){

        int[] test = {5,7,7,8,8,10};
        //System.out.println("-------------"+ Arrays.toString(searchRange(test,8)));

        //binarySearchLeft(test, 7);
        System.out.println("================"+Arrays.toString(searchRange2(test, 100)));

    }
}
