package com.martix.x.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Andrew-Geng on 10:23 下午 2021/4/21
 * N 叉树的后序遍历 lc 590
 * <p>
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[5,6,3,2,4,1]
 * <p>
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[2,6,14,11,7,3,12,8,4,13,9,10,5,1]
 */
public class NPostOrderTraverseSolution {

    private List<Integer> result = new ArrayList<>();

    /**
     * 递归的方式
     *
     * @param root
     * @return
     */
    public List<Integer> postorder(Node root) {
        if (root == null) {
            return result;
        }

        for (Node node : root.children) {
            postorder(node);
        }

        result.add(root.val);

        return result;
    }

    public List<Integer> postorder_1(Node root){
        LinkedList<Node> stack = new LinkedList<>();
        LinkedList<Integer> result = new LinkedList<>();

        if(root==null){
            return result;
        }

        stack.add(root); //入栈

        while (!stack.isEmpty()){
            Node node = stack.pollLast();

            for (Node item : node.children) {
                stack.add(item);
            }

            result.addFirst(node.val);
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
