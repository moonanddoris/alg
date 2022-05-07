package general_algorithm;

import java.util.Arrays;

/**
 * 回溯算法：正则匹配问题
 * @author lixinhai
 * @date 2022/5/3
 */
public class Exercise2 {

    private static char reg1 = '*';

    private static char reg2 = '?';

    /**
     * 朴素的解法 不完美
     * @param pattenStr
     * @param inputStr
     * @return
     */
    public static boolean regLike(String pattenStr, String inputStr){

        char[] pattenCharArray = pattenStr.toCharArray();
        char[] inputCharArray = inputStr.toCharArray();

        int i=0, j=0;
        int reg1Index = -1;

        while (i< pattenCharArray.length && j < inputCharArray.length){

            if(pattenCharArray[i] == reg1 || reg1Index > 0){
                reg1Index = i;

                int m= i+1, n=j;
                while (m < pattenCharArray.length && n < inputCharArray.length && pattenCharArray[m] != reg1){
                    if(pattenCharArray[m] == inputCharArray[n]){
                        m++;
                        n++;
                    }
                    else {
                        //j++; // 往后移动一个字符继续匹配
                        break;
                    }
                }

                // 主串匹配结束
                if(n == inputCharArray.length){
                    i = m;
                    j = n;
                    break;
                }
                else if(m< pattenCharArray.length && pattenCharArray[m] == reg1){
                    i = m;
                    j = n;
                }
                else {
                    j++; // 往后移动一个字符继续匹配
                }
            }
            else {
                if(pattenCharArray[i] == inputCharArray[j]){
                    i++;
                    j++;
                }
                else {
                    return false;
                }
            }
        }

        if(i == pattenCharArray.length && j == inputCharArray.length){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 使用递归来实现
     * @param pattenStr
     * @param inputStr
     * @return
     */
    public static boolean regLikePro(String pattenStr, String inputStr){

        char[] pattenCharArray = pattenStr.toCharArray();
        char[] inputCharArray = inputStr.toCharArray();

        int i=0, j=0;

        return innerRegLike(i, pattenCharArray, j, inputCharArray);
    }

    /**
     * 从模式串的i 和输入串的j 开始向后匹配。这意味着前面的字符都已经匹配成功
     * @param i
     * @param pattenCharArray
     * @param j
     * @param inputCharArray
     * @return
     */
    public static boolean innerRegLike(int i, char[] pattenCharArray, int j, char[] inputCharArray){

        // 模式串匹配完成
        if(i == pattenCharArray.length){
            return true;
        }
        else {
            // 输入串匹配完成 但是匹配串没遍历完，说明没有匹配完成
            if(j == inputCharArray.length){
                return false;
            }
        }

        if(pattenCharArray[i] == reg1){
            // 遇到*
            for(int m=0; j+m< inputCharArray.length ;m++) {
                if(innerRegLike(i + 1, pattenCharArray, j+m, inputCharArray)){
                    return true;
                }
            }
            return false;
        }
        else if(pattenCharArray[i] == reg2){
            for(int m=0; m<2 && j+m< inputCharArray.length ;m++) {
                if(innerRegLike(i + 1, pattenCharArray, j+m, inputCharArray)){
                    return true;
                }
            }
            return false;
        }
        else {
            if (pattenCharArray[i] == inputCharArray[j]) {
                return innerRegLike(++i, pattenCharArray, ++j, inputCharArray);
            } else {
                return false;
            }
        }
    }

    public static void main(String[] args){

        //boolean re = regLike("ab*cd", "abcdcfcd");
        //boolean re = regLike("ab*cd*cd", "abcdcfct");

        boolean re = regLikePro("ab?cd*cd", "abcdcfcd");
        System.out.println("----------------" + re);
    }
}
