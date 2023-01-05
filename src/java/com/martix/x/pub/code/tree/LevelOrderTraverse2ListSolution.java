package com.martix.x.pub.code.tree;

import java.util.*;

/**
 * Created by Andrew-Geng on 9:06 下午 2021/4/21
 * 二叉树的层序遍历 lc 104
 * <p>
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 * <p>
 * <p>
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其层序遍历结果：
 * <p>
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 */
public class LevelOrderTraverse2ListSolution {

    private List<List<Integer>> result = new ArrayList<>();

    /**
     * 借用队列的先进先出思路
     *
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> levelList = new ArrayList<>();

            int curLevelSize = queue.size();
            for (int i = 0; i < curLevelSize; i++) {
                TreeNode treeNode = queue.poll();
                levelList.add(treeNode.val);

                if (treeNode.left != null) {
                    queue.offer(treeNode.left);
                }

                if (treeNode.right != null) {
                    queue.offer(treeNode.right);
                }
            }
            result.add(levelList);
        }

        return result;
    }

}
