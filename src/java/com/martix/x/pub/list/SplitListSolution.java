package com.martix.x.pub.list;

/**
 * Created by Andrew-Geng on 10:57 下午 2021/5/1
 * 分隔链表
 * lc 725
 * <p>
 * 给定一个头结点为 root 的链表, 编写一个函数以将链表分隔为 k 个连续的部分。
 * <p>
 * 每部分的长度应该尽可能的相等: 任意两部分的长度差距不能超过 1，也就是说可能有些部分为 null。
 * <p>
 * 这k个部分应该按照在链表中出现的顺序进行输出，并且排在前面的部分的长度应该大于或等于后面的长度。
 * <p>
 * 返回一个符合上述规则的链表的列表。
 * <p>
 * 举例： 1->2->3->4, k = 5 // 5 结果 [ [1], [2], [3], [4], null ]
 * <p>
 * 示例 1：
 * <p>
 * 输入:
 * root = [1, 2, 3], k = 5
 * 输出: [[1],[2],[3],[],[]]
 * 解释:
 * 输入输出各部分都应该是链表，而不是数组。
 * 例如, 输入的结点 root 的 val= 1, root.next.val = 2, \root.next.next.val = 3, 且 root.next.next.next = null。
 * 第一个输出 output[0] 是 output[0].val = 1, output[0].next = null。
 * 最后一个元素 output[4] 为 null, 它代表了最后一个部分为空链表。
 * 示例 2：
 * <p>
 * 输入:
 * root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3
 * 输出: [[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]]
 * 解释:
 * 输入被分成了几个连续的部分，并且每部分的长度相差不超过1.前面部分的长度大于等于后面部分的长度。
 */
public class SplitListSolution {

    /**
     * 在方法 1 中，我们知道每个部分的大小。我们将不创建新列表，而是直接拆分原链表，并根据需要返回指向原始链表中节点的指针列表
     * <p>
     * 时间复杂度 O(N+k) N指的是链表的结点数，若 k 很大，则还需要添加许多空列表
     * 空间复杂度：O(k)，存储答案时所需的额外空格
     *
     * @param root
     * @param k
     * @return
     */
    public ListNode[] splitListToParts(ListNode root, int k) {
        int length = 0;
        ListNode listNode = root;

        while (listNode != null) {
            listNode = listNode.next;
            length++;
        }

        ListNode[] listNodeArr = new ListNode[k];
        int width = length / k, remainder = length % k;

        listNode = root;
        for (int i = 0; i < k; i++) {
            ListNode head = listNode;

            int subLen = width + (i < remainder ? 1 : 0) - 1; //由于没有新创建一个链表，所以要减1
            for (int j = 0; j < subLen; j++) {
                if (listNode != null) {
                    listNode = listNode.next;
                }
            }

            if (listNode != null) {
                ListNode pre = listNode;
                listNode = listNode.next;
                pre.next = null;
            }

            listNodeArr[i] = head;
        }

        return listNodeArr;
    }

    /**
     * 额外创建新列表
     * <p>
     * 如果链表有
     * length 个结点，则分隔的链表中每个部分中都有
     * length/k 个结点，且前 length%k 部分有一个额外的结点
     * <p>
     * 现在对于每个部分，我们已经计算出该部分有多少个节点
     * subLength + (i < remainder ? 1 : 0)
     * <p>
     * 时间复杂度 O(N+k) N指的是链表的结点数，若 k 很大，则还需要添加许多空列表
     * 空间复杂度 O(max(N,k))
     *
     * @param root
     * @param k
     * @return
     */
    public ListNode[] splitListToParts_1(ListNode root, int k) {
        int length = 0;
        ListNode listNode = root;

        while (listNode != null) {
            listNode = listNode.next;
            length++;
        }

        ListNode[] listNodeArr = new ListNode[k];
        int width = length / k, remainder = length % k;

        listNode = root;
        for (int i = 0; i < k; i++) {
            ListNode head = new ListNode(0), cur = head;

            int subLen = width + (i < remainder ? 1 : 0);
            for (int j = 0; j < subLen; j++) {
                cur.next = new ListNode(listNode.val);
                cur = cur.next;

                if (listNode != null) {
                    listNode = listNode.next;
                }
            }
            listNodeArr[i] = head.next;
        }

        return listNodeArr;
    }
}
