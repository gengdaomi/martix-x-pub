package com.martix.x.window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrew-Geng on 2:39 下午 2021/3/26
 * <p>
 * 串联所有单词的子串
 *
 * lc 30  hard
 * <p>
 * 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * <p>
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序
 * <p>
 * 输入：
 * s = "barfoothefoobarman",
 * words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * <p>
 * 输入：
 * s = "wordgoodgoodgoodbestword",
 * words = ["word","good","best","word"]
 * 输出：[]
 */
public class FindSubStringSolution {

    /**
     * 运用滑动窗口
     *
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }

        int oneWordLength = words[0].length(); //一个单词的长度
        int wordNum = words.length; //单词个数
        int allWordLength = wordNum * oneWordLength;  //整个单词组的长度

        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            if (s.indexOf(word) < 0) { //表示主串没这个单词，直接返回
                return result;
            }

            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        if (allWordLength > s.length()) { //所有单词长度小于主串
            return result;
        }

        /*
         * 每次进行匹配的窗口大小为 wordsLen，每次后移一个单词长度，由左右窗口维持当前窗口位置
         */
        for (int i = 0; i < oneWordLength; i++) {
            int start = i, end = i, count = 0;
            Map<String, Integer> subMap = new HashMap<>(); //统计每个符合的word


            // 右窗口不能超出主串长度
            while (end + oneWordLength <= s.length()) {
                // 得到一个单词
                String word = s.substring(end, end + oneWordLength);
                // 有窗口右移
                end += oneWordLength;
                // words[]中没有这个单词，那么当前窗口肯定匹配失败，直接右移到这个单词后面
                if (!map.containsKey(word)) {
                    start = end;
                    // 窗口内单词统计map清空，重新统计
                    subMap.clear();
                    // 符合要求的单词数清0
                    count = 0;
                } else {
                    // 统计当前子串中这个单词出现的次数
                    subMap.put(word, subMap.getOrDefault(word, 0) + 1);
                    ++count;
                    // 如果这个单词出现的次数大于words[]中它对应的次数，又由于每次匹配和words长度相等的子串
                    // 如 ["foo","bar","foo","the"]  "| foobarfoobar| foothe"
                    // 第二个bar虽然是words[]中的单词，但是次数抄了，那么右移一个单词长度后 "|barfoobarfoo|the"
                    // bar还是不符合，所以直接从这个不符合的bar之后开始匹配，也就是将这个不符合的bar和它之前的单词(串)全移出去
                    while (subMap.getOrDefault(word, 0) > map.getOrDefault(word, 0)) {
                        // 从当前窗口字符统计map中删除从左窗口开始到数量超限的所有单词(次数减一)
                        String w = s.substring(start, start + oneWordLength);
                        subMap.put(w, subMap.getOrDefault(w, 0) - 1);
                        // 符合的单词数减一
                        --count;
                        // 左窗口位置右移
                        start += oneWordLength;
                    }
                    // 当前窗口字符串满足要求
                    if (count == words.length) {
                        result.add(start);
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] s = new String[]{"foo", "bar"};
        System.out.println(new FindSubStringSolution().findSubstring("barfoothefoobarman", s));
    }
}
