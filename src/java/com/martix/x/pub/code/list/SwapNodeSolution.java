package com.martix.x.pub.code.list;

/**
 * Created by Andrew-Geng on 12:24 上午 2021/5/9
 * 交换链表中的节点
 * lc 1721
 * <p>
 * 给你链表的头节点 head 和一个整数 k 。
 * 交换 链表正数第 k 个节点和倒数第 k 个节点的值后，返回链表的头节点（链表 从 1 开始索引）。
 * <p>
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[1,4,3,2,5]
 * 示例 2：
 * <p>
 * 输入：head = [7,9,6,6,7,8,3,0,9,5], k = 5
 * 输出：[7,9,6,6,8,7,3,0,9,5]
 * 示例 3：
 * <p>
 * 输入：head = [1], k = 1
 * 输出：[1]
 * 示例 4：
 * <p>
 * 输入：head = [1,2], k = 1
 * 输出：[2,1]
 * 示例 5：
 * <p>
 * 输入：head = [1,2,3], k = 2
 * 输出：[1,2,3]
 *  
 * <p>
 * 提示：
 * <p>
 * 链表中节点的数目是 n
 * 1 <= k <= n <= 105
 * 0 <= Node.val <= 100
 */
public class SwapNodeSolution {

    /**
     * 通过交换节点的值，而不用交换两个节点
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode swapNodes(ListNode head, int k) {
        ListNode firstK = head, cur = head;

        int count = 1;
        while (cur != null) {
            if (count < k) {
                firstK = firstK.next;
            }
            count++;
            cur = cur.next;
        }

        ListNode lastK = this.findKthEndNode(head, k);

        int temp = firstK.val;
        firstK.val = lastK.val;
        lastK.val = temp;

        return head;
    }

    /**
     * 找到倒数的第K个节点
     *
     * @param head
     * @param k
     * @return
     */
    private ListNode findKthEndNode(ListNode head, int k) {
        ListNode slow = head, fast = head;

        while (k-- > 0) {
            fast = fast.next;
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    /**
     * 值交换
     * 找到倒数第k个节点和第k个节点后进行值交换
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode swapNodes_1(ListNode head, int k) {
        ListNode left = head;// 第k个节点
        ListNode right = head;// 倒数第k个节点

        for (int i = 1; i < k; i++) {
            left = left.next;
        }

        ListNode cur = left;
        while (cur.next != null) {
            right = right.next;
            cur = cur.next;
        }

        // 交换左右两个节点的值
        int m = right.val;
        right.val = left.val;
        left.val = m;

        return head;
    }

    /**
     * 节点交换
     * 需要很多指针，用来进行交换节点的定位
     * 以及分别考虑两种特殊情况：
     * <p>
     * 倒数第k个节点刚好在第k个节点的左侧一个节点
     * 第k个节点刚好在倒数第k个节点的右侧一个节点
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode swapNodes_2(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;// 因为头结点可能会发生交换，所以要构造一个哑结点

        ListNode pre1 = dummy;// pre1指向第k个节点的前一个节点
        ListNode left = dummy.next;// 第k个节点
        ListNode pre2 = dummy;// pre2指向倒数第k个节点的前一个节点
        ListNode right = dummy.next;// 倒数第k个节点

        for (int i = 1; i < k; i++) {
            pre1 = pre1.next;
            left = left.next;
        }

        ListNode cur = left;
        ListNode temp = left.next;// 第k个节点的后一个节点

        while (cur.next != null) {
            pre2 = pre2.next;
            right = right.next;
            cur = cur.next;
        }

        if (right == pre1) {// 特殊情况，倒数第k个节点在第k个节点的左侧
            right.next = temp;
            left.next = right;
            pre2.next = left;

        } else {// 特殊情况，第k个节点在倒数第k个节点的左侧
            left.next = right.next;

            if (pre2 == left) {
                right.next = left;
            } else {
                pre2.next = left;
                right.next = temp;
            }

            pre1.next = right;
        }

        return dummy.next;
    }

}
