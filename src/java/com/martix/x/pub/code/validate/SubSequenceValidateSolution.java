package com.martix.x.pub.code.validate;

import java.util.Arrays;

/**
 * Created by Andrew-Geng on 2:33 下午 2021/3/30
 * <p>
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * <p>
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 * <p>
 * 进阶：
 * <p>
 * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "abc", t = "ahbgdc"
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：s = "axc", t = "ahbgdc"
 * 输出：false

 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 100
 * 0 <= t.length <= 10^4
 * 两个字符串都只由小写字符组成。
 * <p>
 * lc 392
 */
public class SubSequenceValidateSolution {

    /**
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) {
            return true;
        }
        if (s.length() > t.length()) {
            return false;
        }

        boolean[] dp = new boolean[s.length()];
        Arrays.fill(dp, false);

        for (int i = 0, j = 0; i < t.length() && j < s.length(); i++) {
            if (t.charAt(i) != s.charAt(j)) {
                continue;
            }

            dp[j] = true;
            j++;
        }

        for (int i = 0; i < dp.length; i++) {
            if (!dp[i]) {
                return false;
            }
        }

        return true;
    }

    /**
     * 双指针
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence_1(String s, String t) {
        int n = s.length(), m = t.length();
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == n;
    }

    /**
     * 动态规划
     * 动态规划思想是希望连续的，也就是说上一个状态和下一个状态(自变量)之间有关系而且连续。
     * <p>
     * 因为在找两个字符串中的最长公共子序列时，都是按照从前往后的顺序进行遍历的，找两个字符串的最长公共子序列，
     * 若最长公共子序列的长度与字符串 s 的长度相等，则 s 一定是 t 的子序列。
     * <p>
     * dp[i][j]：表示字符串 t 的前 i 个元素和字符串 s 的前 j 个元素中公共子序列的长度。
     * <p>
     * 遍历两个字符串
     * <p>
     * (1) 若当前字符相同，则找到了一个公共元素，此时要在原来已经找到当前最长公共子序列的基础上加 1，即在 f(i - 1)(j - 1) 的基础上加 1，此时状态转移方程：dp[i][j] = dp[i-1][j-1] + 1。
     * <p>
     * (2) 若当前字符不同，此时相当于字符串 t 要将当前元素删除，t 如果把当前元素 t[i - 1] 删除，此时公共子序列的长度就是原来已经求得的公共子序列的长度，
     * 所以 dp[i][j] 的值就是看 s[j - 1] 与 t[i - 2] 的比较结果了，此时状态转移方程：dp[i][j] = dp[i - 1][j]。
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence_2(String s, String t) {
        int n = s.length();
        int m = t.length();

        if (n > m) {
            return false;
        }

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                if (t.charAt(i - 1) == s.charAt(j - 1)) {

                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {

                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        if (dp[m][n] == n) {
            return true;
        }

        return false;
    }


    public static void main(String[] args) {
        String s = "abc", t = "ahbgdc";

        System.out.println(new SubSequenceValidateSolution().isSubsequence_1(s, t));
    }
}
