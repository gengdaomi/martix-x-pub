package com.martix.x.pub.list;

/**
 * Created By Andrew-Geng on 2020/11/7 10:14 下午
 * <p>
 * 求链表的中间结点
 * <p>
 * 使用双指针，一快一慢指针进行遍历链表，例如：1,2,3,4,5，返回3。1,2,3,4,5,6，返回4
 */
public class FindMidListSolution {

    /*
     * 性能较优的办法
     */
    public static ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        //链表长度为偶数时，返回慢指针的后继节点
        return fast == null ? slow : slow.next;
    }

    /*
     * 性能较差的
     */
    public ListNode middleNode1(ListNode head) {
        if (head == null) return null;
        ListNode temp = head;
        int count = 0;
        while (temp != null) {
            temp = temp.next;
            count++;
        }
        int mid = count / 2;
        ListNode result = head;
        while (mid > 0) {
            result = result.next;
            mid--;
        }
        return result;
    }
}
