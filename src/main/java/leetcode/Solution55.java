package leetcode;

/**
 * leecode55 medium 跳跃游戏
 * @author lixinhai
 * @date 2023/8/31
 */
public class Solution55 {

    /**
     * 思路一 贪心 每一步判断可到达的最远距离
     * 思路二 关键是找到0的元素，如果出现0元素，就可能会出现跳跃不过去的情况
     *  所以关键是从后向前遍历，如果遇到0元素，向前判断是否可以跳跃过去它，直到所有0元素都检测通过
     * @param nums
     * @return
     */

    public static boolean canJump(int[] nums) {

        for(int i= nums.length-1;i>=0;){
            if(i<nums.length-1 && nums[i] == 0){
                int b = canJumpZero(nums, i);

                if(b==0){
                    return true;
                }

                if(b==-1){
                    return false;
                }

                i=b;
            }
            else {
                i--;
            }
        }

        return true;
    }

    /**
     * -1 表示无法跳跃
     * >=0 表示可以跳跃，且最远的index
     * @param nums
     * @param zeroIndex
     * @return
     */
    public static int canJumpZero(int[] nums, int zeroIndex) {

        for(int i=zeroIndex-1;i>=0;i--){
            if(nums[i] > zeroIndex-i){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args){
        System.out.println("---------start--------");

        //int[] test = {1,1,0,2,0,1};
        //int[] test = {3,2,1,0,4};
        int[] test = {2,3,1,1,4};
        //System.out.println("---------end--------:" + canJumpZero(test, 4));
        System.out.println("---------end--------:" + canJump(test));
    }

}
