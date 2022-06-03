package leetcode;

/**
 * leetcode81 medium 有重复元素的旋转数组 类似33题
 * @author lixinhai
 * @date 2022/6/3
 */
public class Solution81 {
    //二分查找的变种 时间复杂度仍然是 O(logn)
    public static boolean search(int[] nums, int target){
        int low = 0;
        int high = nums.length-1;
        int mid;

        while (high>=low){
            mid = low +( (high-low)>>1 );

            if(nums[mid] == target){
                return true;
            }
            // 可能会出现 无法判断 区间的情况，如果出现则 缩小区间
            else if(nums[low] == nums[mid] && nums[mid]==nums[high]){
                low++;
                high--;
            }
            else if(nums[mid] > target){

                // 旋转点在右侧
                if(nums[low]<= nums[mid]){
                    if(target >= nums[low]){
                        high = mid -1;
                    }
                    else {
                        low = mid + 1;
                    }
                }
                // 旋转点在左侧
                else if(nums[mid] <= nums[high]){
                    high = mid -1;
                }
            }
            else {
                // nums[mid] < target

                // 旋转点在右侧
                if(nums[low]<= nums[mid]){
                    low = mid + 1;
                }
                // 旋转点在左侧
                else if(nums[mid] <= nums[high]){
                    if(target <= nums[high]){
                        low = mid + 1;
                    }
                    else {
                        high = mid -1;
                    }
                }
            }
        }

        return false;
    }

    public static void main(String[] args){

        int[] test = {1,0,1,1,1};

        System.out.println("------------------"+ search(test, 0));

        for(int x : test){

            System.out.println("------------------"+ x);
            System.out.println("-----------------------------"+ search(test, x));
        }


    }
}
