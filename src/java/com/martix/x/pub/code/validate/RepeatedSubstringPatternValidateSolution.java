package com.martix.x.pub.code.validate;

import java.util.Arrays;

/**
 * Created by Andrew-Geng on 23:01 2022/12/20
 * 重复的子字符串 lc 459
 *
 * 给定一个非空的字符串s，检查是否可以通过由它的一个子串重复多次构成。
 *
 *
 * 示例 1:
 *
 * 输入: s = "abab"
 * 输出: true
 * 解释: 可由子串 "ab" 重复两次构成。
 * 示例 2:
 *
 * 输入: s = "aba"
 * 输出: false
 * 示例 3:
 *
 * 输入: s = "abcabcabcabc"
 * 输出: true
 * 解释: 可由子串 "abc" 重复四次构成。 (或子串 "abcabc" 重复两次构成。)
 */
public class RepeatedSubstringPatternValidateSolution {

    /**
     * 如果您的字符串 S 包含一个重复的子字符串，那么这意味着您可以多次 “移位和换行”`您的字符串，并使其与原始字符串匹配。
     *
     * 例如：abcabc
     *
     * 移位一次：cabcab
     * 移位两次：bcabca
     * 移位三次：abcabc
     *
     * 现在字符串和原字符串匹配了，所以可以得出结论存在重复的子串。
     *
     * 基于这个思想，可以每次移动k个字符，直到匹配移动 length - 1 次。但是这样对于重复字符串很长的字符串，效率会非常低。在 LeetCode 中执行时间超时了。
     *
     * 为了避免这种无用的环绕，可以创建一个新的字符串 str，它等于原来的字符串 S 再加上 S 自身，这样其实就包含了所有移动的字符串。
     *
     * 比如字符串：S = acd，那么 str = S + S = acdacd
     *
     * acd 移动的可能：dac、cda。其实都包含在了 str 中了。就像一个滑动窗口
     *
     * 一开始 acd (acd) ，移动一次 ac(dac)d，移动两次 a(cda)cd。循环结束
     *
     * 所以可以直接判断 str 中去除首尾元素之后，是否包含自身元素。如果包含。则表明存在重复子串

     *
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern(String s) {
        String str = s + s;
        return str.substring(1, str.length() - 1).contains(s);
    }

    /**
     * 枚举
     *
     * 如果一个长度为n 的字符串s 可以由它的一个长度为n，的子串s重复多次构成；
     *
     * 因此，我们可以从小到大枚举n′，并对字符串s 进行遍历，进行上述的判断。注意到一个小优化是，
     * 因为子串至少需要重复一次，所以n′不会大于n 的一半，我们只需要在[1,n/2]的范围内枚举n即可。
     *
     * 时间复杂度O(n^2)
     * 空间复杂度O(1)
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern_1(String s) {
        int n = s.length();

        for (int i = 1; i * 2 <= n; i++) {

            if (n % i == 0) {
                boolean match = true;

                for (int j = i; j < n; j++) {
                    if (s.charAt(j) != s.charAt(j - i)) {
                        match = false;
                        break;
                    }

                }

                if (match) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * KMP算法
     *
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern_3(String s) {
        return kmp(s);
    }

    public boolean kmp(String pattern) {
        int n = pattern.length();
        int[] fail = new int[n];
        Arrays.fill(fail, -1);

        for (int i = 1; i < n; i++) {
            int j = fail[i - 1];

            while (j != -1 && pattern.charAt(j + 1) != pattern.charAt(i)) {
                j = fail[j];
            }

            if (pattern.charAt(j + 1) == pattern.charAt(i)) {
                fail[i] = j + 1;
            }
        }

        return fail[n - 1] != -1 && n % (n - fail[n - 1] - 1) == 0;
    }

}
