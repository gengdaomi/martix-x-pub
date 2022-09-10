package com.martix.x.pub.dymaic;

/**
 * Created by Andrew-Geng on 10:47 2022/9/10
 * <p>
 * 最小路径和 lc 64
 * <p>
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小；
 * 1  3  1
 * 1  5  1
 * 4  2  1
 * 说明：每次只能向下或者向右移动一步
 * <p>
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 * <p>
 * 输入：grid = [[1,2,3],[4,5,6]]
 * 输出：12
 */
public class MinPathSumSolution {

    /**
     * 由于路径的方向只能是向下或向右，因此网格的第一行的每个元素只能从左上角元素开始向右移动到达，网格的第一列的每个元素只能从左上角元素开始向下移动到达，此时的路径是唯一的，
     * 因此每个元素对应的最小路径和即为对应的路径上的数字总和;
     * <p>
     * 对于不在第一行和第一列的元素，可以从其上方相邻元素向下移动一步到达，或者从其左方相邻元素向右移动一步到达，元素对应的最小路径和等于其上方相邻元素与其左方相邻元素两者对应的最小路径和中的最小值加上当前元素的值。
     * 由于每个元素对应的最小路径和与其相邻元素对应的最小路径和有关，因此可以使用动态规划求解
     * <p>
     * 创建二维数组dp，与原始网格的大小相同，dp[i][j] 表示从左上角出发到 (i,j) 位置的最小路径和。
     * 显然，dp[0][0]=grid[0][0]。对于  dp 中的其余元素，通过以下状态转移方程计算元素值。
     * <p>
     * 当i>0 且j=0 时，dp[i][0]=dp[i−1][0]+grid[i][0]。
     * 当i=0 且j>0 时，dp[0][j]=dp[0][j−1]+grid[0][j]。
     * 当i>0 且j>0 时，dp[i][j]=min(dp[i−1][j],dp[i][j−1])+grid[i][j]。
     * <p>
     * 最后得到dp[m−1][n−1] 的值即为从网格左上角到网格右下角的最小路径和。
     * <p>
     * 时间复杂度：O(mn)，其中m 和 n分别是网格的行数和列数。需要对整个网格遍历一次，计算dp 的每个元素的值；
     * 空间复杂度：(mn)，其中m 和n 分别是网格的行数和列数。
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int rows = grid.length, columns = grid[0].length;
        int[][] dp = new int[rows][columns];
        dp[0][0] = grid[0][0];

        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        for (int j = 1; j < columns; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[rows - 1][columns - 1];
    }

    /**
     * 状态定义：设dp 为大小m×n 矩阵，其中 dp[i][j] 的值代表直到走到(i,j) 的最小路径和
     * <p>
     * 只能向右或向下走，换句话说，当前单元格 (i,j) 只能从左方单元格 (i−1,j) 或上方单元格 (i,j−1) 走到，因此只需要考虑矩阵左边界和上边界；
     * 走到当前单元格(i,j) 的最小路径和= “从左方单元格(i−1,j) 与 从上方单元格(i,j−1) 走来的 两个最小路径和中较小的 ”+当前单元格值[i][j]
     * <p>
     * 初始状态：dp 初始化即可，不需要修改初始0。
     * 返回值：返回dp 矩阵右下角值，即走到终点的最小路径和；
     * <p>
     * 时间复杂度 (M×N) ： 遍历整个 矩阵元素
     * 空间复杂度O(1) ： 直接修改原矩阵，不使用额外空间
     *
     * @param grid
     * @return
     */
    public int minPathSum_1(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 && j == 0) {
                    continue;
                } else if (i == 0) {
                    grid[i][j] = grid[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    grid[i][j] = grid[i - 1][j] + grid[i][j];
                } else {
                    grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
                }
            }
        }

        return grid[grid.length - 1][grid[0].length - 1];
    }
}
