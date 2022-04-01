package search;

import java.util.Arrays;

/**
 * @author lixinhai
 * @date 2022/3/30
 */
public class SearchOpt {

    /**
     * 简单版本二分查找 没有重复元素
     * @return 数组下标
     */
    public static int binarySearch(int[] datas, int target){
        int low = 0;
        int high = datas.length-1;

        while (high >= low){
            int mid = low + (high-low)/2;

            if(datas[mid] == target){
                return mid;
            }
            else if(datas[mid] > target){
                high = mid - 1;
                //high = mid;
            }
            else {
                low = mid + 1;
                //low = mid;
            }
        }
        return -1;
    }

    /**
     * 考虑存在重复元素
     * 查找第一个等于给定值
     * @param datas
     * @param target
     * @return
     */
    public static int binarySearchFirst(int[] datas, int target){
        int low = 0;
        int high = datas.length-1;

        while (high >= low){
            int mid = low + (high-low)/2;

            if(datas[mid] == target){

                if(mid==0 || datas[mid-1] != target){
                    return mid;
                }
                else {
                    high = mid - 1;
                }
            }
            else if(datas[mid] > target){
                high = mid - 1;
                //high = mid;
            }
            else {
                low = mid + 1;
                //low = mid;
            }
        }
        return -1;
    }

    /**
     * 考虑存在重复元素
     * 查找最后一个等于给定值
     * @param datas
     * @param target
     * @return
     */
    public static int binarySearchLast(int[] datas, int target){
        int low = 0;
        int high = datas.length-1;

        while (high >= low){
            int mid = low + (high-low)/2;

            if(datas[mid] == target){
                if(mid== datas.length-1 || datas[mid+1] != target){
                    return mid;
                }
                else {
                    low = mid + 1;
                }
            }
            else if(datas[mid] > target){
                high = mid - 1;
                //high = mid;
            }
            else {
                low = mid + 1;
                //low = mid;
            }
        }
        return -1;
    }

    /**
     * 考虑存在重复元素
     * 查找第一个大于等于给定值
     * @return 数组下标
     */
    public static int binarySearchFirstGte(int[] datas, int target){
        int low = 0;
        int high = datas.length-1;

        while (high >= low){
            int mid = low + (high-low)/2;

            // 以下两个条件可以合并 >=
            if(datas[mid] == target){

                if(mid==0 || datas[mid-1] < target){
                    return mid;
                }
                else {
                    high = mid - 1;
                }
            }
            else if(datas[mid] > target){

                if(mid==0 || datas[mid-1] < target){
                    return mid;
                }
                else {
                    high = mid - 1;
                }
            }
            else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 考虑存在重复元素
     * 查找最后一个小于等于给定值
     * @param datas
     * @param target
     * @return
     */
    public static int binarySearchLastLte(int[] datas, int target){
        int low = 0;
        int high = datas.length-1;

        while (high >= low){
            int mid = low + (high-low)/2;

            if(datas[mid] == target){
                if(mid== datas.length-1 || datas[mid+1] > target){
                    return mid;
                }
                else {
                    low = mid + 1;
                }
            }
            else if(datas[mid] > target){
                high = mid - 1;
            }
            //datas[mid] < target
            else {
                if(mid== datas.length-1 || datas[mid+1] > target){
                    return mid;
                }
                else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        /*
        int[] test = {1, 3, 5, 6, 7, 10};
        System.out.println(Arrays.toString(test));
        int re = binarySearch(test, 3);
        System.out.println(re);
         */

        int[] test = {3, 3, 3, 3, 7, 10};
        System.out.println(Arrays.toString(test));
        //int re = binarySearchFirst(test, 3);
        //int re = binarySearchLast(test, 3);
        int re = binarySearchFirstGte(test, 3);
        //int re = binarySearchLastLte(test, 6);
        System.out.println(re);
    }

}