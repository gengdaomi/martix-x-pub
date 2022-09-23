package com.martix.x.pub.code.list;

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
public class RemovedDuplicates2SortedSolution {

    /**
     * 由于给定的链表是排好序的，因此重复的元素在链表中出现的位置是连续的，因此我们只需要对链表进行一次遍历，就可以删除重复的元素。
     * 由于链表的头节点可能会被删除，因此我们需要额外使用一个哑节点（dummy node）指向链表的头节点;
     * <p>
     * 从指针cur 指向链表的哑节点,随后开始对链表进行遍历;
     * 如果当前cur.next 与cur.next.next 对应的元素相同，
     * 那么我们就需要将cur.next 以及所有后面拥有相同元素值的链表节点全部删除。
     * 记下这个元素值x，随后不断将cur.next 从链表中移除，直到cur.next 为空节点或者其元素值不等于x 为止;
     * 此时，我们将链表中所有元素值为x 的节点全部删除。
     * <p>
     * 如果当前cur.next 与cur.next.next 对应的元素不相同，
     * 那么说明链表中只有一个元素值为cur.next 的节点，那么我们就可以将cur 指向cur.next。
     *
     * @param head
     * @return
     */
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
