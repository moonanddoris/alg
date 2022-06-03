package leetcode;

/**
 * leetcode33 medium 搜索旋转排序数组
 * @author lixinhai
 * @date 2022/6/3
 */
public class Solution33 {
    //二分查找的变种 时间复杂度仍然是 O(logn)
    public static int search(int[] nums, int target){
        int low = 0;
        int high = nums.length-1;
        int mid;

        while (high>=low){
            mid = low +( (high-low)>>1 );

            if(nums[mid] == target){
                return mid;
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

        return -1;
    }

    public static void main(String[] args){

        int[] test = {4, 5, 6, 7, 0, 1,2};

        System.out.println("------------------"+ search(test, 3));

        for(int x : test){

            System.out.println("------------------"+ x);
            System.out.println("------------------"+ search(test, x));
        }


    }
}
