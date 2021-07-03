package com.martix.x.pub.window;

import java.util.*;

/**
 * Created by Andrew-Geng on 12:52 上午 2021/4/23
 * 找到字符串中所有字母异位词 lc 438
 * <p>
 * 给定一个字符串s和一个非空字符串p，找到s中所有是p的字母异位词的子串，返回这些子串的起始索引。
 * <p>
 * 字符串只包含小写英文字母，并且字符串s和 p的长度都不超过 20100。
 * <p>
 * 说明：
 * <p>
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 * 示例1:
 * <p>
 * 输入:
 * s: "cbaebabacd" p: "abc"
 * <p>
 * 输出:
 * [0, 6]
 * <p>
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 *  示例 2:
 * <p>
 * 输入:
 * s: "abab" p: "ab"
 * <p>
 * 输出:
 * [0, 1, 2]
 * <p>
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
 */
public class FindAnagramSolution {

    public static void main(String[] args) {

        String s = "abaacbabc";
        String p = "abc";

        List<Integer> result = new FindAnagramSolution().findAnagrams(s,p);
        System.out.println(result.toString());
    }

    /**
     * 滑动窗口 + 数组 的思路
     * <p>
     * 时间复杂度：
     * O(n)
     * 空间复杂度：
     * O(1)
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        if (s == null || p.length() == 0 || s.length() < p.length()) {
            return result;
        }

        int[] sIntArr = new int[26];
        int[] pIntArr = new int[26];

        for (int i = 0; i < p.length(); i++) { //初始化子串
            pIntArr[p.charAt(i) - 'a']++;
        }

        int start = 0;
        for (int end = 0; end < s.length(); end++) {
            sIntArr[s.charAt(end) - 'a']++;

            if (end - start + 1 == p.length()) {
                if (Arrays.equals(sIntArr, pIntArr)) { //当长度相同后，比较两数组是否相等
                    result.add(start);
                }

                sIntArr[s.charAt(start) - 'a']--; //可能存在重复的字符，需要把对应字符的计数减1
                start++;
            }
        }

        return result;
    }

    /**
     * 滑动窗口+双指针
     * <p>
     * * 时间复杂度：
     * * O(n)
     * * 空间复杂度：
     * * O(1)
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams_1(String s, String p) {
        List<Integer> result = new ArrayList<>();

        if (s == null || p.length() == 0 || s.length() < p.length()) {
            return result;
        }

        int[] sIntArr = new int[26];
        int[] pIntArr = new int[26];

        for (int i = 0; i < p.length(); i++) {
            pIntArr[p.charAt(i) - 'a']++;
        }

        int start = 0;
        for (int end = 0; end < s.length(); end++) {
            int curEnd = s.charAt(end) - 'a';
            sIntArr[curEnd]++;

            while (sIntArr[curEnd] > pIntArr[curEnd]) {//
                sIntArr[s.charAt(start) - 'a']--;
                start++;
            }

            if (end - start + 1 == p.length()) {
                result.add(start);
            }
        }

        return result;
    }
}
