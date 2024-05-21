package leetcode;

/**
 * leetcode560 medium 和为K的子数组
 * @author lixinhai
 * @date 2024/5/20
 */
public class Solution560 {
    public static int subarraySum(int[] nums, int k) {
        int count=0;
        int[] result = new int[nums.length];
        for(int x=0; x<nums.length; x++){
            for(int y=0; y<=x; y++){
                result[y] = result[y] + nums[x];
                if(k == result[y]){
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args){
        int[] a = {1,2,1,2,1};
        int k=3;
        System.out.println(subarraySum(a, k));

    }
}
