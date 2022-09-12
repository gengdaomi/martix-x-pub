package com.martix.x.pub.code.list;

/**
 * Created by Andrew-Geng on 12:14 上午 2021/4/26
 * 环形链表 II lc 142
 * <p>
 * 给定一个链表，返回链表开始入环的第一个节点。如果链表无环，则返回null。
 * <p>
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
 * <p>
 * 说明：不允许修改给定的链表。
 * <p>
 * 进阶：
 * <p>
 * 你是否可以使用 O(1) 空间解决此题？
 * <p>
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：返回索引为 1 的链表节点
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * 输入：head = [1,2], pos = 0
 * 输出：返回索引为 0 的链表节点
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 *
 * 输入：head = [1], pos = -1
 * 输出：返回 null
 * 解释：链表中没有环。
 */
public class CycleDetectListSolution {

    /**
     * 第一次相遇的时候，假设slow走了k步，那么fast就走了2k步； 也就是说fast一定会比slow多走k步
     * 设第一次相遇点距离环的起点是m，那么环的起点距离链表的起点是k-m, 而从相遇点继续走k-m步，也刚好是环的起点；
     *
     * 那这个时候 把slow重新置为head，然后slow fast 同时匀速前进k-m步，就到达环的起点
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;

        while (true) {
            if (fast == null || fast.next == null) {
                return null;
            }

            fast = fast.next.next;
            slow = slow.next;

            if (slow == fast) {
                break;
            }
        }

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }
}
