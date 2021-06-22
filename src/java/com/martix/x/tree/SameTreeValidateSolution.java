package com.martix.x.tree;

/**
 * Created by Andrew-Geng on 3:52 下午 2021/4/21
 * 相同的树 lc 100
 * <p>
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 * <p>
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的
 */
public class SameTreeValidateSolution {

    /**
     * 深度优先搜索
     * 时间复杂度：O(min(m,n))，其中
     * m 和 n 分别是两个二叉树的节点数
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        if (p.val != q.val) {
            return false;
        }

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
