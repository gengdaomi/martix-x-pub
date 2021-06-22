package com.martix.x.pub.reverse;

/**
 * Created by Andrew-Geng on 2:01 上午 2021/4/14
 * <p>
 * K 个一组翻转链表 lc 25
 * <p>
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 * <p>
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。
 * <p>
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 进阶：
 * <p>
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * <p>
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 * <p>
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 * <p>
 * 输入：head = [1,2,3,4,5], k = 1
 * 输出：[1,2,3,4,5]
 * <p>
 * 输入：head = [1], k = 1
 * 输出：[1]
 */
public class ReversedKGroupSolution {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        ListNode a = head, b = head;
        for (int i = 0; i < k; i++) { //不足k个，不需要反转，直接返回 可以看做是递归里面的bad case
            if (b == null) {
                return head;
            }

            b = b.next;
        }

        ListNode newHead = this.reverse(a, b); //反转前k个元素
        a.next = reverseKGroup(b, k);

        return newHead;
    }

    /**
     * 反转链表的部分区间，范围是[a,b)
     * 思路是lc 206 的迭代方式
     *
     * @param a
     * @param b
     * @return
     */
    private ListNode reverse(ListNode a, ListNode b) {
        ListNode pre = null, cur = a;
        while (cur != b) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }


    class ListNode {

        ListNode next;
        Integer val;

        public ListNode(Integer val) {
            this.val = val;
            this.next = null;
        }
    }
}
