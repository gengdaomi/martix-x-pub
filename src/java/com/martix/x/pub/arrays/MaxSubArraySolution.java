package com.martix.x.pub.arrays;

/**
 * Created by Andrew-Geng on 12:22 上午 2021/3/10
 * <p>
 * 最大子序和
 * <p>
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * <p>
 * 动态规划
 * <p>
 * <p>
 * 设sum[i]为以第i个元素结尾且和最大的连续子数组。
 * 假设对于元素i，所有以它前面的元素结尾的子数组的长度都已经求得，那么以第i个元素结尾且和最大的连续子数组实际上，要么是以第i-1个元素结尾且和最大的连续子数组加上这个元素，要么是只包含第i个元素，
 * 即sum[i] = max(sum[i-1] + a[i], a[i])。
 * 可以通过判断sum[i-1] + a[i]是否大于a[i]来做选择，而这实际上等价于判断sum[i-1]是否大于0。
 * 由于每次运算只需要前一次的结果，因此并不需要像普通的动态规划那样保留之前所有的计算结果，只需要保留上一次的即可，因此算法的时间和空间复杂度都很小
 */
public class MaxSubArraySolution {

    public static void main(String[] args) {
        int[] arr = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(new MaxSubArraySolution().maxSubArray_2(arr));
    }

    public int maxSubArray(int[] nums) {
        int sum = nums[0]; //初始化子序列的和值
        int n = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (n > 0) {
                n += nums[i];
            } else {
                n = nums[i];
            }

            if (sum < n) {
                sum = n;
            }
        }

        return sum;
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

}
