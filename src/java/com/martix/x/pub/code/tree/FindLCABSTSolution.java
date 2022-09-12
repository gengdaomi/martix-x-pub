package com.martix.x.pub.code.tree;

/**
 * Created by Andrew-Geng on 1:30 上午 2021/4/8
 * 二叉搜索树的最近公共祖先 lc 235
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先
 * <p>
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
 *
 * <p>
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * 输出: 2
 * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 */
public class FindLCABSTSolution {

    /**
     * 相比lc 236来说，该题的特点是二叉搜索树，即左子树的所有节点都小于当前节点，右子树的所有节点都大于当前节点，并且每棵子树都具有上述特点
     * <p>
     * <p>
     * 如果两个节点值都小于根节点，说明他们都在根节点的左子树上，我们往左子树上找
     * 如果两个节点值都大于根节点，说明他们都在根节点的右子树上，我们往右子树上找
     * 如果一个节点值大于根节点，一个节点值小于根节点，说明他们他们一个在根节点的左子树上一个在根节点的右子树上，那么根节点就是他们的最近公共祖先节点
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        /*
        如果根节点和p,q的差相乘是正数，说明这两个差值要么都是正数要么都是负数，也就是说
        他们肯定都位于根节点的同一侧，就继续往下找
         */
        while ((root.val - p.val) * (root.val - q.val) > 0) {

            root = p.val < root.val ? root.left : root.right;
        }

        /*
          如果相乘的结果是负数，说明p和q位于根节点的两侧，如果等于0，说明至少有一个就是根节点
         */
        return root;
    }

    /**
     * 递归的方式
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor_1(TreeNode root, TreeNode p, TreeNode q) {
        if ((root.val - p.val) * (root.val - q.val) <= 0) { //相乘的结果是负数，说明p和q位于根节点的两侧，如果等于0，说明至少有一个就是根节点
            return root;
        }

        return lowestCommonAncestor_1(p.val < root.val ? root.left : root.right, p, q);
    }

    /**
     * TODO 当然可以选择通用的lc 236的通用二叉树的LCA解法，但是没有体现二叉搜索树的特性
     *
     * FindLCASolution
     */


}
