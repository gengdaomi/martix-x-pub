package com.martix.x.pub.code.sort;

/**
 * Created by Andrew-Geng on 11:26 下午 2021/7/18
 * 对链表进行插入排序 lc 147
 *
 * 对链表进行插入排序。
 *
 *
 * 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
 * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
 *
 *  
 *
 * 插入排序算法：
 *
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 *
 输入: 4->2->1->3
 输出: 1->2->3->4
 */
public class SortListByInsertSolution {

    /**
     * 从前往后找插入点
     * 插入排序的基本思想是，维护一个有序序列，初始时有序序列只有一个元素，每次将一个新的元素插入到有序序列中，
     * 将有序序列的长度增加1，直到全部元素都加入到有序序列中。
     *
     * 核心思路：
     * 1.首先判断给定的链表是否为空，若为空，则不需要进行排序，直接返回。
     * 2.创建哑节点 dummyHead，令 dummyHead.next = head。引入哑节点是为了便于在 head 节点之前插入节点。
     * 3.维护 lastSorted 为链表的已排序部分的最后一个节点，初始时 lastSorted = head。
     * 4。维护 curr 为待插入的元素，初始时 curr = head.next。
     * 5.比较 lastSorted 和 curr 的节点值。
     *
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode lastSorted = head, curr = head.next;

        while (curr != null) {
            if (lastSorted.val <= curr.val) {
                lastSorted = lastSorted.next;
            } else {
                ListNode prev = dummyHead;
                while (prev.next.val <= curr.val) {
                    prev = prev.next;
                }

                lastSorted.next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
            }

            curr = lastSorted.next;
        }

        return dummyHead.next;
    }
}
