package leetcode;

/**
 * leecode48 medium 旋转图像
 * @author lixinhai
 * @date 2023/3/27
 */
public class Solution48 {

    public static void rotate(int[][] matrix) {
        int length = matrix.length;
        rotateInner(matrix, 0, length);
    }

    /**
     * 递归-每次旋转最外层即可
     * @param matrix
     * @param startIndex
     * @param length
     */
    public static void rotateInner(int[][] matrix, int startIndex, int length) {
        if(length <= 1){return;}

        int maxIndex = startIndex+length-1;

        // 抽象为 顺时针 向前走length-1步，起点为矩阵的左上角
        for(int x=0; x<length-1; x++){

            int x0=startIndex;
            int y0=startIndex+x;

            int[] newIndex = stepClockWise(x0, y0, startIndex, maxIndex, length-1);
            int x1 = newIndex[0];
            int y1 = newIndex[1];

            newIndex = stepClockWise(x1, y1, startIndex, maxIndex, length-1);
            int x2 = newIndex[0];
            int y2 = newIndex[1];

            newIndex = stepClockWise(x2, y2, startIndex, maxIndex, length-1);
            int x3 = newIndex[0];
            int y3 = newIndex[1];

            int tmp = matrix[x3][y3];
            matrix[x3][y3] = matrix[x2][y2];
            matrix[x2][y2] = matrix[x1][y1];
            matrix[x1][y1] = matrix[x0][y0];
            matrix[x0][y0] = tmp;
        }

        rotateInner(matrix, startIndex+1, length-2);
    }

    public static int[] stepClockWise(int x, int y, int startIndex, int maxIndex, int stepCnt){
        if(stepCnt == 0){
            return new int[]{x,y};
        }

        // 走一步
        if( startIndex == x){
            // 上边
            if(y == maxIndex){
                x++;
            }
            else {
                y++;
            }
        }
        else if(maxIndex == y){
            // 右边
            if(x == maxIndex){
                y--;
            }
            else {
                x++;
            }
        }
        else if(maxIndex == x){
            // 下边
            if(y == startIndex){
                x--;
            }
            else {
                y--;
            }
        }
        else if(startIndex == y){
            // 左边
            if(x == startIndex){
                y++;
            }
            else {
                x--;
            }
        }
        else {}

        return stepClockWise(x, y, startIndex, maxIndex, --stepCnt);
    }


    public static void main(String[] args){

        int[] tt = stepClockWise(1,2,1,2,2);
        System.out.println(tt[0]);
        System.out.println(tt[1]);

        int[][] test = {{1,2}, {3, 4}};

        rotate(test);

        System.out.println("------------");
    }


}
