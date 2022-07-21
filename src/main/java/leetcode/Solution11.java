package leetcode;

/**
 * leetcode11 medium 盛最多水的容器
 * @author lixinhai
 * @date 2022/7/18
 */
public class Solution11 {

    // 时间复杂度 O(n^2)
    public static int maxArea(int[] height) {
        int max = 0;
        for(int i=0; i< height.length-1; i++){

            for(int j=i+1; j< height.length; j++){
                int tmp = (j-i)* Math.min(height[i], height[j]);

                if(tmp > max){ max=tmp; }
            }
        }
        return max;
    }

    // TODO 使用二分法 复杂度为 n*logn
    // 更新 这个思路不通，merge的成本比较大 是n^2 整体的复杂度是 n^2 * logn
    public static int maxAreaPro(int[] height) {
        return innerMax(height, 0, height.length-1);
    }

    public static int innerMax(int[] height, int left, int right){
        if(left >= right){ return 0; }
        if(left < 0 || right>= height.length){return 0;}
        int mid = left + (right - left)/2;

        int leftMax = innerMax(height, left, mid);
        int rightMax = innerMax(height, mid, right);
        int midMax = mergeMax(height, left, right, mid);

        return Math.max(leftMax, Math.max(rightMax, midMax));
    }

    public static int mergeMax(int[] height, int left, int right, int mid){
        return 0;
    }

    // TODO 双指针法 有些类似贪心算法 下一步尽量尝试获取正收益
    public static int maxAreaV2(int[] height) {
        int max = 0;
        int left = 0, right = height.length-1;
        while (right > left){

            max = Math.max(max, Math.min(height[left], height[right])*(right-left));

            // 移动长板的可能无收益 甚至负收益；只有移动短板才有可能有正收益
            if(height[right] > height[left]){
                left++;
            }
            else {
                right--;
            }

            // 更简洁的写法
            //max = height[right] < height[left] ? Math.max(max, (right - left) * height[left++]): Math.max(max, (right - left) * height[right--]);

        }
        return max;
    }


    public static void main(String[] args){
        int[] t = {1,8,6,2,5,4,8,3,7};

        System.out.println(maxAreaV2(t));
    }
}
