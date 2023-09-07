package leetcode;

/**
 * leecode62 medium 不同路径
 * @author lixinhai
 * @date 2023/9/1
 */
public class Solution62 {


    /**
     * 递归 并使用缓存优化速度
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths(int m, int n) {
//        if(n==0 || m==0){
//            return 0;
//        }
//
//        if(m==1 || n==1){
//            return 1;
//        }
//
//        return uniquePaths(m-1, n) + uniquePaths(m, n-1);

        int[][] result = new int[m+1][n+1];
        for(int i=0;i<m+1;i++){
            for(int j=0;j<n+1;j++){
                result[i][j] = -1;
            }
        }
        return uniquePathsWrap(m, n, result);
    }

    public static int uniquePathsWrap(int m, int n, int[][] result) {
        if(n==0 || m==0){
            return 0;
        }

        if(m==1 || n==1){
            return 1;
        }


        int x,y;

        if(result[m-1][n] == -1){
            x=uniquePathsWrap(m-1,n,result);
        }
        else {
            x=result[m-1][n];
        }

        if(result[m][n-1] == -1){
            y=uniquePathsWrap(m,n-1,result);
        }
        else {
            y=result[m][n-1];
        }

        result[m][n] = x+y;
        return x+y;
    }

    public static int uniquePathsPro(int m, int n) {
        //TODO(lxh) 动态规划
        return 0;
    }

    public static void main(String[] args){
        System.out.println("---------start--------");

        System.out.println("---------end--------:" + uniquePaths(51,9));
    }
}
