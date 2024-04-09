package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * leetcode438 medium 找到字符串中所有字母异位词
 * @author lixinhai
 * @date 2024/2/21
 */
public class Solution438 {

    /**
     * 方法一 两层遍历 时间复杂度高
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> findAnagrams(String s, String p) {
        if(s.length()<p.length()){
            return new ArrayList<>();
        }

        List<Integer> result = new ArrayList<>();

        // p中每次字符的频次
        Map<Character, Integer> hashMap = new HashMap<>();
        for(int i=0; i< p.length(); i++){
            if( hashMap.containsKey(p.charAt(i))){
                hashMap.put(p.charAt(i), 1+hashMap.get(p.charAt(i)));
            }
            else {
                hashMap.put(p.charAt(i), 1);
            }
        }

        // 构建滑动窗口
        int left = 0;
        int right = p.length()-1;

        while (right <= s.length()-1){

            int count = p.length();
            Map<Character, Integer> hashMapBak = new HashMap<>(hashMap); //重置hashMap

            // 下次滑动偏移量
            int shift=1;

            int inLeft=left;
            // 窗口内滑动
            while (inLeft<=right){
                if(hashMapBak.containsKey(s.charAt(inLeft))){

                    if(hashMapBak.get(s.charAt(inLeft))>0){
                        count--;
                        hashMapBak.put(s.charAt(inLeft), hashMapBak.get(s.charAt(inLeft))-1);
                    }
                    else {
                        break;
                    }

                }
                else {
                    shift=inLeft-left+1;
                    break;
                }

                inLeft++;
            }

            // 符合条件
            if(count==0){
                result.add(left);
            }

            right+=shift;
            left+=shift;
        }

        return result;
    }


    /**
     * 方法二 一次遍历
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> findAnagramsPro(String s, String p) {
        if(s.length()<p.length()){
            return new ArrayList<>();
        }

        List<Integer> result = new ArrayList<>();

        // p中每次字符的频次
        Map<Character, Integer> hashMap = new HashMap<>();
        for(int i=0; i< p.length(); i++){
            if( hashMap.containsKey(p.charAt(i))){
                hashMap.put(p.charAt(i), 1+hashMap.get(p.charAt(i)));
            }
            else {
                hashMap.put(p.charAt(i), 1);
            }
        }

        int left = 0;
        while (left <= s.length()-1){

            //若存在计数减一
            if(hashMap.containsKey(s.charAt(left))){
                hashMap.put(s.charAt(left), hashMap.get(s.charAt(left))-1);
            }

            // 符合条件
            // hashMap的value和为0
            boolean flag = hashMap.values().stream().allMatch(x -> x==0);
            if(flag){
                result.add(left - (p.length()-1));
            }

            if(left >= p.length()-1){
                // 最左边放回去 计数加一
                if(hashMap.containsKey(s.charAt( left - (p.length()-1) ))){
                    hashMap.put(s.charAt( left - (p.length()-1) ), hashMap.get(s.charAt( left - (p.length()-1) ))+1);
                }
            }
            left++;
        }
        return result;
    }

    public static void main(String[] args){

        String s = "cbaebabacd", p = "abc";

//        String s = "abab", p = "ab";

        System.out.println(findAnagrams(s, p));
        System.out.println(findAnagramsPro(s, p));

    }
}
