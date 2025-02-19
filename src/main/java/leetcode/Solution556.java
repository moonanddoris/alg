package leetcode;

/**
 *leetcode556 medium 下一个更大元素 III
 * @author lixinhai
 * @date 2025/2/17
 */
public class Solution556 {
  public int nextGreaterElement(int n) {
    if(n > Integer.MAX_VALUE || n < 0){
      return -1;
    }

    char[] nums = String.valueOf(n).toCharArray();
    int length = nums.length;

    int i=length-1; //最后一个元素
    while (i>0 && nums[i-1] >= nums[i]){
      i--;
    }
    if(i==0){
      return -1;
    }

    // nums[i-1]; // 目标1

    //找目标2 第一个比目标1大的

    int j=length-1;
    while (nums[j] <= nums[i-1]){
      j--;
    }

    // nums[j]; //目标2
    swap(nums, i-1, j);
    reverse(nums, i, length-1);

    Long l = Long.parseLong(new String(nums));
    if(l > Integer.MAX_VALUE){
      return -1;
    }

    return Integer.parseInt(new String(nums));
  }

  public void swap(char[] nums, int x, int y){
    char tmp = nums[x];
    nums[x] = nums[y];
    nums[y] = tmp;
  }

  public void reverse(char[] nums, int start, int end){
    int len = end-start+1;
    for(int i=0; i<len/2; i++){
      swap(nums, start+i, start+ len-1-i);
    }
  }

  public static void main(String[] args) {
    Solution556 solution556 = new Solution556();
    System.out.println(solution556.nextGreaterElement(2147483648));
  }
}
