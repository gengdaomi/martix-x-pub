package com.martix.x.pub.palindrome;

/**
 * Created by Andrew-Geng on 12:01 上午 2021/7/25
 * <p>
 * 构造 K 个回文字符串 lc 1400
 * <p>
 * 给你一个字符串 s和一个整数 k。请你用 s字符串中 所有字符构造 k个非空 回文串。
 * <p>
 * 如果你可以用s中所有字符构造k个回文字符串，那么请你返回 True，否则返回False。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "annabelle", k = 2
 * 输出：true
 * 解释：可以用 s 中所有字符构造 2 个回文字符串。
 * 一些可行的构造方案包括："anna" + "elble"，"anbna" + "elle"，"anellena" + "b"
 * 示例 2：
 * <p>
 * 输入：s = "leetcode", k = 3
 * 输出：false
 * 解释：无法用 s 中所有字符构造 3 个回文串。
 * 示例 3：
 * <p>
 * 输入：s = "true", k = 4
 * 输出：true
 * 解释：唯一可行的方案是让 s 中每个字符单独构成一个字符串。
 * 示例 4：
 * <p>
 * 输入：s = "yzyzyzyzyzyzyzy", k = 2
 * 输出：true
 * 解释：你只需要将所有的 z 放在一个字符串中，所有的 y 放在另一个字符串中。那么两个字符串都是回文串。
 */
public class BuildKNumPalindromeSolution {

    /**
     * 找出可行的回文串个数
     * <p>
     * 1.求出字符串s最少可以构造的回文串个数left；
     * 2.求出字符串s最多可以构造的回文串个数right；
     * 3.找出在[left,right] 范围内满足要求的那些值，并判断k 是否在其中
     * <p>
     * <p>
     * 如果s 中有p 个字符出现了奇数次，q 个字符出现了偶数次，
     * 那么s 最少可以构造的回文串个数就为p，这是因为每一种出现了奇数次的字符都必须放在不同的回文串中。
     * 特别地，如果p=0，那么最少构造的回文串个数为1。
     *
     * @param s
     * @param k
     * @return
     */
    public boolean canConstruct(String s, int k) {
        int right = s.length(); // 右边界为字符串的长度
        int[] occ = new int[26]; // 统计每个字符出现的次数

        for (int i = 0; i < right; i++) {
            occ[s.charAt(i) - 'a']++;
        }

        int left = 0;  // 左边界为出现奇数次字符的个数
        for (int i = 0; i < 26; ++i) {
            if (occ[i] % 2 == 1) {
                left++;
            }
        }

        left = Math.max(left, 1);// 注意没有出现奇数次的字符的特殊情况

        return left <= k && k <= right;
    }

}
