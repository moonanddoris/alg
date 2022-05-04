package general_algorithm;

/**
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
            return true;
        }

        for(int x=0; x<8; x++){
            //当前位置 如果可以放入，则继续下一行
            if(checkIsOk(index, x, re)) {
                re[index] = x;
                if(innerFunc(re, index+1)){
                    return true;
                }
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
