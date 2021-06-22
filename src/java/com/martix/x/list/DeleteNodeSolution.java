package com.martix.x.list;

/**
 * Created by Andrew-Geng on 3:33 下午 2021/5/8
 *  删除中间节点  面试题 02.03
 *
 *  若链表中的某个节点，既不是链表头节点，也不是链表尾节点，则称其为该链表的「中间节点」。
 *
 * 假定已知链表的某一个中间节点，请实现一种算法，将该节点从链表中删除。
 *
 * 例如，传入节点 c（位于单向链表 a->b->c->d->e->f 中），将其删除后，剩余链表为 a->b->d->e->f
 *
 *  
 *
 * 示例：
 *
 * 输入：节点 5 （位于单向链表 4->5->1->9 中）
 * 输出：不返回任何数据，从链表中删除传入的节点 5，使链表变为 4->1->9
 *
 */
public class DeleteNodeSolution {

    /**
     * 思路： 将当前节点的值 set为下一个节点的值，然后把下一个节点干掉 当前节点直接跳过下一个节点与下下个节点关联
     * @param node
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;

        ListNode next = node.next;
        node.next = next.next;
        next.next=null;
    }
}
