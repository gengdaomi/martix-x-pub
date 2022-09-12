package com.martix.x.pub.code.list;

/**
 * Created by Andrew-Geng on 3:33 下午 2021/5/8
 * 删除中间节点  面试题 02.03
 * <p>
 * 若链表中的某个节点，既不是链表头节点，也不是链表尾节点，则称其为该链表的「中间节点」。
 * <p>
 * 假定已知链表的某一个中间节点，请实现一种算法，将该节点从链表中删除。
 * <p>
 * 例如，传入节点c（位于单向链表a->b->c->d->e->f中），将其删除后，剩余链表为a->b->d->e->f
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：节点5（位于单向链表4->5->1->9中）
 * 输出：不返回任何数据，从链表中删除传入的节点 5，使链表变为4->1->9
 */
public class DeleteNodeSolution {

    /**
     * 思路： 将当前节点的值 set为下一个节点的值，然后把下一个节点干掉 当前节点直接跳过下一个节点与下下个节点关联
     *
     * @param node
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;

        ListNode next = node.next;
        node.next = next.next;
        next.next = null;
    }
}
