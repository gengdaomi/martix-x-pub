package com.martix.x.pub.code.number;

/**
 * Created by Andrew-Geng on 1:07 上午 2021/7/4
 * 剑指 Offer 62. 圆圈中最后剩下的数字
 *
 *
 * 0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
 *
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
 *
 *  
 *
 * 示例 1：
 * 输入: n = 5, m = 3
 * 输出:3
 *
 * 示例 2：
 * 输入: n = 10, m = 17
 * 输出:2
 *
 *
 *
 *
 *参考  环形链表的约瑟夫问题
 */
public class LastRemainingSolution {

    /**
     * 数学 + 递归
     * 这个问题似乎有拆分为较小子问题的潜质：如果我们知道对于一个长度 n - 1 的序列，
     * 留下的是第几个元素，那么我们就可以由此计算出长度为 n 的序列的答案；
     *
     * 首先：
     * 长度为 n 的序列会先删除第 m % n 个元素，然后剩下一个长度为 n - 1 的序列；
     * 那么，我们可以递归地求解 f(n - 1, m)，就可以知道对于剩下的 n - 1 个元素，最终会留下第几个元素，我们设答案为 x = f(n - 1, m)。
     *
     * 由于我们删除了第 m % n 个元素，将序列的长度变为 n - 1。
     * 当我们知道了 f(n - 1, m) 对应的答案 x 之后，我们也就可以知道，长度为 n 的序列最后一个删除的元素，
     * 应当是从 m % n 开始数的第 x 个元素。因此有 f(n, m) = (m % n + x) % n = (m + x) % n
     *
     * 时间空间复杂度O(n)
     */
    public int lastRemaining(int n, int m) {
        return fun(n, m);
    }

    private int fun(int n, int m) {
        if (n == 1) {
            return 0;
        }

        int x = fun(n - 1, m);

        return (m + x) % n;
    }

    /**
     * 迭代的方式
     */
    public int lastRemaining_1(int n, int m) {
        int f = 0;

        for (int i = 2; i != n + 1; ++i) {
            f = (m + f) % i;
        }

        return f;
    }

}
