package com.martix.x.pub.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Andrew-Geng on 1:19 上午 2021/7/13
 * <p>
 * 省份数量 lc 547
 * <p>
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * <p>
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * 返回矩阵中 省份 的数量。
 */
public class FindProvinceNumSolution {

    /**
     * 可以把n 个城市和它们之间的相连关系看成图，城市是图中的节点，相连关系是图中的边，给定的矩阵isConnected 即为图的邻接矩阵，省份即为图中的连通分量
     * <p>
     * 核心：
     * 深度优先搜索
     * <p>
     * 深度优先搜索的思路是很直观的。遍历所有城市，对于每个城市，如果该城市尚未被访问过，则从该城市开始深度优先搜索，
     * 通过矩阵isConnected 得到与该城市直接相连的城市有哪些，这些城市和该城市属于同一个连通分量，
     * 然后对这些城市继续深度优先搜索，直到同一个连通分量的所有城市都被访问到，即可得到一个省份。
     * 遍历完全部城市以后，即可得到连通分量的总数，即省份的总数。
     * <p>
     * 时间复杂度O(n^2);;;需要遍历矩阵n 中的每个元素。
     * 空间复杂度O(n);;;其中中n 是城市的数量。需要使用数组visited 记录每个城市是否被访问过，
     * 数组长度是n，递归调用栈的深度不会超过n；
     *
     * @param isConnected
     * @return
     */
    public int findCircleNum(int[][] isConnected) {
        int provinces = isConnected.length;
        boolean[] visited = new boolean[provinces];

        int circles = 0;

        for (int i = 0; i < provinces; i++) {
            if (!visited[i]) {
                dfs(isConnected, visited, provinces, i);
                circles++;
            }
        }

        return circles;
    }

    public void dfs(int[][] isConnected, boolean[] visited, int provinces, int i) {
        for (int j = 0; j < provinces; j++) {
            if (isConnected[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(isConnected, visited, provinces, j);
            }
        }
    }

    /**
     * 广度优先遍历
     * @param isConnected
     * @return
     */
    public int findCircleNum_1(int[][] isConnected) {
        int provinces = isConnected.length;
        boolean[] visited = new boolean[provinces];
        int circles = 0;

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < provinces; i++) {
            if (!visited[i]) {
                queue.offer(i);

                while (!queue.isEmpty()) {
                    int j = queue.poll();
                    visited[j] = true;

                    for (int k = 0; k < provinces; k++) {
                        if (isConnected[j][k] == 1 && !visited[k]) {
                            queue.offer(k);
                        }
                    }
                }

                circles++;
            }
        }

        return circles;
    }


}
