package leetcode;

import java.util.*;

/**
 * leecode49 medium 字母异位词分组
 * @author lixinhai
 * @date 2023/3/27
 */
public class Solution49 {

    public static List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> result= new HashMap<>(16);

        for(String x : strs){
            String sorted = getSortedStr(x);

            if(result.containsKey(sorted)){
                result.get(sorted).add(x);
            }
            else {
                result.put(sorted, new ArrayList<>(Collections.singletonList(x)));
            }
        }

        return new ArrayList<>(result.values());
    }

    private static String getSortedStr(String a){
        char[] ar = a.toCharArray();
        Arrays.sort(ar);
        return String.valueOf(ar);
    }

    public static void main(String[] args){
//        String test= "edcba";
//        char[] ar = test.toCharArray();
//        Arrays.sort(ar);
//        String sorted = String.valueOf(ar);
//        System.out.println(sorted);

        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};

        List<List<String>> list = groupAnagrams(strs);

        System.out.println("----------");
    }

}
