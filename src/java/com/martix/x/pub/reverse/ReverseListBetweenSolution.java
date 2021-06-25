package com.martix.x.pub.reverse;

/**
 * Created by Andrew-Geng on 1:45 上午 2021/4/13
 * 反转链表 II  lc 92
 * https://leetcode-cn.com/problems/reverse-linked-list-ii/solution/fan-zhuan-lian-biao-ii-by-leetcode-solut-teyq/
 * <p>
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 * <p>
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 * <p>
 * 输入：head = [5], left = 1, right = 1
 * 输出：[5]
 * <p>
 * lc 206 的升级版
 */
public class ReverseListBetweenSolution {

    private ListNode post; //后序节点

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == 1) { //相当于反转前right个节点的链表
            return this.reverseN_1(head, right);
        }

        /**
         * 对于head.next 就是反转区间[left-1,right-1];
         *
         */
        head.next = reverseBetween(head.next, left - 1, right - 1);
        return head;
    }

    public ListNode reverseBetween_1(ListNode head, int left, int right) {
        ListNode virtualNode = new ListNode(-1);
        virtualNode.next = head;

        ListNode pre = virtualNode;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        ListNode cur = pre.next;
        for (int i = 0; i < right - left; i++) {
            post = cur.next;
            cur.next = post.next;
            post.next = pre.next;
            pre.next = post;
        }

        return virtualNode.next;
    }

    /**
     * 反转前n个节点
     * <p>
     * 反转前n个节点和反转整个链表的区别：
     * 1.bad case 由判断head是否为空 改为判断n==1，其实都是为了判断是否仅有一个节点
     * 2.要记录下后序节点，反转整个链表是 head.next=null, 而N个节点则是改为指定的后序节点
     *
     * @param head
     * @param n
     * @return
     */
    private ListNode reverseN(ListNode head, int n) {
        /*
        bad case 仅有一个节点的时候，反转自己，同时记录下后序节点
         */
        if (n == 1) {
            post = head.next;
            return head;
        }

        ListNode last = reverseN(head.next, n - 1);
        head.next.next = head;
        head.next = post;

        return last;
    }

    /**
     * ===================== 迭代 ============
     */

    private ListNode reverseN_1(ListNode listNode, int n) {
        int count = 0; //计数器

        ListNode cur = listNode, pre = null;
        while (count < n && cur != null) {
            post = cur.next;
            cur.next = pre;
            pre = cur;
            cur = post;
            count++;
        }

        listNode.next = post;
        return pre;
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
