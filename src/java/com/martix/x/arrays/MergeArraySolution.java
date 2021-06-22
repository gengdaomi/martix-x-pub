package com.martix.x.arrays;

/**
 * Created By Andrew-Geng on 2020/11/10 9:02 下午
 * <p>
 * 合并两个有序的数组
 * 限定语言：C++、Java
 * 给出两个有序的整数数组 和 ，请将数组 合并到数组 中，变成一个有序的数组
 * 注意：
 * 可以假设 数组有足够的空间存放 数组的元素， 和 中初始的元素数目分别为 和
 * <p>
 * lc 88
 * <p>
 * https://leetcode-cn.com/problems/merge-sorted-array/solution/he-bing-liang-ge-you-xu-shu-zu-by-leetcode/
 */
public class MergeArraySolution {

    public static void main(String[] args) {

    }

    /**
     *
     * 设置两个指针，分别从两个有序递增数组的最后一位开始依次比较，
     * 获取到的数据则是两数组按从大到小的排序
     *
     *
     * 复杂度分析
     * <p>
     * 时间复杂度：
     * O(m+n)。
     * 指针移动单调递减，最多移动m+n 次，因此时间复杂度为
     * O(m+n)。
     * 空间复杂度：
     * O(1)。
     * 直接对数组
     * nums
     * 1
     * 原地修改，不需要额外空间
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int nums1[], int m, int nums2[], int n) {
        int i = m - 1;
        int j = n - 1;

        int size = m + n - 1;

        while (i >= 0 && j >= 0) {
            nums1[size--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
        }

        while (j >= 0) {
            nums1[size--] = nums2[j--];
        }
    }

    /**
     * 额外申请的空间
     * 时间复杂度：
     * O(m+n)。
     * 指针移动单调递增，最多移动m+n次，因此时间复杂度为
     * O(m+n)
     * 空间复杂度：
     * O(m+n)。
     * 需要建立长度为
     * m+n 的中间数组
     * sorted
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge_1(int[] nums1, int m, int[] nums2, int n) {
        int p1 = 0, p2 = 0;
        int[] sorted = new int[m + n];
        int cur;

        while (p1 < m || p2 < n) {
            if (p1 == m) {
                cur = nums2[p2++];
            } else if (p2 == n) {
                cur = nums1[p1++];
            } else if (nums1[p1] < nums2[p2]) {
                cur = nums1[p1++];
            } else {
                cur = nums2[p2++];
            }
            sorted[p1 + p2 - 1] = cur;
        }

        for (int i = 0; i != m + n; ++i) {
            nums1[i] = sorted[i];
        }
    }

}
