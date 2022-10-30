package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode448 easy 数组中消失的数字
 * @author lixinhai
 * @date 2022/10/27
 */
public class Solution448 {
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        if(null == nums){return null;}

        List<Integer> result = new ArrayList<>();
        int len = nums.length;

        for(int x=0; x<len; x++){

            // 直到 找到自己的位置；或者交换对象相等
            while (nums[x] != nums[nums[x]-1]){
                //swap
                int tmp = nums[x];
                nums[x] = nums[nums[x]-1];
                nums[tmp-1] = tmp;
            }
        }

        for(int x=0;x<len;x++){
            if(x+1 != nums[x]){
                result.add(x+1);
            }
        }

        return result;
    }

    public static void main(String[] args){
        int[] test = {4,3,2,7,8,2,3,1};

        List<Integer> a = findDisappearedNumbers(test);

        System.out.println(a);

    }
}
