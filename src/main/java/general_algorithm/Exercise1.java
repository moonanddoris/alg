package general_algorithm;

import java.util.Arrays;

/**
 * 回溯算法：0-1背包问题
 * @author lixinhai
 * @date 2022/5/3
 */
public class Exercise1 {

    /**
     * 几轮n个物品是否存放 0 1
     */
    public static int[] re;

    /**
     * 八皇后问题
     */
    public static void knapsack(int totalWeight, int[] staffs){

        // 初始化
        re = new int[staffs.length];
        for(int x=0; x < staffs.length; x++){
            re[x] = 0;
        }

        innerFunc(totalWeight, staffs, re, 0);

    }

    /**
     * 判断第index个物品，是否需要放入背包
     * @param index 当前寻找位置的行下标
     * @return
     */

    public static void innerFunc(int totalWeight, int[] staffs, int[] re, int index){
        // 当前已经存放的所有重量
        int currentCnt = 0;
        for(int i=0; i<index; i++){
            if(re[i]==1){
                currentCnt += staffs[i];
            }
        }

        if(currentCnt > totalWeight){
            return;
        }

        if(index == staffs.length){
            //打印所有符合条件的答案
            System.out.println(Arrays.toString(re) + currentCnt);
            return;
        }

        re[index] = 0;
        innerFunc(totalWeight, staffs, re, index+1);

        re[index] = 1;
        innerFunc(totalWeight, staffs, re, index+1);

        /*
        if(currentCnt + staffs[index] <= totalWeight){
            re[index] = 1;
        }
        else {
            re[index] = 0;
        }
        */
    }

    public static void main(String[] args){

        int [] a = {1, 2, 3, 4, 5};

        knapsack(10, a);

        System.out.println(re);

    }
}
