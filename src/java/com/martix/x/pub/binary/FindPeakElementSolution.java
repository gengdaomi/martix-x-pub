package com.martix.x.pub.binary;

/**
 * Created by Andrew-Geng on 12:37 上午 2021/7/4
 * 寻找峰值 lc 162
 * 同时也是 山脉数组的峰顶索引 lc 852
 * (给你由整数组成的山脉数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i 。)
 * <p>
 * 峰值元素是指其值大于左右相邻值的元素。
 * 给你一个输入数组nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * <p>
 * 你可以假设nums[-1] = nums[n] = -∞ 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3,1]
 * 输出：2
 * 解释：3 是峰值元素，你的函数应该返回其索引 2。
 * 示例2：
 * <p>
 * 输入：nums = [1,2,1,3,5,6,4]
 * 输出：1 或 5
 * 解释：你的函数可以返回索引 1，其峰值元素为 2；
 * 或者返回索引 5， 其峰值元素为 6。
 */
public class FindPeakElementSolution {

    public static void main(String[] args) {
        int[] i = new int[]{1, 2, 1, 3, 5, 6, 4};
    }

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
     * 我们可以利用二分查找来找到所需的峰值元素;
     *
     * 简单的二分查找中，我们处理的是一个有序数列，并通过在每一步减少搜索空间来找到所需要的数字;
     * 本例中有部分的调整，对二分查找进行一点修改。首先从数组nums 中找到中间的元素mid，
     * 若该元素恰好位于降序序列或者一个局部下降坡度中（通过将nums[i] 与右侧比较判断)，
     * 则说明峰值会在本元素的左边。于是，我们将搜索空间缩小为mid 的左边(包括其本身)，并在左侧子数组上重复上述过程。
     * <p>
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
