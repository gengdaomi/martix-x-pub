package com.martix.x.pub.code.tree;

/**
 * Created by Andrew-Geng on 11:51 下午 2021/8/2
 * 左叶子之和 lc 404
 *
 * 计算给定二叉树的所有左叶子之和。
 *
 * 示例：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 *
 */
public class SumLeftLeavesSolution {

    /**
     *
     * 深度优先搜索
     *
     * 遍历到节点node 时，如果它的左子节点是一个叶子结点，那么就将它的左子节点的值累加计入答案。
     *
     * 时间空间复杂度O(n)
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        return root != null ? dfs(root) : 0;
    }

    public int dfs(TreeNode node) {
        int result = 0;

        if (node.left != null) {
            result += isLeafNode(node.left) ? node.left.val : dfs(node.left);
        }

        if (node.right != null && !isLeafNode(node.right)) {
            result += dfs(node.right);
        }

        return result;
    }

    public boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }

}
