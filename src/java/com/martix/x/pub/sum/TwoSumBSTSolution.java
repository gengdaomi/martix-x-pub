package com.martix.x.pub.sum;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrew-Geng on 11:11 下午 2021/4/14
 * 两数之和 IV - 输入 BST
 * <p>
 * lc 653
 * <p>
 * 给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
 * <p>
 * 输入:
 * 5
 * / \
 * 3   6
 * / \   \
 * 2   4   7
 * <p>
 * Target = 9
 * <p>
 * 输出: True
 * <p>
 * 输入:
 * 5
 * / \
 * 3   6
 * / \   \
 * 2   4   7
 * <p>
 * Target = 28
 * <p>
 * 输出: False
 */
public class TwoSumBSTSolution {

    public List<Integer> result = new ArrayList<>();

    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = this.inorderTraversal(root);

        int left = 0, right = list.size() - 1;
        while (left < right) {
            int sum = list.get(left) + list.get(right);

            if (sum == k) {
                return true;
            }

            if (sum > k) {
                right--;
            }

            if (sum < k) {
                left++;
            }
        }

        return false;
    }

    /**
     * 二叉树的中序遍历 获取有序数组 BST 的中序遍历结果是按升序排列的
     * <p>
     * 使用两个指针
     * l 和
     * r 作为
     * <p>
     * list 的头部索引和尾部索引
     * <p>
     * 时间复杂度：
     * O(n)，其中
     * n 是树中节点的数量。本方法需要中序遍历整棵树
     * <p>
     * 空间复杂度：
     * O(n)，
     * list 中存储
     * n 个元素。
     *
     * @param root
     * @return
     */
    private List<Integer> inorderTraversal(TreeNode root) {
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
     * 通过hashSet的方式
     * <p>
     * 时间复杂度：
     * O(n)
     * <p>
     * 空间复杂度：
     * O(n)。最坏的情况下，
     * set 存储
     * n 个节点的值。
     *
     * @param root
     * @param k
     * @return
     */
    public boolean findTarget_1(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return find(root, k, set);
    }

    private boolean find(TreeNode root, int k, Set<Integer> set) {
        if (root == null) {
            return false;
        }

        if (set.contains(k - root.val)) {
            return true;
        }

        set.add(root.val); //主要是为了去重

        return find(root.left, k, set) || find(root.right, k, set);
    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
