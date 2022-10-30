package leetcode;

/**
 * leetcode461 easy 汉明距离
 * @author lixinhai
 * @date 2022/10/27
 */
public class Solution461 {
    /**
     * 先异或 再计算1个数
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance(int x, int y) {

        int a = x^y;

        int cnt=0;
        while (a!=0){
            a = a & (a-1);
            cnt++;
        }

        return cnt;
    }
}
