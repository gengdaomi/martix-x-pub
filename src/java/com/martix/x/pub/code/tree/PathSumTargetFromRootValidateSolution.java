package com.martix.x.pub.code.tree;

/**
 * Created by Andrew-Geng on 11:58 下午 2021/5/15
 * 路径总和 lc 112
 * <p>
 * 给你二叉树的根节点root 和一个表示目标和的整数targetSum ，判断该树中是否存在根节点到叶子节点的路径，
 * 这条路径上所有节点值相加等于目标和targetSum 。
 * <p>
 * 叶子节点 是指没有子节点的节点。
 * <p>
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * 输出：true
 * <p>
 * 输入：root = [1,2], targetSum = 0
 * 输出：false
 */
public class PathSumTargetFromRootValidateSolution {

    /**
     * 递归的思路
     *
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) { //空树 则不存在路径和
            return false;
        }

        if (root.left == null && root.right == null) { //当只有根节点的时候，就是比较值与目标值
            if (root.val == targetSum) {
                return true;
            }

            return false;
        }

        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

}
