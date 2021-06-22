package com.martix.x.pub.sum;

/**
 * Created by ayue on 下午5:14 2018/6/27
 */
public class MaxSumSolution {

    /**
     * <pre>
     * Find the contiguous subarray within an array (containing at least one number)
     * which has the largest sum.
     *
     * For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
     * the contiguous subarray [4,-1,2,1] has the largest sum = 6.
     *
     * 题目大意：
     * 求数组的最大子数组的和
     * 解题思路：
     * 动态规划问题，已知了前k个元素的最大子序列和为maxSub（已经被记录下来了），以及一个临时和sum，
     * 如果添加了第k+1这个元素，由于是连续子序列这个限制，所以如果k+1这个元素之前的和是小于0的，
     * 那么对于增大k+1这个元素从而去组成最大子序列是没有贡献的，所以可以把sum 置0。
     * </pre>
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        // 参数校验
        if (nums == null || nums.length < 1) {
            throw new IllegalArgumentException();
        }

        int cur = 0;
        int max = Integer.MIN_VALUE;

        for (int i : nums) {
            // 当前和小于0，就将当前值赋给curSum
            if (cur <= 0) {
                cur = i;
            } else { // 否则进行累加
                cur += i;
            }

            // 保存较大的值
            if (max < cur) {
                max = cur;
            }
        }

        return max;
    }

    public int maxSubArray_1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int maxSum = nums[0];
        int curSum = 0;

        for (int i = 0; i < nums.length; i++) {
            curSum = Math.max(curSum + nums[i], nums[i]);
            maxSum = Math.max(curSum, maxSum);
        }

        return maxSum;
    }

    public int maxSubArray_2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }

        int result = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            result = Math.max(result, dp[i]);
        }

        return result;
    }


    public static void main(String[] args) {
        int[] params = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(new MaxSumSolution().maxSubArray(params));
    }
}
