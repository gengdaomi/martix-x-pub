package com.martix.x.pub.dymaic;

/**
 * Created by Andrew-Geng on 10:27 2022/9/10
 * 不同路径 II lc63
 * <p>
 * 一个机器人位于一个m x n网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * 网格中的障碍物和空位置分别用 1 和 0 来表示
 *
 * 输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * 输出：2
 * 解释：3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 *
 */
public class RobotUniquePaths2Solution {

    /**
     * 用f(i,j)表示从坐标(0,0)到坐标(i,j)的路径总数，u(i,j)表示坐标(i,j)是否可行，如果坐标(i,j)有障碍物，u(i,j)=0,否则u(i,j)=1
     *
     * 因为机器人每次只能移动一步；以从坐标(0,0)到坐标(i,j)的路径总数的值只取决于从坐标(0,0)到(i-1,j)的路径总数和从坐标(0,0)到(i,j-1)的路径总数
     * 即f(i,j)只能通过f(i-1,j)和f(i,j-1)转移得到；当(i,j)有障碍的时候，任何路径到不了f(i,j),此时f(i,j)=0;
     *
     * 转移方程：f(i,j)=0,当u(i,j)=0
     *         f(i,j)=f(i-1,j)+f(i,j+1),当u(i,j)!=0
     *
     * 时间复杂度：O(nm)，其中n 为网格的行数，m 为网格的列数。我们只需要遍历所有网格一次即可;
     * 空间复杂度：O(m)。利用滚动数组优化，我们可以只用O(m) 大小的空间来记录当前行的f 值;
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length, m = obstacleGrid[0].length;
        int[] f = new int[m];

        f[0] = obstacleGrid[0][0] == 0 ? 1 : 0;

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (obstacleGrid[i][j] == 1) {
                    f[j] = 0;
                    continue;
                }
                if (j - 1 >= 0 && obstacleGrid[i][j - 1] == 0) {
                    f[j] += f[j - 1];
                }
            }
        }

        return f[m - 1];
    }
}
