package com.martix.x.pub.tree;

/**
 * Created by Andrew-Geng on 1:02 上午 2021/5/10
 * 二叉树中的列表 lc 1367
 * <p>
 * 给你一棵以 root 为根的二叉树和一个 head 为第一个节点的链表。
 * <p>
 * 如果在二叉树中，存在一条一直向下的路径，且每个点的数值恰好一一对应以 head 为首的链表中每个节点的值，那么请你返回 True ，否则返回 False 。
 * <p>
 * 一直向下的路径的意思是：从树中某个节点开始，一直连续向下的路径。
 * <p>
 * <p>
 * <p>
 * 输入：head = [4,2,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
 * 输出：true
 * 解释：树中蓝色的节点构成了与链表对应的子路径。
 */
public class IsSubPathSolution {

    /**
     * 深度优先搜索，逐个检查每一条纵深路径上的节点是否匹配，在检查过程中如果匹配到了就返回 true，其他情况：
     * <p>
     * 链表还没到末尾呢，树已经到头了。
     * 链表中的节点和树中的节点已经不匹配了。
     * 就返回 false。
     *
     * @param head
     * @param root
     * @return
     */
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (root == null) {
            return false;
        }

        return dfs(head, root)
                || this.isSubPath(head, root.left)
                || this.isSubPath(head, root.right); //检查当前子树和左右子树中是否有符合要求的序列
    }

    private boolean dfs(ListNode head, TreeNode root) {
        if (head == null) { //检查到了链表的末尾，说明找到了一个完整匹配的序列，返回 true
            return true;
        }

        if (root == null) { //链表还没结束呢，树自己先到头了，所以不能完全匹配，返回 false
            return false;
        }

        if (root.val != head.val) { //如果树中节点已经不匹配了，那么就直接返回 false
            return false;
        }

        return dfs(head.next, root.left) || dfs(head.next, root.right);
    }

    class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
