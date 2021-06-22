package com.martix.x.list;

/**
 * Created by Andrew-Geng on 1:24 上午 2021/3/25
 * <p>
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 * leetcode 82
 * <p>
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->3->4->5
 * <p>
 * 输入: 1->1->1->2->3
 * 输出: 1->2->3
 */
public class RemovedDuplicates1Solution {

    /**
     * 通过快慢指针的方式
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null ) {
            if (fast.val != slow.val) {

                slow.next = fast;
                slow = slow.next;
            }

            fast = fast.next;
        }

        //断开后面重复元素的关联
        slow.next = null;
        return head;
    }

    public static void main(String[] args) {

    }
}
