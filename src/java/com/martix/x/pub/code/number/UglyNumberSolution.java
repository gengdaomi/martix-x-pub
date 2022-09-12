package com.martix.x.pub.code.number;

/**
 * Created by Andrew-Geng on 12:20 上午 2021/4/27
 * 丑数 lc263
 * <p>
 * 给你一个整数 n ，请你判断 n 是否为 丑数 。如果是，返回 true ；否则，返回 false 。
 * <p>
 * 丑数 就是只包含质因数2、3 和/或5的正整数。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 6
 * 输出：true
 * 解释：6 = 2 × 3
 * 示例 2：
 * <p>
 * 输入：n = 8
 * 输出：true
 * 解释：8 = 2 × 2 × 2
 * 示例 3：
 * <p>
 * 输入：n = 14
 * 输出：false
 * 解释：14 不是丑数，因为它包含了另外一个质因数 7 。
 * 示例 4：
 * <p>
 * 输入：n = 1
 * 输出：true
 * 解释：1 通常被视为丑数。
 */
public class UglyNumberSolution {

    public static void main(String[] args) {
        System.out.println(new UglyNumberSolution().isUgly(8));
    }

    public boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }

        int[] factors = {2, 3, 5};
        for (int factor : factors) {
            while (n % factor == 0) {
                n /= factor;
            }
        }

        return n == 1;
    }
}
