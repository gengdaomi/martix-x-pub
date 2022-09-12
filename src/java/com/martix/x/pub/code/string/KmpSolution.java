package com.martix.x.pub.code.string;

/**
 * Created By Andrew-Geng on 2020/6/2 10:34 上午
 * <p>
 * 面临这样一个问题：有一个文本串S，和一个模式串P，现在要查找P在S中的位置
 * <p>
 * 经典的KMP算法，用于模式匹配
 */
public class KmpSolution {
    public static int kmp(String str, String dest, int[] next) {//str文本串  dest 模式串
        for (int i = 0, j = 0; i < str.length(); i++) {
            while (j > 0 && str.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            if (str.charAt(i) == dest.charAt(j)) {
                j++;
            }
            if (j == dest.length()) {
                return i - j + 1;
            }
        }
        return 0;
    }

    public static int[] kmpnext(String dest) {
        int[] next = new int[dest.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {
            while (j > 0 && dest.charAt(j) != dest.charAt(i)) {
                j = next[j - 1];
            }
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    public static void main(String[] args) {
        String a = "ababa";
        String b = "ssdfgasdbababa";
        int[] next = kmpnext(a);
        int res = kmp(b, a, next);
        System.out.println(res + " ===");

        for (int i = 0; i < next.length; i++) {
            System.out.println(next[i]);
        }
        System.out.println(next.length);
    }
}
