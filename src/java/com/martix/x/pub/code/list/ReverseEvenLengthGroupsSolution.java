package com.martix.x.pub.code.list;

/**
 * Created by Andrew-Geng on 21:22 2022/12/19
 * 反转偶数长度组的节点 lc2074
 * <p>
 * 给你一个链表的头节点 head 。
 * <p>
 * 链表中的节点 按顺序 划分成若干 非空 组，这些非空组的长度构成一个自然数序列（1, 2, 3, 4, ...）。
 * 一个组的 长度 就是组中分配到的节点数目。换句话说：
 * <p>
 * 节点 1 分配给第一组
 * 节点 2 和 3 分配给第二组
 * 节点 4、5 和 6 分配给第三组，以此类推
 * 注意，最后一组的长度可能小于或者等于 1 + 倒数第二组的长度 。
 * <p>
 * 反转 每个 偶数 长度组中的节点，并返回修改后链表的头节点 head 。
 * <p>
 * 输入：head = [5,2,6,3,9,1,7,3,8,4]
 * 输出：[5,6,2,3,9,1,4,8,3,7]
 * 解释：
 * - 第一组长度为 1 ，奇数，没有发生反转。
 * - 第二组长度为 2 ，偶数，节点反转。
 * - 第三组长度为 3 ，奇数，没有发生反转。
 * - 最后一组长度为 4 ，偶数，节点反转。
 * <p>
 * 输入：head = [2,1]
 * 输出：[2,1]
 * 解释：
 * - 第一组长度为 1 ，没有发生反转。
 * - 最后一组长度为 1 ，没有发生反转。
 */
public class ReverseEvenLengthGroupsSolution {

    public ListNode reverseEvenLengthGroups(ListNode head) {
        ListNode prehead = new ListNode(-1);
        prehead.next = head;
        ListNode p = head;
        int flag = 0;

        for (int j = 1; flag == 0; j++) {
            int count = 0;// 统计结点个数
            ListNode reverse_head = prehead;//执行反转时候，反转区间的左端点为prehead
            ListNode reverse_tail = null;

            while (p != null && count < j) {
                reverse_tail = p;
                prehead = prehead.next;//不执行反转时候，prehead++以确定下一次反转区间的左端点
                p = p.next;
                count++;

                if (p == null) {
                    flag = 1;//flag==1时候跳出大循环
                }
            }

            if (count % 2 == 0) {
                prehead = reverse(reverse_head, reverse_tail);//当执行反转操作时候，prehead需要重新设置，更新前:反转前区间的右端点 更新后:反转后的右端点
            }

        }

        return head;
    }

    //反转prehead到tail区间的结点 结点顺序: prehead->(head->...->tail)
    public ListNode reverse(ListNode prehead, ListNode tail) {
        ListNode head = prehead.next;//反转区间内的第一个结点
        //结点顺序prehead head tail

        ListNode temp = head;//用于遍历
        while (temp != tail) {
            ListNode cur = temp;

            temp = temp.next;
            cur.next = tail.next;
            tail.next = cur;
        }

        prehead.next = tail;//左边界连接新头结点
        return head;//返回反转后的最后结点 提供给prehead
    }

}
