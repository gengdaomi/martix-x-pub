package com.martix.x.pub.code.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Andrew-Geng on 4:05 下午 2021/4/21
 * 二叉树的后序遍历 lc 145
 * <p>
 * 给定一个二叉树，返回它的 后序 遍历。
 */
public class PostOrderTraverseSolution {
    public List<Integer> result = new ArrayList<>();

    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return result;
        }

        if (root.left != null) { //遍历左子树
            postorderTraversal(root.left);
        }
        if (root.right != null) {//遍历右子树
            postorderTraversal(root.right);
        }

        result.add(root.val);

        return result;
    }

    /**
     * 使用迭代的方式
     * 时间 空间复杂度O（N）
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal_1(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();

        TreeNode preNode = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {//处理所有左结点，进栈
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            if (root.right == null || root.right == preNode) {
                result.add(root.val);
                preNode = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }

        return result;
    }
}
