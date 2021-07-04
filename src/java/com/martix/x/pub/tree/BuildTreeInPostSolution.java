package com.martix.x.pub.tree;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Andrew-Geng on 1:50 上午 2021/5/31
 *
 * 从中序与后序遍历序列构造二叉树  lc 106
 *
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 中序遍历的顺序是每次遍历左孩子，再遍历根节点，最后遍历右孩子
 * 后序遍历的顺序是每次遍历左孩子，再遍历右孩子，最后遍历根节点
 */
public class BuildTreeInPostSolution {

    /**
     * 我们用一个栈和一个指针辅助进行二叉树的构造。初始时栈中存放了根节点（后序遍历的最后一个节点），指针指向中序遍历的最后一个节点；
     * 我们依次枚举后序遍历中除了第一个节点以外的每个节点。如果 index 恰好指向栈顶节点，那么我们不断地弹出栈顶节点并向左移动 index，并将当前节点作为最后一个弹出的节点的左儿子；
     * 如果 index 和栈顶节点不同，我们将当前节点作为栈顶节点的右儿子；
     * 无论是哪一种情况，我们最后都将当前的节点入栈
     *
     * 时间 空间复杂度 O(n)
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder){
        if (postorder == null || postorder.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);

        int inorderIndex = inorder.length - 1;
        for (int i = postorder.length - 2; i >= 0; i--) {
            int postorderVal = postorder[i];
            TreeNode node = stack.peek();

            if (node.val != inorder[inorderIndex]) {
                node.right = new TreeNode(postorderVal);
                stack.push(node.right);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex--;
                }

                node.left = new TreeNode(postorderVal);
                stack.push(node.left);
            }
        }

        return root;
    }

    int postIdx;
    int[] postorder;
    int[] inorder;
    Map<Integer, Integer> idxMap = new HashMap<Integer, Integer>(); //(元素，下标）键值对的哈希表

    /**
     * 时间 空间复杂度 O(n)
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree_1(int[] inorder, int[] postorder){
        this.postorder = postorder;
        this.inorder = inorder;
        // 从后序遍历的最后一个元素开始
        postIdx = postorder.length - 1;

        // 建立（元素，下标）键值对的哈希表
        int idx = 0;
        for (Integer val : inorder) {
            idxMap.put(val, idx++);
        }

        return this.build(0, inorder.length - 1);
    }

    private TreeNode build(int in_left, int in_right){
        // 如果这里没有节点构造二叉树了，就结束
        if (in_left > in_right) {
            return null;
        }

        // 选择 postIdx 位置的元素作为当前子树根节点
        int root_val = postorder[postIdx];
        TreeNode root = new TreeNode(root_val);

        // 根据 root 所在位置分成左右两棵子树
        int index = idxMap.get(root_val);

        // 下标减一
        postIdx--;
        // 构造右子树
        root.right = build(index + 1, in_right);
        // 构造左子树
        root.left = build(in_left, index - 1);

        return root;
    }

}
