package com.martix.x.pub.code.tree;

/**
 * Created by Andrew-Geng on 12:03 上午 2021/4/9
 * 验证二叉搜索树 lc 98
 * <p>
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 */
public class ValidBSTSolution {

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode treeNode, TreeNode min, TreeNode max) {
        if (treeNode == null) {
            return true;
        }

        if (min != null && min.val >= treeNode.val) {
            return false;
        }

        if (max != null && max.val <= treeNode.val) {
            return false;
        }

        return isValidBST(treeNode.left, min, treeNode) && isValidBST(treeNode.right, treeNode, max);
    }
}
