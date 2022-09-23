package com.martix.x.pub.code.string;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Andrew-Geng on 21:13 2022/9/14
 * <p>
 * 寻找字符串中出现频次最高且最先出现的字符
 * <p>
 * 假设有一个字符串，字符串内部的所有字符都是在ascii编码的范围内，编码求出字符串中出现频率最高的字符，
 * 如果频率最高的字符有几个字符出现的频率一样，则输出最先出现的字符。
 * <p>
 * 如输入串为"hello world,every body!"，则输出频率最高且最先出现的字符’e’
 * <p>
 * 方法定义：char getMaxOccurChar(String str)
 */
public class TopFrequencyAndOccurSolution {

    public static void main(String[] args) {
        char s = getMaxOccurChar("aaaahfdfbbbbbbbbbb");
        System.out.println(s);
    }

    /**
     * 要求且最早出现的字符，因此采用倒序遍历；如"aaaccc",正序遍历为c，倒序遍历则为a
     * @param s
     * @return
     */
    public static char getMaxOccurChar(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;

        if (n == 0) {
            return ' ';
        }

        int[] numArr = new int[512];
        Arrays.fill(numArr, 0);
        int i;

        int max = 0;//最高频次
        char result = ' ';//结果字符

        for (i = n - 1; i >= 0; i--) {//倒序遍历
            numArr[arr[i]-'a']++;

            if (numArr[arr[i]-'a'] >= max) {//动态更新最高频次及对应字符
                max = numArr[arr[i]-'a'];
                result = arr[i];
            }
        }

        return result;
    }

    public static char getMaxOccurChar_1(String str) {

        int maxCount = 1;
        Character result = str.charAt(0);

        Map<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < str.length(); i++) {
            Character content = str.charAt(i);
            Integer count = map.get(content);
            if (count == null) {
                map.put(content, 1);
            } else {
                map.put(content, count + 1);
            }
        }

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxCount) {
                result = entry.getKey();
                maxCount = entry.getValue();
            }
        }
        return result;
    }

}
