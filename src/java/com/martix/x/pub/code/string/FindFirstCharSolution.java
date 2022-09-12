package com.martix.x.pub.code.string;

/**
 * 题目1
 * 1: 给定任意一个字符串，找到这个字符串当中，第一次出现且仅出现一次的字符。 如：“abac”,输出b，“abbacdc”，输出d，若无法找到，则输出“cannot find char”。
 * Created By Andrew-Geng on 2020/8/28 8:43 下午
 */
public class FindFirstCharSolution {

    public String execute(String str) {
        int[] count = new int[26];
        char[] strArr = str.toCharArray();

        for (char c : strArr) {
            count[c - 'a']++;
        }

        for (char c : strArr) {
            if (count[c - 'a'] == 1) {
                return String.valueOf(c);
            }
        }
        return "cannot find char";
    }
}
