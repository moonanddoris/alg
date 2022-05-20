package leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * leetcode56 合并区间
 * @author lixinhai
 * @date 2022/5/20
 */
public class Solution56 {



    /**
     * 合并重合区间
     * @param intervals
     * @return
     */
    public static int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]){
                    return o1[1] - o2[1];
                }
                else {
                    return o1[0] - o2[0];
                }
            }
        });

        int[][] re = new int[intervals.length][2];
        int reIndex = 0;

        re[reIndex][0] = intervals[0][0];
        re[reIndex][1] = intervals[0][1];

        for(int i=1; i<intervals.length; i++){

            // 合并重合区间
            if(intervals[i][0] <= re[reIndex][1]){
                re[reIndex][1] = Math.max(re[reIndex][1], intervals[i][1]);
            }
            else {
                // 重置哨兵
                reIndex++;
                re[reIndex][0] = intervals[i][0];
                re[reIndex][1] = intervals[i][1];
            }
        }

        int[][] result = Arrays.copyOfRange(re, 0, reIndex+1);

        return result;
    }

    public static void main(String[] args){

        int[][] intervals = {{9,11}, {1,3}, {2,6}, {8,10}};
        int[][] re = merge(intervals);

        for(int[] t : re){
            System.out.println("-----------------"+ Arrays.toString(t));
        }

    }
}
