package com.martix.x.pub.list;

import com.martix.x.pub.tree.TreeNode;

/**
 * Created by Andrew-Geng on 1:32 上午 2021/5/8
 * 有序链表转换二叉搜索树 lc 109
 * <p>
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 * <p>
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * <p>
 * 示例:
 * <p>
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 * <p>
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 * <p>
 * 0
 * / \
 * -3   9
 * /   /
 * -10  5
 */
public class SortedListToBSTSolution {

    /**
     * * 时间复杂度：O(n)
     * * 空间复杂度：(logn)
     *
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        return this.build(head, null);
    }

    public TreeNode build(ListNode left, ListNode right) {
        if (left == right) {
            return null;
        }

        ListNode mid = getMid(left, right);
        TreeNode root = new TreeNode(mid.val);

        root.left = build(left, mid);
        root.right = build(mid.next, right);

        return root;
    }

    /**
     * 利用快慢指针
     *
     * @param left
     * @param right
     * @return
     */
    private ListNode getMid(ListNode left, ListNode right) {
        ListNode slow = left;
        ListNode fast = left;

        while (fast != right && fast.next != right) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}
