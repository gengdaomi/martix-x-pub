package com.martix.x.pub.code.list;

/**
 * Created by Andrew-Geng on 12:16 上午 2021/4/26
 * 链表节点信息
 */
public class ListNode {

    public int val;
    public ListNode next;

    ListNode(int x) {
        val = x;
    }

    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
