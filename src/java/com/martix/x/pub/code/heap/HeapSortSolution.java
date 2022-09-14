package com.martix.x.pub.code.heap;

import java.util.Arrays;

/**
 * Created by ayue on 下午6:09 2018/6/29
 * 堆排序算法：
 * 1.把无序数组构成二叉堆，需要从小到大排序，则先构成大顶堆；需要从大到小排序，则先构成小顶堆
 * 2.循环删除堆顶元素，替换到二叉堆的末尾，调整堆产生新的堆顶；
 */
public class HeapSortSolution {

    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        //1.构建大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr, i, arr.length);
        }

        //2.调整堆结构+交换堆顶元素与末尾元素
        for (int j = arr.length - 1; j > 0; j--) {
            swap(arr, 0, j);//将堆顶元素与末尾元素进行交换
            adjustHeap(arr, 0, j);//重新对堆进行调整
        }

    }

    /**
     * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
     *
     * @param arr
     * @param parentIndex
     * @param length
     */
    public static void adjustHeap(int[] arr, int parentIndex, int length) {
        int temp = arr[parentIndex];//先取出当前元素parentIndex

        for (int childIndex = parentIndex * 2 + 1; childIndex < length; childIndex = childIndex * 2 + 1) {//从parentIndex结点的左子结点开始，也就是2*parentIndex+1处开始
            if (childIndex + 1 < length && arr[childIndex] < arr[childIndex + 1]) {//如果左子结点小于右子结点，k指向右子结点
                childIndex++;
            }

            if (arr[childIndex] > temp) {//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                arr[parentIndex] = arr[childIndex];
                parentIndex = childIndex;
            } else {
                break;
            }
        }
        arr[parentIndex] = temp;//将temp值放到最终的位置
    }

    /**
     * 交换元素
     *
     * @param arr
     * @param a
     * @param b
     */
    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }


}
