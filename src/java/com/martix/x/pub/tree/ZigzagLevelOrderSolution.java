package com.martix.x.pub.tree;

import java.util.*;

/**
 * Created by Andrew-Geng on 1:22 上午 2021/4/22
 * 二叉树的锯齿形层序遍历 lc 103
 * <p>
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * <p>
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回锯齿形层序遍历如下：
 * <p>
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 */
public class ZigzagLevelOrderSolution {

    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return result;
        }

        boolean isLeftOrder = true; //判断是否从左到右的遍历,第一层根节点层初始化为从左向右
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

            if (!isLeftOrder) {
                Collections.reverse(levelList);
            }
            result.add(levelList);

            isLeftOrder = !isLeftOrder; //颠倒下一层遍历的方向
        }

        return result;
    }
}
