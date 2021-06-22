package com.martix.x.pub.string;

import java.util.HashSet;
import java.util.Set;

/**
 * source: bytedancer
 *
 * 无重复字符的最长子串
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * <p>
 * Created By Andrew-Geng on 2020/5/13 11:53 上午
 */
public class NoRepeatStringSolution {

    public static void main(String[] args) {
        NoRepeatStringSolution noRepeatStringSolution = new NoRepeatStringSolution();
        int i = noRepeatStringSolution.lengthOfLongestSubstring("pwwkew");

        System.out.println(i);
    }

    /**
     * 滑动窗口法：
     * <p>
     * 定义两个指针，start和end，代表当前窗口的开始和结束位置，同样使用hashset,当窗口中出现重复的字符时，start++,没有重复时，end++,每次更新长度的最大值
     * <p>
     * 时间复杂度o(n)
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int res = 0;
        int end = 0, start = 0;

        Set<Character> set = new HashSet<>();
        while (start < n && end < n) {
            if (set.contains(s.charAt(end))) {
                set.remove(s.charAt(start++));
            } else {
                set.add(s.charAt(end++));
                res = Math.max(res, end - start);
            }

        }

        return res;
    }
}
