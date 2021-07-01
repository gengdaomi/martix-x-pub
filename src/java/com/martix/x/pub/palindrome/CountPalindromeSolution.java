package com.martix.x.pub.palindrome;

/**
 * Created by Andrew-Geng on 3:38 上午 2021/6/14
 * 回文子串 lc 647
 * <p>
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * <p>
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入："abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * 示例 2：
 * <p>
 * 输入："aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 */
public class CountPalindromeSolution {

    /**
     * 中心扩展
     * <p>
     * 确定了一个中心点后的寻找的路径，然后我们只要寻找到所有的中心点
     * <p>
     * 想象一下单个字符的哪个中心点扩展可以得到这个子串？似乎不可能。所以中心点不能只有单个字符构成，还要包括两个字符
     * 比如上面这个子串abab，就可以有中心点 ba 扩展一次得到，
     * 所以最终的中心点由 2 * len - 1 个，分别是 len 个单字符和 len - 1 个双字符
     * <p>
     * 为什么有 2 * len - 1 个中心点？
     * aba 有5个中心点，分别是 a、b、c、ab、ba
     * abba 有7个中心点，分别是 a、b、b、a、ab、bb、ba
     * <p>
     * 中心点即 left 指针和 right 指针初始化指向的地方，可能是一个也可能是两个
     * <p>
     * 时间复杂度O(n^2),空间复杂度O(1)
     *
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        int n = s.length(), result = 0;

        for (int i = 0; i < 2 * n - 1; ++i) {
            //先是left，有一个很明显的2倍关系的存在，其次是right，可能和left指向同一个（偶数时），也可能往后移动一个（奇数）
            int left = i / 2, right = left + i % 2;

            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                --left;
                ++right;

                result++;
            }
        }

        return result;
    }
}
