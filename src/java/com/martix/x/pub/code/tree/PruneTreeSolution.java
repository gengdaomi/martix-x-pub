package com.martix.x.pub.code.tree;

/**
 * Created by Andrew-Geng on 18:03 2022/12/19
 *
 * 二叉树剪枝  lc814
 *
 * 给你二叉树的根结点root，此外树的每个结点的值要么是 0 ，要么是 1 。
 *
 * 返回移除了所有不包含 1 的子树的原二叉树。
 *
 * 节点 node 的子树为 node 本身加上所有 node 的后代。
 *
 * 1
 *  \
 *   0
 *  /  \
 *  0   1
 *
 *  转换为：
 *
 *   * 1
 *  *  \
 *  *   0
 *  *    \
 *  *      1
 *
 * 输入：root = [1,null,0,0,1]
 * 输出：[1,null,0,null,1]
 * 解释：
 * 只有红色节点满足条件“所有不包含 1 的子树”。 右图为返回的答案。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/binary-tree-pruning
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PruneTreeSolution {

    /**
     * 递归
     *
     * 首先确定边界条件，当输入为空时，即可返回空。然后对左子树和右子树分别递归进行pruneTree 操作。
     * 递归完成后，当这三个条件：左子树为空，右子树为空，当前节点的值为0，同时满足时，才表示以当前节点为根的原二叉树的所有节点都为0，需要将这棵子树移除，返回空。
     * 有任一条件不满足时，当前节点不应该移除，返回当前节点。
     *
     * 时间复杂度O(n), 空间复杂度O(n)
     * @param root
     * @return
     */
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);

        if (root.left == null && root.right == null && root.val == 0) {
            return null;
        }

        return root;
    }
}
