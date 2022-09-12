package com.martix.x.pub.code.list;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Andrew-Geng on 12:46 上午 2021/5/7
 * <p>
 * 相交链表 lc 160
 * <p>
 * 编写一个程序，找到两个单链表相交的起始节点。
 * <p>
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Reference of the node with value = 8
 * 输入解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
 * 从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 * <p>
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * 输出：null
 * 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
 * 解释：这两个链表不相交，因此返回 null
 * <p>
 * <p>
 * 注意：
 * <p>
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 */
public class FindIntersectionNodeSolution {

    /**
     * 双指针的思路
     * 设「第一个公共节点」为 node，「链表 headA」的节点数量为a ，
     * 「链表 headB」的节点数量为b，「两链表的公共尾部」的节点数量为c；
     * <p>
     * 头节点 headA 到 node 前，共有a−c 个节点
     * 头节点 headB 到 node 前，共有b−c 个节点；
     * <p>
     * 指针 A 先遍历完链表 headA ，再开始遍历链表 headB ，当走到 node 时，共走步数为a+(b−c)
     * 指针 B 先遍历完链表 headB ，再开始遍历链表 headA ，当走到 node 时，共走步数为b+(a−c)
     * <p>
     * 如下式所示，此时指针 A , B 重合，并有两种情况：
     * a+(b−c)=b+(a−c)
     * 若两链表 有 公共尾部 (即c>0 ) ：指针 A , B 同时指向「第一个公共节点」node
     * 若两链表 无 公共尾部 (即c=0 ) ：指针 A , B 同时指向null
     * <p>
     * 时间复杂度 :(m+n)。
     * 空间复杂度 : O(m)或者O(n)。
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode_1(ListNode headA, ListNode headB) {
        ListNode A = headA, B = headB;
        while (A != B) {
            A = A != null ? A.next : headB;
            B = B != null ? B.next : headA;
        }
        return A;

    }

    /**
     * hash的思路
     * 时间复杂度 :(m+n)。
     * 空间复杂度 : O(m)或者O(n)。
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode_2(ListNode headA, ListNode headB) {
        Set<ListNode> listNodeSet = new HashSet<>();

        ListNode curA = headA;
        while (curA != null) {
            listNodeSet.add(curA);
            curA = curA.next;
        }

        ListNode curB = headB;
        while (curB != null) {
            if (listNodeSet.contains(curB)) {
                return curB;
            }
            curB = curB.next;
        }

        return null;
    }

    /**
     * 暴力解决方法
     * 对链表A中的每一个结点，遍历整个链表 B 并检查链表 B 中是否存在结点相同
     * <p>
     * 时间复杂度 :(mn)。
     * 空间复杂度 : O(1)。
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode_3(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode curA = headA;
        boolean isExist = false;
        ListNode result = null;
        while (curA != null) {
            ListNode curB = headB;

            while (curB != null) {
                if (curB.val == curA.val) {
                    if (!isExist) {
                        result = curA;
                    }

                    isExist = true;

                    curB = curB.next;
                    curA = curA.next;
                    continue;
                }

                isExist = false;
                result = null;
                curB = curB.next;
            }

            curA = curA.next;
        }

        return result;
    }
}
