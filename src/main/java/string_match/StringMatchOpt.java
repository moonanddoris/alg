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
    public static int bMBadChar(String patternStr, String mainStr){
        int patternLen = patternStr.length();
        int mainLen = mainStr.length();

        if(patternLen > mainLen){
            return -1;
        }

        char[] patternCharArray = patternStr.toCharArray();
        char[] mainCharArray = mainStr.toCharArray();

        for(int i=0; i< mainLen-patternLen+1; ){

            int j, si=-1, xi;
            char badChar = 'a';
            for(j=0;j<patternLen;j++){
                if(patternCharArray[patternLen-1 -j] == mainCharArray[i + patternLen-1 - j]){
                    continue;
                }

                si = patternLen-1 - j;
                badChar = mainCharArray[i+ patternLen -1 -j];
                break;
            }

            // 没有出现坏字符，说明完成匹配
            if(si == -1){
                return i;
            }
            else {
                // 寻找xi  直接遍历寻找低效，可以使用辅助空间进行提前存储 空间换时间
                for(;j<patternLen;j++){
                    if(badChar == patternCharArray[patternLen-1-j]){
                        break;
                    }
                }
                //计算偏移量
                xi = patternLen-1-j;
                i+= si - xi;
            }
        }

        return -1;
    }

    /**
     * BM 算法-好后缀原则
     * @param patternStr
     * @param mainStr
     */
    public static int bMGoodPostfix(String patternStr, String mainStr){
        int patternLen = patternStr.length();
        int mainLen = mainStr.length();

        if(patternLen > mainLen){
            return -1;
        }

        char[] patternCharArray = patternStr.toCharArray();
        char[] mainCharArray = mainStr.toCharArray();

        int[] suffix = new int[patternLen];
        boolean[] prefix = new boolean[patternLen];

        // 初始化
        for(int i=0;i<patternLen;i++){
            suffix[i] = -1;
            prefix[i] = false;
        }

        // 先计算suffix数组
        for(int i=0; i< patternLen-1; i++){

            int j = i;
            int k = 0;

            while (j>=0 && patternCharArray[j] == patternCharArray[patternLen -1 -k]){
                j--;
                k++;
                suffix[k] = j+1;
            }

            if(j==-1) prefix[k] = true;
        }


        for(int i=0; i< mainLen-patternLen+1; ) {

            int j;
            for (j = 0; j < patternLen; j++) {
                if (patternCharArray[patternLen - 1 - j] == mainCharArray[i + patternLen - 1 - j]) {
                    continue;
                }
                break;
            }

            // j为好后缀的长度
            if(j == 0){
                // 表示没有好后缀 直接跳过整个模式串
                i += patternLen;
            }
            else if(j == patternLen){
                // 完全匹配
                return i;
            }
            else {
                // 计算偏移量
                if(suffix[j] >= 0){
                    //i += patternLen-1 - suffix[j];
                    i += patternLen -j - suffix[j];

                }
                else {
                    // 接着判断prefix
                    boolean hasPrefix = false;
                    int x = j;
                    for(; x>0 ;x--){
                        if(prefix[x]){
                            hasPrefix = true;
                            break;
                        }
                    }

                    if(hasPrefix){
                        i += patternLen-1 - suffix[x];
                    }
                    else {
                        i += patternLen;
                    }
                }
            }
        }

        return -1;
    }

    /**
     * KMP
     * @param patternStr
     * @param mainStr
     */
    public static int kMPMatch(String patternStr, String mainStr){
        //TODO
        return -1;
    }


    public static void main(String[] args){

        //String mainStr = "bfadgiislluooww";
        String mainStr = "baaaabaaaaabaaba" ;
        //String patternStr = "luo";
        String patternStr = "baab";

        int re = bFMatch(patternStr, mainStr);
        System.out.println(re);

        re = rKMatch(patternStr, mainStr);
        System.out.println(re);

        re = bMBadChar(patternStr, mainStr);
        System.out.println(re);

        mainStr = "xxxcabcab";
        patternStr = "cabcab";
        // TODO 测试更多的case
        re = bMGoodPostfix(patternStr, mainStr);
        System.out.println(re);

    }

}
