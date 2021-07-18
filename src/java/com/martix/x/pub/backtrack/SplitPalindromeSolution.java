package com.martix.x.pub.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrew-Geng on 11:42 下午 2021/7/15
 * 分割回文串  lc 131
 *
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 *
 * 回文串 是正着读和反着读都一样的字符串。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * 示例 2：
 *
 * 输入：s = "a"
 * 输出：[["a"]]
 *
 */
public class SplitPalindromeSolution {

    boolean[][] f;
    List<List<String>> ret = new ArrayList<List<String>>();
    List<String> ans = new ArrayList<String>();
    int n;

    /**
     * 回溯 + 动态规划预处理
     *
     * 思路：
     * 由于需要求出字符串s 的所有分割方案，因此我们考虑使用搜索 + 回溯的方法枚举所有可能的分割方法并进行判断；
     *
     * 假设我们当前搜索到字符串的第i 个字符，且s[0..i−1] 位置的所有字符已经被分割成若干个回文串，
     * 并且分割结果被放入了答案数组ans 中，那么我们就需要枚举下一个回文串的右边界j，使得 s[i..j] 是一个回文串；
     *
     *1. 因此，我们可以从i 开始，从小到大依次枚举j。
     * 2.对于当前枚举的j 值，我们使用双指针的方法判断s[i..j] 是否为回文串：如果s[i..j] 是回文串，
     * 那么就将其加入答案数组ans 中，并以j+1 作为新的 i 进行下一层搜索，并在未来的回溯时将s[i..j] 从 ans 中移除。
     *3.如果我们已经搜索完了字符串的最后一个字符，那么就找到了一种满足要求的分割方法。

     时间复杂度：O(n⋅2^n)，其中n 是字符串s 的长度。在最坏情况下，s 包含n 个完全相同的字符，因此它的任意一种划分方法都满足要求;

     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        n = s.length();
        f = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(f[i], true);
        }

        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
            }
        }

        dfs(s, 0);
        return ret;
    }

    public void dfs(String s, int i) {
        if (i == n) {
            ret.add(new ArrayList<String>(ans));
            return;
        }
        for (int j = i; j < n; ++j) {
            if (f[i][j]) {
                ans.add(s.substring(i, j + 1));
                dfs(s, j + 1);
                ans.remove(ans.size() - 1);
            }
        }
    }



    int[][] f1;
    List<List<String>> ret1 = new ArrayList<List<String>>();
    List<String> ans1 = new ArrayList<String>();
    int n1;

    /**
     * 回溯 + 记忆化搜索
     * @param s
     * @return
     */
    public List<List<String>> partition_1(String s) {
        n1 = s.length();
        f1= new int[n1][n1];

        dfs(s, 0);
        return ret;
    }

    public void dfs_1(String s, int i) {
        if (i == n1) {
            ret.add(new ArrayList<String>(ans));
            return;
        }
        for (int j = i; j < n1; ++j) {
            if (isPalindrome_1(s, i, j) == 1) {
                ans.add(s.substring(i, j + 1));
                dfs_1(s, j + 1);
                ans.remove(ans.size() - 1);
            }
        }
    }

    // 记忆化搜索中，f[i][j] = 0 表示未搜索，1 表示是回文串，-1 表示不是回文串
    public int isPalindrome_1(String s, int i, int j) {
        if (f1[i][j] != 0) {
            return f1[i][j];
        }
        if (i >= j) {
            f1[i][j] = 1;
        } else if (s.charAt(i) == s.charAt(j)) {
            f1[i][j] = isPalindrome_1(s, i + 1, j - 1);
        } else {
            f1[i][j] = -1;
        }
        return f1[i][j];
    }

}
