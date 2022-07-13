package leetcode;

/**
 * leetcode5 medium 最长回文子串
 * @author lixinhai
 * @date 2022/7/13
 */
public class Solution5 {

    //  遍历元素 作为中心点向两侧扩散 时间复杂度O(n^2)
    public static String longestPalindrome(String s) {
        if(null == s || s.length() <=1){ return s; }

        char[] sArray = s.toCharArray();

        String maxStr = null;

        for(int i=1; i<s.length(); i++){

            //无中心元素
            int right=0;
            int left=1;
            while (i+right<s.length() && i-left>=0 && (sArray[i-left]==sArray[i+right]) ){
                right++;
                left++;
            }

            if(right > 0){
                String imax = s.substring(i-(left-1), i+(right-1)+1);
                if(maxStr == null || imax.length()>maxStr.length()){
                    maxStr = imax;
                }
            }

            //i为中心元素
            int l = 0;
            while (i+l<s.length() && i-l>=0 && (sArray[i-l]==sArray[i+l]) ){
                l++;
            }

            if(l>0){
                String imax = s.substring(i-(l-1), i+(l-1)+1);
                if(maxStr == null || imax.length()>maxStr.length()){
                    maxStr = imax;
                }
            }
        }

        return maxStr;
    }

    public static void main(String[] args){
        //String s = "ababd";
        String s = "bbc";

        String max = longestPalindrome(s);
        System.out.println(max);
    }
}
