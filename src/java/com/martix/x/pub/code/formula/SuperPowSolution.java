package com.martix.x.pub.code.formula;
/**
 * Created by Andrew-Geng on 1:18 上午 2021/4/17
 * 超级次方 lc 372
 * <p>
 * 你的任务是计算ab对1337 取模，a 是一个正整数，b 是一个非常大的正整数且会以数组形式给出。
 * <p>
 * 示例 1：
 * <p>
 * 输入：a = 2, b = [3]
 * 输出：8
 * 示例 2：
 * <p>
 * 输入：a = 2, b = [1,0]
 * 输出：1024
 * 示例 3：
 * <p>
 * 输入：a = 1, b = [4,3,3,8,5,2]
 * 输出：1
 * 示例 4：
 * <p>
 * 输入：a = 2147483647, b = [2,0,0]
 * 输出：1198
 */
public class SuperPowSolution {

    public static void main(String[] args) {

    }

    /**
     * 结合递归的方式处理
     * <p>
     * (a*b)%k = (a%k)*(b%k)%k
     *
     * @param a
     * @param b
     * @return
     */
    public int superPow(int a, int[] b) {
        int n = b.length;

        if (n == 0) {
            return 1;
        }

        int[] copy = new int[n - 1]; //每次少一位，即最后一位去掉
        for (int i = 0; i < n - 1; i++) {
            copy[i] = b[i];
        }


        int part_1 = myPow_1(a, b[n - 1]);
        int part_2 = myPow_1(superPow(a, copy), 10);

        return (part_1 * part_2) % 1337;
    }

    int modVal = 1337;

    private int myPow_1(int a, int k) {
        if (k == 0) {
            return 1;
        }

        a %= modVal;

        if (k % 2 == 1) { //奇数
            return (a * myPow_1(a, k - 1)) % modVal;
        } else {
            int sub = myPow_1(a, k / 2);
            return (sub * sub) % modVal;
        }
    }
}
