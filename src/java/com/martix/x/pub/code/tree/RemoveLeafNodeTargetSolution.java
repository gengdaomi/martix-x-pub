package com.martix.x.pub.code.tree;

/**
 * Created by Andrew-Geng on 18:10 2022/12/19
 * <p>
 * 删除给定值的叶子节点 lc 1325
 * <p>
 * 给你一棵以root为根的二叉树和一个整数target，请你删除所有值为target 的叶子节点 。
 * <p>
 * 注意，一旦删除值为target的叶子节点，它的父节点就可能变成叶子节点；如果新叶子节点的值恰好也是target ，那么这个节点也应该被删除。
 * <p>
 * 也就是说，你需要重复此过程直到不能继续删除
 * <p>
 * <p>
 * 输入：root = [1,2,3,2,null,2,4], target = 2
 * 输出：[1,null,3,null,4]
 * 解释：
 * 上面左边的图中，绿色节点为叶子节点，且它们的值与 target 相同（同为 2 ），它们会被删除，得到中间的图。
 * 有一个新的节点变成了叶子节点且它的值与 target 相同，所以将再次进行删除，从而得到最右边的图。
 */
public class RemoveLeafNodeTargetSolution {

    /**
     * 方法：二叉树后序遍历（递归的核心思想--使用函数后可认为左右子树已经算出结果）
     * <p>
     * 基本思想：每次递归修改二叉树的数据（每次递归判断节点是否满足题目要求，进而修改节点的左右子树）
     * <p>
     * 具体过程：
     * 递归终止条件：当越过叶子节点时，直接返回null
     * 每次递归的任务：1）判断是否满足递归终止条件
     * 2）继续递归该节点的左子树，返回值赋给root.left
     * 3）继续递归该节点的右子树，返回值赋给root.right
     * 每次递归返回的值（两种情况）：
     * 1）当root的左右子树都为null时，说明该节点为叶子节点，继续判断该节点的值是否等于target，若相等返回null，不相等则返回该节点；
     * 2）当root的左右子树至少有一个不为null时，直接返回该接节点；
     *
     * @param root
     * @param target
     * @return
     */
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        // 递归终止条件
        if (root == null) {
            return null;
        }

        // 每次递归的任务
        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);

        // 递归返回值
        if (root.left == null && root.right == null && root.val == target) {
            return null;
        }

        return root;
    }
}
