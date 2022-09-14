package com.martix.x.pub.code.formula;

/**
 * Created by Andrew-Geng on 1:12 上午 2021/5/11
 * 4的幂 lc 342
 * <p>
 * 给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回 true ；否则，返回 false 。
 * <p>
 * 整数 n 是 4 的幂次方需满足：存在整数 x 使得 n == 4x
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 16
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：n = 5
 * 输出：false
 * 示例 3：
 * <p>
 * 输入：n = 1
 * 输出：true
 * <p>
 * 提示：
 * <p>
 * -231 <= n <= 231 - 1
 * <p>
 * 进阶：
 * <p>
 * 你能不使用循环或者递归来完成本题吗？
 */
public class IsFourPowerSolution {

    public static void main(String[] args) {
        System.out.println(new IsFourPowerSolution().isPowerOfFour_2(4));
    }

    /**
     * (101010...10)
     * 2
     *
     * @param num
     * @return
     */
    public boolean isPowerOfFour_0(int num) {
        return (num > 0) && ((num & (num - 1)) == 0) && ((num & 0xaaaaaaaa) == 0);
    }

    /**
     * x 是否为 2 的幂;x > 0 and x & (x - 1) == 0
     * 可以确定若 x 为 4 的幂则a 为偶数
     * <p>
     * 考虑a=2k 和 a=2k+1 两种情况,
     * x 对 3 进行取模
     *
     * @param n
     * @return
     */
    public boolean isPowerOfFour(int n) {
        return (n > 0) && ((n & (n - 1)) == 0) && (n % 3 == 1);
    }

    /**
     * 数学运算的方式
     * x=4^a, a=log4 x = 1/2 * log2 x , 如果如题要求，那么a需要为整数，那么我们只需要检查log2 x 是否为偶数
     * <p>
     * 时间复杂度 空间复杂度都为O(1)
     *
     * @param n
     * @return
     */
    public boolean isPowerOfFour_1(int n) {
        if (n > 0 && (Math.log(n) / Math.log(2) % 2 == 0)) {
            return true;
        }

        return false;
    }

    /**
     * 通过循环的方式处理，
     *
     * @param n
     * @return
     */
    public boolean isPowerOfFour_2(int n) {
        if (n == 1) {
            return true;
        }

        int base = 1;
        long result = 1l, num = 1l * n;

        while (result <= num) {
            if (result == num) {
                return true;
            }

            result = (long) Math.pow(4, base++);
        }

        return false;
    }
}
