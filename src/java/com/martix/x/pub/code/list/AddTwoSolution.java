package com.martix.x.pub.code.list;

/**
 * source： bytedance
 * 两数相加  面试题 02.05. 链表求和
 * <p>
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * <p>
 * 进阶：思考一下，假设这些数位是正向存放的，又该如何解决呢?
 * <p>
 * 示例：
 * <p>
 * 输入：(6 -> 1 -> 7) + (2 -> 9 -> 5)，即617 + 295
 * 输出：9 -> 1 -> 2，即912
 *
 * <p>
 * Created By Andrew-Geng on 2020/5/14 1:59 下午
 */
public class AddTwoSolution {

    public static void main(String[] args) {
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0); //相加后链表的头节点
        ListNode curNode = head;

        int tempVal = 0; //进位的值
        while (l1 != null || l2 != null) {
            int l1Val = l1 != null ? l1.val : 0;
            int l2Val = l2 != null ? l2.val : 0;

            int sum = l1Val + l2Val + tempVal;
            tempVal = sum >= 10 ? sum / 10 : 0; //新的进位

            curNode.next = new ListNode(sum % 10);
            curNode = curNode.next;

            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }

        if (tempVal != 0) {
            curNode.next = new ListNode(tempVal);
        }

        return head.next;
    }

    /**
     * 进阶：思考一下，假设这些数位是正向存放的，又该如何解决呢?
     * <p>
     * 示例：
     * <p>
     * 输入：(6 -> 1 -> 7) + (2 -> 9 -> 5)，即617 + 295
     * 输出：9 -> 1 -> 2，即912
     * <p>
     * 解题思路，即对两个链表反转 然后按照上面的方式相加，然后再把结果数组反转
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers_1(ListNode l1, ListNode l2) {
        ListNode l1Reverse = this.reverse(l1);
        ListNode l2Reverse = this.reverse(l2);

        ListNode result = addTwoNumbers(l1Reverse, l2Reverse);
        return this.reverse(result);
    }

    /**
     * 链表的反转
     *
     * @param listNode
     */
    private ListNode reverse(ListNode listNode) {
        if (listNode == null || listNode.next == null) {
            return listNode;
        }

        ListNode last = reverse(listNode.next);
        listNode.next.next = listNode;
        listNode.next = null;

        return last;
    }

    /**
     * 迭代的方式
     *
     * @param head
     * @return
     */
    private ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }
}
