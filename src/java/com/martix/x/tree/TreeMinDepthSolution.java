package com.martix.x.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by Andrew-Geng on 8:54 下午 2021/4/21
 * 二叉树的最小深度 lc111
 * <p>
 * 给定一个二叉树，找出其最小深度。
 * <p>
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * <p>
 * 说明：叶子节点是指没有子节点的节点。
 * <p>
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：2
 * 示例 2：
 * <p>
 * 输入：root = [2,null,3,null,4,null,5,null,6]
 * 输出：5
 */
public class TreeMinDepthSolution {

    /**
     * 分左右子树递归，主要是防止树的结构只是某边
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }

        int leftHeight = minDepth(root.left);
        int rightHeight = minDepth(root.right);

        if (leftHeight == 0) { //当左边子树高度为0的时候，只能算右子树的高度
            return rightHeight + 1;
        }
        if (rightHeight == 0) { //当右子树高度为0的时候，只能算左子树的高度
            return leftHeight + 1;
        }

        return Math.min(leftHeight, rightHeight) + 1;
    }

    /**
     * 运用广度遍历的方式处理，
     * @param root
     * @return
     */
    public int minDepth_1(TreeNode root){
        if (root == null) {
            return 0;
        }

        int minDepth = 1;

        Queue<TreeNode> queue  =new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            int size = queue.size();

            for(int i=0;i<size;i++){
                TreeNode treeNode = queue.poll();

                if(treeNode.left==null && treeNode.right==null){
                    return minDepth;
                }

                if(treeNode.left!=null){
                    queue.offer(treeNode.left);
                }

                if(treeNode.right!=null){
                    queue.offer(treeNode.right);
                }
            }

            minDepth++;
        }

        return minDepth;
    }
}
