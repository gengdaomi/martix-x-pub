package com.martix.x.pub.code.list;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created By Andrew-Geng on 2020/11/7 10:19 下午
 *
 * 删除链表倒数第 n 个结点
 * lc 19
 *
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 *
 * 扫描链表，获取长度
 * ② 标记倒数位，再次扫描遍历
 * ③ 断开标记位前一个tail连接标记为下一个head，例如倒数第二位
 *
 *
 */
public class RemoveNthBackwardNodeSolution {

    /**
     * 双指针 快慢指针
     *
     *  时间复杂度O（n）
     *  空间复杂度O(1)
      * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode slow = dummy;

        for (int i = 0; i < n; i++) {
            first = first.next;
        }

        while (first != null) {
            first = first.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return dummy.next;
    }

    /**
     * 栈
     * 在遍历链表的同时将所有节点依次入栈。根据栈「先进后出」的原则，我们弹出栈的第
     *  n 个节点就是需要删除的节点，并且目前栈顶的节点就是待删除节点的前驱节点
     *
     * 时间复杂度O（n）
     * 空间复杂度O(n)
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd_0(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        Deque<ListNode> stack = new LinkedList<ListNode>();

        ListNode cur = dummy;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        for (int i = 0; i < n; ++i) {
            stack.pop();
        }

        ListNode prev = stack.peek();
        prev.next = prev.next.next;

        return dummy.next;

    }

    /**
     * 我们首先从头节点开始对链表进行一次遍历，得到链表的长度L。随后我们再从头节点开始对链表进行一次遍历，当遍历到第
     * L−n+1 个节点时，它就是我们需要删除的节点
     *
     *  时间复杂度O（n）
     *  空间复杂度O(1)
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd_1(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        int length = getLength(head);
        ListNode cur = dummy;

        for (int i = 1; i < length - n + 1; ++i) {
            cur = cur.next;
        }

        cur.next = cur.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

    private int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            ++length;
            head = head.next;
        }
        return length;
    }

    public ListNode removeNthFromEnd_4(ListNode head, int n) {

        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode temp = head;
        int length = 0;

        while (temp != null) {
            temp = temp.next;
            length++;
        }

        temp = pre;
        length = length - n;
        if (length < 0) {
            return null;
        }

        while (length > 0) {
            temp = temp.next;
            length--;
        }
        temp.next = temp.next.next;

        return pre.next;
    }

}
