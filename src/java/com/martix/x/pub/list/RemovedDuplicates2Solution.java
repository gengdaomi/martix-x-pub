package com.martix.x.pub.list;

/**
 * Created by Andrew-Geng on 1:40 上午 2021/3/25
 * <p>
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 * leetcode 82
 * <p>
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * <p>
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 */
public class RemovedDuplicates2Solution {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(0, head);

        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int x = cur.next.val;
                while (cur.next != null && cur.next.val == x) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }

        return dummy.next;
    }

    public ListNode deleteDuplicates_0(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode p = new ListNode(0);
        p.next = head;
        head = p;
        ListNode left = null, right = null;

        while (p.next != null) {

            left = p.next;
            right = left;

            while (right.next != null && right.next.val == left.val) {
                right = right.next;
            }

            if (left != right) {
                p.next = right.next;
            } else {
                p = p.next;
            }
        }
        return head.next;
    }

    public ListNode deleteDuplicates_1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        if (head.val == head.next.val) {
            while (head != null && head.next != null && head.val == head.next.val) {
                head = head.next;
            }
            return deleteDuplicates_1(head.next);
        } else {
            head.next = deleteDuplicates_1(head.next);
            return head;
        }
    }

    public static void main(String[] args) {

    }
}
