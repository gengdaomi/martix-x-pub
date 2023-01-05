package com.martix.x.pub.code.dymaic;

/**
 * Created by Andrew-Geng on 16:03 2023/1/5
 *
 * 剑指 Offer 47. 礼物的最大价值  最大路径和
 *
 * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
 * 你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。
 * 给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 *
 *  
 *
 * 示例 1:
 *
 * 输入:
 * [
 *  [1,3,1],
 *  [1,5,1],
 *  [4,2,1]
 * ]
 * 输出: 12
 * 解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
 *
 */
public class GridPathSumMaxSolution {

    public int maxValue(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(i == 0 && j == 0){
                    continue;
                }
                if(i == 0){
                    grid[i][j] += grid[i][j - 1] ;
                } else if(j == 0){
                    grid[i][j] += grid[i - 1][j];
                } else{
                    grid[i][j] += Math.max(grid[i][j - 1], grid[i - 1][j]);
                }
            }
        }

        return grid[m - 1][n - 1];
    }

    /**
     * 优化版
     *
     * 当grid 矩阵很大时，i=0 或j=0 的情况仅占极少数，相当循环每轮都冗余了一次判断。
     * 因此，可先初始化矩阵第一行和第一列，再开始遍历递推。
     *
     * @param grid
     * @return
     */
    public int maxValue_1(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        for(int j = 1; j < n; j++) {// 初始化第一行
            grid[0][j] += grid[0][j - 1];
        }
        for(int i = 1; i < m; i++){// 初始化第一列
            grid[i][0] += grid[i - 1][0];
        }

        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++) {
                grid[i][j] += Math.max(grid[i][j - 1], grid[i - 1][j]);
            }
        }

        return grid[m - 1][n - 1];
    }

}
