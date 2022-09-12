package com.martix.x.pub.code.dymaic;

import java.util.Arrays;

/**
 * Created by Andrew-Geng on 12:54 上午 2021/3/30
 * 最长递增子序列
 * <p>
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * <p>
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 *  
 * 示例 1：
 * <p>
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * 示例 2：
 * <p>
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * 示例 3：
 * <p>
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 * <p>
 * lc 300
 */
public class MaxLISSolution {

    /**
     * 动态规划
     *
     * 核心思路：
     *
     * 1.状态定义：dp[i] 的值代表 nums 前i 个数字的最长子序列长度。
     * 2.转移方程：
     * 2.1 当 nums[i]>nums[j] 时：[i] 可以接在nums[j] 之后（此题要求严格递增），此情况下最长上升子序列长度为 dp[j]+1 ；
     * 2.2 当 nums[i]<=nums[j] 时：nums[i] 无法接在 nums[j] 之后，此情况上升子序列不成立，跳过。
     * 所以 转移方程 转移方程： dp[i] = max(dp[i], dp[j] + 1) for j in [0, i)。
     *
     * 初始状态：dp[i] 所有元素置1，含义是每个元素都至少可以单独成为子序列，此时长度都为1；
     * 返回dp 列表最大值，即可得到全局最长上升子序列长度
     *
     * 时间复杂度O(n^2)
     * 空间复杂度O(n)
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result = Math.max(result, dp[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] i = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(new MaxLISSolution().lengthOfLIS(i));
    }
}
