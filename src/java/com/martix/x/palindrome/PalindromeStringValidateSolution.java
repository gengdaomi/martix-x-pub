package com.martix.x.palindrome;

/**
 * Created by Andrew-Geng on 12:49 上午 2021/4/14
 * 验证回文串 lc 125
 * <p>
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * <p>
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "race a car"
 * 输出: false
 */
public class PalindromeStringValidateSolution {

    public static void main(String[] args) {
        System.out.println(new PalindromeStringValidateSolution().isPalindrome("A man, a plan, a canal: Panama"));
    }

    /**
     * 借助双指针的方式 两边向中间靠拢
     * 当左右指针指向无效字符的时候持续向中间靠拢 直到遇到第一个字母为止
     *
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        int left = 0, right = s.length() - 1;

        while (left < right) {
            while ((left < right) && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }

            while ((left < right) && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            if (left < right) {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }

                left++;
                right--;
            }
        }

        return true;
    }
}
