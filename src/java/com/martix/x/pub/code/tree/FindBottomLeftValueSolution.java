package com.martix.x.pub.code.tree;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by Andrew-Geng on 12:15 2023/1/5
 * 剑指 Offer II 045. 二叉树最底层最左边的值
 *
 * 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
 *
 * 假设二叉树中至少有一个节点。
 *
 *     2
 *    / \
 *   3   4
 *  /
 *5
 *
 * 输出5
 */
public class FindBottomLeftValueSolution {
    private int curVal = 0;
    private int curHeight = 0;

    /**
     * 深度优先搜索
     *
     * 使用height 记录遍历到的节点的高度，curVal 记录高度在curHeight 的最左节点的值。
     * 在深度优先搜索时，我们先搜索当前节点的左子节点，再搜索当前节点的右子节点，然后判断当前节点的高度height 是否大于curHeight，
     * 如果是，那么将curVal 设置为当前结点的值，curHeight 设置为height。
     *
     * 因为我们先遍历左子树，然后再遍历右子树，所以对同一高度的所有节点，最左节点肯定是最先被遍历到的
     *
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     *
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        int curHeight = 0;
        dfs(root, 0);

        return curVal;
    }

    private void dfs(TreeNode root, int height) {
        if (root == null) {
            return;
        }

        height++;

        dfs(root.left, height);
        dfs(root.right, height);

        if (height > curHeight) {
            curHeight = height;
            curVal = root.val;
        }
    }

    /**
     * 广度优先搜索
     *
     * 用广度优先搜索遍历每一层的节点。
     * 在遍历一个节点时，需要先把它的非空右子节点放入队列，然后再把它的非空左子节点放入队列，这样才能保证从右到左遍历每一层的节点。
     * 广度优先搜索所遍历的最后一个节点的值就是最底层最左边节点的值
     *
     * @param root
     * @return
     */
    public int findBottomLeftValue_1(TreeNode root) {
        int result = 0;
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode p = queue.poll();

            if (p.right != null) {
                queue.offer(p.right);
            }

            if (p.left != null) {
                queue.offer(p.left);
            }

            result = p.val;
        }

        return result;
    }


}
