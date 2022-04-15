package string_match;

/**
 * @author lixinhai
 * @date 2022/4/15
 */
public class StringMatchOpt {

    /**
     * BF 暴力匹配算法
     * @param patternStr 模式串
     * @param  mainStr 主串
     * @return
     */
    public static int bFMatch(String patternStr, String mainStr){

        int patternLen = patternStr.length();
        int mainLen = mainStr.length();

        if(patternLen > mainLen){
            return -1;
        }

        char[] patternCharArray = patternStr.toCharArray();
        char[] mainCharArray = mainStr.toCharArray();

        for(int i=0; i< mainLen-patternLen+1; i++){

            int j;
            for(j=0; j< patternLen; j++){
                if(patternCharArray[j] != mainCharArray[j + i]){
                    break;
                }
            }

            if(j == patternLen){
                return i;
            }
        }

        return -1;
    }

    /**
     *
     * @param patternStr
     * @param mainStr
     * @return
     */
    public static int rKMatch(String patternStr, String mainStr){
        //TODO 使用26进制进行计算hash值
        return -1;
    }

    public static void main(String[] args){

        String mainStr = "bfadgiislluooww";
        String patternStr = "ww";

        int re = bFMatch(patternStr, mainStr);
        System.out.println(re);

    }

}
