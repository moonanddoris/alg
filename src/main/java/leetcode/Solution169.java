package leetcode;

/**
 * leetcode169 easy 多数元素
 * @author lixinhai
 * @date 2022/5/24
 */
public class Solution169 {

    // 大于n/2的相同元素，排序之后，肯定会出现在n/2的位置
    // 转换为 利用快速排序 求中位数  O(n)
    public static int majorityElement(int[] nums) {
        int len = nums.length;

        int mid= -1;
        int startIndex = 0;
        int endIndex = len-1;

        while (mid != len/2){
            mid = pivotArray2(nums, startIndex, endIndex);

            if(mid > len/2){
                endIndex = mid-1;
            }
            else {
                startIndex = mid+1;
            }
        }
        return nums[mid];
    }

    /**
     *
     * @param nums
     * @param sIndex
     * @param eIndex
     */
    public static int pivotArray(int nums[], int sIndex, int eIndex){

        int pivot = nums[eIndex];

        int i,j;
        i=j=sIndex;

        while (i<=eIndex && j<=eIndex){

            if(j<i){j=i;}

            if(nums[i]<pivot){
                i++;
            }
            else {
                while (j<=eIndex && nums[j++] >= pivot){}
                j--;

                //swap num[i] num[j]
                int tmp;
                tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;

                i++;j++;
            }
        }

        return i-1;
    }

    /**
     * 快速排序的核心 分区函数
     * @param nums
     * @param sIndex
     * @param eIndex
     * @return
     */
    public static int pivotArray2(int nums[], int sIndex, int eIndex){
        int pivot = nums[eIndex];

        int i,j;
        i=j=sIndex;

        for(;j<eIndex;j++){
            if(nums[j] < pivot){
                if(i == j){
                    i++;
                }
                else {
                    int tmp;
                    tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;

                    i++;
                }
            }
        }

        int tmp;
        tmp = nums[i];
        nums[i] = nums[eIndex];
        nums[eIndex] = tmp;

        return i;
    }

    // 更加精巧的解决方法 统计数字出现的次数： 相同数字计数+1  不同数字计数-1  最终计数大于0的数字，为答案

    public static void main(String[] args){

        int[] test = {6, 3, 15, 11, 4, 3, 8};

        //System.out.println("------------------"+ pivotArray(test, 0, 5));
        System.out.println("------------------"+ pivotArray2(test, 0, 5));


        //int [] re = {2,2,1,1,1,2,2};
        int [] re = {2,2,1,1,1,2,2};
        System.out.println("------------------"+ majorityElement(re));

    }


}
