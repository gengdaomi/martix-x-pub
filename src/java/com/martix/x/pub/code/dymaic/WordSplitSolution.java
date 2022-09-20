package com.martix.x.pub.code.dymaic;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrew-Geng on 11:36 下午 2021/7/22
 * <p>
 * 单词拆分 lc 139
 * <p>
 * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * <p>
 * 说明：
 * <p>
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 * <p>
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 示例 2：
 * <p>
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 * 示例 3：
 * <p>
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 */
public class WordSplitSolution {

    /**
     * 动态规划
     * <p>
     * 定义 dp[i] 表示字符串s前i 个字符组成的字符串s[0..i−1] 是否能被空格拆分成若干个字典中出现的单词。
     * 从前往后计算考虑转移方程，每次转移的时候我们需要枚举包含位置i−1 的最后一个单词，
     * 看它是否出现在字典中以及除去这部分的字符串是否合法即可。
     * <p>
     * 对于边界条件，我们定义dp[0]=true 表示空串且合法。
     * 转移方程：dp[i]=dp[j] && check(s[j..i−1])，，其中check(s[j..i−1]) 表示子串s[j..i−1] 是否出现在字典中。
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {

                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

}
