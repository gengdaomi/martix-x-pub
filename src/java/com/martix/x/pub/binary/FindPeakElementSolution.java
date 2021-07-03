package com.martix.x.pub.binary;

/**
 * Created by Andrew-Geng on 12:37 上午 2021/7/4
 * 寻找峰值 lc 162
 * <p>
 * 峰值元素是指其值大于左右相邻值的元素。
 * <p>
 * 给你一个输入数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * <p>
 * 你可以假设 nums[-1] = nums[n] = -∞ 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3,1]
 * 输出：2
 * 解释：3 是峰值元素，你的函数应该返回其索引 2。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,1,3,5,6,4]
 * 输出：1 或 5
 * 解释：你的函数可以返回索引 1，其峰值元素为 2；
 *      或者返回索引 5， 其峰值元素为 6。
 */
public class FindPeakElementSolution {

    /**
     * 线性扫描
     * <p>
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     *
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1])
                return i;
        }

        return nums.length - 1;
    }

    /**
     * 迭代二分查找
     * 核心思路： 通过将数组中的任何给定序列视为交替的升序和降序序列，通过利用这一点，以及“可以返回任何一个峰作为结果”的要求，
     * 我们可以利用二分查找来找到所需的峰值元素
     *
     * 时间复杂度O(logN)
     * 空间复杂度O(1)
     *
     * @param nums
     * @return
     */
    public int findPeakElement_1(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

}
