package leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * leetcode139 medium 单词拆分
 * @author lixinhai
 * @date 2024/11/29
 */
public class Solution139 {
  public boolean wordBreak(String s, List<String> wordDict) {
//    if(wordDict.contains(s)){
//      return true;
//    }
//
//    for(String word : wordDict){
//      if(s.startsWith(word)){
//        if(wordBreak(s.substring(word.length()), wordDict)){
//          return true;
//        }
//      }
//    }
//    return false;

    boolean[] isFailArray = new boolean[s.length()+1];
    return wordBreakWrap(s, wordDict, 0, isFailArray);
  }

  public boolean wordBreakWrap(String s, List<String> wordDict, int index, boolean[] isFailArray) {
    if(isFailArray[index]){
      return false;
    }

    String tmp = s.substring(index);
    if(wordDict.contains(tmp)){
      return true;
    }

    for(String word : wordDict){
      if(tmp.startsWith(word)){
        if(wordBreakWrap(s, wordDict, index+word.length(),  isFailArray)){
          return true;
        }
        else {
          isFailArray[index+word.length()] = true;
        }
      }
    }

    isFailArray[index] = true;
    return false;
  }



  public static void main(String[] args) {
//    String s = "leetcode";
//    List<String> wordDict = Arrays.asList("leet", "code");

    String s = "applepenapple";
    List<String> wordDict = Arrays.asList("apple","pen");

//    String s = "catsandog";
//    List<String> wordDict = Arrays.asList("cats","dog","sand","and","cat");
//
//    String s = "cars";
//    List<String> wordDict = Arrays.asList("car","ca","rs");

    Solution139 solution139 = new Solution139();
    System.out.println(solution139.wordBreak(s, wordDict));

  }
}
