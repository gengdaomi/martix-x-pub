package com.martix.x.pub.dymaic;

/**
 * Created by Andrew-Geng on 10:08 下午 2021/7/19
 * <p>
 * 打家劫舍 lc 198
 * <p>
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2：
 * <p>
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 */
public class HouseRobberSolution {

    /**
     * 动态规划
     * <p>
     * 思路：
     * 首先考虑最简单的情况。如果只有一间房屋，则偷窃该房屋，可以偷窃到最高总金额。
     * 如果只有两间房屋，则由于两间房屋相邻，不能同时偷窃，只能偷窃其中的一间房屋，因此选择其中金额较高的房屋进行偷窃，可以偷窃到最高总金额。
     * <p>
     * 如果房屋数量大于两间，应该如何计算能够偷窃到的最高总金额呢？对于第k(k>2) 间房屋，有两个选项：
     * 1.偷窃第k间房屋，那么就不能偷窃第 k−1 间房屋，偷窃总金额为前k−2 间房屋的最高总金额与第k 间房屋的金额之和。
     * 2.不偷窃第k 间房屋，偷窃总金额为前k−1 间房屋的最高总金额。
     * <p>
     * 在两个选项中选择偷窃总金额较大的选项，该选项对应的偷窃总金额即为前k 间房屋能偷窃到的最高总金额；
     * <p>
     * 用dp[i]表示前i 间房屋能偷窃到的最高总金额，那么就有如下的状态转移方程:
     * dp[i]=max(dp[i−2]+nums[i],dp[i−1]);
     * <p>
     * 边界条件:
     * dp[0]=num[0],只有一间房屋，则偷窃该房屋
     * dp[1]=max(num[0],num[1]) 只有两间房，选择其中金额较高的房屋进行偷窃；
     * <p>
     * 最终的答案即为dp[n−1]，其中n 是数组的长度。
     *
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }

        int[] dp = new int[length];

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        return dp[length - 1];
    }

    /**
     * 方法一的简化版
     * @param nums
     * @return
     */
    public int rob_1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }

        int first = nums[0], second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }

        return second;
    }

}
