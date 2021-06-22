package com.martix.x.list;

/**
 * Created by Andrew-Geng on 1:27 上午 2021/5/9
 * 重排链表 lc 143
 * <p>
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 示例 1:
 * <p>
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 示例 2:
 * <p>
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 */
public class ReorderListSolution {

    /**
     * 运用快慢指针，先找到链表的中间节点
     *
     * @param head
     */
    public void reorderList(ListNode head) {
        ListNode slow = head, fast = head;

        while (fast.next != null && fast.next.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode last = slow.next;
        last = this.reverse(last); //后半段的链表进行反转
        slow.next = null; //断开原先链表

        ListNode cur = head, result = new ListNode(0);
        ListNode temp = result;

        while (cur != null && last != null) {
            temp.next = new ListNode(cur.val);
            temp = temp.next;
            temp.next = new ListNode(last.val);
            temp = temp.next;

            cur = cur.next;
            last = last.next;
        }

        if (cur != null) {
            temp.next = cur;
        }
        if (last != null) {
            temp.next = last;
        }

        head.next = result.next.next;

    }

    /**
     * 反转某个链表
     *
     * @param listNode
     * @return
     */
    private ListNode reverse(ListNode listNode) {
        ListNode cur = listNode, pre = null;

        while (cur != null) {
            ListNode next = cur.next;

            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

}
