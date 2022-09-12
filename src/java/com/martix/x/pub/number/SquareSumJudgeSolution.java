package com.martix.x.pub.number;

/**
 * Created by Andrew-Geng on 10:31 2022/9/12
 * 平方数之和 lc 633
 *
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a^2 + b^2 = c 。
 *
 * 输入：c = 5
 * 输出：true
 * 解释：1 * 1 + 2 * 2 = 5
 *
 * 输入：c = 3
 * 输出：false
 */
public class SquareSumJudgeSolution {

    /**
     * 双指针
     * 不失一般性，可以假设a≤b；初始时 a=0，b=sqrt(c)
     *
     *  ，进行如下操作：
     * 如果a^2+b^2=c，我们找到了题目要求的一个解，返回true；
     * 如果a^2+b^2<c，此时需要将a 的值加1，继续查找；
     * 如果a^2+b^2  >c，此时需要将 b 的值减1，继续查找。
     *
     * 当 a=b 时，结束查找，此时如果仍然没有找到整数a 和 b 满足 a^2+b^2=c，则说明不存在题目要求的解，返回false
     *
     * 时间复杂度O(sqrt(c)),最坏情况下a和b一共枚举了0到sqrt(c)里面的所有整数
     * 空间复杂度O(1)
     *
     * @param c
     * @return
     */
    public boolean judgeSquareSum(int c) {
        long left = 0;
        long right = (long) Math.sqrt(c);

        while (left <= right) {

            long sum = left * left + right * right;

            if (sum == c) {
                return true;
            } else if (sum > c) {
                right--;
            } else {
                left++;
            }
        }

        return false;
    }

    /**
     * 使用开根函数sqrt
     *
     * 在枚举a 的同时，使用sqrt函数找到b；注意：本题c的q取值范围在[0,2^31 -1];因此有可能发生int溢出情况，使用long型解决；
     *
     *时间复杂度O(sqrt(c)),最坏情况下a和b一共枚举了0到sqrt(c)里面的所有整数
     *空间复杂度O(1)
     * @param c
     * @return
     */
    public boolean judgeSquareSum_1(int c) {
        for (long a = 0; a * a <= c; a++) {

            double b = Math.sqrt(c - a * a);
            if (b == (int) b) {
                return true;
            }
        }

        return false;
    }
}
