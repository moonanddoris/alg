package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode70 easy 爬楼梯
 * @author lixinhai
 * @date 2022/9/13
 */
public class Solution70 {
    public static int climbStairs(int n) {
        if(n == 0){return 0;}
        if(n == 1){return 1;}
        if(n == 2){return 2;}

        return climbStairs(n-1) + climbStairs(n-2);
    }

    /**
     * 缓存优化
     * @param n
     * @return
     */
    public static int climbStairsPro(int n) {
        Map<Integer, Integer> cache = new HashMap<>();
        cache.put(0, 0);
        cache.put(1, 1);
        cache.put(2, 2);

        return climbStairsInner(n, cache);
    }

    public static int climbStairsInner(int n, Map<Integer, Integer> cache) {
        if(n <= 2){return cache.get(n);}

        int tmp1, tmp2;
        if(cache.containsKey(n-1)){
            tmp1 = cache.get(n-1);
        }
        else {
            tmp1 = climbStairsInner(n-1, cache);
            cache.put(n-1, tmp1);
        }

        if(cache.containsKey(n-2)){
            tmp2 = cache.get(n-2);
        }
        else {
            tmp2 = climbStairsInner(n-2, cache);
            cache.put(n-2, tmp2);
        }

        return tmp1+tmp2;
    }

    public static void main(String[] args){
        System.out.println(climbStairs(4));
    }
}
