package com.martix.x.pub.code.tree;

/**
 * Created by Andrew-Geng on 19:00 2022/12/23
 * 树的子结构 剑指 Offer 26.
 * <p>
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 * <p>
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 * <p>
 * 例如:
 * 给定的树 A:
 * <p>
 * 3
 * / \
 * 4   5
 * / \
 * 1  2
 * 给定的树 B：
 * <p>
 * 4
 * /
 * 1
 * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
 * <p>
 * 示例 1：
 * <p>
 * 输入：A = [1,2,3], B = [3,1]
 * 输出：false
 * 示例 2：
 * <p>
 * 输入：A = [3,4,5,1,2], B = [4,1]
 * 输出：true
 */
public class IsSubStructureSolution {

    /**
     * 遍历树A的每个节点，以每个节点为根节点判断是否存在以B为根节点的子树
     *
     * @param A
     * @param B
     * @return
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B == null || A == null) {
            return false;
        }
        if (A.val == B.val && (helper(A.left, B.left) && helper(A.right, B.right))) {
            return true;
        }
        return isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    /**
     * 判断树A以root1为根节点的子树 是否包含 树root2
     * <p>
     * 终止条件：
     * 当节点B 为空：说明树B 已匹配完成（越过叶子节点），因此返回true ；
     * 当节点A 为空：说明已经越过树A 叶子节点，即匹配失败，返回false ；
     * 当节点A 和B 的值不同：说明匹配失败，返回false ；
     * <p>
     * 返回值：
     * 判断A 和B 的左子节点是否相等，即 recur(A.left, B.left) ；
     * 判断A 和B 的右子节点是否相等，即 recur(A.right, B.right)
     *
     * @param root1
     * @param root2
     * @return
     */
    private boolean helper(TreeNode root1, TreeNode root2) {
        if (root2 == null) {
            return true;
        }
        if (root1 == null) {
            return false;
        }
        if (root1.val == root2.val) {
            return helper(root1.left, root2.left) && helper(root1.right, root2.right);
        } else {
            return false;
        }
    }
}
