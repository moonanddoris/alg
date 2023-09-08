package leetcode;

/**
 * @author lixinhai
 * @date 2023/9/8
 */
public class Solution64 {

    /**
     * 动态规划  优化 原数组
     * @param grid
     * @return
     */
    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        //TODO 这里可以优化，在原数组上进行记录
        // 范围 0~m-1 0~n-1 记录的是每个位置 到达右下角的最短路径
        int[][] minPathArray = new int[m][n];

        for(int indexM=0; indexM<m; indexM++){
            for(int indexN=0; indexN<n; indexN++){

                if(indexM == 0){
                    if(indexN==0){
                        minPathArray[indexM][indexN] = grid[indexM][indexN];
                    }
                    else {
                        minPathArray[indexM][indexN] = grid[indexM][indexN] + minPathArray[indexM][indexN-1];
                    }
                }
                else {
                    if(indexN==0){
                        minPathArray[indexM][indexN] = grid[indexM][indexN] + minPathArray[indexM-1][indexN];
                    }
                    else {
                        minPathArray[indexM][indexN] = grid[indexM][indexN] + Math.min(minPathArray[indexM-1][indexN], minPathArray[indexM][indexN-1])  ;
                    }
                }
            }
        }

        return minPathArray[m-1][n-1];
    }

    public static void main(String[] args){

        //int[][] test = {{1,3,1},{1,5,1},{4,2,1}};
        int[][] test = {{1,2,3},{4,5,6}};

        System.out.println(minPathSum(test));

    }

}
