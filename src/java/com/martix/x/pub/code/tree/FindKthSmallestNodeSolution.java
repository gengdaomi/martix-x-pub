package com.martix.x.pub.code.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Andrew-Geng on 11:05 上午 2021/7/17
 *
 * 二叉搜索树中第K小的元素 lc 230
 *
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 *
 * 输入：root = [3,1,4,null,2], k = 1
 * 输出：1
 */
public class FindKthSmallestNodeSolution {

    /**
     * 迭代 + 中序遍历
     *
     * 中序将树转换为一个递增的链表，然后找第k个最小的节点
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> inorder = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root;
        TreeNode temp;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.addFirst(curr);
                curr = curr.left;
            }

            temp = stack.removeFirst();
            inorder.add(temp.val);
            curr = temp.right;
        }

        return inorder.get(k - 1);
    }


    private int result;
    private int count;
    /**
     * 递归 + 中序遍历
     */
    public int kthSmallest_1(TreeNode root, int k) {
        inorder(root, k);
        return result;
    }

    public void inorder(TreeNode root, int k) {
        if (root == null) {
            return;
        }

        inorder(root.left, k);

        count++;
        if (count == k) {
            result = root.val;
            return;
        }

        inorder(root.right, k);
    }

}
