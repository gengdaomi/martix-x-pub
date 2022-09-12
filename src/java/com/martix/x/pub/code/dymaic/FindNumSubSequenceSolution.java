package com.martix.x.pub.code.dymaic;

/**
 * Created by Andrew-Geng on 8:21 下午 2021/3/30
 */
public class FindNumSubSequenceSolution {

    public int numMatchingSubseq(String s, String[] words) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < words.length; i++) {
            if (new CheckSubSequenceSolution().isSubsequence(words[i], s)) {
                count++;
            }
        }

        return count;
    }

    /**
     * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
     * <p>
     * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）
     *
     * 通过lc 392的方式暴力处理，但是存在超时的问题，比如S过大的时候
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence_1(String s, String t) {
        int n = s.length(), m = t.length();
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == n;
    }

    public static void main(String[] args) {
        String s = "abcde";
        String[] words = new String[]{"a", "bb", "acd", "ace"};


        System.out.println(new FindNumSubSequenceSolution().numMatchingSubseq(s, words));
    }
}
