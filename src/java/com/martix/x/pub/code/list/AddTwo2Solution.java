package com.martix.x.pub.code.list;

import java.util.Stack;

/**
 * Created by Andrew-Geng on 11:42 下午 2021/5/6
 * 两数相加 II lc 445
 * <p>
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * <p>
 * 进阶：
 * <p>
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 * <p>
 * 示例：
 * <p>
 * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 8 -> 0 -> 7
 */
public class AddTwo2Solution {
    /**
     * 运用链表反转的思路
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l1Reverse = this.reverse(l1);
        ListNode l2Reverse = this.reverse(l2);

        ListNode resultHead = new ListNode(0);
        ListNode cur = resultHead;

        int tempVal = 0;
        while (l1Reverse != null || l2Reverse != null) {
            int l1Val = l1Reverse != null ? l1Reverse.val : 0;
            int l2Val = l2Reverse != null ? l2Reverse.val : 0;
            int sum = l1Val + l2Val + tempVal;

            cur.next = new ListNode(sum % 10);
            tempVal = sum >= 10 ? sum / 10 : 0;

            cur = cur.next;

            l1Reverse = l1Reverse != null ? l1Reverse.next : null;
            l2Reverse = l2Reverse != null ? l2Reverse.next : null;
        }

        if (tempVal != 0) {
            cur.next = new ListNode(tempVal);
        }

        return this.reverse(resultHead.next);
    }

    /**
     * 进阶的思路，运用栈的方式
     * 为了逆序处理所有数位，我们可以使用栈：把所有数字压入栈中，再依次取出相加。计算过程中需要注意进位的情况。
     * <p>
     * 时间复杂度：
     * O(max(m,n))，其中
     * m 和
     * n 分别为两个链表的长度。我们需要遍历两个链表的全部位置，而处理每个位置只需要
     * O(1) 的时间。
     * 空间复杂度：
     * O(m+n)，其中m 和n 分别为两个链表的长度。空间复杂度主要取决于我们把链表内容放入栈中所用的空间。
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers_1(ListNode l1, ListNode l2) {
        Stack<Integer> l1Stack = new Stack<>();
        Stack<Integer> l2Stack = new Stack<>();

        while (l1 != null) {
            l1Stack.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            l2Stack.push(l2.val);
            l2 = l2.next;
        }

        int tempVal = 0;
        Stack<Integer> resultStack = new Stack<>();
        while (!l1Stack.isEmpty() || !l2Stack.isEmpty()) {
            int l1Val = l1Stack.isEmpty() ? 0 : l1Stack.pop();
            int l2Val = l2Stack.isEmpty() ? 0 : l2Stack.pop();
            int sum = l1Val + l2Val + tempVal;

            resultStack.push(sum % 10);
            tempVal = sum >= 10 ? sum / 10 : 0;
        }

        if (tempVal != 0) {
            resultStack.push(tempVal);
        }

        ListNode resultHead = new ListNode(0);
        ListNode cur = resultHead;
        while (!resultStack.isEmpty()) {
            cur.next = new ListNode(resultStack.pop());
            cur = cur.next;
        }

        return resultHead.next;
    }

    /**
     * addTwoNumbers_1的变种，即省掉结果resultStack
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers_1_1(ListNode l1, ListNode l2) {
        Stack<Integer> l1Stack = new Stack<>();
        Stack<Integer> l2Stack = new Stack<>();

        while (l1 != null) {
            l1Stack.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            l2Stack.push(l2.val);
            l2 = l2.next;
        }

        int tempVal = 0;
        ListNode result = null;
        while (!l1Stack.isEmpty() || !l2Stack.isEmpty() || tempVal != 0) {
            int l1Val = l1Stack.isEmpty() ? 0 : l1Stack.pop();
            int l2Val = l2Stack.isEmpty() ? 0 : l2Stack.pop();
            int sum = l1Val + l2Val + tempVal;

            ListNode cur = new ListNode(sum % 10);
            tempVal = sum >= 10 ? sum / 10 : 0;

            cur.next = result;
            result = cur;
        }

        return result;
    }

    /**
     * 反转链表
     *
     * @param head
     * @return
     */
    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;

            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
