package com.martix.x.pub.dymaic;

/**
 * Created by Andrew-Geng on 10:11 2022/9/10
 * 不同路径 lc 62
 * <p>
 * 一个机器人位于一个 m x n网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 * <p>
 * 输入：m = 3, n = 7
 * 输出：28
 * <p>
 * 输入：m = 3, n = 2
 * 输出：3
 * 解释：
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向下
 * <p>
 * 输入：m = 7, n = 3
 * 输出：28
 */
public class RobotUniquePathsSolution {

    /**
     * 我们用f(i,j) 表示从左上角走到(i,j) 的路径数量，其中i 和 j的范围分别是[0,m) 和 [0,n);
     * <p>
     * 由于我们每一步只能从向下或者向右移动一步，因此要想走到(i,j)，如果向下走一步，那么会从(i−1,j) 走过来；
     * 如果向右走一步，那么会从 (i,j−1) 走过来。因此我们可以写出动态规划转移方程:f(i,j)=f(i−1,j)+f(i,j−1)；
     * <p>
     * 需要注意的是，如果 i=0，那么 f(i−1,j) 并不是一个满足要求的状态，我们需要忽略这一项；
     * 同理，如果j=0，那么f(i,j−1) 并不是一个满足要求的状态，我们需要忽略这一项；
     * <p>
     * 初始条件为 f(0,0)=1，即从左上角走到左上角有一种方法。
     * 最终的答案即为f(m−1,n−1)。
     * <p>
     * 时间 空间复杂度O(mn)
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] f = new int[m][n];

        for (int i = 0; i < m; i++) {
            f[i][0] = 1;
        }

        for (int j = 0; j < n; j++) {
            f[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }

        return f[m - 1][n - 1];
    }

    /**
     * 组合数学
     * <p>
     * 从左上角到右下角的过程中，我们需要移动m+n−2 次，其中有 m−1 次向下移动， n−1 次向右移动。
     * 因此路径的总数，就等于从m+n−2 次移动中选择m−1 次向下移动的方案数;
     * <p>
     * 时间复杂度：O(m)。由于我们交换行列的值并不会对答案产生影响，因此我们总可以通过交换m 和 n使得m≤n，这样空间复杂度降低至O(min(m,n))。
     * 空间复杂度O(1)
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths_1(int m, int n) {
        long ans = 1;

        for (int x = n, y = 1; y < m; ++x, ++y) {
            ans = ans * x / y;
        }

        return (int) ans;
    }
}
