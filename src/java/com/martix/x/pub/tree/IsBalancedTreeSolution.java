package com.martix.x.pub.tree;

/**
 * Created by Andrew-Geng on 12:18 上午 2021/5/18
 * 平衡二叉树 lc 110
 * <p>
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * <p>
 * 本题中，一棵高度平衡二叉树定义为：
 * <p>
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1
 */
public class IsBalancedTreeSolution {

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        int left = depth(root.left);
        int right = depth(root.right);

        return Math.abs(left - right) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    /**
     * 获取树的高度
     *
     * @param root
     * @return
     */
    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(depth(root.left), depth(root.right)) + 1;
    }
}
