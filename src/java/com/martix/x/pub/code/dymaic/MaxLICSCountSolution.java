package com.martix.x.pub.code.dymaic;

import java.util.Arrays;

/**
 * Created by Andrew-Geng on 1:09 上午 2021/7/26
 * 最长递增子序列的个数
 * lc 673
 *
 * 给定一个未排序的整数数组，找到最长递增子序列的个数。
 *
 * 示例 1:
 *
 * 输入: [1,3,5,4,7]
 * 输出: 2
 * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 * 示例 2:
 *
 * 输入: [2,2,2,2,2]
 * 输出: 5
 * 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
 *
 */
public class MaxLICSCountSolution {

    /**
     * 动态规划
     *
     * 核心思路：
     * 1.假设对于以 nums[i] 结尾的序列，我们知道最长序列的长度 length[i]，以及具有该长度的序列的 count[i]；
     * 2.对于每一个 i<j 和一个 A[i]<A[j]，我们可以将一个 A[j] 附加到以 A[i] 结尾的最长子序列上；
     * 3.如果这些序列比 length[j] 长，那么我们就知道我们有count[i] 个长度为 length 的序列,
     *   如果这些序列的长度与 length[j] 相等，那么我们就知道现在有 count[i] 个额外的序列（即 count[j]+=count[i]);
     *
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
     * O(N^2),其中N 是 nums 的长度。有两个 for 循环是O(1)。
     * 空间复杂度：
       O(N)，lengths 和 counts 所用的空间。
     * @param nums
     * @return
     */
    public int findNumberOfLIS(int[] nums) {
        int size = nums.length;

        if (size <= 1){
            return size;
        }
        int[] lengths = new int[size]; //lengths[i] = length of longest ending in nums[i]
        int[] counts = new int[size]; //count[i] = number of longest ending in nums[i]
        Arrays.fill(counts, 1);

        for (int j = 0; j < size; j++) {
            for (int i = 0; i < j; ++i) {
                if (nums[i] < nums[j]) {
                    if (lengths[i] >= lengths[j]) {
                        lengths[j] = lengths[i] + 1;
                        counts[j] = counts[i];
                    } else if (lengths[i] + 1 == lengths[j]) {
                        counts[j] += counts[i];
                    }
                }
            }
        }

        int longest = 0, result = 0;
        for (int length: lengths) {
            longest = Math.max(longest, length);
        }

        for (int i = 0; i < size; i++) {
            if (lengths[i] == longest) {
                result += counts[i];
            }
        }

        return result;
    }
}
