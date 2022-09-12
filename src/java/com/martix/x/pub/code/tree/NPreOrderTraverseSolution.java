package com.martix.x.pub.code.tree;

import java.util.*;

/**
 * Created by Andrew-Geng on 9:49 下午 2021/4/21
 * N 叉树的前序遍历 lc 589
 * 给定一个 N 叉树，返回其节点值的 前序遍历 。
 * <p>
 * N 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
 * <p>
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[1,3,5,6,2,4]
 * <p>
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[1,2,3,6,7,11,14,4,8,12,5,9,13,10]
 */
public class NPreOrderTraverseSolution {

    private List<Integer> result = new ArrayList<>();

    /**
     * 递归的方式
     *
     * @param root
     * @return
     */
    public List<Integer> preorder(Node root) {
        if (root == null) {
            return result;
        }

        result.add(root.val);

        for (Node node : root.children) {
            preorder(node);
        }

        return result;
    }

    /**
     * 迭代的方式 借用栈
     * <p>
     * 使用一个栈来帮助我们得到前序遍历，需要保证栈顶的节点就是我们当前遍历到的节点。我们首先把根节点入栈，
     * 因为根节点是前序遍历中的第一个节点。随后每次我们从栈顶取出一个节点 u，它是我们当前遍历到的节点，并把 u 的所有子节点逆序推入栈中。
     * 例如 u 的子节点从左到右为 v1, v2, v3，那么推入栈的顺序应当为 v3, v2, v1，
     * 这样就保证了下一个遍历到的节点（即 u 的第一个子节点 v1）出现在栈顶的位置。
     *
     * @param root
     * @return
     */
    public List<Integer> preorder_1(Node root) {
        if (root == null) {
            return result;
        }

        LinkedList<Node> stack = new LinkedList<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            Node node = stack.pollLast(); //获取最后一个节点
            result.add(node.val);

            Collections.reverse(node.children);
            for (Node item : node.children) {
                stack.add(item);
            }
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
