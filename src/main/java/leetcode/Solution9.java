package leetcode;

import java.util.Arrays;

/**
 * leetcode9 easy 回文数
 * @author lixinhai
 * @date 2022/5/20
 */
public class Solution9 {

    public static boolean isPalindrome(int x) {
        String str = Integer.toString(Math.abs(x));
        char[] strArray = str.toCharArray();
        int strLen = strArray.length;

        if(strLen == 1){
            return true;
        }

        int i=0;
        while (i< strLen/2 && strArray[i] == strArray[strLen-1-i]){
            i++;
        }

        if(i == strLen/2){
            return true;
        }
        else {
            return false;
        }
    }

    public static void main(String[] args){

        System.out.println(isPalindrome(-121));

    }
}
