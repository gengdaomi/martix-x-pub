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

        for (char c : strArr) {  //计数每个字符的数量值
            count[c - 'a']++;
        }

        for (char c : strArr) { //重新遍历字符串各个字符，获取对应的数量值，当是1的时候 直接返回
            if (count[c - 'a'] == 1) {
                return String.valueOf(c);
            }
        }
        return "cannot find char";
    }

    public static void main(String[] args){
        String str = "zddsf";
        System.out.println(new FindFirstCharSolution().execute(str));
    }
}
