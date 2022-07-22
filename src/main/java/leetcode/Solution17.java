package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * leetcode17 medium 电话号码的字母组合
 * @author lixinhai
 * @date 2022/7/22
 */
public class Solution17 {

    private static Map<Character, char[]> letterMap = new HashMap<>();

    static {
        letterMap.put('2', new char[]{'a', 'b', 'c'});
        letterMap.put('3', new char[]{'d', 'e', 'f'});
        letterMap.put('4', new char[]{'g', 'h', 'i'});
        letterMap.put('5', new char[]{'j', 'k', 'l'});
        letterMap.put('6', new char[]{'m', 'n', 'o'});
        letterMap.put('7', new char[]{'p', 'q', 'r', 's'});
        letterMap.put('8', new char[]{'t', 'u', 'v'});
        letterMap.put('9', new char[]{'w', 'x', 'y', 'z'});
    }

    public static List<String> letterCombinations(String digits) {

        List<String> result = new ArrayList<>();
        if(null == digits || digits.length()==0){
            return result;
        }

        String road = "";
        dfs(digits, 0,  road, result);
        return result;
    }

    /**
     * dfs 写法
     * @param digits
     * @param level 数组下标
     * @param road
     * @param result
     */
    public static void dfs(String digits, int level, String road, List<String> result){
        // 终止条件
        if(level >= digits.length()){
            result.add(road);
            return;
        }

        char curChar = digits.substring(level, level+1).charAt(0);

        for(char c : letterMap.get(curChar)){
            road += c;
            dfs(digits, level+1, road, result);
            road = road.substring(0, road.length()-1);
        }
    }

    // TODO 使用队列来辅助

    public static void main(String[] args){
        Long.valueOf("");

        String a = "abc";
        System.out.println(a.substring(0,1));

        System.out.println(letterCombinations("23"));

    }
}
