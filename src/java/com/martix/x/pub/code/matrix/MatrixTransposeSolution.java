package com.martix.x.pub.code.matrix;

/**
 * Created by Andrew-Geng on 16:00 2023/6/20
 * 867. 转置矩阵
 *
 * 给你一个二维整数数组 matrix， 返回 matrix 的 转置矩阵 。
 *
 * 矩阵的 转置 是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。
 *
 * 示例 1：
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[1,4,7],[2,5,8],[3,6,9]]
 * 示例 2：
 *
 * 输入：matrix = [[1,2,3],[4,5,6]]
 * 输出：[[1,4],[2,5],[3,6]]
 *
 */
public class MatrixTransposeSolution {

    /**
     *
     * 创建一个 n 行 m 列的新矩阵，根据转置的规则对新矩阵中的每个元素赋值，则新矩阵为转置后的矩阵。
     *
     * 时间复杂度O(mn) 其中m 和n 分别是矩阵  的行数和列数。需要遍历整个矩阵，并对转置后的矩阵进行赋值操作
     *
     * 空间复杂度O(1)
     * @param matrix
     * @return
     */
    public int[][] transpose(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        int[][] transposed = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }

        return transposed;
    }

}
