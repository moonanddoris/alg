package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode39 medium 组合总和
 * @author lixinhai
 * @date 2022/11/18
 */
public class Solution39 {

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        if(target < 0){
            return Arrays.asList();
        }

        if(target == 0){
            return Arrays.asList();
        }

        if(candidates.length == 1){
            if(target % candidates[0] == 0){
                Integer [] tmpArray = new Integer[target/candidates[0]];
                Arrays.fill(tmpArray, candidates[0]);
                return Arrays.asList(Arrays.asList(tmpArray));
            }
            else {
                return Arrays.asList();
            }
        }
        List<List<Integer>> result = new ArrayList<>();

        int t = candidates[0];
        int[] newArray = Arrays.copyOfRange(candidates, 1, candidates.length);

        for(int cnt=0; t*cnt<=target; cnt++){

            Integer [] tmpArray = new Integer[cnt];
            Arrays.fill(tmpArray, t);
            List<Integer> tmpList = Arrays.asList(tmpArray);

            if(t*cnt == target){
                result.add(tmpList);
                continue;
            }

            List<List<Integer>> list = combinationSum(newArray, target - t * cnt);
            if(list.isEmpty()){continue;}

            for(List<Integer> i : list){
                List<Integer> j= new ArrayList<>();
                j.addAll(tmpList);
                j.addAll(i);

                result.add(j);
            }

        }

        return result;
    }


    public static void main(String[] args){
        int target = 6;
        int [] test = {2, 3};

        List<List<Integer>> re = combinationSum(test, target);

        System.out.println(re);
    }

}
