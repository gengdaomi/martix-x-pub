package com.martix.x.pub.tree;

/**
 * Created by Andrew-Geng on 12:30 上午 2021/4/8
 * 二叉树的最近公共祖先 lc 236
 * <p>
 * “对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出：3
 * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
 * <p>
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出：3
 * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
 * <p>
 * https://assets.leetcode.com/uploads/2018/12/14/binarytree.png
 */
public class FindLCASolution {

    /**
     * 核心：树的后序遍历
     * <p>
     * 祖先的定义： 若节点 p在节点 root的左（右）子树中，或 p=root ，则称 root 是 p 的祖先
     * <p>
     * <p>
     * 最近公共祖先的定义： 设节点 root 为节点 p,q 的某公共祖先，若其左子节点 root.left 和右子节点 root.right 都不是p,q 的公共祖先，
     * 则称 root 是 “最近的公共祖先” 。
     * <p>
     * p,q 的 最近公共祖先 ，则只可能为以下情况之一：
     * p 和 q 在 root 的子树中，且分列 root 的 异侧（即分别在左、右子树中）；
     * p=root ，且 q 在 root 的左或右子树中；
     * q=root ，且 p 在 root 的左或右子树中；
     * <p>
     * <p>
     * 时间复杂度 空间复杂度 都是O(N)
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if (root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        /*
        说明 root 的左 / 右子树中都不包含 p,q ，返回 null
         */
        if (left == null && right == null) {
            return null;
        }

        /*
        说明 p,q 分列在 root 的 异侧 （分别在 左 / 右子树），因此 root 为最近公共祖先，返回 root ；
         */
        if (left != null && right != null) {
            return root;
        }

        /*
        当 left 为空 ，right 不为空 ：p,q 都不在 root 的左子树中，直接返回 right，具体两种情况:
        1.p,q 其中一个在root 的 右子树 中，此时 right 指向p（假设为 p ）
        2.p,q 两节点都在 root 的 右子树 中，此时的 right 指向 最近公共祖先节点 ；

        反过来 left不为空，right为空，
         */
        return left == null ? right : left;
    }


    public TreeNode lowestCommonAncestor_1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor_1(root.left, p, q);
        TreeNode right = lowestCommonAncestor_1(root.right, p, q);

        if (left == null) {
            return right;
        }

        if (right == null) {
            return left;
        }

        return root;
    }

}
