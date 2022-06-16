package leetcode;

import java.util.*;

/**leetcode22 medium 括号生成
 * @author lixinhai
 * @date 2022/6/16
 */
public class Solution22 {

    /**
     * 递归方法 f(n) = f(n-1) 组合当前括号对
     * 关键的组合思路是，插入已经生成的括号对中，注意前后闭合顺序  用set来进行排重
     *
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        if (0 == n) {
            return new ArrayList<>();
        }
        if (1 == n) {
            return Arrays.asList("()");
        }

        Set<String> re = new HashSet<>();

        List<String> lastList = generateParenthesis(n - 1);

        // 将() 插入合适位置
        for (String s : lastList) {
            for (int i = 0; i <= s.length(); i++) {
                // 将左括号"(" 插入位置 i
                String tmp = s.substring(0,i) + "(" + s.substring(i);

                for (int j = i + 1; j <= s.length() + 1; j++) {
                    // 将右括号")" 插入位置j
                    String innS = tmp.substring(0,j) + ")" + tmp.substring(j);
                    re.add(innS);
                }
                // TODO(lxh) 这里第一次遍历就可以了，后面都是重复的 这里没有想明白，为什么后面都是重复的了
                break;
            }
        }

        List<String> result = new ArrayList<>();
        result.addAll(re);
        return result;
    }

    public static void main(String[] args) {
        String s = "ab";

        // 插入0位置
        //System.out.println("i" + s);
        System.out.println(s.substring(0,0)+ "i" + s.substring(0));

        // 插入1位置
        System.out.println(s.substring(0,1)+ "i" +s.substring(1));

        // 插入2位置
        //System.out.println(s + "i");
        System.out.println(s.substring(0,2)+ "i" + s.substring(2));


        List<String> re = generateParenthesis(4);
        System.out.println("------------------" + re);

    }

}