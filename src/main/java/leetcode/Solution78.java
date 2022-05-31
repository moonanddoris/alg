package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode78 medium 子集
 * @author lixinhai
 * @date 2022/5/31
 */
public class Solution78 {
    /**
     * 时间复杂度 1+2^1+2^2+... +2^(n-1) = O(n*2^n)
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());

        for(int i=0; i<nums.length; i++){
            int listLen = result.size();

            for(int j=0; j< listLen; j++){
                List<Integer> tmp = new ArrayList<>();
                tmp.addAll(result.get(j));
                tmp.add(nums[i]);

                result.add(tmp);
            }
        }

        return result;
    }

    public static void main(String[] args){

        //int[] test = {100,4,200,1,3,2};
        //int[] test = {100,4,200,101,0,2};
        int[] test = {1,2,3};

        System.out.println("------------------"+ subsets(test));

    }
}
