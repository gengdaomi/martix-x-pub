package com.martix.x.pub.dymaic;

/**
 * Created by Andrew-Geng on 10:16 下午 2021/6/27
 * <p>
 * 最长公共子序列 lc 1143
 * <p>
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * <p>
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * <p>
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 * 示例 2：
 * <p>
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
 * 示例 3：
 * <p>
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0 。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= text1.length, text2.length <= 1000
 * text1 和 text2 仅由小写英文字符组成。
 */
public class LongestCommonSubsequenceSolution {

    /**
     * 动态规划  二维动态规划问题。
     * <p>
     * 解题思路：假设字符串 text1和text2的长度分别为m和n，那么创建m+1和n+1列的二维数组dp,
     * 其中dp[i][j]表示为text1[0..i]和text2[0..j]的最长公共子序列的长度
     * <p>
     * 考虑动态规划的边界情况：
     * i=0时，text1[0..i]为空，空字符串和任意字符串的公共子序列为空，即长度是0，因此dp[0][j]=0;
     * j=0时，text2[0..j]为空，和上面同理
     * 因此动态规划的边界是：当i=0或j=0时，dp[i][j]=0
     * <p>
     * 找到动态规划的状态转移方程：dp[i][j]=dp[i-1][j-1]+1  ,text1[i-1]=text2[j-1]
     * dp[i][j]=max(dp[i-1][j],dp[i][j-1])  ,text1[i-1]!=text2[j-1]
     * <p>
     * 时间复杂度：O（mn)
     * 空间复杂度：O(mn)
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            char c1 = text1.charAt(i - 1);

            for (int j = 1; j <= n; j++) {
                char c2 = text2.charAt(j - 1);

                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }
}
