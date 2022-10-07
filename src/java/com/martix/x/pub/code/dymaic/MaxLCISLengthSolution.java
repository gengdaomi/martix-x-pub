package com.martix.x.pub.code.dymaic;

import java.util.Arrays;

/**
 * Created by Andrew-Geng on 1:37 上午 2021/3/30
 * <p>
 * 最长连续递增序列
 * lc 674
 * <p>
 * 给定一个未经排序的整数数组，找到最长且连续递增的子序列，并返回该序列的长度。
 * <p>
 * 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，
 * 都有 nums[i] < nums[i + 1] ，那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
 * <p>
 * 输入：nums = [1,3,5,4,7]
 * 输出：3
 * 解释：最长连续递增序列是 [1,3,5], 长度为3。
 * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。
 * <p>
 * 输入：nums = [2,2,2,2,2]
 * 输出：1
 * 解释：最长连续递增序列是 [2], 长度为1
 * <p>
 */
public class MaxLCISLengthSolution {

    /**
     * 动态规划
     * <p>
     * dp[i]：表示以 nums[i] 结尾的递增子数组的长度。
     * 初始化：dp[0] = 1，因为要将当前位置上的元素值nums[i] 和它前一个位置上的元素值nums[i−1] 进行比较，并且当只有一个元素时，本身的长度就是1。
     * 若 nums[i] > nums[i - 1]，则说明i 位置上的元素和i−1 位置上的元素能够形成递增序列（当前 i 位置上的递增序列的长度与 i - 1 位置上递增序列的长度有关）
     * ，此时 状态转移方程：dp[i] = dp[i - 1] + 1；
     * 否则，不能够形成递增序列并且只有nums[i] 一个值，此时 状态转移方程：dp[i] = 1。
     * <p>
     * 每遍历一个元素值，就记录当前最大的dp[i] 的值，
     * <p>
     * 时空复杂度O(n)
     *
     * @param nums
     * @return
     */
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] < nums[i]) {
                dp[i] = Math.max(dp[i - 1] + 1, dp[i]);
            }
        }

        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result = Math.max(result, dp[i]);
        }

        return result;
    }

    /**
     * 贪心
     * <p>
     * 对于下标范围[l,r] 的连续子序列，如果对任意l≤i<r 都满足nums[i]<nums[i+1]，则该连续子序列是递增序列；
     * 假设数组nums 的长度是n，对于 0<l≤r<n−1，如果下标范围[l,r] 的连续子序列是递增序列，则考虑nums[l−1] 和 nums[r+1]。
     * 1.如果 nums[l−1]<nums[l]，则将  nums[l−1] 加到nums[l] 的前面，可以得到更长的连续递增序列.
     * 2.如果nums[r+1]>nums[r]，则将nums[r+1] 加到 nums[r] 的后面，可以得到更长的连续递增序列。
     * <p>
     * 基于上述分析可知，为了得到最长连续递增序列，可以使用贪心的策略得到尽可能长的连续递增序列；
     * 做法是使用记录当前连续递增序列的开始下标和结束下标，遍历数组的过程中每次比较相邻元素，根据相邻元素的大小关系决定是否需要更新连续递增序列的开始下标。
     * <p>
     * 具体而言，令start 表示连续递增序列的开始下标，初始时start=0，然后遍历数组nums，进行如下操作：
     * 1.如果下标i>0 且 nums[i]≤nums[i−1]，则说明当前元素小于或等于上一个元素，因此nums[i−1] 和nums[i] 不可能属于同一个连续递增序列，
     * 必须从下标i 处开始一个新的连续递增序列，因此令start=i。如果下标i=0 或nums[i]>nums[i−1]，则不更新start 的值。
     * 2.此时下标范围[start,i] 的连续子序列是递增序列，其长度为i−start+1，使用当前连续递增序列的长度更新最长连续递增序列的长度。
     *
     * @param nums
     * @return
     */
    public int findLengthOfLCIS_1(int[] nums) {
        int result = 0;
        int n = nums.length;
        int start = 0;

        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] <= nums[i - 1]) {
                start = i;
            }

            result = Math.max(result, i - start + 1);
        }

        return result;
    }

    public int findLengthOfLCIS_2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int max = 0;
        int count = 1;

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                count++;
            } else {
                if (max < count) {
                    max = count;
                }
                count = 1;
            }
        }

        return Math.max(max, count);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 5, 4, 7};

        int result = new MaxLCISLengthSolution().findLengthOfLCIS(arr);
        System.out.println(result);
    }
}
