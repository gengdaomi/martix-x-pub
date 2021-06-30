package com.martix.x.pub.list;

/**
 * Created by Andrew-Geng on 12:19 上午 2021/5/6
 * 分隔链表 lc 86
 * <p>
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * <p>
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 * <p>
 * 输入：head = [1,4,3,2,5,2], x = 3
 * 输出：[1,2,2,4,3,5]
 * 示例 2：
 * <p>
 * 输入：head = [2,1], x = 2
 * 输出：[1,2]
 *  
 * <p>
 * 提示：
 * <p>
 * 链表中节点的数目在范围 [0, 200] 内
 * -100 <= Node.val <= 100
 * -200 <= x <= 200
 */
public class PartitionListSolution {

    /**
     * 时间复杂度O(n),空间复杂度O(1)
     *
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode result = new ListNode(0); //按顺序存储所有小于x的节点
        ListNode larger = new ListNode(0);  //按顺序存储所有大于等于x的节点
        ListNode curResult = result, curLarge = larger;

        while (head != null) {
            if (head.val >= x) {
                curLarge.next = new ListNode(head.val);
                curLarge = curLarge.next;
            } else {
                curResult.next = new ListNode(head.val);
                curResult = curResult.next;
            }

            head = head.next;
        }

        if (larger.next != null) {
            curResult.next = larger.next;
            larger.next = null;
        }

        return result.next;
    }
}
