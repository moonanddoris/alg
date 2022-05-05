package general_algorithm;

import java.util.Arrays;

/**
 * 回溯算法：八皇后问题
 * @author lixinhai
 * @date 2022/5/3
 */
public class Exercise {

    public static int[] re = new int[8];

    /**
     * 八皇后问题
     */
    public static void eightQueens(){

        innerFunc(re, 0);
    }

    /**
     * 在index行之后，寻找满足条件的位置
     * @param re
     * @param index 当前寻找位置的行下标
     * @return
     */

    public static boolean innerFunc(int[] re, int index){
        if(index == 8){
            //打印所有符合条件的答案
            System.out.println(Arrays.toString(re));
            return true;
        }

        for(int x=0; x<8; x++){
            //当前位置 如果可以放入，则继续下一行
            if(checkIsOk(index, x, re)) {
                re[index] = x;
                innerFunc(re, index+1);
                /*
                // 仅获取第一个符合条件的答案
                if(innerFunc(re, index+1)){
                    return true;
                }
                */
            }
        }

        return false;
    }
    /**
     * 校验位置是否满足条件
     * @param x 行下标
     * @param y 列下标
     * @param re
     * @return
     */
    public static boolean checkIsOk(int x, int y, int[] re){

        for(int i=0; i<x; i++){
            // 是否同列  右对角线  左对角线
            if(y == re[i] || y == re[i]+x-i || y == re[i]+i-x){
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args){

        eightQueens();

        System.out.println(re);

    }
}
