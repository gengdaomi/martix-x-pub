package com.martix.x.pub.list;

/**
 * Created by Andrew-Geng on 12:59 上午 2021/5/8
 * 移除链表元素
 * lc 203
 * <p>
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 * 输入：head = [1,2,6,3,4,5,6], val = 6
 * 输出：[1,2,3,4,5]
 * 示例 2：
 * <p>
 * 输入：head = [], val = 1
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：head = [7,7,7,7], val = 7
 * 输出：[]
 *  
 * <p>
 * 提示：
 * <p>
 * 列表中的节点在范围 [0, 104] 内
 * 1 <= Node.val <= 50
 * 0 <= k <= 50
 */
public class RemovedElementListSolution {

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        ListNode result = new ListNode(0);
        ListNode cur = head, pre = result;

        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
                cur.next = null;
                cur = pre.next;
            } else {
                pre.next = cur;
                pre = pre.next;
                cur = cur.next;
            }
        }

        return result.next;
    }

    /**
     * 时空复杂度O(n)
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements_1(ListNode head, int val) {
        if (head == null) {
            return head;
        }

        head.next = removeElements_1(head.next, val);
        return head.val == val ? head.next : head;
    }

    public ListNode removeElements_2(ListNode head, int val) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode temp = dummyHead;

        while (temp.next != null) {
            if (temp.next.val == val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return dummyHead.next;
    }
}
