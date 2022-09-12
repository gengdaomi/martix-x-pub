package com.martix.x.pub.code.list;

/**
 * Created by Andrew-Geng on 12:26 下午 2021/5/1
 * 旋转链表 lc 61
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 * <p>
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 */
public class RotateRightListSolution {

    /**
     * 核心思路：遍历链表，将其组合为一个环
     * <p>
     * 特别地，当链表长度不大于 1，或者k 为n的倍数时，新链表将与原链表相同，我们无需进行任何处理
     * <p>
     * 首先计算出链表的长度length，并找到该链表的末尾节点，将其与头节点相连。这样就得到了闭合为环的链表。然后我们找到新链表的最后一个节点
     * 即原链表中的第（n-1）-(k%n)个节点，将当前闭合为环的链表断开，即可得到我们所需要的结果
     * <p>
     * 最优解法
     * <p>
     * 时间复杂度O(N),空间复杂度O(1) ,极端情况下遍历两遍
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        int length = 1; //用于计数当前链表的长度
        ListNode listNode = head;
        while (listNode.next != null) {
            length++;
            listNode = listNode.next;
        }

        int index = length - k % length; //计数链表真实要移动的位置（从尾结点开始算）
        if (index == length) { //当k为链表的整数倍的时候，相当于不移动
            return head;
        }

        listNode.next = head;
        while (index > 0) {
            listNode = listNode.next;
            index--;
        }

        ListNode resultNode = listNode.next;
        listNode.next = null;

        return resultNode;
    }

    /**
     * 快慢指针的思路，这种方式也可以经常用来解决需要反向操作链表的问题
     * <p>
     * 让快指针先走k次，然后慢指针再和快指针一起走，当快指针走完时，慢指针会刚好来到k的位置。
     * 此时慢指针的下一个节点就是新的头节点，慢指针当前的节点就是尾节点，最后把快指针当前的节点链上原来的头节点即可
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight_1(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        ListNode slow = head, fast = head;
        int len = 0;
        while (len < k && fast != null) {
            len++;
            fast = fast.next;
        }

        /*
         * 如果fast为null，则代表k的值大于链表的长度
         * 那么实际上：当k值大于链表的长度时，旋转k次的结果等于旋转（k%链表的长度）次
         * 比如：链表为： 1---2---3，k为：4
         * k的长度要大于链表的长度，所以k=4%3，k=1，即整个链表实际上只需要旋转一次即可
         */
        if (fast == null) {
            k %= len;
            if (k == 0) { //如果k==0，则实际上不用旋转，直接返回即可
                return head;
            }

            fast = head;//否则快指针从头开始，重新先走k次，此时的k已经是和len取模的结果了
            for (int i = 0; i < k; i++) {
                fast = fast.next;
            }
        }

        while (fast != null && fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        ListNode newHead = slow.next;
        slow.next = null;//慢指针当前节点就是尾节点
        fast.next = head;//快指针的下一节点（就是原来链表的最后一个节点）链上原来的头节点即可

        return newHead; //最后返回新的头节点
    }
}
