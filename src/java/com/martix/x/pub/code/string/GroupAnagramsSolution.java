package com.martix.x.pub.code.string;

import java.util.*;

/**
 * Created by Andrew-Geng on 1:14 上午 2021/4/2
 * <p>
 * 字母异位词分组 lc 49
 * <p>
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * <p>
 * 示例:
 * <p>
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * 说明：
 * <p>
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 */
public class GroupAnagramsSolution {

    /**
     * 借助hashmap，将strs各个值进行排序和比较
     * <p>
     * 由于互为字母异位词的两个字符串包含的字母相同，因此对两个字符串分别进行排序之后得到的字符串一定是相同的，故可以将排序之后的字符串作为哈希表的键
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr);

            String key = new String(arr);

            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }

        return new ArrayList<>(map.values());
    }

    /**
     * 计数，
     * <p>
     * 由于互为字母异位词的两个字符串包含的字母相同，因此两个字符串中的相同字母出现的次数一定是相同的，故可以将每个字母出现的次数使用字符串表示，作为哈希表的键。
     * <p>
     * 由于字符串只包含小写字母，因此对于每个字符串，可以使用长度为
     * 26
     * 26 的数组记录每个字母出现的次数。需要注意的是，在使用数组作为哈希表的键时，不同语言的支持程度不同，因此不同语言的实现方式也不同
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams_1(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();

        for (String str : strs) {
            int[] counts = new int[26];
            int length = str.length();

            for (int i = 0; i < length; i++) {
                counts[str.charAt(i) - 'a']++;
            }

            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    sb.append((char) ('a' + i));
                    sb.append(counts[i]);
                }
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {

    }
}
