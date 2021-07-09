package com.martix.x.pub.validate;

import java.util.*;

/**
 * Created by Andrew-Geng on 5:26 下午 2021/4/22
 * <p>
 * 有效的字母异位词 lc 242
 * <p>
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * <p>
 * 示例1:
 * <p>
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 * <p>
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 */
public class AnagramsValidateSolution {
    public static void main(String[] args) {
        String s = "rat", t = "car";
        System.out.println(new AnagramsValidateSolution().isAnagram_0(s, t));
    }

    /**
     * 通过异或 位操作符进行
     * 时间复杂度 O(n)
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram_0(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            result ^= (s.charAt(i) - 'a') ^ (t.charAt(i) - 'a');
        }

        return result == 0;
    }

    /**
     * 字符排序的思路 时间复杂度：O(nlogn)
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        char[] sCharArr = s.toCharArray();
        char[] tCharArr = t.toCharArray();

        Arrays.sort(sCharArr);
        Arrays.sort(tCharArr);

        Set<String> set = new HashSet<>();//用于去重
        set.add(new String(sCharArr));
        set.add(new String(tCharArr));

        return set.size() == 1;
    }

    /**
     * 哈希表的思路，
     * <p>
     * 计算每个字符的在字符串的个数，如果是异位词，只是字符串中字符顺序不同，
     * 但是字符对应个数相同
     * <p>
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram_1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] sCharCountArr = new int[26];
        for (int i = 0; i < s.length(); i++) {  //将各个字符按照int的数组下标对应的方式 在数组对应位置记录他们出现的次数
            sCharCountArr[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < t.length(); i++) { //用t字符串中的各个字符去数组匹配
            sCharCountArr[t.charAt(i) - 'a']--;

            if (sCharCountArr[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * 解决 Unicode的问题
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram_2(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();

        if (s.length() != t.length()) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);

            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) - 1);
        }

        for (int val : map.values()) {
            if (val < 0) {
                return false;
            }
        }
        return true;
    }

}
