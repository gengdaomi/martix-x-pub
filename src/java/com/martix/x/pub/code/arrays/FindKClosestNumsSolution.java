package com.martix.x.pub.code.arrays;

import java.util.*;

/**
 * Created by Andrew-Geng on 11:03 下午 2021/7/6
 * 找到 K 个最接近的元素
 * lc 658
 *
 * 给定一个排序好的数组arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。
 * 返回的结果必须要是按升序排好的。
 *
 * 整数 a 比整数 b 更接近 x 需要满足：
 *
 * |a - x| < |b - x| 或者
 * |a - x| == |b - x| 且 a < b
 *
 * 示例 1：
 *
 * 输入：arr = [1,2,3,4,5], k = 4, x = 3
 * 输出：[1,2,3,4]
 * 示例 2：
 *
 * 输入：arr = [1,2,3,4,5], k = 4, x = -1
 * 输出：[1,2,3,4]
 *
 * 提示：
 *
 * 1 <= k <= arr.length
 * 1 <= arr.length<= 104
 * 数组里的每个元素与x 的绝对值不超过 104
 *
 */
public class FindKClosestNumsSolution {

    /**
     * 核心思路：
     * 排除法（双指针）
     *
     * 一个一个删，因为是有序数组，且返回的是连续升序子数组，所以 每一次删除的元素一定位于边界 ；
     *
     * 由于数组有序，所以最后找到的k个元素也一定是有序的，其实就是返回了一个长度为k的子数组，相当于在长度为n的数组中去掉n-k个数字，
     * 而且去掉的顺序肯定是从两头开始去，因为距离x最远的数字肯定在首尾出现。
     * 每次比较首尾两个数字跟x的距离，将距离大的那个数字删除，直到剩余的数组长度为k为止;
     *
     * 因为要删除的元素都位于边界，于是可以使用 双指针 对撞的方式确定保留区间，即「最优区间」
     *
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     * @param arr
     * @param k
     * @param x
     * @return
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int size = arr.length;

        int left = 0;
        int right = size - 1;

        int removeNums = size - k;
        while (removeNums > 0) {
            if (x - arr[left] <= arr[right] - x) {
                right--;
            } else {
                left++;
            }
            removeNums--;
        }

        List<Integer> result = new ArrayList<>();
        for (int i = left; i < left + k; i++) {
            result.add(arr[i]);
        }
        return result;
    }

    /**
     * 使用 Collection.sort()
     *可以将数组中的元素按照与目标 x 的差的绝对值排序，排好序后前 k 个元素就是我们需要的答案。
     *
     * 时间复杂度O(nlogn);; Collections.sort() 使用二叉排序所以时间复杂度是  O(nlogn)
     * 空间复杂度O(k);;就地排序不需要额外的空间
     * @param arr
     * @param k
     * @param x
     * @return
     */
    public List<Integer> findClosestElements_1(int[] arr, int k, int x) {
        List<Integer> result = new ArrayList<>();
        for(int num:arr){
            result.add(num);
        }

        Collections.sort(result, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return a == b ? a - b : Math.abs(a - x) - Math.abs(b - x);
            }
        });

        result = result.subList(0, k);
        Collections.sort(result);

        return result;
    }
}
