package leetcode;

/**
 * leetcode581 medium 最短无序连续子数组
 * @author lixinhai
 * @date 2022/6/4
 */
public class Solution581 {

    //TODO(lxh) 自己的解法有问题 看了题解，有点头疼。。。
    public static int findUnsotedSubarray(int[] nums){
        if(nums.length <= 1){
            return 0;
        }

        int minVal = nums[0], maxVal = nums[0];
        int start = -1, end = -1;

        for(int x=0; x < nums.length; x++){

            // 逆序
            if(nums[x] < maxVal){

                if(nums[x] > minVal){
                    if(start != -1){
                        end = x;
                    }
                    else {
                        start = x-1;
                    }
                }
                else {
                    // 重置
                    start = 0;
                    end = -1;
                }
            }
            else { }

            minVal = Math.min(minVal, nums[x]);
            maxVal = Math.max(maxVal, nums[x]);
        }

        if(start != -1 && end == -1){
            end = nums.length-1;
        }

        if(start == -1 && end == -1){
            return 0;
        }

        return end - start +1;
    }

    public static void main(String[] args){

        int[] test = {2, 6, 4, 8, 10, 9, 15, 1};

        findUnsotedSubarray(test);


    }
}
