package com.martix.x.pub.code.arrays;

/**
 * Created by Andrew-Geng on 23:34 2022/12/20
 * 有序数组中出现次数超过25%的元素 lc 1287
 *
 * 给你一个非递减的有序整数数组，已知这个数组中恰好有一个整数，它的出现次数超过数组元素总数的 25%。
 *
 * 请你找到并返回这个整数
 *

 * 示例：
 *
 * 输入：arr = [1,2,2,6,6,6,6,7,10]
 * 输出：6
 *
 */
public class MajorityElementQuarterSortedArraySolution {

    /**
     * 求出 25% 对应的出现次数threshold
     *
     * 遍历数组
     * 由于是有序数组，只需比较 当前位置 i 值和 i + threshold的值是否相等即可
     * @param arr
     * @return
     */
    public int findSpecialInteger(int[] arr) {
        int threshold = arr.length / 4;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i + threshold] == arr[i]) {
                return arr[i];
            }
        }

        return 0;
    }

    public int findSpecialInteger_1(int[] arr) {
        int pre = arr[0];
        int count = 1;
        int b = arr.length / 4;

        for(int i = 1; i < arr.length; i++){
            if(arr[i] == pre){
                count++;
            }else if(count > b){
                return pre;
            }else{
                count = 1;
                pre = arr[i];
            }
        }
        return pre;
    }

}
