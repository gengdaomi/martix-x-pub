package com.martix.x.pub.list;

/**
 * Created by Andrew-Geng on 1:06 上午 2021/5/9
 * 删除链表的节点 剑指 Offer 18.
 * <p>
 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
 * <p>
 * 返回删除后的链表的头节点。
 * <p>
 * 注意：此题对比原题有改动
 * <p>
 * 示例 1:
 * <p>
 * 输入: head = [4,5,1,9], val = 5
 * 输出: [4,1,9]
 * 解释: 给定你链表中值为5的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 * 示例 2:
 * <p>
 * 输入: head = [4,5,1,9], val = 1
 * 输出: [4,5,9]
 * 解释: 给定你链表中值为1的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
 * <p>
 * <p>
 * 说明：
 * <p>
 * 题目保证链表中节点的值互不相同
 * 若使用 C 或 C++ 语言，你不需要 free 或 delete 被删除的节点
 */
public class DeleteNode2Solution {

    public ListNode deleteNode(ListNode head, int val) {
        if (head.val == val) { //排除掉第一个节点 就为要删除的节点
            return head.next;
        }

        ListNode slow = head, fast = head.next;
        while (fast != null) {

            if (fast.val == val) {
                slow.next = fast.next;
                fast.next = null;

                return head;
            }

            slow = slow.next;
            fast = fast.next;
        }

        return head;
    }
}
