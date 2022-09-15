package com.martix.x.pub.code.arrays;

/**
 * Created by Ayue 2022/9/15 09:50
 * 分隔数组以得到最大和 lc 1043
 * <p>
 * 给你一个整数数组 arr，请你将该数组分隔为长度最多为 k 的一些（连续）子数组。
 * 分隔完成后，每个子数组的中的所有值都会变为该子数组中的最大值。
 * <p>
 * 返回将数组分隔变换后能够得到的元素最大和。
 * <p>
 * <p>
 * 注意，原数组和分隔后的数组对应顺序应当一致，也就是说，你只能选择分隔数组的位置而不能调整数组中的顺序。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [1,15,7,9,2,5,10], k = 3
 * 输出：84
 * 解释：
 * 因为 k=3 可以分隔成 [1,15,7] [9] [2,5,10]，结果为 [15,15,15,9,10,10,10]，和为 84，是该数组所有分隔变换后元素总和最大的。
 * 若是分隔成 [1] [15,7,9] [2,5,10]，结果就是 [1, 15, 15, 15, 10, 10, 10] 但这种分隔方式的元素总和（76）小于上一种。
 * 示例 2：
 * <p>
 * 输入：arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
 * 输出：83
 * 示例 3：
 * <p>
 * 输入：arr = [1], k = 1
 * 输出：1
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 500
 * 0 <= arr[i] <= 109
 * 1 <= k <= arr.length
 */
public class ArrayPartitionMaxSumSolution {

    /**
     * 核心思路：动态规划
     * 将数组分隔为长度最多为 K 的几个（连续）子数组，意思是分割出任意几个子数组，每个子数组的长度不超过 K，而不是分成 K 个子数组。
     * <p>
     * 定义dp[i] 为 数组arr按照题意分割后的子数组A[i]的最大和；定义最后一个分割区间长度为 j，1<=j<=K。
     * <p>
     * 求 dp[i-1]，
     * 表示数组的前 i 个数即 nums[0,1...i-2]，第二部分是 nums[i-1]，也就是说 dp[i-1] + max(nums[i-1])*(i-(i-1))
     * 求 dp[i-2]，
     * 表示数组的前 i-1 个数即 nums[0,1...i-3]，第二部分是 nums[i-2...i-1]，也就是说 dp[i-2] + max(nums[i-2...i-1]) * (i-(i-2))
     * 求 dp[i-3]，
     * 表示数组的前 i-2 个数即 nums[0,1...i-4]，第二部分是 nums[i-3...i-1]，也就是说 dp[i-3]+ max(nums[i-3...i-1]) * (i-(i-3))
     * 求 dp[0]，
     * 表示数组的前 1 个数即 nums[0,0]，第二部分是nums[0...i−1]，也就是说 dp[0] + max(nums[0...i-1])* (i-(0))
     * <p>
     * 状态转移方程：dp[i]=max(dp[i]，dp[j]+(i-j)*MAX)
     * 其中 MAX 是 nums[j...i−1] 范围内的局部最大值，一旦找到最大值，该范围内的所有值都改成这个局部最大值 MAX
     * 其中 0 =< j< i
     *
     * @param arr
     * @param k
     * @return
     */
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n + 1];

        //核心思想是每到一个数，就把这个数放到一个新的框子里，然后开始往前调整
        //框子里的值为进来的数的最大值
        //往前取，最多只能取到i-k+1，每取一个数，放进框子里，看放这个数的结果更大还是不放大
        for (int i = 0; i <= n; i++) {
            int j = i - 1;
            int max = dp[i];

            while ((i - j) <= k && j >= 0) {
                max = Math.max(max, arr[j]);
                dp[i] = Math.max(dp[i], dp[j] + (i - j) * max);
                j--;
            }
        }

        return dp[n];
    }
}