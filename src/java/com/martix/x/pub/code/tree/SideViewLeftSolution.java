package com.martix.x.pub.code.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Andrew-Geng on 10:18 下午 2021/7/16
 * 二叉树的左视图
 */
public class SideViewLeftSolution {

    public void view(TreeNode root) {
        if (null == root) {
            return;
        }

        int level = 0;
        List<Integer> resultList = new ArrayList<>();
        leftView(root, resultList, level);

        System.out.println(resultList);
    }

    /**
     * 二叉树左视图
     * 打印每一层的第一个节点
     *
     * @param root
     * @param resultList
     * @param level
     */
    private void leftView(TreeNode root, List<Integer> resultList, int level) {
        if (null == root)
            return;
        if (level == resultList.size()){
            //判断是不是每一层的最后一个节点
            resultList.add(root.val);
        }

        leftView(root.left, resultList, level + 1);
        leftView(root.right, resultList, level + 1);
    }

    public List<Integer> leftView_1(TreeNode root) {
        List<Integer> result=  new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        //设置 size 和 child 两个标记，child在循环中会变，size不会变，作为循环条件
        //第一层只有根节点1个，所以size = 1
        int size = 1;
        //记录孩子数
        int child;

        while (!queue.isEmpty()) {
            //初始化孩子数为 0
            child = 0;

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                // i = 0,说明是该层第一个结点
                if (i == 0) {
                    result.add(node.val);
                }

                if (node.left != null) {
                    queue.offer(node.left);
                    child++;
                }

                if (node.right != null) {
                    queue.offer(node.right);
                    child++;
                }
            }

            size = child;
        }

        return result;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode r2 = new TreeNode(2);
        TreeNode r3 = new TreeNode(3);
        TreeNode r4 = new TreeNode(4);
        TreeNode r5 = new TreeNode(5);
        TreeNode r6 = new TreeNode(6);
        TreeNode r7 = new TreeNode(7);
        TreeNode r8 = new TreeNode(8);
        TreeNode r9 = new TreeNode(9);
        TreeNode r10 = new TreeNode(10);
        TreeNode r11 = new TreeNode(11);
        TreeNode r12 = new TreeNode(12);
        TreeNode r13 = new TreeNode(13);
        TreeNode r14 = new TreeNode(14);
        TreeNode r15 = new TreeNode(15);
        root.left = r2;
        root.right = r3;
        r2.right = r4;
        r3.left = r5;
        r3.right = r6;
        r5.left=r7;
        r5.right=r8;

       List<Integer> result =  new SideViewLeftSolution().leftView_1(root);
       System.out.println(result);
    }
}
