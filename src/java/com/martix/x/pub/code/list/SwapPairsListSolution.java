package com.martix.x.pub.code.list;

/**
 * Created by Andrew-Geng on 12:06 上午 2021/5/3
 * 两两交换链表中的节点
 * lc 24
 * <p>
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 */
public class SwapPairsListSolution {

    /**
     * 递归
     * <p>
     * 用 head 表示原始链表的头节点，新的链表的第二个节点，用 newHead 表示新的链表的头节点，原始链表的第二个节点，
     * 则原始链表中的其余节点的头节点是 newHead.next。令 head.next = swapPairs(newHead.next)，表示将其余节点进行两两交换，
     * 交换后的新的头节点为 head 的下一个节点。然后令 newHead.next = head，即完成了所有节点的交换。最后返回新的链表的头节点 newHead。
     *
     * <p>
     * 时间复杂度 空间复杂度O(n)
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;

        return newHead;
    }

    /**
     * 也可以通过迭代的方式实现两两交换链表中的节点。
     * 创建哑结点 dummyHead，令 dummyHead.next = head。令 listNode 表示当前到达的节点，初始时 listNode = dummyHead。
     * 每次需要交换 listNode 后面的两个节点。
     * 如果 listNode 的后面没有节点或者只有一个节点，则没有更多的节点需要交换，因此结束交换。
     * 否则，获得 listNode 后面的两个节点 node1 和 node2，通过更新节点的指针关系实现两两交换节点。
     * 具体而言，交换之前的节点关系是 listNode -> node1 -> node2，交换之后的节点关系要变成 listNode -> node2 -> node1，因此需要进行如下操作。
     * <p>
     * <p>
     * listNode.next = node2
     * node1.next = node2.next
     * node2.next = node1
     * 完成上述操作之后，节点关系即变成 listNode -> node2 -> node1。再令 listNode = node1，对链表中的其余节点进行两两交换，直到全部节点都被两两交换。
     * <p>
     * 两两交换链表中的节点之后，新的链表的头节点是 dummyHead.next，返回新的链表的头节点即可。
     * <p>
     * 时间复杂度O(n) 空间复杂度O(1)
     *
     * @param head
     * @return
     */
    public ListNode swapPairs_1(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode listNode = dummyHead;

        while (listNode.next != null && listNode.next.next != null) {
            ListNode node1 = listNode.next;
            ListNode node2 = listNode.next.next;

            listNode.next = node2;
            node1.next = node2.next;
            node2.next = node1;

            listNode = node1;
        }

        return dummyHead.next;
    }
}
