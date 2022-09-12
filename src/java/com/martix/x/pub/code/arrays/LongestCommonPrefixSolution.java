package com.martix.x.pub.code.arrays;

/**
 * source: bytedancer
 * <p>
 * 最长公共前缀
 * <p>
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * <p>
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 * <p>
 * 所有输入只包含小写字母 a-z 。
 * <p>
 * Created By Andrew-Geng on 2020/5/13 2:06 下午
 */
public class LongestCommonPrefixSolution {

    public static void main(String[] args) {
        LongestCommonPrefixSolution longestCommonPrefixSolution = new LongestCommonPrefixSolution();

        String[] strings = new String[]{"flower", "flow", "flight"};
        String str = longestCommonPrefixSolution.longestCommon(strings);

        System.out.println(str);
    }

    public String longestCommon(String[] strs) {
        int count = strs.length;
        String prefix = "";

        if (count == 0) {
            return prefix;
        }

        prefix = strs[0];
        for (int i = 1; i < count; i++) {
            /*
            关键代码，不断将前缀字符串从后往前一个个的截取字符，然后与str数组中的字符串相比，直到startsWith()返回true；
            然后再去比较str数组的下一个字符串，求prefix的最小值
             */
            while (!strs[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);
            }
        }

        return prefix;
    }

    /**
     * 最开始我的想法是利用双重for循环，对于相邻字符串的每个字符进行比较。
     * 例如"flower"和"flow"，最长公共前缀就是'flo'，利用一个计数器保存公共前缀的长度3；
     * 然后比较"flow"和"flight"的最长公共前缀，长度为2，也用计数器保存。
     * 最后在这个计数器数组中找到最小值，就是整个String数组中所有元素的最长公共前缀的长度
     *
     * @param strs
     * @return
     */
    public String longestCommon1(String[] strs) {
        if (strs.length == 0)
            return "";
        if (strs == null) {
            return null;
        }
        if (strs.length == 1) {
            return strs[0];
        }

        int minLength = Integer.MAX_VALUE; //获取整个字符组中各个字符串长度的最小值
        for (int i = 0; i < strs.length; i++) {
            if (minLength > strs[i].length()) {
                minLength = strs[i].length();
            }
        }
        if (minLength == 0)
            return "";

        int[] commons = new int[strs.length - 1];
        for (int i = 0; i < strs.length - 1; i++) {
            for (int j = 0; j < minLength; j++) {
                if (strs[i].charAt(j) == strs[i + 1].charAt(j)) {
                    commons[i]++;
                } else
                    break;
            }
        }

        int longestCommonPrefix = Integer.MAX_VALUE;
        for (int i = 0; i < commons.length; i++) {
            if (longestCommonPrefix > commons[i]) {
                longestCommonPrefix = commons[i];
            }
        }

        return strs[0].substring(0, longestCommonPrefix);
    }
}
