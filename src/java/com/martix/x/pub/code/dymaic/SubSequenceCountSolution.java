package com.martix.x.pub.code.dymaic;

/**
 * Created by Andrew-Geng on 11:09 2022/9/10
 * 不同的子序列 lc 155 hard
 *
 * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
 *
 * 字符串的一个子序列是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。
 * （例如，"ACE"是"ABCDE"的一个子序列，而"AEC"不是）
 *
 * 输入：s = "rabbbit", t = "rabbit"
 * 输出：3
 * 解释：
 * 如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
 * rabbbit
 * rabbbit
 * rabbbit
 *
 * 输入：s = "babgbag", t = "bag"
 * 输出：5
 * 解释：
 * 如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。
 * babgbag
 * babgbag
 * babgbag
 * babgbag
 * babgbag
 *
 */
public class SubSequenceCountSolution {

    /**
     * 动态规划
     * dp[i][j] 代表 T 前 i 字符串可以由 S j 字符串组成最多个数
     * 转移方程：
     * 当 S[j] == T[i] , dp[i][j] = dp[i-1][j-1] + dp[i][j-1];
     * 当 S[j] != T[i] , dp[i][j] = dp[i][j-1]
     *
     * 对于第一行, T 为空,因为空集是所有字符串子集, 所以我们第一行都是 1
     * 对于第一列, S 为空,这样组成 T 个数当然为 0` 了
     *
     * 时间复杂度：O(mn)，其中m 和n 分别是字符串s 和t 的长度;需要对dp 中的每个元素进行计算
     * 空间复杂度：O(mn)，其中m 和n 分别是字符串s 和t 的长度。创建了m+1 行n+1 列的二维数组dp
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        int[][] dp = new int[t.length() + 1][s.length() + 1];

        for (int j = 0; j < s.length() + 1; j++){
            dp[0][j] = 1;
        }

        for (int i = 1; i < t.length() + 1; i++) {
            for (int j = 1; j < s.length() + 1; j++) {
                if (t.charAt(i - 1) == s.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        return dp[t.length()][s.length()];
    }

    public int numDistinct_1(String s, String t) {
        int m = s.length(), n = t.length();
        if (m < n) {
            return 0;
        }

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][n] = 1;
        }

        for (int i = m - 1; i >= 0; i--) {
            char sChar = s.charAt(i);

            for (int j = n - 1; j >= 0; j--) {
                char tChar = t.charAt(j);

                if (sChar == tChar) {
                    dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }

        return dp[0][0];
    }
}
