package com.martix.x.sort;

import java.util.Arrays;

/**
 * Created by ayue on 下午3:07 2018/6/27
 * 快速排序  时间复杂度为O(nlogn)
 */
public class QuickSortSolution {


    public static void main(String[] args) {
        int[] integers = new int[]{33, 1, 3, 234, 5, 3435, 32, 34};

        sort(integers, 0, integers.length - 1);

        System.out.println(Arrays.toString(integers));
    }

    private static void sort(int[] params, int low, int high) {
        if (params == null || params.length == 0) {
            return;
        }
        if (low > high) {
            return;
        }

        int i = low;
        int j = high;

        Integer partition = params[i]; // 用子表的第一个记录做基准
        while (i < j) {// 从表的两端交替向中间扫描

            while (i < j && params[j] >= partition) {
                j--;
            }

            if (i < j) {
                params[i++] = params[j];// 用比基准小的记录替换低位记录
            }

            while (i < j && params[i] < partition) {
                i++;
            }

            if (i < j) {// 用比基准大的记录替换高位记录
                params[j--] = params[i];
            }
        }

        params[i] = partition; // 将基准数值替换回 a[i]

        sort(params, low, i - 1); // 对低子表进行递归排序
        sort(params, i + 1, high); // 对高子表进行递归排序
    }
}
