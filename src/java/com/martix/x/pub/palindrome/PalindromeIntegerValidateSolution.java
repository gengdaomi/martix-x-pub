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
 * 示例2：
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
     * 反转一半数字
     *
     * 思路：
     * 毕竟，如果该数字是回文，其后半部分反转后应该与原始数字的前半部分相同。
     *
     * 首先，我们应该处理一些临界情况。所有负数都不可能是回文；
     * 除了 0 以外，所有个位是 0 的数字不可能是回文，因为最高位不等于 0；
     *
     * 核心点：
     * 如何知道反转数字的位数已经达到原始数字位数的一半？
     * Answer：由于整个过程我们不断将原始数字除以 10，然后给反转后的数字乘上 10，
     * 所以，当原始数字小于或等于反转后的数字时，就意味着我们已经处理了一半位数的数字了
     *
     * 时间复杂度：O(logn),,对于每次迭代，我们会将输入除以10，因此时间复杂度为 O(logn)
     * 空间复杂度：O(1)。我们只需要常数空间存放若干变量
     * @param x
     * @return
     */
    public boolean isPalindrome_0(int x) {
        // 特殊情况：
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revertedNumber || x == revertedNumber / 10;
    }

    /**
     * 依次比较数字的头和尾，如果相等，去掉头和尾，再进行比较
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        int a = x, h = 1;

        if (a < 0){
            return false;
        }

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
