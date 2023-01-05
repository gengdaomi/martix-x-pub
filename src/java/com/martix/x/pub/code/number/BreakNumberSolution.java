package com.martix.x.pub.code.number;

/**
 * Created by Andrew-Geng on 15:18 2023/1/5
 * 整数拆分 剑指 Offer 14- I. 剪绳子
 *
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
 * 每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 * 示例 1：
 *
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1
 * 示例2:
 *
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 ×3 ×4 = 36
 */
public class BreakNumberSolution {

    /**
     * 动态规划
     *
     * 对于正整数n，当n≥2 时，可以拆分成至少两个正整数的和。令x 是拆分出的第一个正整数，则剩下的部分是n−x，n−x 可以不继续拆分，
     * 或者继续拆分成至少两个正整数的和。由于每个正整数对应的最大乘积取决于比它小的正整数对应的最大乘积，因此可以使用动态规划求解。
     * 创建数组dp，其中dp[i] 表示将正整数i 拆分成至少两个正整数的和之后，这些正整数的最大乘积。
     * 特别地，0 不是正整数，1 是最小的正整数，0 和1 都不能拆分，因此dp[0]=dp[1]=0。
     * 当 i≥2 时，假设对正整数i 拆分出的第一个正整数是j（1≤j<i），则有以下两种方案：
     *
     * 将i 拆分成j 和i−j 的和，且i−j 不再拆分成多个正整数，此时的乘积是j×(i−j)；
     * 将i 拆分成j 和i−j 的和，且i−j 继续拆分成多个正整数，此时的乘积是j×dp[i−j]。
     *
     *
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            int curMax = 0;

            for (int j = 1; j < i; j++) {
                curMax = Math.max(curMax, Math.max(j * (i - j), j * dp[i - j]));
            }

            dp[i] = curMax;
        }

        return dp[n];
    }

    /**
     * 优化版 动态规划
     *
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     * @param n
     * @return
     */
    public int integerBreak_1(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(Math.max(2 * (i - 2), 2 * dp[i - 2]), Math.max(3 * (i - 3), 3 * dp[i - 3]));
        }
        return dp[n];
    }

    /**
     * 数学归纳法
     *
     * 正整数4 可以拆分成2+2，乘积不变（4=2×2）。
     * 对于大于4 的正整数，总是存在一种拆分的方案，使得拆分成的两个正整数的乘积大于拆分前的正整数（例如，5=2+3，2×3=6>5）
     *
     * 时间复杂度O(1)
     * 空间复杂度O(1)
     * @param n
     * @return
     */
    public int integerBreak_2(int n) {
        if (n <= 3) {
            return n - 1;
        }

        int quotient = n / 3;
        int remainder = n % 3;

        if (remainder == 0) {
            return (int) Math.pow(3, quotient);
        } else if (remainder == 1) {
            return (int) Math.pow(3, quotient - 1) * 4;
        } else {
            return (int) Math.pow(3, quotient) * 2;
        }
    }
}
