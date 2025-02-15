package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * leetcode496 easy 下一个更大元素 I
 * @author lixinhai
 * @date 2025/1/16
 */
public class Solution496 {

  public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    Stack<Integer> stack = new Stack<>();
    Map<Integer, Integer> map = new HashMap<>(nums2.length);

    for(int x=0; x< nums2.length; x++){

      while (!stack.isEmpty()){
        if(stack.peek() < nums2[x]){
          map.put(stack.pop(), nums2[x]);
        }
        else {
          break;
        }
      }

      if(x<nums2.length-1 && nums2[x+1] > nums2[x]){
        map.put(nums2[x], nums2[x+1]);
      }
      else {
        stack.push(nums2[x]);
      }
    }

    while (!stack.isEmpty()){
      map.put(stack.pop(), -1);
    }

//    System.out.println(map);

    int[] result = new int[nums1.length];
    for(int y =0; y<nums1.length; y++){
      result[y] = map.get(nums1[y]);
    }

    return result;
  }

  public static void main(String[] args){
//    int[] nums2 = {1, 3, 4, 2, 5};
    int[] nums2 = {6,5,4,3,2,1,7};
    int[] nums1 = {1, 2, 3, 4, 5};

    Solution496 solution496 = new Solution496();
    solution496.nextGreaterElement(nums1, nums2);


  }


}
