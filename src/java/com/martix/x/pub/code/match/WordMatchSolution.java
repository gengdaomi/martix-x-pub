package com.martix.x.pub.code.match;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrew-Geng on 22:15 2022/9/12
 * ali
 * <p>
 * 有一个字符串它的构成是词+空格的组合，如“北京 杭州 杭州 北京”， 要求输入一个匹配模式（简单的以字符来写）.
 * 比如 aabb,来判断该字符串是否符合该模式， 举个例子：
 * <p>
 * pattern = “abba”, str=”北京 杭州 杭州 北京” 返回 ture
 * pattern = “aabb”, str=”北京 杭州 杭州 北京” 返回 false
 * pattern = “baab”, str=”北京 杭州 杭州 北京” 返回 ture
 */
public class WordMatchSolution {

    public static void main(String[] args) {
        boolean result = new WordMatchSolution().wordPattern("abba", "北京 杭州 杭州 北京");
        System.out.println(result);
    }

    /**
     * 解法：
     * <p>
     * 首先以空格切词，并使用一个Map保存匹配关系
     * 遍历模式，在匹配关系中查找key
     * 如果找到，比较value是否与词是否相同，如果不同，返回false
     * 如果未找到，查找value，如果存在返回false，不存在则将key，value存入
     * 遍历结束，返回true
     *
     * @param pattern
     * @param str
     * @return
     */
    public boolean wordPattern(String pattern, String str) {
        if (str == null || pattern == null) {
            return false;
        }

        Map<Character, String> reflect = new HashMap<>();//模式字符与词的匹配关系
        String[] strArr = str.split(" "); //切分好的词

        if (pattern.length() != strArr.length) {
            return false;
        }

        /*
        1.遍历模式，在匹配关系中查找key
        2.如果找到，比较value是否与词是否相同，如果不同，返回false
        3.如果未找到，查找value，如果存在返回false，不存在则将key，value存入
         */
        for (int i = 0; i < pattern.length(); i++) {
            boolean isHaveKey = reflect.containsKey(pattern.charAt(i));
            boolean isHaveValue = reflect.containsValue(strArr[i]);
            String value = reflect.get(pattern.charAt(i));

            if (isHaveKey) {
                if (!value.equals(strArr[i])) {
                    return false;
                }
            } else {
                if (isHaveValue) {
                    return false;
                } else {
                    reflect.put(pattern.charAt(i), strArr[i]);
                }
            }
        }

        /**
         * 输出项，用于调试 展示
         */
        for (Character ch : reflect.keySet()) {
            System.out.println(ch + ":" + reflect.get(ch));
        }


        return true;

    }
}
