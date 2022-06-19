package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode3 medium 无重复字符的最长子串
 * @author lixinhai
 * @date 2022/6/19
 */
public class Solution3 {

    public static int lengthOfLongestSubstring(String s) {
        if(null == s || s.length()==0){ return 0; }
        if(s.length() == 1){ return 1; }

        int left=0, right=0;
        int maxLen = 0;
        Map<Character, Integer> charMap = new HashMap<>();

        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);

            // 这里判断 左右边界，是为了避免map中存留的历史记录导致误判
            // 还有一种写法同样的效果，直接去left的最大值  left = Math.max(left,map.get(s.charAt(i)) + 1);
            if(charMap.containsKey(c) && charMap.get(c)>= left && charMap.get(c)<=right){
                left = charMap.get(c) + 1;
            }

            charMap.put(c, i);
            right = i; // 其实right可以用i来替代
            maxLen = Math.max(maxLen, right-left+1);
        }

        return maxLen;
    }

    public static void main(String[] args){
        //String s = "abcabcbb";
        //String s = "bbbbbb";
        String s = "pwwkew";
        System.out.println(lengthOfLongestSubstring(s));
    }
}
