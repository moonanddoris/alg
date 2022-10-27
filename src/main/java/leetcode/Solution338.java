package leetcode;

import java.util.Arrays;

/**
 * leetcode338 easy 比特位计数
 * @author lixinhai
 * @date 2022/10/25
 */
public class Solution338 {

    public static int[] countBits(int n) {
        int[] result = new int[n+1];
        for(int x=0;x<=n;x++){
            if(x==0){
                result[x] = 0;
            }
            else if(x==1 || x == 2){
                result[x] = 1;
            }
            else {
                if(x%2 == 0){
                    result[x] = result[x/2];
                }
                else {
                    result[x] = result[x-1] + 1;
                }
            }
        }

        return result;
    }

    public static void main(String[] args){

        int[] test = countBits(5);
        System.out.println(Arrays.toString(test));

    }

}
