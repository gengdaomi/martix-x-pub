package com.martix.x.pub.bit;

/**
 * Created by Andrew-Geng on 12:52 上午 2021/5/11
 * 2的幂 lc 231
 * <p>
 * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1
 * 输出: true
 * 解释: 2^0= 1
 * 示例 2:
 * <p>
 * 输入: 16
 * 输出: true
 * 解释: 2^4= 16
 * 示例 3:
 * <p>
 * 输入: 218
 * 输出: false
 */
public class IsTwoPowerSolution {

    public static void main(String[] args) {
        System.out.println(new IsTwoPowerSolution().isPowerOfTwo_1(8));
    }

    /**
     * 最优解法
     * <p>
     * 如果n是2次幂，那么他的二进制，就是只有第一位为1，其余为0， 那么他减1后，就变为全部1
     * 如 n=8，二进制1000，减1，即111
     * 位操作& ，0和1与操作，结果0.
     * 所以凡是2的整数次幂和它本身减1的结果进行与操作，结果必定为0；反之，如果一个整数不是2的整数次幂，结果一定不是0
     * <p>
     * 时间复杂度：
     * O(1)。
     * 空间复杂度：
     * O(1)。
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        if (n == 0) {
            return false;
        }

        long x = (long) n;
        return (x & x - 1) == 0;
    }

    /**
     * 性能较差，容易超出时间限制
     * 思路：设置一个基数，不断累积乘以2，将结果以n比较，当相等则表示是，否则不是
     * <p>
     * 时间复杂度O(logn)
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwo_1(int n) {
        int base = 1;
        while (base < n) {
            base *= 2;
        }

        if (base == n) {
            return true;
        }

        return false;
    }
}
