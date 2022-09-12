package com.martix.x.pub.code.top;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by Andrew-Geng on 1:08 上午 2021/5/29
 * 最小K个数
 * https://leetcode-cn.com/problems/smallest-k-lcci/
 * <p>
 * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
 * <p>
 * 示例：
 * <p>
 * 输入： arr = [1,3,5,7,2,4,6,8], k = 4
 * 输出： [1,2,3,4]
 * 提示：
 * <p>
 * 0 <= len(arr) <= 100000
 * 0 <= k <= min(100000, len(arr))
 */
public class SmallestKSolution {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 5, 7, 2, 4, 6, 8};
        int[] result = new SmallestKSolution().smallestK_1(arr, 4);
        System.out.println(result);
    }

    public int[] smallestK(int[] arr, int k) {
        BigHeap bigHeap = new BigHeap();
        bigHeap.buildBigHeap(arr, k);

        for (int i = k; i < arr.length; i++) {
            if (arr[0] > arr[i]) {
                arr[0] = arr[i];
                bigHeap.downAdjust(arr, 0, k);
            }
        }

        return Arrays.copyOfRange(arr, 0, k);
    }

    /**
     * 借用java优先级队列
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] smallestK_1(int[] arr, int k) {
        if (k == 0) {
            return new int[0];
        }

        if (arr.length == 0) {
            return arr;
        }

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int i = 0; i < arr.length; i++) {
            if (priorityQueue.size() == k) {
                if (priorityQueue.peek() > arr[i]) {
                    priorityQueue.poll();
                    priorityQueue.offer(arr[i]);
                }
            } else {
                priorityQueue.offer(arr[i]);
            }
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = priorityQueue.poll();
        }

        return result;
    }

    class BigHeap {

        /**
         * 构建大顶堆
         *
         * @param nums
         * @param length
         */
        public void buildBigHeap(int[] nums, int length) {
            for (int i = (length - 2) / 2; i >= 0; i--) {
                downAdjust(nums, i, length);
            }
        }

        /**
         * @param nums
         * @param index
         * @param length 堆的大小
         */
        public void downAdjust(int[] nums, int index, int length) {
            int temp = nums[index]; //用于交换

            int childIndex = 2 * index + 1;

            while (childIndex < length) {
                if (childIndex + 1 < length && nums[childIndex + 1] > nums[childIndex]) { //如果有右孩子，且右孩子小于左孩子的值，则定位到右孩子
                    childIndex++;
                }

                if (temp >= nums[childIndex]) { //如果父节点小于任何一个孩子的值，直接跳出
                    break;
                }

                nums[index] = nums[childIndex];
                index = childIndex;
                childIndex = 2 * childIndex + 1;
            }

            nums[index] = temp;
        }
    }
}
