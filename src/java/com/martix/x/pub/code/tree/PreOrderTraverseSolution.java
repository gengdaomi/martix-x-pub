package com.martix.x.pub.code.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Andrew-Geng on 5:32 下午 2021/4/9
 * 二叉树的前序遍历
 * lc 144
 */
public class PreOrderTraverseSolution {

    public List<Integer> result = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return result;
        }

        result.add(root.val);

        if (root.left != null) { //遍历左子树
            preorderTraversal(root.left);
        }
        if (root.right != null) {//遍历右子树
            preorderTraversal(root.right);
        }

        return result;
    }

    /**
     * 通过迭代 借用栈的方式处理
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal_1(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = root;

        while (!stack.isEmpty() || treeNode != null) {
            while (treeNode != null) {  //先遍历左子树
                result.add(treeNode.val);

                stack.add(treeNode);
                treeNode = treeNode.left;
            }

            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                treeNode = treeNode.right;
            }
        }

        return result;
    }
}
