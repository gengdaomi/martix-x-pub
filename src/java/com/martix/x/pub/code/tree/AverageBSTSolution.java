package com.martix.x.pub.code.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Andrew-Geng on 12:09 上午 2021/4/22
 * 二叉树的层平均值 lc 637
 */
public class AverageBSTSolution {

    /**
     * 广度优先搜索 时间复杂度：O(n)
     * <p>
     * 空间复杂度：O(n)，其中n 是二叉树中的节点个数。空间复杂度取决于队列开销，队列中的节点个数不会超过n
     *
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            long sum = 0;

            int curLevelSize = queue.size();
            for (int i = 0; i < curLevelSize; i++) {
                TreeNode treeNode = queue.poll();
                sum += 1l * treeNode.val;

                if (treeNode.left != null) {
                    queue.offer(treeNode.left);
                }

                if (treeNode.right != null) {
                    queue.offer(treeNode.right);
                }
            }
            result.add(1.0d * sum / curLevelSize);
        }
        return result;
    }

    private List<Integer> counts = new ArrayList<>();
    private List<Long> sums = new ArrayList<>();
    private List<Double> result = new ArrayList<>();
    /**
     * 深度搜索遍历
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)，
     * <p>
     * counts 用于存储二叉树的每一层的节点数，
     * sums
     * sums 用于存储二叉树的每一层的节点值之和
     *
     * @param root
     * @return
     */
    public List<Double> averageOfLevels_1(TreeNode root) {
        this.dfs(root, 0);

        for (int i = 0; i < sums.size(); i++) {
            result.add(1.0d * sums.get(i) / counts.get(i));
        }

        return result;
    }

    private void dfs(TreeNode treeNode, int level) {
        if (treeNode == null) {
            return ;
        }

        if (level < sums.size()) { //层数小于已经存入sums的数量，运用的思路是 层数是从0计数，所以level<=size-1
            counts.set(level, counts.get(level) + 1);
            sums.set(level, sums.get(level) + treeNode.val);
        } else {
            counts.add(1);
            sums.add(1l * treeNode.val);
        }

        dfs(treeNode.left, level + 1);
        dfs(treeNode.right, level + 1);
    }
}
