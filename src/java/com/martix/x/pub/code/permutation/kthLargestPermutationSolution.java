package com.martix.x.pub.code.permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ayue on 下午3:55 2018/7/6
 * <p>
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 * lc 60
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
 * 说明：
 * <p>
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 * <p>
 * 输入: n = 3, k = 3
 * 输出: "213"
 * <p>
 * 输入: n = 4, k = 9
 * 输出: "2314"
 */
public class kthLargestPermutationSolution {


    public static void main(String[] args) {
    }

    /**
     * 数学 + 缩小问题规模
     *
     * 时间复杂度O(n^2)  空间复杂度O(n)
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


    /**
     * 时间复杂度O(n^2)  空间复杂度O(n)
     *
     * @param n
     * @param k
     * @return
     */
    public String getPermutation_0(int n, int k) {
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

    //相当于因式分解
    public String getPermutation(int n, int k) {
        int[] factorial = new int[n];

        //因式分解需要的基数
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                factorial[i] = 1;
                continue;
            }
            factorial[i] = factorial[i - 1] * (i);
        }

        //1,1,2,6,24
        //1*0+1*1+2*2+6*3+24*4=119
        //而我们实际需要的数是：1、2、3、4、5，但他们的组合序列就相当于0、1、2、3、4的组合，只是各自加1而已。
        //二者的不同还在于，0-4的k的表是范围是从0-119，而我们的k是从1-120，所以变换关系是k-1。

        StringBuilder res = new StringBuilder();
        boolean[] used = new boolean[n];
        int i = n - 1;
        while (i >= 0) {
            int digit = (k - 1) / factorial[i];//变换关系k-1
            res.append(findKth(used, digit));//先取最高位的值
            k -= digit * factorial[i--];
        }

        return res.toString();
    }

    //再次强调下，数组是用的地址，而我们传递的对象就是普通的参数
    public int findKth(boolean[] used, int digit) {
        int res = -1;
        while (digit >= 0) {
            if (!used[++res]) { //从小到大的去取值，同时进行标记
                digit--;
            }
        }
        used[res] = true;
        return res + 1;//从0-4，变为1-5
    }
}
