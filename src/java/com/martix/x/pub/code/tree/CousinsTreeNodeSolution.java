package com.martix.x.pub.code.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrew-Geng on 12:36 上午 2021/4/22
 * 二叉树的堂兄弟节点 lc 993
 * <p>
 * 在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
 * <p>
 * 如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。
 * <p>
 * 我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。
 * <p>
 * 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。
 * <p>
 * 输入：root = [1,2,3,4], x = 4, y = 3
 * 输出：false
 * <p>
 * 输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
 * 输出：true
 * <p>
 * 输入：root = [1,2,3,null,4], x = 2, y = 3
 * 输出：false
 */
public class CousinsTreeNodeSolution {

    private Map<Integer, Integer> heightMap = new HashMap<>(); //key->具体的节点值，value->节点所在高度
    private Map<Integer, TreeNode> parentMap = new HashMap<>();//key->具体的节点值，value->节点的父节点

    public boolean isCousins(TreeNode root, int x, int y) {
        this.dfs(root,null);

        if (heightMap.get(x) == heightMap.get(y) && parentMap.get(x) != parentMap.get(y)) {
            return true;
        }

        return false;
    }

    private void dfs(TreeNode treeNode, TreeNode parent) {
        if (treeNode == null) {
            return;
        }

        heightMap.put(treeNode.val, parent == null ? 0 : heightMap.get(parent.val) + 1);
        parentMap.put(treeNode.val, parent);

        dfs(treeNode.left, treeNode);
        dfs(treeNode.right, treeNode);
    }
}
