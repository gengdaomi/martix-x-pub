package com.martix.x.pub.tree;

import java.util.*;

/**
 * Created by Andrew-Geng on 12:45 上午 2021/7/4
 *  二叉树的右视图 lc 199
 *
 *  给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 示例:
 *
 * 输入:[1,2,3,null,5,null,4]
 * 输出:[1, 3, 4]
 * 解释:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 *
 */
public class RightSideViewSolution {

    /**
     * 广度优先搜索
     *
     * 核心思路：
     * 我们可以对二叉树进行层次遍历，那么对于每层来说，最右边的结点一定是最后被遍历到的。二叉树的层次遍历可以用广度优先搜索实现
     *
     * 时间空间复杂度  O(n)
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        Map<Integer, Integer> rightmostValueAtDepthMap = new HashMap<>();
        int maxDepth = -1;

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> depthQueue = new LinkedList<>();

        nodeQueue.add(root);
        depthQueue.add(0);

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();
            int depth = depthQueue.remove();

            if (node != null) {
                // 维护二叉树的最大深度
                maxDepth = Math.max(maxDepth, depth);

                // 由于每一层最后一个访问到的节点才是我们要的答案，因此不断更新对应深度的信息即可
                rightmostValueAtDepthMap.put(depth, node.val);

                nodeQueue.add(node.left);
                nodeQueue.add(node.right);
                depthQueue.add(depth + 1);
                depthQueue.add(depth + 1);
            }
        }

        List<Integer> rightViewList = new ArrayList<>();
        for (int depth = 0; depth <= maxDepth; depth++) {
            rightViewList.add(rightmostValueAtDepthMap.get(depth));
        }

        return rightViewList;
    }

    /**
     * 深度优先搜索
     *
     * 核心思路：
     * 我们对树进行深度优先搜索，在搜索过程中，我们总是先访问右子树。那么对于每一层来说，我们在这层见到的第一个结点一定是最右边的结点
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView_1(TreeNode root){
        Map<Integer, Integer> rightmostValueAtDepthMap = new HashMap<>();
        int maxDepth = -1;

        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> depthStack = new Stack<>();
        nodeStack.push(root);
        depthStack.push(0);

        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            int depth = depthStack.pop();

            if (node != null) {
                // 维护二叉树的最大深度
                maxDepth = Math.max(maxDepth, depth);

                // 如果不存在对应深度的节点我们才插入
                if (!rightmostValueAtDepthMap.containsKey(depth)) {
                    rightmostValueAtDepthMap.put(depth, node.val);
                }

                nodeStack.push(node.left);
                nodeStack.push(node.right);
                depthStack.push(depth + 1);
                depthStack.push(depth + 1);
            }
        }

        List<Integer> rightViewList = new ArrayList<>();
        for (int depth = 0; depth <= maxDepth; depth++) {
            rightViewList.add(rightmostValueAtDepthMap.get(depth));
        }

        return rightViewList;
    }
}
