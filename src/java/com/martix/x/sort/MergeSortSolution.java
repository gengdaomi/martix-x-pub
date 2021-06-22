package com.martix.x.sort;

/**
 * Created by ayue on 下午5:22 2018/6/29
 * 归并排序
 */
public class MergeSortSolution {


    public int[] sort(int[] nums, int low, int high) {
        int mid = low + (high - low) / 2;

        if (low < high) {
            sort(nums, low, mid);
            sort(nums, mid + 1, high);
            //左右归并
            merge(nums, low, mid, high);
        }

        return nums;
    }

    public int[] merge(int[] a, int low, int mid, int high) {
        int[] temp = new int[a.length];

        int i = low;
        int j = mid + 1;

        int k = 0;
        // 把较小的数先移到新数组中
        while (i <= mid && j <= high) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }

        // 把左边剩余的数移入数组
        while (i <= mid) {
            temp[k++] = a[i++];
        }

        // 把右边边剩余的数移入数组
        while (j <= high) {
            temp[k++] = a[j++];
        }

        // 把新数组中的数覆盖nums数组
        for (int x = 0; x < temp.length; x++) {
            a[x + low] = temp[x];
        }

        return a;
    }

    public static void main(String[] args) {
        new MergeSortSolution().sort(null, 1, 4);
    }
}
