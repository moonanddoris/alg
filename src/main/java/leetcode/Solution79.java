package leetcode;

/**
 * leetcode79 medium 单词搜索
 * @author lixinhai
 * @date 2022/6/16
 */
public class Solution79 {

    public static boolean exist(char[][] board, String word) {

        // 默认值为false
        boolean[][] road = new boolean[board.length][board[0].length];

        for(int i =0; i< board.length; i++){
            for(int j=0; j<board[0].length; j++){
                boolean re = existInner(board, i, j, word, road);
                if(re){ return true;}
            }
        }

        return false;
    }

    /**
     * 以坐标 x,y 为起点，寻找word是否存在
     * @param board 二维字符数组
     * @param x
     * @param y
     * @param word 目标字符串
     * @param road 已经过的路径
     * @return
     */
    public static boolean existInner(char[][] board, int x, int y, String word, boolean[][] road) {
        // 查询到最后一个 成功
        //if(word.length() == 0){ return true;}

        // x y 边界判断
        if(x >= board.length || x<0){
            return false;
        }
        if(y >= board[0].length || y<0){
            return false;
        }

        // 是否已经走过的路径判读
        if(road[x][y]){return false;}

        // 终止条件 查询到最后一个
        if(word.length() ==1){
            return word.charAt(0) == board[x][y];
        }

        if(word.charAt(0) != board[x][y]){
            return false;
        }
        else {
            // 添加当前路径
            road[x][y] = true;

            // 向后截取
            String sub = word.substring(1);

            boolean re;

            if(y+1 < board[0].length ) {
                re = existInner(board, x, y + 1, sub, road);
                if (re) {
                    return true;
                }
            }

            if(y-1 >= 0 ) {
                re = existInner(board, x, y - 1, sub,  road);
                if (re) {
                    return true;
                }
            }
            if(x+1 < board.length ) {
                re = existInner(board, x + 1, y, sub,  road);
                if (re) {
                    return true;
                }
            }
            if(x-1 >= 0) {
                re = existInner(board, x - 1, y, sub,  road);
                if (re) {
                    return true;
                }
            }
        }

        // 这里是关键 如果当前路径失败，则删除路径的记录
        road[x][y] = false;
        return false;
    }

    public static void main(String[] args){

        //char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        char[][] board = {{'A','A','A','A','A','A'},{'A','A','A','A','A','A'},{'A','A','A','A','A','A'},{'A','A','A','A','A','A'},{'A','A','A','A','A','A'},{'A','A','A','A','A','A'}};

        String word = "AAAAAAAAAAAAAAB";

        System.out.println(exist(board, word));

        /*
        System.out.println(board.length);
        System.out.println(board[0].length);

        for(int i =0; i< board.length; i++){
            for(int j=0; j<board[0].length; j++){
                System.out.println(board[i][j]);
            }
        }
        */
    }
}
