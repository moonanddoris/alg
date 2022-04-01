package sorting;

import java.util.Arrays;

/**
 * 均以升序说明
 * @author lixinhai
 * @date 2022/3/22
 */
public class SortingOpt {

    //冒泡
    public static void bubbleSort(int[] datas){
        final int size = datas.length;

        for(int i=0; i<size-1 ;i++){

            // 若某次没有进行交换过数据 说明已经有序
            boolean hasSwap = false;
            for(int j=0; j< size-1-i; j++){

                if(datas[j] > datas[j+1]){
                    int tmp = datas[j];
                    datas[j] = datas[j+1];
                    datas[j+1] = tmp;

                    hasSwap = true;
                }
            }

            if(!hasSwap){
                return;
            }
        }
    }

    /**
     * 选择排序是不稳定的  因为每次通过位置进行交换 破坏了稳定性
     * @param datas
     */
    public static void selectSort(int[] datas){
        final int size = datas.length;

        for(int i=0; i<size-1; i++){
            int minIndex = i;

            for(int j=i+1;j<size;j++){
                if(datas[j] < datas[minIndex]){
                    minIndex = j;
                }
            }

            int tmp = datas[i];
            datas[i] = datas[minIndex];
            datas[minIndex] = tmp;
        }
    }

    //插入

    /**
     * 和冒泡 最大的不同在于每次不需要 前后替换元素，减少了赋值操作
     * 插入排序的做法是，前面值直接覆盖后面值，最终最前面的坑位由 比较值来填充
     * @param datas
     */
    public static void insetSort(int[] datas){
        final int size = datas.length;

        for(int i=0; i<size-1 ;i++){

            int target = datas[i+1];

            int j=i;
            for(; j >= 0; j--){

                if(target < datas[j]){
                    datas[j+1] = datas[j];
                }
                else {
                    break;
                }
            }

            datas[j+1] = target;
        }
    }

    /**
     *
     * @param datas
     */
    public static void quickSort(int[] datas) {
        final int size = datas.length;

        quickSort(datas, 0, size-1);
    }

    public static void quickSort(int[] datas, int startIndex, int endIndex) {
        if(startIndex >= endIndex){
            return;
        }

        int mid = partitionArray(datas, startIndex, endIndex);

        quickSort(datas, startIndex, mid-1);
        quickSort(datas, mid+1, endIndex);
    }

    /**
     * 返回pivot位置
     * @return
     */
    public static int partitionArray(int[] datas, int startIndex, int endIndex){

        int i,j,pivot;

        i=j=startIndex;

        pivot = datas[endIndex];

        while (i < endIndex){
            if(datas[i] <= pivot){
                i++;
                j=i;
            }
            else {
                while (j<=endIndex && datas[j]>=pivot){
                   j++;
                }

                if(j>endIndex){
                    //未找到 结束
                    break;
                }
                else {
                    //swap i j
                    int tmp = datas[i];
                    datas[i] = datas[j];
                    datas[j] = tmp;

                    i++;
                    j++;
                }
            }
        }

        datas[endIndex] = datas[i];
        datas[i] = pivot;

        return i;
    }

    /**
     * 归并排序
     * @param datas
     */
    public static int[] mergeSort(int datas[], int startIndex, int endIndex){

        if(startIndex == endIndex){
            return new int[]{datas[startIndex]};
        }

        //(endIndex+startIndex)/2 取中值优化
        int[] a1 = mergeSort(datas, startIndex, startIndex + (endIndex - startIndex)/2);
        int[] a2 = mergeSort(datas,  startIndex + (endIndex - startIndex)/2 +1, endIndex);

        System.out.println(Arrays.toString(a1));
        System.out.println(Arrays.toString(a2));

        return mergeSortedArray(a1, a2);
    }


    //合并两个数组
    public static int[] mergeSortedArray(int datas1[], int[] datas2){
        int[] tmp = new int[datas1.length+ datas2.length];

        int i, j, x;
        i = j = x =0;

        while (true){
            if(i< datas1.length && j < datas2.length) {
                if (datas1[i] < datas2[j]) {
                    tmp[x] = datas1[i];
                    i++;
                } else {
                    tmp[x] = datas2[j];
                    j++;
                }
            }
            else if(i == datas1.length && j< datas2.length){
                tmp[x] = datas2[j];
                j++;
            }
            else if(j == datas2.length && i < datas1.length){
                tmp[x] = datas1[i];
                i++;
            }

            x++;

            if(i==datas1.length && j==datas2.length){
                break;
            }
        }

        return tmp;
    }

    /**
     * 计数排序 提前预知数据范围 且数据范围不大 且数据均为整数
     * @param datas
     * @return
     */
    public static int[] countSort(int[] datas, int dataMax){
        int[] tmp = new int[datas.length];

        int[] cntArray = new int[dataMax+1];
        for(int x : datas){
            cntArray[x] ++;
        }

        int[] cntArrayPro = new int[dataMax+1];
        for(int j=0; j< cntArrayPro.length-1;j++){
            cntArrayPro[j+1] = cntArrayPro[j] + cntArray[j+1];
        }

        for(int x=datas.length-1 ; x>=0; x--){

            tmp[cntArrayPro[datas[x]] - 1] = datas[x];
            cntArrayPro[datas[x]]--;
        }
        return tmp;
    }

    public static void main(String [] args){

        int[] test = {6, 5, 4, 3, 2, 1, 3};
        //int[] test = {3, 6, 5, 4};

        System.out.println(Arrays.toString(test));

        //冒泡
        //bubbleSort(test);

        //插入
        //insetSort(test);

        //选择
        //selectSort(test);

        //归并
        //int[] re = mergeSort(test, 0, 6);
        //System.out.println(Arrays.toString(re));

        /*
        // 合并有序数组
        int[] test1 = {1, 4, 7, 10, 12};
        int[] test2 = {0, 2, 9, 11};
        int[] re = mergeSortedArray(test1, test2);
        System.out.println(Arrays.toString(re));
         */

        // 快排分区
        //int re = partitionArray(test, 0, 3);
        //System.out.println(re);

        // 快速排序
        //quickSort(test);

        int[] re = countSort(test,  6);


        System.out.println(Arrays.toString(re));


    }
}
