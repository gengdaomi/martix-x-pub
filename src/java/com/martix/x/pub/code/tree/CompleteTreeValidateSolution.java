package com.martix.x.pub.code.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Andrew-Geng on 14:35 2022/12/6
 * 二叉树的完全性检验 lc 958
 * <p>
 * 给定一个二叉树的root，确定它是否是一个完全二叉树。
 * <p>
 * 在一个完全二叉树中，除了最后一个关卡外，所有关卡都是完全被填满的，并且最后一个关卡中的所有节点都是尽可能靠左的。它可以包含1到2h节点之间的最后一级 h 。
 * <p>
 * 1
 * / \
 * 2   3
 * / \ /
 * 4  5 6
 * <p>
 * 输入：root = [1,2,3,4,5,6]
 * 输出：true
 * 解释：最后一层前的每一层都是满的（即，结点值为 {1} 和 {2,3} 的两层），且最后一层中的所有结点（{4,5,6}）都尽可能地向左。
 * <p>
 * 1
 * / \
 * 2   3
 * / \   \
 * 4   5   7
 * <p>
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：false
 * 解释：值为 7 的结点没有尽可能靠向左侧
 */
public class CompleteTreeValidateSolution {

    /**
     * 层序遍历
     *
     * 完全二叉树有个特点就是除了最后一层没有被填满以外，
     * 上面的所有层都被填满，并且最后一层如果没被填满的话都是靠左的。
     * 对于这题的解题思路我们可以使用 BFS 遍历的方式，一层一层的遍历，记录每个节点的两个子节点，
     * 不需要判断是否为空，直接添加到队列中即可。
     *
     * 对于完全二叉树来说队列中只要有一个为空，那么队列的后面就全部为空，
     * 如果队列中有空值并且后面又出现了没有空值的现象说明不是完全二叉树;
     * @param root
     * @return
     */
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 如果遇到有空的则停止循环
        while (queue.peek() != null) {
            TreeNode cur = queue.poll();
            // 把子节点添加到队列，不需要判断是否为空
            queue.offer(cur.left);
            queue.offer(cur.right);
        }

        // 到这一步说明遇到空的了，我们需要判断队列中是否还有不为空的
        while (!queue.isEmpty() && queue.peek() == null) {
            queue.poll();
        }

        return queue.isEmpty();
    }

    int n = 0, p = 0;

    public boolean isCompleteTree_1(TreeNode root) {
        if (!dfs(root, 1)) {
            return false;
        }

        return n == p;
    }

    private boolean dfs(TreeNode root, int k) { //k是当前节点编号{
        if (root == null) {
            return true;  //递归到了叶子节点
        }

        if (k > 100) {
            return false;
        }

        n++;
        p = Math.max(p, k); //记录节点数和最大节点编号值
        return dfs(root.left, 2 * k) && dfs(root.right, 2 * k + 1); //递归左右子树
    }

}
