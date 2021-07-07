package com.martix.x.pub.string;

/**
 * source: btyedancer
 * <p>
 * 字符串的排列
 * <p>
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * <p>
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 * <p>
 * 示例1:
 * <p>
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 * <p>
 * <p>
 * 示例2:
 * <p>
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 * <p>
 * <p>
 * 注意：
 * <p>
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 * <p>
 * Created By Andrew-Geng on 2020/5/13 2:22 下午
 */
public class CheckInclusionSolution {

    public static void main(String[] args) {
        CheckInclusionSolution checkInclusionSolution = new CheckInclusionSolution();

        Boolean flag = checkInclusionSolution.checkInclusion("ab", "eidbaooo");
        System.out.println(flag);
    }

    /**
     * 题意：在s2中连续的s1串长度内，出现与s1串中各个字母出现次数相同即为true
     * <p>
     * 解题思路：在s2的s1的框口大小内存在着与s1的所有相同数量的字母，
     * 存在一个问题如果对字母排序会影响效率，由于都是小写字母，可以考虑26个字母对应26位，
     * 每一位对应对应字母出现次数
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        // ·窗口固定大小，尺寸参照s1.length()
        int[] s1Arr = new int[26];
        int[] s2Arr = new int[26];
        int size = s1.length();

        char[] s1Ch = s1.toCharArray();
        char[] s2Ch = s2.toCharArray();

        // ·s1 s2同时初始化
        for (int i = 0; i < size; i++) {
            s1Arr[(int) (s1Ch[i] - 'a')]++;
            s2Arr[(int) (s2Ch[i] - 'a')]++;
        }

        for (int i = size, start = 0; i < s2.length(); i++, start++) {
            if (match(s1Arr, s2Arr)) {
                return true;
            }

            // ·窗口右移
            s2Arr[(int) (s2Ch[i] - 'a')]++;
            s2Arr[(int) (s2Ch[start] - 'a')]--;
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
