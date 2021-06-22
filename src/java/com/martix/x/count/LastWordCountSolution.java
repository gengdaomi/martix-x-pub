package com.martix.x.count;

/**
 * Created by ayue on 下午7:20 2018/6/27
 * <p>
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。
 * <p>
 * 如果不存在最后一个单词，请返回 0 。
 * <p>
 * 说明：一个单词是指由字母组成，但不包含任何空格的字符串。
 * <p>
 * 输入: "Hello World"
 * 输出: 5
 */
public class LastWordCountSolution {

    public int lengthOfLastWord(String s) {
        int count = 0;

        if (s == null || s.length() == 0) {
            return count;
        }

        String[] strings = s.toLowerCase().split(" ");
        if (strings == null || strings.length == 0) {
            return count;
        }

        return strings[strings.length - 1].trim().length();
    }

    public static void main(String[] args) {
        System.out.println(new LastWordCountSolution().lengthOfLastWord("Today is a nice day"));
    }
}
