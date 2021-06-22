package com.martix.x.pub.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Andrew-Geng on 11:56 下午 2021/4/8
 * 二叉树的中序遍历 lc 94
 */
public class InOrderTraverseSolution {

    public List<Integer> result = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return result;
        }

        if (root.left != null) {
            inorderTraversal(root.left);
        }

        result.add(root.val);

        if (root.right != null) {
            inorderTraversal(root.right);
        }

        return result;
    }

    /**
     * 通过迭代 借用栈的方式处理
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal_1(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;

        while (!stack.isEmpty() || node != null) {
            while (node != null) {//处理所有左结点，进栈
                stack.push(node);
                node = node.left;
            }

            if (!stack.isEmpty()) {//执行到这里，栈顶元素没有左孩子或者左子树都被访问过
                node = stack.pop();
                result.add(node.val);
                node = node.right;
            }
        }

        return result;
    }

}
