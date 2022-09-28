package com.martix.x.pub.code.window;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrew-Geng on 22:12 2022/9/28
 *
 * 子串的最大出现次数 lc 1297
 *
 * 给你一个字符串s ，请你返回满足以下条件且出现次数最大的任意子串的出现次数：
 *
 * 子串中不同字母的数目必须小于等于 maxLetters 。
 * 子串的长度必须大于等于minSize 且小于等于maxSize 。
 *
 * 示例 1：
 *
 * 输入：s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
 * 输出：2
 * 解释：子串 "aab" 在原字符串中出现了 2 次。
 * 它满足所有的要求：2 个不同的字母，长度为 3 （在 minSize 和 maxSize 范围内）。
 * 示例 2：
 *
 * 输入：s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3
 * 输出：2
 * 解释：子串 "aaa" 在原字符串中出现了 2 次，且它们有重叠部分。
 * 示例 3：
 *
 * 输入：s = "aabcabcab", maxLetters = 2, minSize = 2, maxSize = 3
 * 输出：3
 * 示例 4：
 *
 * 输入：s = "abcde", maxLetters = 2, minSize = 3, maxSize = 3
 * 输出：0
 *
 */
public class SubstringMaxOccurSolution {

    /**
     * .为什么可以使用滑动窗口？
     * 从题目中给的条件可得：题目需要的不同字母的数目小于等于maxLetters，长度大于等于minSize且小于等于maxSize的子串。
     *
     * 使用滑动窗口的必要条件：
     * 1.字符串的子串
     * 2.数组的子数组
     *
     * 2.为什么要使用哈希表？
     * 题目需要统计字串出现的最大次数。
     *
     * 3.本题的重中之重（也是我卡的最久的地方）：本题只需统计长度为minSize的子串，而不需要统计长度为maxSize的子串。
     *
     * Why?
     * "abc" 肯定会覆盖 a，ab， 即长的肯定会覆盖短的，只要考虑最短的就好咯。
     *
     * 如果理解以上这些点，我们就可以愉快的码代码了。
     *
     * @param s
     * @param maxLetters
     * @param minSize
     * @param maxSize
     * @return
     */
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int n = s.length();

        Map<String,Integer> map = new HashMap<>();//统计子串出现的个数
        char[] c = s.toCharArray();
        int left = 0,right = 0;

        int letterCount = 0;//统计窗口中不同字母的数目
        int[] count = new int[128];//记录窗口中字母的个数

        while(right < n){
            count[c[right]]++;
            if(count[c[right]] == 1){//当下面条件成立时，则说明窗口中多了一个不同的字母
                letterCount++;
            }

            right++;
            int len = right - left;

            while(letterCount > maxLetters || len > minSize){
                count[c[left]]--;
                if(count[c[left]] == 0){//当窗口左移的过程中，一个字母减为0，则说明窗口中少了一个不同的字母
                    letterCount--;
                }

                left++;
                len--;//如果没有这句，会陷入死循环，len会一直大于minSize
            }

            if(letterCount <= maxLetters){//当不同字母的数目小于等于maxLetters
                if(len == minSize){
                    String str = s.substring(left,right);
                    map.put(str,map.getOrDefault(str,0)+1);
                }
            }
        }

        int result = 0;//统计字串最大出现的次数
        for(String key : map.keySet()){
            result = Math.max(result,map.get(key));
        }

        return result;
    }
}
