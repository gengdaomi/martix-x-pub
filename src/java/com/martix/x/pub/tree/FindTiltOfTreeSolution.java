package com.martix.x.pub.tree;

/**
 * Created by Andrew-Geng on 12:18 上午 2021/7/25
 * <p>
 * 二叉树的坡度 lc 563
 * <p>
 * 给定一个二叉树，计算 整个树 的坡度 。
 * <p>
 * 一个树的 节点的坡度 定义即为，该节点左子树的节点之和和右子树节点之和的 差的绝对值 。
 * 如果没有左子树的话，左子树的节点之和为 0 ；没有右子树的话也是一样。空结点的坡度是 0 。
 * 整个树 的坡度就是其所有节点的坡度之和。
 * <p>
 * demo:
 * 1    ->     1
 * 2   3       0   0
 * <p>
 * 输入：root = [1,2,3]
 * 输出：1
 * 解释：
 * 节点 2 的坡度：|0-0| = 0（没有子节点）
 * 节点 3 的坡度：|0-0| = 0（没有子节点）
 * 节点 1 的坡度：|2-3| = 1（左子树就是左子节点，所以和是 2 ；右子树就是右子节点，所以和是 3 ）
 * 坡度总和：0 + 0 + 1 = 1
 */
public class FindTiltOfTreeSolution {

    private int tilt = 0;

    /**
     * 递归
     *
     * 时间空间复杂度 O(n)
     * @param root
     * @return
     */
    public int findTilt(TreeNode root) {
        traverse(root);
        return tilt;
    }

    public int traverse(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = traverse(root.left);
        int right = traverse(root.right);

        tilt += Math.abs(left - right);

        return left + right + root.val;
    }

}
