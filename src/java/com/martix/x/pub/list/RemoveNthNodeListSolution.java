package com.martix.x.pub.list;

/**
 * Created By Andrew-Geng on 2020/11/7 10:19 下午
 *
 * 删除链表倒数第 n 个结点
 *
 *
 * 扫描链表，获取长度
 * ② 标记倒数位，再次扫描遍历
 * ③ 断开标记位前一个tail连接标记为下一个head，例如倒数第二位
 *
 *
 */
public class RemoveNthNodeListSolution {

    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode temp = head;
        int length = 0;

        while (temp != null) {
            temp = temp.next;
            length++;
        }

        temp = pre;
        length = length - n;
        if (length < 0) {
            return null;
        }

        while (length > 0) {
            temp = temp.next;
            length--;
        }
        temp.next = temp.next.next;

        return pre.next;
    }

}
