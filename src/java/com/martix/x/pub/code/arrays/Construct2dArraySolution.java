package com.martix.x.pub.code.arrays;

/**
 * Created by Andrew-Geng on 22:57 2022/9/27
 * 将一维数组转变成二维数组 lc 2022
 *
 * 给你一个下标从 0开始的一维整数数组original和两个整数m和n。你需要使用original中所有元素创建一个m行n列的二维数组。
 *
 * original中下标从 0到 n - 1（都 包含 ）的元素构成二维数组的第一行，下标从 n到 2 * n - 1（都 包含）的元素构成二维数组的第二行，依此类推。
 *
 * 请你根据上述过程返回一个m x n的二维数组。如果无法构成这样的二维数组，请你返回一个空的二维数组。
 *
 * 输入：original = [1,2,3,4], m = 2, n = 2
 * 输出：[[1,2],[3,4]]
 * 解释：
 * 构造出的二维数组应该包含 2 行 2 列。
 * original 中第一个 n=2 的部分为 [1,2] ，构成二维数组的第一行。
 * original 中第二个 n=2 的部分为 [3,4] ，构成二维数组的第二行。
 *
 输入：original = [1,2,3], m = 1, n = 3
 输出：[[1,2,3]]
 解释：
 构造出的二维数组应该包含 1 行 3 列。
 将 original 中所有三个元素放入第一行中，构成要求的二维数组。
 */
public class Construct2dArraySolution {

    public static void main(String[] args){
        int[] arr = {3,2,4,5,7,1};
        int[][] result = new Construct2dArraySolution().construct2DArray(arr,3,2);
        System.out.println(result);
    }

    /**
     * 设original 的长度为k，根据题意，如果k≠mn则无法构成二维数组，此时返回空数组。否则我们可以遍历
    original，每n 个元素创建一个一维数组，放入二维数组中
     时间复杂度:O(mn) 或O(m)
     空间复杂度：O(1)
     * @param original
     * @param m
     * @param n
     * @return
     */
    public int[][] construct2DArray(int[] original, int m, int n) {
        if (original.length != m * n) {
            return new int[0][];
        }

        int[][] result = new int[m][n];
        for (int i = 0; i < original.length; i += n) {
            System.arraycopy(original, i, result[i / n], 0, n);
        }
        return result;
    }
}
