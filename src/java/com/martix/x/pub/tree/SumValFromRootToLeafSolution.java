package com.martix.x.pub.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Andrew-Geng on 12:27 上午 2021/6/1
 * 求根节点到叶节点数字之和 lc 129
 * <p>
 * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 * <p>
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 * <p>
 * 叶节点 是指没有子节点的节点。
 * <p>
 * 输入：root = [1,2,3]
 * 输出：25
 * 解释：
 * 从根到叶子节点路径 1->2 代表数字 12
 * 从根到叶子节点路径 1->3 代表数字 13
 * 因此，数字总和 = 12 + 13 = 25
 * <p>
 * 输入：root = [4,9,0,5,1]
 * 输出：1026
 * 解释：
 * 从根到叶子节点路径 4->9->5 代表数字 495
 * 从根到叶子节点路径 4->9->1 代表数字 491
 * 从根到叶子节点路径 4->0 代表数字 40
 * 因此，数字总和 = 495 + 491 + 40 = 1026
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/sum-root-to-leaf-numbers
 */
public class SumValFromRootToLeafSolution {

    /**
     * 深度遍历
     * 时间复杂度O(n)  空间复杂度O(n)
     *
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int prevSum) {
        if (root == null) {
            return 0;
        }

        int sum = prevSum * 10 + root.val;

        if (root.left == null && root.right == null) {
            return sum;
        } else {
            return dfs(root.left, sum) + dfs(root.right, sum);
        }
    }

    /**
     * 广度遍历
     * 时间复杂度O(n)  空间复杂度O(n)
     *
     * @param root
     * @return
     */
    public int sumNumbers_1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int sum = 0;

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> valQueue = new LinkedList<>();

        nodeQueue.offer(root);
        valQueue.offer(root.val);

        while (!nodeQueue.isEmpty()) {
            TreeNode treeNode = nodeQueue.poll();
            int val = valQueue.poll();

            TreeNode left = treeNode.left, right = treeNode.right;
            if (left == null && right == null) { //到达叶子节点
                 sum += val;
            }

            if (left != null) {
                nodeQueue.offer(left);
                valQueue.offer(val * 10 + left.val);
            }

            if (right != null) {
                nodeQueue.offer(right);
                valQueue.offer(val * 10 + right.val);
            }
        }

        return sum;
    }

}
