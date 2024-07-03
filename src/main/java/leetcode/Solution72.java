package leetcode;

/**
 * leetcode72 medium 编辑距离 动态规划
 * @author lixinhai
 * @date 2024/5/29
 */
public class Solution72 {

    public int minDistance(String word1, String word2) {
        int x = word1.length();
        int y = word2.length();
        if(x==0 || y==0){
            return Math.max(x, y);
        }

        int[][] result = new int[x][y];

        // 初始化首行首列
        for(int j=0; j<y; j++){
            if(word1.charAt(0) == word2.charAt(j)){
                result[0][j] = j;
            }
            else {
                result[0][j] = j==0 ? 1 : result[0][j-1]+1;
            }
        }

        for(int i=0; i<x; i++){
            if(word1.charAt(i) == word2.charAt(0)){
                result[i][0] = i;
            }
            else {
                result[i][0] = i==0 ? 1 : result[i-1][0]+1;
            }
        }

        for(int i=1; i<x; i++){
            for(int j=1; j<y; j++){

                if(word1.charAt(i) == word2.charAt(j)){
                    // min(result[i-1][j]+1, result[i][j-1]+1, result[i-1][j-1])
                    result[i][j] = Math.min(Math.min(result[i-1][j]+1, result[i][j-1]+1), result[i-1][j-1]);
                }
                else {
                    // min(result[i-1][j]+1, result[i][j-1]+1, result[i-1][j-1]+1)
                    result[i][j] = Math.min(Math.min(result[i-1][j]+1, result[i][j-1]+1), result[i-1][j-1]+1);
                }

                //打印result二维数组，便于观察的形式
                System.out.println("---------------------");
                for(int[] a : result){
                    for(int b : a){
                        System.out.print(b + " ");
                    }
                    System.out.println();
                }
            }
        }
        return result[x-1][y-1];
    }

    public static void main(String[] args){
        Solution72 solution72 = new Solution72();
//        System.out.println(solution72.minDistance("sea", "eat"));
//        System.out.println(solution72.minDistance("zoologicoarchaeologist", "zoogeologist"));
//        System.out.println(solution72.minDistance("intention", "execution"));
        System.out.println(solution72.minDistance("pneumonoultramicroscopicsilicovolcanoconiosis", "ultramicroscopically"));
    }
}
