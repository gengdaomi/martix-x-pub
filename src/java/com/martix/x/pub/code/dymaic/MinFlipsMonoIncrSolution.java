package com.martix.x.pub.code.dymaic;

/**
 * Created by Andrew-Geng on 16:48 2023/1/5
 * 将字符串翻转到单调递增 lc 926 剑指 Offer II 092
 *
 * 如果一个二进制字符串，是以一些 0（可能没有 0）后面跟着一些 1（也可能没有 1）的形式组成的，那么该字符串是 单调递增 的。
 *
 * 给你一个二进制字符串 s，你可以将任何 0 翻转为 1 或者将 1 翻转为 0 。
 *
 * 返回使 s 单调递增的最小翻转次数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "00110"
 * 输出：1
 * 解释：翻转最后一位得到 00111.
 * 示例 2：
 *
 * 输入：s = "010110"
 * 输出：2
 * 解释：翻转得到 011111，或者是 000111。
 * 示例 3：
 *
 * 输入：s = "00011000"
 * 输出：2
 * 解释：翻转得到 00000000
 *
 */
public class MinFlipsMonoIncrSolution {

    /**
     * 动态规划
     *
     * 单调递增的字符串满足以下性质：
     *
     * 首个字符是0 或 1；其余的每个字符，字符0 前面的相邻字符一定是0，字符1 前面的相邻字符可以是0 或 1
     *
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     * @param s
     * @return
     */
    public int minFlipsMonoIncr(String s){
        int n = s.length();
        int dp0 = 0, dp1 = 0;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int dp0New = dp0, dp1New = Math.min(dp0, dp1);

            if (c == '1') {
                dp0New++;
            } else {
                dp1New++;
            }

            dp0 = dp0New;
            dp1 = dp1New;
        }

        return Math.min(dp0, dp1);
    }
}
