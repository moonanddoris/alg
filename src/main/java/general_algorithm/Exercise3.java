package general_algorithm;

import java.util.Arrays;

/**
 * 动态规划：0-1背包问题 及添加价值属性
 * @author lixinhai
 * @date 2022/5/3
 */
public class Exercise3 {

    /**
     * 0-1背包问题。
     * 给定一组不可分割的物品，和一个载重限制limitVal的背包。在不超过最大载重限制的前提下，背包内最大重量是多大？
     * @param limitVal
     * @param staffs
     */
    public static void knapsack(int limitVal, int[] staffs){

        int[][] st = new int[staffs.length][limitVal+1];

        // 初始化
        for (int i=0; i< staffs.length; i++){
            for(int j =0; j< limitVal+1; j++){
                st[i][j] = 0;
            }
        }

        for (int i=0; i< staffs.length; i++){
            if(i==0){
                // 不放入背包
                st[i][0] = 1;

                // 不能超过最大重量
                if(staffs[i] <= limitVal) {
                    st[i][staffs[i]] = 1;
                }
            }
            else {

                for(int j=0; j< limitVal+1; j++){
                    if(st[i-1][j] != 1){
                        continue;
                    }
                    st[i][j] = 1;

                    if(j + staffs[i] <= limitVal) {
                        st[i][j + staffs[i]] = 1;
                    }
                }
            }

            System.out.println("----------" + Arrays.toString(st[i]));
        }

        for(int i=limitVal; i>=0; i--){
            for(int j= staffs.length-1; j>=0; j--){
                if(st[j][i] == 1){
                    System.out.println("--------------最大值为" + i);
                    return;
                }
            }
        }

    }

    /**
     * 优化二维数组，使用一维数据即可完成
     * @param limitVal
     * @param staffs
     */
    public static void knapsackPro(int limitVal, int[] staffs){
        int[] st = new int[limitVal+1];
        for(int i=0; i<limitVal+1; i++){
            st[i] = 0;
        }

        for(int i=0; i< staffs.length; i++){
            if(i == 0){
                st[0] = 1;
                if(staffs[i] <= limitVal) {
                    st[staffs[i]] = 1;
                }
            }
            else {
                // 避免重复赋值的问题，从后向前遍历
                for(int j=limitVal; j>=0; j--){
                    if(st[j] != 1){
                        continue;
                    }

                    if(j + staffs[i] <= limitVal) {
                        st[j + staffs[i]] = 1;
                    }
                }
            }
        }
        System.out.println("--------------------"+ Arrays.toString(st));
    }

    /**
     * 加入物品价值
     * @param limitVal
     * @param staffs
     */
    public static void knapsackPro2(int limitVal, int[] staffs, int[] staffsValue){

        int[] st = new int[limitVal+1];

        for(int i=0; i<limitVal+1; i++){
            st[i] = -1;
        }

        for(int i=0; i< staffs.length; i++){
            System.out.println("--------------------"+ Arrays.toString(st));
            if(i == 0){
                st[0] = 0;
                if(staffs[i] <= limitVal) {
                    st[staffs[i]] = staffs[i] * staffsValue[i];
                }
            }
            else {
                // 避免重复赋值的问题，从后向前遍历
                for(int j=limitVal; j>=0; j--){
                    if(st[j] == -1){
                        continue;
                    }

                    if(j + staffs[i] <= limitVal) {
                        //st[j + staffs[i]] = 1;
                        if(st[j] + staffs[i] * staffsValue[i] > st[j+staffs[i]]) {
                            st[j + staffs[i]] = st[j] + staffs[i] * staffsValue[i];
                        }
                    }
                }
            }
        }

        System.out.println("--------------------"+ Arrays.toString(st));
    }

    public static void main(String[] args){

        int limitVal = 10;
        int[] staff = {1, 1, 2, 3, 4, 5};
        int[] staffValue = {10, 10, 4, 6, 7, 1};

        //knapsack(10, staff);
        knapsackPro(10, staff);
        knapsackPro2(10, staff, staffValue);

        System.out.println("----------------");
    }
}
