package com.martix.x.pub.palindrome;

/**
 * Created by Andrew-Geng on 3:38 上午 2021/6/14
 * 回文子串 lc 647
 *
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 *
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 *
 *  
 *
 * 示例 1：
 *
 * 输入："abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * 示例 2：
 *
 * 输入："aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 *
 */
public class CountPalindromeSolution {

    /**
     * 中心扩展
     * 时间复杂度O(n^2),空间复杂度O(1)
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        int n = s.length(), result = 0;

        for (int i = 0; i < 2 * n - 1; ++i) {
            int l = i / 2, r = i / 2 + i % 2;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                --l;
                ++r;
                result++;
            }
        }

        return result;
    }
}
