package com.martix.x.pub.code.tree;

/**
 * Created by Andrew-Geng on 12:39 2022/12/9
 * 二叉搜索树中的搜索 lc700
 *
 * 给定二叉搜索树（BST）的根节点root和一个整数值val。
 *
 * 你需要在 BST 中找到节点值等于val的节点。 返回以该节点为根的子树。 如果节点不存在，则返回null。
 *
 输入：root = [4,2,7,1,3], val = 2
 输出：[2,1,3]
 */
public class SearchBSTSolution {

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        if (val == root.val) {
            return root;
        }

        return searchBST(val < root.val ? root.left : root.right, val);
    }

    public TreeNode searchBST_1(TreeNode root, int val) {
        while (root != null) {
            if (val == root.val) {
                return root;
            }

            root = val < root.val ? root.left : root.right;
        }

        return null;
    }
}
