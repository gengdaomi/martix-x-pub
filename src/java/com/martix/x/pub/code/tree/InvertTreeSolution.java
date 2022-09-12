package com.martix.x.pub.code.tree;

import java.util.LinkedList;

/**
 * Created by Andrew-Geng on 2:11 下午 2021/7/26
 *
 * 翻转二叉树 lc 226
 *
 * 翻转一棵二叉树。
 *
 * 示例：
 *
 * 输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 */
public class InvertTreeSolution {

    /**
     * 递归
     *
     * 从根节点开始，递归地对树进行遍历，并从叶子节点先开始翻转。如果当前遍历到的节点
     * root 的左右两棵子树都已经翻转，那么我们只需要交换两棵子树的位置，即可完成以
     * root 为根节点的整棵子树的翻转
     *
     * 时间空间复杂度O(n)
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        root.left = right;
        root.right = left;

        return root;
    }

    /**
     * 迭代
     *
     * @param root
     * @return
     */
    public TreeNode invertTree_1(TreeNode root) {
        if(root==null) {
            return null;
        }

        //将二叉树中的节点逐层放入队列中，再迭代处理队列中的元素
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        while(!queue.isEmpty()) {
            //每次都从队列中拿一个节点，并交换这个节点的左右子树
            TreeNode node = queue.poll();
            TreeNode left = node.left;

            node.left = node.right;
            node.right = left;

            //如果当前节点的左子树不为空，则放入队列等待后续处理
            if(node.left!=null) {
                queue.add(node.left);
            }

            //如果当前节点的右子树不为空，则放入队列等待后续处理
            if(node.right!=null) {
                queue.add(node.right);
            }
        }

        return root;
    }
}
