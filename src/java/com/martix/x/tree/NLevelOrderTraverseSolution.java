package com.martix.x.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Andrew-Geng on 9:36 下午 2021/4/21
 * <p>
 * N 叉树的层序遍历 lc 429
 * <p>
 * 给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
 * <p>
 * 树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
 * <p>
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[[1],[3,2,4],[5,6]]
 * <p>
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
 */
public class NLevelOrderTraverseSolution {

    private List<List<Integer>> result = new ArrayList<>();

    /**
     * 借助队列的先进先出的思路处理
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) {
            return result;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> levelList = new ArrayList<>();
            int curLevelSize = queue.size();

            for (int i = 0; i < curLevelSize; i++) {
                Node node = queue.poll();
                levelList.add(node.val);

                if (node.children != null) {
                    for (Node sub : node.children) {
                        queue.offer(sub);
                    }
                }
            }
            result.add(levelList);
        }
        return result;
    }

    /**
     * N叉树节点
     */
    private class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
