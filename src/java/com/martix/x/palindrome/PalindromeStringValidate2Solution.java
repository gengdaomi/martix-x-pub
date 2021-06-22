package com.martix.x.palindrome;

/**
 * Created by Andrew-Geng on 1:28 上午 2021/4/14
 * 验证回文字符串 Ⅱ lc 680
 * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "aba"
 * 输出: True
 * 示例 2:
 * <p>
 * 输入: "abca"
 * 输出: True
 * <p>
 */
public class PalindromeStringValidate2Solution {

    public static void main(String[] args) {
        System.out.println(new PalindromeStringValidate2Solution().validPalindrome("eeccccbebaeeabebccceea"));
    }

    /**
     * 解题思路：两侧向中间靠拢，当遇到不相等 尝试跳过前后一个字符后再判断是否是回文
     *
     * @param s
     * @return
     */
    public boolean validPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return this.isValidate(s, left + 1, right) || this.isValidate(s, left, right - 1);
            }

            left++;
            right--;
        }
        return true;
    }

    private boolean isValidate(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }
        return true;
    }
}
