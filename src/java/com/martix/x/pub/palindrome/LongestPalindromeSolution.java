package com.martix.x.pub.palindrome;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrew-Geng on 12:49 上午 2021/4/29
 * 最长回文串
 * lc 409
 * <p>
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 * <p>
 * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
 * <p>
 * 注意:
 * 假设字符串的长度不会超过 1010。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * "abccccdd"
 * <p>
 * 输出:
 * 7
 * <p>
 * 解释:
 * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 */
public class LongestPalindromeSolution {

    public static void main(String[] args) {
        String s = "abccccdd";
        System.out.println(new LongestPalindromeSolution().longestPalindrome(s));
    }

    /**
     * 回文串是一个正着读和反着读都一样的字符串。以回文中心为分界线，对于回文串中左侧的字符 ch，
     * 在右侧对称的位置也会出现同样的字符。例如在字符串 "abba" 中，回文中心是 "ab|ba" 中竖线的位置，
     * 而在字符串 "abcba" 中，回文中心是 "ab(c)ba" 中的字符 "c" 本身。我们可以发现，在一个回文串中，
     * 只有最多一个字符出现了奇数次，其余的字符都出现偶数次
     * <p>
     * <p>
     * 回文的判定，就是要么全是偶数的字符，要么就是偶数+一个奇书个数的字符
     * 那么我们把偶数个数的字符 依次全部按个数相加；然后奇数次的字符全部-1 变成偶数次 继续叠加
     * 最后判断是否存在一个奇数次的字符，如果有 就在结果上加1
     *
     * @param s
     * @return
     */
    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int evenCount = 0; //偶数次数
        boolean isExist = false;
        Map<Character, Integer> map = new HashMap<>(); //key 字符  value 次数
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        /*
         可以用位操作符& 来做是否是偶数的判断更高效；；a、b对应位都为1时，c对应位为1；反之为0。
         if((map.get(key)&1)==0)作为偶数的判断
         */
        for (Character key : map.keySet()) {
            if (map.get(key) % 2 == 0) {
                evenCount += map.get(key);
            } else {
                evenCount += map.get(key) - 1;
                isExist = true;
            }
        }

        return isExist ? evenCount + 1 : evenCount;
    }

}
