package com.martix.x.pub.code.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Andrew-Geng on 18:16 2022/12/19
 * 奇偶树 lc 1609
 *
 * 如果一棵二叉树满足下述几个条件，则可以称为 奇偶树 ：
 *
 * 二叉树根节点所在层下标为 0 ，根的子节点所在层下标为 1 ，根的孙节点所在层下标为 2 ，依此类推。
 * 偶数下标 层上的所有节点的值都是 奇 整数，从左到右按顺序 严格递增
 * 奇数下标 层上的所有节点的值都是 偶 整数，从左到右按顺序 严格递减
 * 给你二叉树的根节点，如果二叉树为 奇偶树 ，则返回 true ，否则返回 false 。
 *
 *               1
 *             /   \
 *            10    4
 *           /     / \
 *          3     7   9
 *         / \   /     \
 *        12  8  6      2
 *
 * 输入：root = [1,10,4,3,null,7,9,12,8,6,null,null,2]
 * 输出：true
 * 解释：每一层的节点值分别是：
 * 0 层：[1]
 * 1 层：[10,4]
 * 2 层：[3,7,9]
 * 3 层：[12,8,6,2]
 * 由于 0 层和 2 层上的节点值都是奇数且严格递增，而 1 层和 3 层上的节点值都是偶数且严格递减，因此这是一棵奇偶树。
 *
 *
 */
public class EvenOddTreeValidateSolution {

    /**
     * 层序遍历
     *
     * 时间复杂度 空间复杂度 O(n)
     * @param root
     * @return
     */
    public boolean isEvenOddTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int prev = level % 2 == 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                int value = node.val;

                if (level % 2 == value % 2) {
                    return false;
                }

                if ((level % 2 == 0 && value <= prev) || (level % 2 == 1 && value >= prev)) {
                    return false;
                }

                prev = value;
                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            level++;
        }

        return true;
    }

    /**
     * 另一种层序遍历
     * @param root
     * @return
     */
    public boolean isEvenOddTree_1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        boolean even = true;// 初始为偶数层
        while(!queue.isEmpty()) {
            int size = queue.size();
            int last = even ? -1 : 1000001; // 上一个节点的值，针对偶数层和奇数层设置不一样的初始值

            while (size-- > 0) {// 遍历一层
                TreeNode node = queue.poll();

                if (!check(even, node.val, last)) {
                    return false;
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }

                last = node.val;
            }

            even = !even;
        }

        return true;
    }

    private boolean check(boolean even, int val, int last) {
        // 偶数层：全部为奇数且严格递增
        // 奇数层：全部为偶数且严格递减
        return even ? val % 2 == 1 && last < val : val % 2 == 0 && last > val;
    }
}
