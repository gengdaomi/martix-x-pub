package com.martix.x.pub.code.expand;

/**
 * Created by Andrew-Geng on 12:23 上午 2021/5/11
 * 获取最大公约数
 */
public class GreatestCommonDivisorSolution {

    public static void main(String[] args) {
        int result = new GreatestCommonDivisorSolution().getGreatestCommonDivisor_1(25, 10);
        System.out.println(result);
    }

    /**
     * 欧几里得算法
     * 两个正整数 a 和b(a>b)，他们的最大公约数等于a除以b的余数，余数c和b之间的最大公约数
     * 例如：25和10，25/10的商是2余5，那么10和25的最大公约数，等同于10和5的最大公约数
     * <p>
     * 缺点：当两整数较大的时候，a%b的性能较差
     * 时间复杂度 O(log(max(a,b)))
     *
     * @param a
     * @param b
     * @return
     */
    public int getGreatestCommonDivisor(int a, int b) {
        int big = a > b ? a : b;
        int small = a > b ? b : a;

        if (big % small == 0) {
            return small;
        }

        return getGreatestCommonDivisor(big % small, small);
    }

    /**
     * 更相减损术
     * <p>
     * 最大公约数等于a-b的差值c和较小数b的最大公约数
     * <p>
     * 避免大整数%计算的开销，但是运算次数肯定大于辗转相除法的取模方式
     * <p>
     * 时间复杂度 O(max(a,b))
     *
     * @param a
     * @param b
     * @return
     */
    public int getGreatestCommonDivisor_1(int a, int b) {
        if (a == b) {
            return a;
        }

        int big = a > b ? a : b;
        int small = a > b ? b : a;

        return getGreatestCommonDivisor(big - small, small);
    }

    /**
     * 性能最优版，更相减损术+位操作符进行运算
     * 思路：
     * 1、当a和b都为偶数的时候，fun(a,b)=2*fun(a/2,b/2)=2*fun(a>>1,b>>1)=fun(a>>1,b>>1)<<1
     * 2、当a为偶数，b都为奇数的时候，fun(a,b)=fun(a/2,b)=2*fun(a>>1,b)
     * 3、当a为奇数，b都为偶数的时候，fun(a,b)=fun(a,b/2)=fun(a1,b>>1)
     * 4、当a为奇数，b都为奇数的时候，先利用更相减损术运算一次，fun(a,b)=fun(a-b,b)，此时的a-b必然是偶数，然后继续进行移位运算
     *
     *  (a&1)==0 等价于a%2==0,判断是否是偶数
     * @param a
     * @param b
     * @return
     */
    public int getGreatestCommonDivisor_3(int a, int b) {
        if (a == b) {
            return a;
        }

        if ((a & 1) == 0 && (b & 1) == 0) {
            return getGreatestCommonDivisor_3(a >> 1, b >> 1) << 1;
        }

        if ((a & 1) == 0 && (b & 1) != 0) {
            return getGreatestCommonDivisor_3(a >> 1, b);
        }

        if ((a & 1) != 0 && (b & 1) == 0) {
            return getGreatestCommonDivisor_3(a, b >> 1);
        }

        int big = a > b ? a : b;
        int small = a > b ? b : a;

        return getGreatestCommonDivisor_3(big - small, small);
    }
}
