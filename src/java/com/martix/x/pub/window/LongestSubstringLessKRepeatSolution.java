package com.martix.x.pub.window;

/**
 * Created by Andrew-Geng on 1:42 上午 2021/7/12
 * <p>
 * 至少有 K 个重复字符的最长子串
 * lc 395
 * <p>
 * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串，要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aaabb", k = 3
 * 输出：3
 * 解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
 * 示例 2：
 * <p>
 * 输入：s = "ababbc", k = 2
 * 输出：5
 * 解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
 */
public class LongestSubstringLessKRepeatSolution {

    /**
     * 滑动窗口
     *
     * 当某个字符的出现次数从0 增加到1 时，将less 加一；
     * 当某个字符的出现次数从 k−1 增加到k 时，将less 减一
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring_0(String s, int k) {
        int result = 0;
        int size = s.length();

        for (int t = 1; t <= 26; t++) {
            int low = 0, right = 0;
            int[] count = new int[26];  //滑动窗口内部每个字符出现的次数count
            int total = 0;  //滑动窗口内的字符种类数目total
            int less = 0;

            while (right < size) {
                count[s.charAt(right) - 'a']++;
                if (count[s.charAt(right) - 'a'] == 1) {
                    total++;
                    less++;
                }

                if (count[s.charAt(right) - 'a'] == k) {
                    less--;
                }

                while (total > t) {
                    count[s.charAt(low) - 'a']--;
                    if (count[s.charAt(low) - 'a'] == k - 1) {
                        less++;
                    }

                    if (count[s.charAt(low) - 'a'] == 0) {
                        total--;
                        less--;
                    }
                    low++;
                }

                if (less == 0) {
                    result = Math.max(result, right - low + 1);
                }

                right++;
            }
        }

        return result;
    }

    /**
     * 核心：分治
     * <p>
     * 对于字符串s，如果存在某个字符ch，它的出现次数大于0 且小于k，则任何包含ch 的子串都不可能满足要求；
     * 也就是说，我们将字符串按照ch 切分成若干段，则满足要求的最长子串一定出现在某个被切分的段内，而不能跨越一个或多个段；
     * <p>
     * 时间复杂度：O(N⋅∣Σ∣)，其中N 为字符串的长度，Σ 为字符集，
     *
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring(String s, int k) {
        int size = s.length();
        return dfs(s, 0, size - 1, k);
    }

    private int dfs(String s, int low, int right, int k) {
        int[] cnt = new int[26];
        for (int i = low; i <= right; i++) {
            cnt[s.charAt(i) - 'a']++;
        }

        char split = 0;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0 && cnt[i] < k) {
                split = (char) (i + 'a');
                break;
            }
        }

        if (split == 0) {
            return right - low + 1;
        }

        int i = low;
        int result = 0;

        while (i <= right) {
            while (i <= right && s.charAt(i) == split) {
                i++;
            }
            if (i > right) {
                break;
            }
            int start = i;
            while (i <= right && s.charAt(i) != split) {
                i++;
            }

            int length = dfs(s, start, i - 1, k);
            result = Math.max(result, length);
        }

        return result;
    }

}
