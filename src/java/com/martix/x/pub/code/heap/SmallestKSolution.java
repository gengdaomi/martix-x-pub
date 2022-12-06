package com.martix.x.pub.code.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Andrew-Geng on 1:08 上午 2021/5/29
 * 最小K个数 https://leetcode-cn.com/problems/smallest-k-lcci/
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
        List<Integer> result = new SmallestKSolution().smallestK_1(arr, 4);
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


    /**
     * 核心思路：快排
     *
     * 借鉴快速排序的思想。
     * 我们知道快排的划分函数每次执行完后都能将数组分成两个部分，
     * 小于等于分界值 pivot 的元素的都会被放到数组的左边，
     * 大于的都会被放到数组的右边，然后返回分界值的下标。
     * 与快速排序不同的是，快速排序会根据分界值的下标递归处理划分的两侧，而这里我们只处理划分的一边。
     *
     * 我们定义函数 randomized_selected(arr, l, r, k) 表示划分数组 arr 的 [l,r] 部分，使前 k 小的数在数组的左侧，
     * 在函数里我们调用快排的划分函数，假设划分函数返回的下标是 pos（表示分界值 pivot 最终在数组中的位置），
     * 即 pivot 是数组中第 pos - l + 1 小的数，那么一共会有三种情况：
     *
     * 如果 pos - l + 1 == k，表示 pivot 就是第k 小的数，直接返回即可；
     * 如果 pos - l + 1 < k，表示第k 小的数在 pivot 的右侧，
     * 因此递归调用 randomized_selected(arr, pos + 1, r, k - (pos - l + 1))；
     * 如果 pos - l + 1 > k，表示第k 小的数在 pivot 的左侧，递归调用 randomized_selected(arr, l, pos - 1, k)。
     * 函数递归入口为 randomized_selected(arr, 0, arr.length - 1, k)。在函数返回后，将前 k 个数放入答案数组返回即可。
     *
     *
     *
     * 时间复杂度：O(n), 最坏的情况是O(n^2)
     * 空间复杂度：O(logn)
     * @param arr
     * @param k
     * @return
     */
    public int[] smallestK_1(int[] arr, int k) {
        randomizedSelected(arr, 0, arr.length - 1, k);
        int[] vec = new int[k];
        for (int i = 0; i < k; ++i) {
            vec[i] = arr[i];
        }
        return vec;
    }

    private void randomizedSelected(int[] arr, int l, int r, int k) {
        if (l >= r) {
            return;
        }
        int pos = randomizedPartition(arr, l, r);
        int num = pos - l + 1;
        if (k == num) {
            return;
        } else if (k < num) {
            randomizedSelected(arr, l, pos - 1, k);
        } else {
            randomizedSelected(arr, pos + 1, r, k - num);
        }
    }

    // 基于随机的划分
    private int randomizedPartition(int[] nums, int l, int r) {
        int i = new Random().nextInt(r - l + 1) + l;
        swap(nums, r, i);
        return partition(nums, l, r);
    }

    private int partition(int[] nums, int l, int r) {
        int pivot = nums[r];
        int i = l - 1;
        for (int j = l; j <= r - 1; ++j) {
            if (nums[j] <= pivot) {
                i = i + 1;
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, r);
        return i + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 快排的另一种写法
     */
    int k;
    public int[] smallestK_3(int[] arr, int _k) {
        k = _k;
        int n = arr.length;
        int[] result = new int[k];

        if (k == 0) {
            return result;
        }

        qsort(arr, 0, n - 1);
        for (int i = 0; i < k; i++){
            result[i] = arr[i];
        }

        return result;
    }

    private void qsort(int[] arr, int l, int r) {
        if (l >= r){
            return ;
        }

        int i = l, j = r;
        int ridx = new Random().nextInt(r - l + 1) + l;
        swap(arr, ridx, l);

        int x = arr[l];
        while (i < j) {
            while (i < j && arr[j] >= x) j--;
            while (i < j && arr[i] <= x) i++;
            swap(arr, i, j);
        }

        swap(arr, i, l);
        // 集中答疑：因为题解是使用「基准点左侧」来进行描述（不包含基准点的意思），所以这里用的 k（写成 k - 1 也可以滴
        if (i > k){
            qsort(arr, l, i - 1);
        }
        
        if (i < k){
            qsort(arr, i + 1, r);
        }
    }

}
