package leetcode;

/**
 * leetcode189 medium 轮转数组
 * 经典解法：三次翻转
 * @author lixinhai
 * @date 2024/7/3
 */
public class Solution189 {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        if (k==0) return;
        swap(nums, 0, nums.length-k-1, nums.length-k, nums.length-1);
    }

    /**
     * 按照块进行交换比较麻烦，经典的解法是三次翻转，左右各一次swap，整体再一次swap
     * @param nums
     * @param leftStart
     * @param leftEnd
     * @param rightStart
     * @param rightEnd
     */
    public void swap(int[] nums, int leftStart, int leftEnd, int rightStart, int rightEnd){

        // 如果两个块的长度可以整除，直接交换完毕
        if((leftEnd-leftStart) == (rightEnd-rightStart)){
            for(int i=0; i<leftEnd-leftStart+1; i++){
                swap(nums, leftStart+i, rightStart+i);
            }
        }
        else if((leftEnd-leftStart) > (rightEnd-rightStart)){
            int minLen = rightEnd-rightStart+1;

            int tmpStart = leftStart;
            while (tmpStart+ minLen <= rightStart && tmpStart+minLen-1 <= rightEnd){
                swap(nums, tmpStart, tmpStart+minLen-1, rightStart, rightEnd);
                tmpStart +=minLen;
            }

            // 如果没有正好交换完，继续交换
            if(tmpStart != rightStart){
                swap(nums, tmpStart, rightStart-1, rightStart, rightEnd);
            }
        }
        else {
            int minLen = leftEnd-leftStart+1;

            int tmpEnd = rightEnd;
            while (tmpEnd- minLen >= leftEnd && tmpEnd-minLen+1 >= rightStart){
                swap(nums, leftStart, leftEnd, tmpEnd-minLen+1, tmpEnd);
                tmpEnd -=minLen;
            }

            // 如果没有正好交换完，继续交换
            if(tmpEnd != leftEnd){
                swap(nums, leftStart, leftEnd, leftEnd+1, tmpEnd);
            }
        }
    }

    public void swap(int[] nums, int left, int right){
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }

    public static void main(String[] args){
        int[] a = {1,2,3,4,5,6,7};
        int k=8;
        Solution189 solution189 = new Solution189();
        solution189.rotate(a, k);

        for (int i : a) {
            System.out.println(i);
        }
    }
}
