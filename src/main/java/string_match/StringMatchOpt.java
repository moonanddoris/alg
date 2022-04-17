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
     * RK算法 使用26进制进行hash值计算
     * @param patternStr
     * @param mainStr
     * @return
     */
    public static int rKMatch(String patternStr, String mainStr){

        int patternLen = patternStr.length();
        int mainLen = mainStr.length();

        if(patternLen > mainLen){
            return -1;
        }

        // 26进制数组
        int[] tmpData = new int[26];
        int pre = 1;
        for(int i=0; i< 26; i++){
            tmpData[i] = pre;
            pre *= 26;
        }

        char[] patternCharArray = patternStr.toCharArray();
        char[] mainCharArray = mainStr.toCharArray();

        int pattenValue = 0;
        for(int i=0;i<patternLen;i++){
            pattenValue += (patternCharArray[i]-'a') * tmpData[patternLen-i];
        }

        for(int i=0; i< mainLen-patternLen+1; i++){

            int mainValue = 0;
            for(int j=0; j< patternLen; j++){
                mainValue += (mainCharArray[i+j] - 'a') * tmpData[patternLen - j];

                // 如果大于，提前结束本次计算
                if(mainValue > pattenValue){
                    break;
                }
            }

            if(mainValue == pattenValue){
                return i;
            }
        }

        return -1;
    }


    /**
     * BM 算法-坏字符原则
     * @param patternStr
     * @param mainStr
     */
    public static void bMBadChar(String patternStr, String mainStr){
        //TODO

    }

    /**
     * BM 算法-坏字符原则
     * @param patternStr
     * @param mainStr
     */
    public static void bMGoodPostfix(String patternStr, String mainStr){
        //TODO

    }

    /**
     * KMP
     * @param patternStr
     * @param mainStr
     */
    public static void kMPMatch(String patternStr, String mainStr){
        //TODO

    }


    public static void main(String[] args){

        String mainStr = "bfadgiislluooww";
        String patternStr = "bfadg";

        int re = bFMatch(patternStr, mainStr);
        System.out.println(re);

        re = rKMatch(patternStr, mainStr);
        System.out.println(re);

    }

}
