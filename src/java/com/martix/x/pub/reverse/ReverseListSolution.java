package com.martix.x.pub.reverse;

/**
 * source : bytedance
 * 反转一个单链表。 lc 206
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 * <p>
 * Created By Andrew-Geng on 2020/5/14 1:03 下午
 */
public class ReverseListSolution {

    public static void main(String[] args) {
    }

    /**
     * 迭代的方式
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
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

    /**
     * 递归的方式
     *
     * @param head
     * @return
     */
    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode last = reverseList1(head.next);

        head.next.next = head;
        head.next = null;

        return last;
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
