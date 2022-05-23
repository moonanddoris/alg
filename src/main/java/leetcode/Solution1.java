package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * leetcode1 easy 两数之和
 * @author lixinhai
 * @date 2022/5/22
 */
public class Solution1 {

    // 暴力破解  n*(n-1)/2种组合数  时间复杂度O(n^2)

    // 利用Map辅助空间 O(n)时间复杂度 空间复杂度O(n)
    public static int[] twoSum(int[] nums, int target) {
        int [] re = new int[2];
        Map<Integer, Integer> indexMap = new HashMap<>();

        for(int i=0; i<nums.length; i++){

            if(indexMap.containsKey(nums[i])){
                re[0] = indexMap.get(nums[i]);
                re[1] = i;
                return re;
            }

            indexMap.put(target-nums[i], i);
        }

        return new int[0];
    }

    // 先排序 再使用双指针法 进行求解；时间复杂度，排序一般是 O(nlogn)，排序后双指针遍历O(n) 所以整体时间复杂度是O(nlogn)
    public static int[] twoSum2(int[] nums, int target) {
        Arrays.sort(nums);

        for(int m=0, n=nums.length-1; m<n; ){
            if(nums[m] + nums[n] == target){
                return new int[]{m, n};
            }
            else if(nums[m] + nums[n] > target){
                // 右指针向左
                n--;
            }
            // nums[m] + nums[n] < target
            else {
                // 左指针向右
                m++;
            }
        }

        return new int[0];
    }

    public static void main(String[] args){
        System.out.println("---------start--------");

        int[] test = {2,7,11,15};

        System.out.println("-----------------result:" + Arrays.toString(twoSum(test, 17)));
        System.out.println("-----------------result:" + Arrays.toString(twoSum2(test, 17)));


        System.out.println("---------end--------");
    }
}
