package com.martix.x.pub.code.tree;

/**
 * Created by Andrew-Geng on 12:14 上午 2021/3/26
 * <p>
 * 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * 说明:叶子节点是指没有子节点的节点。
 * <p>
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最大深度 3
 * lc 104
 */
public class TreeMaxDepthSolution {

    private int result = 0;

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        result = Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;

        return result;
    }

    public static void main(String[] args) {
    }

}
