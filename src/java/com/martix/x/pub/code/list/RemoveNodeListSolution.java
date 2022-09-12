package com.martix.x.pub.code.list;

/**
 * Created By Andrew-Geng on 2020/11/7 10:32 下午
 * <p>
 * 删除链表
 * lc 237
 * <p>
 * 使其可以删除某个链表中给定的（非末尾）节点。传入函数的唯一参数为 要被删除的节点
 * <p>
 * 链表至少包含两个节点。
 * 链表中所有节点的值都是唯一的。
 * 给定的节点为非末尾节点并且一定是链表中的一个有效节点。
 * 不要从你的函数中返回任何结果
 */
public class RemoveNodeListSolution {

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }


    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        //定义一个哨兵作为传入链表的头结点
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode temp = pre;
        while (temp != null) {
            if (temp.next.val == val) {
                temp.next = temp.next.next;
                break;
            } else {
                temp = temp.next;
            }
        }

        return pre.next;
    }

}
