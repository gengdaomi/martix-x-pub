package com.martix.x.pub.merge;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度
 * <p>
 * 输入:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 */
public class KLinkedMergeSolution {


    // 原地归并，并不申请新的数组空间，算法实现上，其实是找规律。
    public ListNode mergeKLists(ListNode[] lists) {
        // 步长为 2 时，和后面的第 1 个合并
        // 步长为 4 时，和后面的第 2 个合并
        // ...
        if (lists == null) {
            return null;
        }
        int len = lists.length;
        int interval = 1;
        while (interval < len) {
            for (int i = 0; i + interval < len; i += 2 * interval) {
                lists[i] = this.merge(lists[i], lists[i + interval]);
            }

            interval *= 2;
        }
        return len != 0 ? lists[0] : null;
    }

    /**
     * 借助归并排序的思想，只有治没有分c
     */
    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode tail = res;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }

            tail = tail.next;
        }

        if (l1 != null) {
            tail.next = l1;
        } else {
            tail.next = l2;
        }

        return res.next;
    }

    public static void main(String[] args){

    }
}
