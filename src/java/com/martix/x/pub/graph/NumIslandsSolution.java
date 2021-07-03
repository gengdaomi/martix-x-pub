package com.martix.x.pub.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Andrew-Geng on 1:54 上午 2021/7/4
 * 岛屿数量
 * lc 200
 * <p>
 * 给你一个由'1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * <p>
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * <p>
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * 输出：1。
 */
public class NumIslandsSolution {

    /**
     * 深度优先搜索 dfs
     * <p>
     * 核心思路：
     * <p>
     * 可以将二维网格看成一个无向图，竖直或水平相邻的1 之间有边相连。
     * <p>
     * 为了求出岛屿的数量，我们可以扫描整个二维网格。如果一个位置为1，则以其为起始节点开始进行深度优先搜索。
     * 在深度优先搜索的过程中，每个搜索到的1 都会被重新标记为0
     * <p>
     * 最终岛屿的数量就是我们进行深度优先搜索的次数;
     * <p>
     * 时间复杂度: O(MN)，其中M 和N 分别为行数和列数
     * 空间复杂度：O(MN),在最坏情况下，整个网格均为陆地，深度优先搜索的深度达到MN
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;

        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {

                if (grid[r][c] == '1') {
                    ++num_islands;
                    dfs(grid, r, c);
                }
            }
        }

        return num_islands;
    }

    private void dfs(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;

        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return;
        }

        grid[r][c] = '0';
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    /**
     * 广度优先搜索 bfs
     * <p>
     * 核心：
     * <p>
     * 为了求出岛屿的数量，我们可以扫描整个二维网格。如果一个位置为1，
     * 则将其加入队列，开始进行广度优先搜索。在广度优先搜索的过程中，
     * 每个搜索到的1 都会被重新标记为0。直到队列为空，搜索结束
     * <p>
     * 最终岛屿的数量就是我们进行广度优先搜索的次数
     * <p>
     * 时间复杂度: O(MN)，其中M 和N 分别为行数和列数
     * 空间复杂度：O(min(M,N),在最坏情况下，整个网格均为陆地，深度优先搜索的深度达到Min(n,m)
     *
     * @param grid
     * @return
     */
    public int numIslands_1(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int numIslands = 0;

        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {

                if (grid[r][c] == '1') {
                    ++numIslands;
                    grid[r][c] = '0';

                    Queue<Integer> neighbors = new LinkedList<>();
                    neighbors.add(r * nc + c);

                    while (!neighbors.isEmpty()) {
                        int id = neighbors.remove();
                        int row = id / nc;
                        int col = id % nc;

                        if (row - 1 >= 0 && grid[row - 1][col] == '1') {
                            neighbors.add((row - 1) * nc + col);
                            grid[row - 1][col] = '0';
                        }

                        if (row + 1 < nr && grid[row + 1][col] == '1') {
                            neighbors.add((row + 1) * nc + col);
                            grid[row + 1][col] = '0';
                        }

                        if (col - 1 >= 0 && grid[row][col - 1] == '1') {
                            neighbors.add(row * nc + col - 1);
                            grid[row][col - 1] = '0';
                        }

                        if (col + 1 < nc && grid[row][col + 1] == '1') {
                            neighbors.add(row * nc + col + 1);
                            grid[row][col + 1] = '0';
                        }
                    }
                }
            }
        }

        return numIslands;
    }
}
