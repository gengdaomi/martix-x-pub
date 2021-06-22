package com.martix.x.pub.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew-Geng on 12:25 上午 2021/4/9
 * 二叉搜索树中的众数 lc 501
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 * <p>
 * 假定 BST 有如下定义：
 * <p>
 * 结点左子树中所含结点的值小于等于当前结点的值
 * 结点右子树中所含结点的值大于等于当前结点的值
 * 左子树和右子树都是二叉搜索树
 * <p>
 * <p>
 * 核心：中序遍历，对于二叉搜索树来说就是有序的
 * <p>
 * <p>
 * TODO 优化版的还没有搞，即使用Mirror 中序遍历的方式  ，这种可以保障空间复杂度是O(1)
 */
public class FindModeSolution {

    private int count = 0, maxCount = 0;
    private TreeNode preNode;
    private List<Integer> result = new ArrayList<>();

    public int[] findMode(TreeNode root) {
        searchBST(root);

        int[] arr = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            arr[i] = result.get(i);
        }

        return arr;
    }

    /**
     * 中序遍历的方式: 中序遍历的时候，一定先遍历左子树，然后遍历当前节点，最后遍历右子树。
     * 时间 空间复杂度都是O(N)
     * <p>
     * 核心：前置节点preNode为空的时候，表示当前节点的数仅出现一次，
     * 前置节点preNode和当前节点的值相等，则递增
     * 不相等 则重新计数为1
     *
     * @param node
     */
    private void searchBST(TreeNode node) {
        if (node == null) {
            return;
        }

        searchBST(node.left);

        if (preNode == null) {
            count = 1;
        } else if (preNode.val == node.val) {
            count++;
        } else {
            count = 1;
        }
        preNode = node; //更新上一个节点

        if (count == maxCount) {
            result.add(node.val);
        }

        if (count > maxCount) {
            maxCount = count;
            result.clear();
            result.add(node.val);
        }

        searchBST(node.right);

        return;
    }

}
