package com.martix.x.pub.code.formula;

/**
 * Created by Andrew-Geng on 1:45 上午 2021/5/11
 * 3的幂
 * lc 326
 * <p>
 * 给定一个整数，写一个函数来判断它是否是 3的幂次方。如果是，返回 true ；否则，返回 false 。
 * <p>
 * 整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3x
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 27
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：n = 0
 * 输出：false
 * 示例 3：
 * <p>
 * 输入：n = 9
 * 输出：true
 * 示例 4：
 * <p>
 * 输入：n = 45
 * 输出：false
 * <p>
 * 进阶：
 * <p>
 * 你能不使用循环或者递归来完成本题吗？
 */
public class IsThreePowerSolution {

    public boolean isPowerOfThree(int n) {
        if (n == 1) {
            return true;
        }

        int base = 1;
        long result = 1l, num = 1l * n;

        while (result <= num) {
            if (result == num) {
                return true;
            }

            result = (long) Math.pow(3, base++);
        }

        return false;
    }

    public boolean isPowerOfThree_1(int n) {
        return n > 0 && 1162261467 % n == 0;
    }

}
