package com.martix.x.pub.code.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Andrew-Geng on 12:21 2023/1/5
 * 剑指 Offer II 044. 二叉树每层的最大值
 *
 * 给定一棵二叉树的根节点root ，请找出该二叉树中每一层的最大值。
 *
 *
 * 示例1：
 *
 * 输入: root = [1,3,2,5,3,null,9]
 * 输出: [1,3,9]
 * 解释:
 *           1
 *          / \
 *         3   2
 *        / \   \
 *       5   3   9
 * 示例2：
 *
 * 输入: root = [1,2,3]
 * 输出: [1,3]
 * 解释:
 *           1
 *          / \
 *         2   3
 * 示例3：
 *
 * 输入: root = [1]
 * 输出: [1]
 * 示例4：
 *
 * 输入: root = [1,null,2]
 * 输出: [1,2]
 * 解释:
 *           1
 *            \
 *             2
 * 示例5：
 *
 * 输入: root = []
 * 输出: []
 *
 *
 */
public class MaxValuePerLeaveSolution {

    /**
     * 深度优先搜索遍历
     *
     * 我们用树的「先序遍历」来进行「深度优先搜索」处理，并用
     * curHeight 来标记遍历到的当前节点的高度。当遍历到
     * curHeight 高度的节点就判断是否更新该层节点的最大值
     *
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        if (root == null) {
            return new ArrayList<Integer>();
        }

        List<Integer> res = new ArrayList<Integer>();

        dfs(res, root, 0);
        return res;
    }

    private void dfs(List<Integer> res, TreeNode root, int curHeight) {
        if (curHeight == res.size()) {
            res.add(root.val);
        } else {
            res.set(curHeight, Math.max(res.get(curHeight), root.val));
        }

        if (root.left != null) {
            dfs(res, root.left, curHeight + 1);
        }

        if (root.right != null) {
            dfs(res, root.right, curHeight + 1);
        }
    }

    /**
     * 广度优先搜索遍历
     * @param root
     * @return
     */
    private List<Integer> largestValues_1(TreeNode root) {
        if (root == null) {
            return new ArrayList<Integer>();
        }

        List<Integer> res = new ArrayList<Integer>();
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();

        queue.offer(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            int maxVal = Integer.MIN_VALUE;

            while (len > 0) {
                len--;
                TreeNode t = queue.poll();
                maxVal = Math.max(maxVal, t.val);

                if (t.left != null) {
                    queue.offer(t.left);
                }

                if (t.right != null) {
                    queue.offer(t.right);
                }
            }

            res.add(maxVal);
        }

        return res;
    }

}
