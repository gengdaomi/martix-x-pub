package com.martix.x.pub.arrays;

/**
 * Created by Andrew-Geng on 1:16 上午 2021/5/8
 * 将有序数组转换为二叉搜索树
 * lc 108
 * <p>
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
 * <p>
 * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
 * <p>
 * 输入：nums = [-10,-3,0,5,9]
 * 输出：[0,-3,9,-10,null,5]
 * 解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
 * <p>
 * 输入：nums = [1,3]
 * 输出：[3,1]
 * 解释：[1,3] 和 [3,1] 都是高度平衡二叉搜索树。
 */
public class SortedArrayToBSTSolution {

    /**
     * BST的中序遍历是升序的，因此本题等同于根据中序遍历的序列恢复二叉搜索树。
     * 因此我们可以以升序序列中的任一个元素作为根节点，以该元素左边的升序序列构建左子树，
     * 以该元素右边的升序序列构建右子树，这样得到的树就是一棵二叉搜索树啦～
     * 又因为本题要求高度平衡，因此我们需要选择升序序列的中间元素作为根节点奥
     *
     * 时间复杂度：O(n)
     * 空间复杂度：(logn)，空间复杂度主要取决于递归栈的深度
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return this.dfs(nums, 0, nums.length - 1);
    }

    private TreeNode dfs(int[] nums, int low, int high) {
        if (low > high) {
            return null;
        }

        int mid = low + (high - low) / 2;
        TreeNode treeNode = new TreeNode(nums[mid]);
        treeNode.left = dfs(nums, low, mid - 1);
        treeNode.right = dfs(nums, mid + 1, high);

        return treeNode;
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
