package com.martix.x.pub.code.window;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrew-Geng on 11:17 下午 2021/4/28
 * 最小覆盖子串
 * lc 76 hard
 * <p>
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * <p>
 * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 示例 2：
 * <p>
 * 输入：s = "a", t = "a"
 * 输出："a"
 */
public class MinWindowSolution {

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "NEB";

        System.out.println(new MinWindowSolution().minWindow(s, t));
    }

    /**
     * 滑动窗口
     * 当valid == tCharMap.size()的时候，表示t中所有字符已经被覆盖，已经得到一个可行的覆盖子串，
     * 应该开始收缩窗口，以便得到一个最小覆盖子串
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }

        Map<Character, Integer> tCharMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            tCharMap.put(t.charAt(i), tCharMap.getOrDefault(t.charAt(i), 0) + 1);
        }

        Map<Character, Integer> windowMap = new HashMap<>();
        int start = 0, end = 0;
        int valid = 0;//用于表示窗口中满足tCharMap条件的字符个数，如果valid==tCharMap.size,表示窗口已经满足
        int startIndex = 0, minLength = Integer.MAX_VALUE;

        while (end < s.length()) {
            char c = s.charAt(end);
            end++;

            if (tCharMap.containsKey(c)) {
                windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);

                if (windowMap.get(c).equals(tCharMap.get(c))) {
                    valid++;
                }
            }

            while (valid == tCharMap.size()) {
                if (end - start < minLength) { //当发现当前符合的窗口的大小 比之前的最小窗口大小的时候，更新覆盖子串的起始位置 和最小子串的长度
                    startIndex = start;
                    minLength = end - start;
                }

                char startChar = s.charAt(start);
                start++;

                if (tCharMap.containsKey(startChar)) {
                    if (windowMap.get(startChar).equals(tCharMap.get(startChar))) {
                        valid--;
                    }

                    windowMap.put(startChar, windowMap.getOrDefault(startChar, 0) - 1);
                }
            }
        }

        return minLength == Integer.MAX_VALUE ? "" : s.substring(startIndex, startIndex + minLength);
    }
}
