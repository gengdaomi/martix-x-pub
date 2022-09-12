package com.martix.x.pub.code.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created By Andrew-Geng on 2020/11/9 11:34 下午
 * <p>
 * 给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。
 * <p>
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * <p>
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 * <p>
 * 输入：n = 3, k = 3
 * 输出："213"
 * <p>
 * 二、解题思路
 * <p>
 * k要先变成k-1，因为现在用的是下标值，是以0开头的，k–;
 * <p>
 * 用k对(n-1)!取商，结果为数据 余数为下一个数字
 * <p>
 * 比如n=4,这样排列出来的数据就有[1234,1243,1324,1342,1423,1432,2134,
 * 2143,2314,2341,2413,2431,3124...]
 * <p>
 * 第一轮：
 * 可以看到以1开头的有3*2*1 = 6种，同理2.3.4的都是这样
 * 这样8/6 = 1..2（商为1，余数为2）， 结果数据就是索引为1的2（第0个是1）
 * 然后把2从数组中移除
 * <p>
 * 第二轮
 * 这时候数据把2除外就有[134,143,314,341,413,431]
 * 可以看到以1开头的有2*1 = 2种，同理3.4的都是这样
 * 上一把余数是2，所以2/2 = 1..0，结果数据就是索引为1的3（第0个是1）
 * <p>
 * 第三轮
 * 这时候数据把2除外就有[14,41]
 * 可以看到以1开头的有1 =1种，同理4的都是这样
 * 上一把余数是0，所以0/1 = 0..1，结果数据就是索引为0的1（第0个是1）
 */
public class PermutationArraySolution {

    /**
     * 时间复杂度O(n^2) 空间复杂度O(n)
     *
     * @param n
     * @param k
     * @return
     */
    public String getPermutation_1(int n, int k) {
        int[] factorial = new int[n];
        factorial[0] = 1;

        for (int i = 1; i < n; ++i) {
            factorial[i] = factorial[i - 1] * i;
        }

        --k;

        StringBuffer ans = new StringBuffer();
        int[] valid = new int[n + 1];

        Arrays.fill(valid, 1);
        for (int i = 1; i <= n; ++i) {
            int order = k / factorial[n - i] + 1;

            for (int j = 1; j <= n; ++j) {
                order -= valid[j];

                if (order == 0) {
                    ans.append(j);
                    valid[j] = 0;

                    break;
                }
            }

            k %= factorial[n - i];
        }

        return ans.toString();
    }

    public String getPermutation(int n, int k) {
        if (n <= 1) {
            return "" + n;
        }

        List<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 1; i <= n; i++) {
            arrayList.add(i);
        }

        StringBuilder result = new StringBuilder();
        k--;

        while (n > 0) {
            //先求出n-1的阶乘
            int val = 1;
            for (int i = 1; i < n; i++) {
                val *= i;
            }

            int index = k / val;
            result.append(arrayList.get(index));
            arrayList.remove(index);

            k = k % val;
            n--;
        }

        return result.toString();
    }
}
