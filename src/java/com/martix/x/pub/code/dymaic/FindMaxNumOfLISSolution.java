package com.martix.x.pub.code.dymaic;

import java.util.Arrays;

/**
 * Created by Andrew-Geng on 2:06 上午 2021/3/30
 * <p>
 * 给定一个未排序的整数数组，找到最长递增子序列的个数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,5,4,7]
 * 输出: 2
 * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 * <p>
 * 给定一个未排序的整数数组，找到最长递增子序列的个数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,5,4,7]
 * 输出: 2
 * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 * <p>
 * lc 673
 */
public class FindMaxNumOfLISSolution {

    /**
     * 对于每一个数nums[i]，看在它之前的数nums[j](0<= j < i)是否比当前数nums[i]小，如果nums[i] > nums[j]，
     * 那么相当于到nums[j]为止的最长递增子序列长度到nums[i]增加了1，到nums[i]为止的最长递增子序列长度就变成了dp[i] = dp[j] + 1；
     * 但是因为满足nums[i] > nums[j] 的nums[j]不止一个，dp[i]应该取这些dp[j] + 1的最大值，并且这些dp[j] + 1还会有相等的情况，一旦相等，到nums[i]为止的最长递增子序列个数就应该增加了。
     *
     * 因此，具体的状态转移如下，在nums[i] > nums[j]的大前提下：
     * 如果dp[j] + 1 > dp[i]，说明最长递增子序列的长度增加了，dp[i] = dp[j] + 1，长度增加，数量不变 count[i] = count[j]
     * 如果dp[j] + 1 == dp[i]，说明最长递增子序列的长度并没有增加，但是出现了长度一样的情况，数量增加 count[i] += count[j]
     * 记录最长递增子序列的最大长度max_length
     * 遍历dp数组，如果dp数组记录的最大长度dp[i]等于max_length，将对应的数量count[i]加到结果res中
     *
     * 时间复杂度：
     * O(N^2),其中
     * N
     * N 是 nums 的长度。有两个 for 循环是
     * O(1)。
     * 空间复杂度：
     * O(N)，lengths 和 counts 所用的空间。
     *
     * @param nums
     * @return
     */
    public int findNumberOfLIS(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }

        int[] dp = new int[nums.length];  //到nums[i]为止的最长递增子序列长度
        int[] count = new int[nums.length]; //到nums[i]为止的最长递增子序列个数
        Arrays.fill(dp, 1);
        Arrays.fill(count, 1);

        int result = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {

                    if (dp[j] + 1 > dp[i]) { //如果dp[j] + 1 > dp[i]，说明最长递增子序列的长度增加了，dp[i] = dp[j] + 1，长度增加，数量不变 count[i] = count[j]
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    }

                    if (dp[j] + 1 == dp[i]) { //如果dp[j] + 1 == dp[i]，说明最长递增子序列的长度并没有增加，但是出现了长度一样的情况，数量增加 count[i] += count[j]
                        count[i] += count[j];
                    }
                }
            }

            result = Math.max(result, dp[i]);
        }

        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (dp[i] == result) {
                res += count[i];
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 5, 4, 7};

        int result = new FindMaxNumOfLISSolution().findNumberOfLIS(arr);
        System.out.println(result);
    }
}
