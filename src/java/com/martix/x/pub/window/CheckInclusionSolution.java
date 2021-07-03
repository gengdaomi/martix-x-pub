package com.martix.x.pub.window;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrew-Geng on 12:17 上午 2021/4/29
 * <p>
 * 字符串的排列
 * lc 567
 * <p>
 * 给定两个字符串s1和s2，写一个函数来判断 s2 是否包含 s1的排列。
 * <p>
 * 换句话说，第一个字符串的排列之一是第二个字符串的 子串 。
 *
 * <p>
 * 示例 1：
 * <p>
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 * 示例 2：
 * <p>
 * 输入: s1= "ab" s2 = "eidbcaoo"
 * 输出: False
 *  
 * <p>
 * 提示：
 * <p>
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 */
public class CheckInclusionSolution {

    public static void main(String[] args) {
        String s1 = "ab", s2 = "eidbaooo";
        System.out.println(new CheckInclusionSolution().checkInclusion_1(s1, s2));
    }

    /**
     * 滑动窗口  end-start 是一个左闭右开的区间，所以不用减1
     *
     * 这个里面的map 用作 滑动窗口， 核心就是只有在子串中存在的字符才会记录到map中，否则只需要移动窗口的起始下标 start end
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        Map<Character, Integer> s1CharMap = new HashMap<>(); //先把子串(短的)放进map中
        for (int i = 0; i < s1.length(); i++) {
            s1CharMap.put(s1.charAt(i), s1CharMap.getOrDefault(s1.charAt(i), 0) + 1);
        }

        Map<Character, Integer> s2CharMap = new HashMap<>(); //用于实时记录符合的滑动窗口的值
        int start = 0, end = 0;
        int valid = 0; //用于计数，表示窗口中满足子串s1条件的字符个数，如果相等，则表示已经覆盖子串；；以字母维度区分，且出现次数相同时 加1

        while (end < s2.length()) {
            char c = s2.charAt(end);
            end++;

            if (s1CharMap.containsKey(c)) {
                s2CharMap.put(c, s2CharMap.getOrDefault(c, 0) + 1);

                if (s1CharMap.get(c).equals(s2CharMap.get(c))) {
                    valid++;
                }
            }

            while (end - start >= s1.length()) { //当滑动窗口的长度大于等于子串长度
                if (valid == s1CharMap.size()) {
                    return true;
                }

                char startChar = s2.charAt(start);
                start++;

                if (s1CharMap.containsKey(startChar)) {
                    if (s2CharMap.get(startChar).equals(s1CharMap.get(startChar))) {
                        valid--;
                    }

                    s2CharMap.put(startChar, s2CharMap.getOrDefault(startChar, 0) - 1);
                }
            }
        }
        return false; //没有找到符合的子串
    }

    /**
     * 提前将 子串 和 主串 按照子串的长度 初始化成两个滑动窗口
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion_1(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        // ·窗口固定大小，尺寸参照s1.length()
        int[] s1Arr = new int[26];
        int[] s2Arr = new int[26];
        int size = s1.length();

        char[] s1Ch = s1.toCharArray();
        char[] s2Ch = s2.toCharArray();

        // ·s1 s2同时初始化 按照s1的长度来初始化
        for (int i = 0; i < size; i++) {
            s1Arr[(s1Ch[i] - 'a')]++;
            s2Arr[(s2Ch[i] - 'a')]++;
        }

        for (int end = size, start = 0; end < s2.length(); end++, start++) {
            if (match(s1Arr, s2Arr)) { //也可以用Arrays.equals(s1Arr, s2Arr)
                return true;
            }

            // ·窗口右移
            s2Arr[(s2Ch[end] - 'a')]++;
            s2Arr[(s2Ch[start] - 'a')]--;
        }

        return match(s1Arr, s2Arr);
    }

    private static boolean match(int[] c1, int[] c2) {
        for (int i = 0; i < 26; i++) {
            if (c1[i] != c2[i]) {
                return false;
            }
        }
        return true;
    }
}
