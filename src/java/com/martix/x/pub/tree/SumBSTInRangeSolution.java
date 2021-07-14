package com.martix.x.pub.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Andrew-Geng on 1:14 上午 2021/4/27
 * 二叉搜索树的范围和 lc 938
 * <p>
 * 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
 * <p>
 * 输入：root = [10,5,15,3,7,null,18], low = 7, high = 15
 * 输出：32
 * <p>
 * 输入：root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
 * 输出：23
 */
public class SumBSTInRangeSolution {

    /**
     * 深度递归遍历 DFS
     *
     * @param root
     * @param low
     * @param high
     * @return
     */
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        if (root.val < low) {
            return rangeSumBST(root.right, low, high);
        }
        if (root.val > high) {
            return rangeSumBST(root.left, low, high);
        }

        int left = rangeSumBST(root.left, low, high);
        int right = rangeSumBST(root.right, low, high);

        return left + right + root.val;
    }

    /**
     * 广度遍历 BFS
     *
     * @param root
     * @param low
     * @param high
     * @return
     */
    public int rangeSumBST_1(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int sum = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();

                if (treeNode == null) {
                    continue;
                }

                if (treeNode.val > high) {
                    queue.offer(treeNode.left);
                } else if (treeNode.val < low) {
                    queue.offer(treeNode.right);
                } else {
                    sum += treeNode.val;
                    queue.offer(treeNode.left);
                    queue.offer(treeNode.right);
                }
            }
        }

        return sum;
    }
}
