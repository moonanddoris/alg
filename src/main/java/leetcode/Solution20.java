package leetcode;

import java.util.Stack;

/**
 * leetcode20 easy 括号的合法性
 * @author lixinhai
 * @date 2022/6/13
 */
public class Solution20 {

    public static boolean isValid(String s) {
        if(null == s || s.length()<2){
            return false;
        }

        char[] sArray = s.toCharArray();
        Stack<Character> leftStack = new Stack<>();

        for(char c : sArray){
            if(c == '[' || c == '{' || c == '('){
                leftStack.push(c);
            }
            else if(c == ']' && !leftStack.empty() && leftStack.pop() == '['){ }
            else if(c == '}' && !leftStack.empty() &&leftStack.pop() == '{'){ }
            else if(c == ')' && !leftStack.empty() &&leftStack.pop() == '('){ }
            else {
                return false;
            }
        }

        if(leftStack.empty()){
            return true;
        }
        else {
            return false;
        }
    }

    public static void main(String[] args) {
        String s = "([)]";
        System.out.println("---------------" + isValid(s));

    }
}
