package com.martix.x.pub.code.tree;

/**
 * Created by Andrew-Geng on 11:47 下午 2021/4/8
 * <p>
 * 完全二叉树的节点个数 lc 222
 * <p>
 * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
 * <p>
 * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。
 * 若最底层为第 h 层，则该层包含 1~2h个节点。
 * <p>
 * 输入：root = [1,2,3,4,5,6]
 * 输出：6
 */
public class CountCompleteTreeNodeSolution {

    public int countNodes(TreeNode root) {
        TreeNode left = root, right = root;
        int leftCount = 0, rightCount = 0;

        while (left != null) {
            leftCount++;
            left = left.left;
        }

        while (right != null) {
            rightCount++;
            right = right.right;
        }

        if (rightCount == leftCount) { //左右高度相同，说明是一个满二叉树，count=2^h-1;   h=log2(count+1)
            return (int) Math.pow(2, leftCount) - 1;
        }

        //如果左右高度不一样，按照普通二叉树的逻辑算
        return countNodes(root.left) + countNodes(root.right) + 1;
    }
}
