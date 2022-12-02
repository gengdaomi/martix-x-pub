package com.martix.x.pub.code.list;

/**
 * Created by Andrew-Geng on 13:33 2022/12/2
 * 二进制链表转整数 lc1290
 *
 * 给你一个单链表的引用结点 head。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式。
 *
 * 请你返回该链表所表示数字的 十进制值
 *
 * 输入：head = [1,0,1]
 * 输出：5
 * 解释：二进制数 (101) 转化为十进制数 (5)
 */
public class BinaryList2IntSolution {

    public int getDecimalValue(ListNode head) {
        ListNode curNode = head;
        int result = 0;

        while (curNode != null) {
            result = result * 2 + curNode.val;
            curNode = curNode.next;
        }

        return result;
    }

}
