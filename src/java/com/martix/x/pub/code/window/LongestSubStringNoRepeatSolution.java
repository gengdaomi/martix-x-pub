package com.martix.x.pub.code.window;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Andrew-Geng on 2:44 下午 2021/3/25
 * <p>
 * 给定一个字符串，请你找出其中不含有重复字符的最长子串的长度
 * lc:3
 * <p>
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是"wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke"是一个子序列，不是子串。
 * 示例 4:
 * <p>
 * 输入: s = ""
 * 输出: 0
 */
public class LongestSubStringNoRepeatSolution {

    /**
     * 采取滑动窗口的方法降低时间复杂度
     * 定义一个 map 数据结构存储 (k, v)，其中 key 值为字符，value 值为字符位置 +1，加 1 表示从字符位置后一个才开始不重复
     * 我们定义不重复子串的开始位置为 start，结束位置为 end
     * 随着 end 不断遍历向后，会遇到与 [start, end] 区间内字符相同的情况，此时将字符作为 key 值，获取其 value 值，并更新 start，此时 [start, end] 区间内不存在重复字符
     * 无论是否更新 start，都会更新其 map 数据结构和结果 ans。
     * <p>
     * 时间、空间复杂度O(n)
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }

        int offset = 0;
        int result = 0;

        Map<Character, Integer> map = new HashMap<>(); //key: 字符， value：以当前字符 key 结尾的 子串长度
        for (int end = 0; end < s.length(); end++) {
            char endChar = s.charAt(end);
            if (map.containsKey(endChar)) { //已经存在以endChar结尾的字符
                offset = Math.max(map.get(endChar), offset);
            }

            map.put(endChar, end + 1);
            result = Math.max(result, end + 1 - offset);
        }

        return result;
    }

    public int lengthOfLongestSubstring_1(String s) {
        int res = 0;
        int end = 0, start = 0;

        Set<Character> set = new HashSet<>();

        while (start < s.length() && end < s.length()) {
            if (set.contains(s.charAt(end))) {
                set.remove(s.charAt(start++));
            } else {
                set.add(s.charAt(end++));
                res = Math.max(res, end - start);
            }
        }
        return res;
    }

    public int lengthOfLongestSubstring_2(String s) {
        int end = 0, start = 0;
        int res = 0;

        Set<Character> set = new HashSet<>();
        while (start < s.length() && end < s.length()) {
            if (set.contains(s.charAt(end))) {
                set.remove(s.charAt(start));
                start++;
            } else {
                set.add(s.charAt(end));
                end++;

                res = Math.max(res, end - start);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new LongestSubStringNoRepeatSolution().lengthOfLongestSubstring_1("abcabcbb"));
    }
}
