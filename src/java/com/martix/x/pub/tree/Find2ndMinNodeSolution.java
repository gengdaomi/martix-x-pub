package com.martix.x.pub.tree;

/**
 * Created by Andrew-Geng on 12:15 上午 2021/7/27
 * 二叉树中第二小的节点 lc 671
 *
 * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为2或0。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。
 *
 * 更正式地说，root.val = min(root.left.val, root.right.val) 总成立。
 *
 * 给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
 *
 *   2
 * 2   5
 *    5  7
 *
 输入：root = [2,2,5,null,null,5,7]
 输出：5
 解释：最小的值是 2 ，第二小的值是 5 。

 输入：root = [2,2,2]
 输出：-1
 解释：最小的值是 2, 但是不存在第二小的值。
 */
public class Find2ndMinNodeSolution {

    private int result;
    private int rootValue;

    /**
     * 深度优先搜索
     *
     * 对于二叉树中的任意节点x，x 的值不大于以x 为根的子树中所有节点的值。
     * 令x 为二叉树的根节点，因此 二叉树根节点的值即为所有节点中的最小值。
     *
     * 因此，我们可以对整棵二叉树进行一次遍历。设根节点的值为rootvalue，我们只需要通过遍历，找出严格大于rootvalue 的最小值，即为「所有节点中的第二小的值」。
     *
     * 时间空间复杂度O(n)
     * @param root
     * @return
     */
    public int findSecondMinimumValue(TreeNode root) {
        result = -1;
        rootValue = root.val;
        dfs(root);
        return result;
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        if (result != -1 && node.val >= result) {
            return;
        }
        if (node.val > rootValue) {
            result = node.val;
        }

        dfs(node.left);
        dfs(node.right);
    }

}
