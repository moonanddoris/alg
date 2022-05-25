package leetcode;

/**
 * leetcode136 easy 只出现一次的数字
 * @author lixinhai
 * @date 2022/5/24
 */
public class Solution136 {
    /**
     *
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        int re = 0;

        for(int i : nums){
            re = re ^ i;
        }

        return re;
    }

    public static void main(String[] args){

        int[] test = {4,1,2,1,2};
        System.out.println("----------:"+ singleNumber(test));

    }
}
