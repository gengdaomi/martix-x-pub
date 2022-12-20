package com.martix.x.pub.code.dymaic;

/**
 * Created by Andrew-Geng on 21:02 2022/12/20
 * 比特位计数 lc338
 *
 * 给你一个整数 n ，对于0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
 *
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：[0,1,1]
 * 解释：
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 示例 2：
 *
 * 输入：n = 5
 * 输出：[0,1,1,2,1,2]
 * 解释：
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 3 --> 11
 * 4 --> 100
 * 5 --> 101
 *
 *
 */
public class CountBitsSolution {

    /**
     * 动态规划--最低设置位
     *
     * 定义正整数x 的「最低设置位」为x 的二进制表示中的最低的1 所在位。
     * 例如， 10 的二进制表示是1010(2)，其最低设置位为2;
     * 令y=x&(x−1)，则y 为将x 的最低设置位从1 变成 0 之后的数，显然0≤y<x，bits[x]=bits[y]+1。
     * 因此对任意正整数x，都有bits[x]=bits[x&(x−1)]+1。
     * 遍历从1 到n 的每个正整数 i，计算bits 的值。最终得到的数组bits 即为答案
     *
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     * @param n
     * @return
     */
    public int[] countBits(int n) {
        int[] bits = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            bits[i] = bits[i & (i - 1)] + 1;
        }

        return bits;
    }


    /**
     * 对从0 到n的每个整数直接计算「一比特数」。每个int 型的数都可以用32 位二进制数表示，
     * 只要遍历其二进制表示的每一位即可得到1 的数目；
     *
     * 利用 Brian Kernighan 算法，可以在一定程度上进一步提升计算速度。
     * 对于任意整数x，令x=x&(x−1)，该运算将x 的二进制表示的最后一个1 变成0。
     * 因此，对x 重复该操作，直到x 变成0，则操作次数即为x 的「一比特数」。
     *
     * 时间复杂度O(nlogn)
     * 空间复杂度O(1)
     * @param n
     * @return
     */
    public int[] countBits_1(int n) {
        int[] bits = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            bits[i] = countOnes(i);
        }
        return bits;
    }

    public int countOnes(int x) {
        int ones = 0;
        while (x > 0) {
            x &= (x - 1);
            ones++;
        }
        return ones;
    }
}
