package com.martix.x.dymaic;

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
            if (new FindSubSequenceSolution().isSubsequence(words[i], s)) {
                count++;
            }
        }

        return count;
    }

    /**
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
