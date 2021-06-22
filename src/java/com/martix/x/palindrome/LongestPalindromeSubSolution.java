package com.martix.x.palindrome;

/**
 * 最长回文子串 lc 5
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 * TODO 需要再了解下解法
 *
 * <p>
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba"也是一个有效答案。
 * <p>
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 * <p>
 * <p>
 * 解题思路：
 * <p>
 * 从第一个字符串开始，一次向左向右判断，如果相同就继续向左向右直到不相同或者越界为止，并判断最大长度，依次更新最大长度值，并记录最大长度值的开始为止。示例代码如下：
 */
public class LongestPalindromeSubSolution {

    private int low;

    private int maxLength;

    public static void main(String[] args) {
        String s = "babad";

        System.out.println(new LongestPalindromeSubSolution().longestPalindrome(s));
    }

    public String longestPalindrome(String s) {
        int len = s.length();

        if (len < 2) {
            return s;
        }

        for (int i = 0; i < len - 1; i++) {//遍历整个数组，寻找回文子串最中间的字符，如果回文子串是奇数长度，那么最中间有一个字符；否则有两个字符
            /*
              以s[i]为中心的奇数长度的回文串
             */
            this.extendPalindrome(s, i, i);
            /*
              以s[i] s[i+1]为中心的偶数长度的回文串
             */
            this.extendPalindrome(s, i, i + 1);
        }

        return s.substring(low, low + maxLength);
    }

    public String longestPalindrome_1(String s) {
        if (s.length() < 2) {
            return s;
        }

        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = this.getPalindrome(s, i, i);
            int len2 = this.getPalindrome(s, i, i + 1);

            int length = Math.max(len1,len2);
            if (length > end - start) {
                start = i - (length - 1) / 2;
                end = i + length / 2;
            }
        }
        return s.substring(start, end + 1);
    }


    /**
     * 借助双指针的思路，回文都是中心向两边对称
     * 回文串的长度可能是奇数 也可能是偶数
     *
     * @param s
     * @param j
     * @param k
     */
    private void extendPalindrome(String s, int j, int k) {
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;//如果满足括号里的条件，则继续向两边扩展
            k++;
        }

        if (maxLength < k - j - 1) {//更新最大长度的值
            low = j + 1;
            maxLength = k - j - 1;
        }
    }

    private int getPalindrome(String s, int j, int k) {
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;//如果满足括号里的条件，则继续向两边扩展
            k++;
        }

        return k-j-1;
    }

    public String longestPalindrome_2(String s) {
        // ababa 求最长公共子串
        int len = s.length();
        String result = "";

        for (int i = 0; i < len * 2 - 1; i++) {
            int left = i / 2;
            int right = left + i % 2;
            while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                String tmp = s.substring(left, right + 1);
                if (tmp.length() > result.length()) {
                    result = tmp;
                }
                left--;
                right++;
            }
        }
        return result;
    }

}
