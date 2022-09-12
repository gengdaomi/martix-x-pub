package com.martix.x.pub.code.dymaic;

/**
 * Created by Andrew-Geng on 11:26 下午 2021/7/25
 * 使用最小花费爬楼梯 lc 746
 * <p>
 * 数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值cost[i]（下标从 0 开始）。
 * 每当你爬上一个阶梯你都要花费对应的体力值，一旦支付了相应的体力值，你就可以选择向上爬一个阶梯或者爬两个阶梯。
 * 请你找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯。
 * <p>
 * 示例1：
 * <p>
 * 输入：cost = [10, 15, 20]
 * 输出：15
 * 解释：最低花费是从 cost[1] 开始，然后走两步即可到阶梯顶，一共花费 15 。
 * 示例 2：
 * <p>
 * 输入：cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 * 输出：6
 * 解释：最低花费方式是从 cost[0] 开始，逐个经过那些 1 ，跳过 cost[3] ，一共花费 6 。
 */
public class ClimbStairsMinCostSolution {

    /**
     * 动态规划
     * <p>
     * 核心思路：
     * 假设数组cost 的长度为n，则n 个阶梯分别对应下标0 到n−1，楼层顶部对应下标n，
     * 问题等价于计算达到下标n 的最小花费。可以通过动态规划求解。
     * <p>
     * 创建长度为n+1 的数组dp，其中 dp[i] 表示达到下标i 的最小花费。
     * <p>
     * 由于可以选择下标0 或1 作为初始阶梯，因此有dp[0]=dp[1]=0。
     * 状态转移方程：
     * dp[i]=min(dp[i−1]+cost[i−1],dp[i−2]+cost[i−2])
     * <p>
     * 依次计算dp 中的每一项的值，最终得到的dp[n] 即为达到楼层顶部的最小花费。
     * <p>
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     *
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];

        dp[0] = dp[1] = 0;

        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }

        return dp[n];
    }

    /**
     * 动态规划
     * <p>
     * 空间复杂度优化到O(1)
     * <p>
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     *
     * @param cost
     * @return
     */
    public int minCostClimbingStairs_1(int[] cost) {
        int n = cost.length;
        int prev = 0, curr = 0;

        for (int i = 2; i <= n; i++) {
            int next = Math.min(curr + cost[i - 1], prev + cost[i - 2]);
            prev = curr;
            curr = next;
        }

        return curr;
    }
}
