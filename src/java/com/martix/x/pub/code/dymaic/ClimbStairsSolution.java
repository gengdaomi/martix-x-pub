package com.martix.x.pub.code.dymaic;

/**
 * Created by Andrew-Geng on 10:34 下午 2021/7/11
 *
 * 爬楼梯  lc 70
 *
 * 假设你正在爬楼梯。需要 n阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 */
public class ClimbStairsSolution {

    /**
     * 核心：动态规划
     *
     * 用f(x) 表示爬到第x 级台阶的方案数，考虑最后一步可能跨了一级台阶，也可能跨了两级台阶，所以f(x)=f(x−1)+f(x−2)；
     * 意味着爬到第x 级台阶的方案数是爬到第x−1 级台阶的方案数和爬到第x−2 级台阶的方案数的和。
     *
     * 时间复杂度：循环执行n 次，每次花费常数的时间代价，故渐进时间复杂度为 O(n)
     * 空间复杂度：这里只用了常数个变量作为辅助空间，故渐进空间复杂度为 O(1)
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        int p = 0, q = 0, r = 1;

        for (int i = 1; i <= n; ++i) {
            p = q;
            q = r;
            r = p + q;
        }

        return r;
    }

    public int climbStairs_1(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
