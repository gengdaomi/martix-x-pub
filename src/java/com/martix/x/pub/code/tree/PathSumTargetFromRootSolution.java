package com.martix.x.pub.code.tree;

import java.util.*;


/**
 * Created by Andrew-Geng on 12:12 上午 2021/5/16
 * <p>
 * 路径总和 II lc 113
 * <p>
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * <p>
 * 叶子节点 是指没有子节点的节点。
 * <p>
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：[[5,4,11,2],[5,8,4,5]]
 * <p>
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：[]
 */
public class PathSumTargetFromRootSolution {

    private List<List<Integer>> result = new LinkedList<>();
    private List<Integer> path = new LinkedList<>();

    /**
     * ，找到所有满足从「根节点」到某个「叶子节点」经过的路径上的节点之和等于目标和的路径。核心思想是对树进行一次遍历，在遍历时记录从根节点到当前节点的路径和，以防止重复计算。
     * 通过深度遍历的思路
     * <p>
     * 时间复杂度：O(N^2) 空间复杂度O(n)
     *
     * @param root
     * @param targetSum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        this.dfs(root, targetSum);

        return result;
    }

    private void dfs(TreeNode treeNode, int targetSum) {
        if (treeNode == null) {
            return;
        }

        path.add(treeNode.val);
        targetSum -= treeNode.val;

        if (treeNode.left == null && treeNode.right == null && targetSum == 0) {
            result.add(new ArrayList<>(path));
        }

        dfs(treeNode.left, targetSum);
        dfs(treeNode.right, targetSum);

        path.remove(path.size() - 1); //删除最后一个
    }

    List<List<Integer>> ret = new LinkedList<>();
    Map<TreeNode, TreeNode> map = new HashMap<>(); //key: 当前节点， value:父节点

    /**
     * 广度遍历
     *
     * 时间复杂度：O(N^2)
     * 空间复杂度; O(N)
     * @param root
     * @param targetSum
     * @return
     */
    public List<List<Integer>> pathSum_1(TreeNode root, int targetSum){
        if (root == null) {
            return ret;
        }

        Queue<TreeNode> queueNode = new LinkedList<>();
        Queue<Integer> queueSum = new LinkedList<>();
        queueNode.offer(root);
        queueSum.offer(0);

        while (!queueNode.isEmpty()) {
            TreeNode node = queueNode.poll();
            int rec = queueSum.poll() + node.val;

            if (node.left == null && node.right == null) {
                if (rec == targetSum) {
                    getPath(node);
                }
            } else {
                if (node.left != null) {
                    map.put(node.left, node);
                    queueNode.offer(node.left);
                    queueSum.offer(rec);
                }

                if (node.right != null) {
                    map.put(node.right, node);
                    queueNode.offer(node.right);
                    queueSum.offer(rec);
                }
            }
        }

        return ret;
    }

    public void getPath(TreeNode node) {
        List<Integer> temp = new LinkedList<>();
        while (node != null) {
            temp.add(node.val);
            node = map.get(node);
        }

        Collections.reverse(temp);
        ret.add(new LinkedList<>(temp));
    }

}
