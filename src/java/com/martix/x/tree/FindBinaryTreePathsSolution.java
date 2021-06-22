package com.martix.x.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Andrew-Geng on 1:32 上午 2021/4/23
 * 二叉树的所有路径 lc 257
 * <p>
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * <p>
 * 1
 * /   \
 * 2     3
 * \
 * 5
 * <p>
 * 输出: ["1->2->5", "1->3"]
 * <p>
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 */
public class FindBinaryTreePathsSolution {

    private List<String> result = new ArrayList<>();

    /**
     * 时间/空间复杂度：
     * O(N^2)
     * 深度搜索遍历  递归的方式
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        dfs(root, "");
        return result;
    }

    private void dfs(TreeNode treeNode, String path) {
        if (treeNode == null) {
            return;
        }

        StringBuffer sb = new StringBuffer(path);
        sb.append(treeNode.val);

        if (treeNode.left == null && treeNode.right == null) {
            result.add(sb.toString());
        } else { //不是叶子节点继续遍历
            sb.append("->");
            dfs(treeNode.left, sb.toString());
            dfs(treeNode.right, sb.toString());
        }
    }

    /**
     * 广度遍历
     * @param root
     * @return
     */
    public List<String> binaryTreePaths_1(TreeNode root) {
        if (root == null) {
            return result;
        }

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<String> valQueue = new LinkedList<>();

        nodeQueue.offer(root);
        valQueue.offer(Integer.toString(root.val));

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            String val = valQueue.poll();

            if (node.left == null && node.right == null) {
                result.add(val);
            } else { //非叶子节点
                if (node.left != null) {
                    nodeQueue.offer(node.left);
                    valQueue.offer(val + "->" + node.left.val);
                }

                if (node.right != null) {
                    nodeQueue.offer(node.right);
                    valQueue.offer(val + "->" + node.right.val);
                }
            }
        }

        return result;
    }
    
}
