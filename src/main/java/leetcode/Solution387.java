package leetcode;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * leetcode387 easy 字符串中的第一个唯一字符
 * @author lixinhai
 * @date 2022/6/16
 */
public class Solution387 {

    public static int firstUniqChar(String s) {
        if(null == s || s.length() == 0){return -1;}
        if(s.length() == 1) {return 0;}

        // 保证顺序
        Map<Character, Integer> resultMap = new LinkedHashMap<>();

        for(int x=0; x < s.length();x++){
            char c = s.charAt(x);

            if(resultMap.containsKey(c)){
                resultMap.put(c, resultMap.get(c)+1);
            }
            else {
                resultMap.put(c, 1);
            }
        }

        for(Map.Entry<Character, Integer> entry : resultMap.entrySet()){
            if(entry.getValue() == 1){
                return s.indexOf(entry.getKey());
            }
        }

        return -1;
    }

    public static void main(String[] args){
        //String s = "leetcode";
        String s = "xloveleetcode";
        //String s = "aabb";

        System.out.println(firstUniqChar(s));
    }

}
