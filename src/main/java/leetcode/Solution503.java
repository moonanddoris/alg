package leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * leetcode503 medium 下一个更大元素 II
 * @author lixinhai
 * @date 2025/2/15
 */
public class Solution503 {
  public int[] nextGreaterElements(int[] nums) {
    int length = nums.length;

    Stack<Integer> stack = new Stack<>();
    int[] result = new int[length*2];

    for(int x =0; x <length*2-1; x++){

      while (!stack.isEmpty()){

        int tmp = stack.peek();
        if(nums[tmp%length] < nums[x%length]){
          result[tmp] = nums[x%length];
          stack.pop();
        }
        else {
          break;
        }
      }

      if(nums[x%length] < nums[(x+1)%length]){
        result[x] = nums[(x+1)%length];
      }
      else {
        stack.push(x);
      }
    }

    while (!stack.isEmpty()){
      int tmp = stack.pop();
      result[tmp] = -1;
    }

    return Arrays.copyOfRange(result, 0 , length);
  }

  public static void main(String[] args) {
    Solution503 solution503 = new Solution503();

//    int [] nums = new int[]{1,2,1};
    int [] nums = new int[]{1,2,3,4,3};

    int[] result = solution503.nextGreaterElements(nums);

    System.out.println(solution503.nextGreaterElements(nums));

  }
}
