package com.martix.x.pub.code.formula;

/**
 * Created by Andrew-Geng on 3:24 下午 2021/3/26
 * <p>
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。
 * lc 80
 */
public class MyPowDoubleSolution {

    /**
     * 快速幂思想
     * 求斐波那契数列的第n项，我们就可以通过快速幂将复杂度降到O(logn)，
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        double res = 1;
        long a = 1l * n;

        if (n < 0) {
            x = 1 / x;
            a = -a;
        }

        while (a > 0) {
            if (a % 2 == 1) { //判断 n 的二进制的最后一位是否为 1
                res *= x;
            }

            a >>= 1;

            if (a > 0) {
                x *= x;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new MyPowDoubleSolution().myPow(2, -2));
    }
}
