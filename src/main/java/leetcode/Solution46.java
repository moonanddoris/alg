package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode46 medium 全排列
 * @author lixinhai
 * @date 2022/5/31
 */
public class Solution46 {

    public static List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        List<Integer> innerInit = new ArrayList<>();
        for(int i=0; i< nums.length; i++){
            innerInit.add(null);
        }

        result.add(innerInit);

        for(int i=0; i< nums.length; i++){
            List<List<Integer>> innerResult = new ArrayList<>(); // result的替换变量 每次循环替换result

            for(int j=0; j< result.size(); j++){  //1 [0 0 0]

                for(int m=0; m<result.get(j).size(); m++){

                    if(null == result.get(j).get(m)){
                        List<Integer> innerTmp = new ArrayList<>(result.get(j));
                        innerTmp.set(m, nums[i]); // 1 0 0
                        innerResult.add(innerTmp);
                    }
                }
            }

            result = innerResult;
        }

        return result;
    }

    public static void main(String[] args){

        //int[] test = {100,4,200,1,3,2};
        //int[] test = {100,4,200,101,0,2};
        int[] test = {1,2,3};

        System.out.println("------------------"+ permute(test));

    }
}
