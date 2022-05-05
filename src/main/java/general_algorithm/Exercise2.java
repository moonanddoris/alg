package general_algorithm;

import java.util.Arrays;

/**
 * 回溯算法：正则匹配问题
 * @author lixinhai
 * @date 2022/5/3
 */
public class Exercise2 {

    private static String reg1 = "*";

    private static String reg2 = "?";


    public static boolean regLike(String pattenStr, String inputStr){
        return true;
    }



    public static void main(String[] args){

        boolean re = regLike("ab*cd", "abcdcfcd");

        System.out.println("----------------" + re);
    }
}
