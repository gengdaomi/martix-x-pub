package com.martix.x.pub.list;

/**
 * Created by Andrew-Geng on 12:24 上午 2021/5/9
 * 交换链表中的节点 lc 1721
 * <p>
 * 给你链表的头节点 head 和一个整数 k 。
 * <p>
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
}
