package com.martix.x.pub.palindrome;

/**
 * Created By Andrew-Geng on 2020/6/2 2:36 下午
 * 判断一个数字 是否是回文数 lc 9
 * <p>
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * <p>
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：x = 121
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：x = -121
 * 输出：false
 * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3：
 * <p>
 * 输入：x = 10
 * 输出：false
 * 解释：从右向左读, 为 01 。因此它不是一个回文数。
 * 示例 4：
 * <p>
 * 输入：x = -101
 * 输出：false
 * <p>
 * 进阶：你能不将整数转为字符串来解决这个问题吗？
 */
public class PalindromeIntegerValidateSolution {

    public static void main(String[] args) {
    }

    /**
     * 依次比较数字的头和尾，如果相等，去掉头和尾，再进行比较
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        int a = x, h = 1;

        if (a < 0) return false;

        while (a / h >= 10) {
            h = h * 10;
        }

        while (a > 0) {
            if (a / h != a % 10) {
                return false;
            }

            a = a % h;
            a = a / 10;
            h = h / 100;
        }

        return true;
    }

    /**
     * 将数字反转，只需要判断反转前后是否相等即可 但是需要注意，如果数字过大可能导致溢出的问题
     *
     * @param x
     * @return
     */
    public boolean isPalindrome2(int x) {
        int a = x, r = 0;

        if (x < 0) {
            return false;
        }

        while (a > 0) {
            r = r * 10 + a % 10;
            a = a / 10;
        }

        return r == x;
    }

    /**
     * 不建议，但是可行的办法
     * 将整数转换为字符串的方式进行比较
     *
     * @param x
     * @return
     */
    public boolean isPalindrome3(int x) {
        if (x < 0) {
            return false;
        }
        if (x >= 0 && x < 10) {
            return true;
        }

        String str = Integer.toString(x);
        int right = str.length() - 1;
        int left = 0;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}
