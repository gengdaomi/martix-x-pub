package com.martix.x.string;

/**
 * Created by Andrew-Geng on 1:19 上午 2021/4/20
 * 实现 strStr()
 * <p>
 * lc 28
 * <p>
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回  -1 。
 * <p>
 * 说明：
 * <p>
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * <p>
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。
 * <p>
 * 示例 1：
 * <p>
 * 输入：haystack = "hello", needle = "ll"
 * 输出：2
 * 示例 2：
 * <p>
 * 输入：haystack = "aaaaa", needle = "bba"
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：haystack = "", needle = ""
 * 输出：0
 */
public class StrStrSolution {

    public static void main(String[] args) {

    }

    /**
     * 暴力解法
     * <p>
     * 我们可以让字符串
     * needle 与字符串
     * haystack 的所有长度为
     * m 子串均匹配一次。
     * <p>
     * 为了减少不必要的匹配，我们每次匹配失败即立刻停止当前子串的匹配，对下一个子串继续匹配。如果当前子串匹配成功，我们返回当前子串的开始位置即可。如果所有子串都匹配失败，则返回
     * −1
     * <p>
     * 时间复杂度O(N*M)其中
     * n 是字符串
     * haystack 的长度，
     * m 是字符串
     * needle 的长度。最坏情况下我们需要将字符串
     * needle 与字符串
     * haystack 的所有长度为
     * m 的子串均匹配一次。
     * 空间复杂度：
     * O(1)。我们只需要常数的空间保存若干变量。。
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }

        int n = haystack.length(), m = needle.length();
        for (int i = 0; i + m <= n; i++) {

            boolean flag = true;
            for (int j = 0; j < m; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }

        return -1;
    }
}
