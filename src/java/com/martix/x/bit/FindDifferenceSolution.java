package com.martix.x.bit;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrew-Geng on 11:34 下午 2021/4/23
 * 找不同
 * <p>
 * lc 389
 * <p>
 * 给定两个字符串 s 和 t，它们只包含小写字母。
 * <p>
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 * <p>
 * 请找出在 t 中被添加的字母。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "abcd", t = "abcde"
 * 输出："e"
 * 解释：'e' 是那个被添加的字母。
 * 示例 2：
 * <p>
 * 输入：s = "", t = "y"
 * 输出："y"
 * 示例 3：
 * <p>
 * 输入：s = "a", t = "aa"
 * 输出："a"
 * 示例 4：
 * <p>
 * 输入：s = "ae", t = "aea"
 * 输出："a"
 */
public class FindDifferenceSolution {

    public static void main(String[] args) {
        System.out.println(new FindDifferenceSolution().findTheDifference_0("abcd", "abcde"));
    }

    /**
     * 异或的位操作
     * <p>
     * 时间复杂度O(N) 空间复杂度O(1)
     *
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference(String s, String t) {
        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            result ^= (s.charAt(i) - 'a');
        }

        for (int i = 0; i < t.length(); i++) {
            result ^= (t.charAt(i) - 'a');
        }

        return (char) (result + 'a');
    }

    /**
     * 计数的方式
     *
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference_0(String s, String t) {
        int[] nums = new int[26];

        for (int i = 0; i < s.length(); i++) {
            nums[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);

            nums[c - 'a']--;
            if (nums[c - 'a'] < 0) {
                return c;
            }
        }

        return 0;
    }

    /**
     * 求和的方式
     *
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference_1(String s, String t) {
        int valS = 0, valT = 0;

        for (int i = 0; i < s.length(); i++) {
            valS += s.charAt(i);
        }

        for (int i = 0; i < t.length(); i++) {
            valT += t.charAt(i);
        }

        return (char) (valT - valS);
    }

    /**
     * 借助hash表的思路，
     *
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference_2(String s, String t) {
        Map<Character, Integer> charMap = new HashMap<>();

        if (s.length() > 0) {
            for (char c : s.toCharArray()) { //先把s中各个字符依次灌进map
                charMap.put(c, charMap.getOrDefault(c, 0) + 1);
            }
        }

        for (char c : t.toCharArray()) {
            if (!charMap.containsKey(c)) {
                return c;
            }

            if (charMap.containsKey(c)) {
                int count = charMap.get(c);
                if (count <= 1) {
                    charMap.remove(c);
                } else {
                    charMap.put(c, count - 1);
                }
            }
        }

        return 0;
    }
}
