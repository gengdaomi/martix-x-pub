package com.martix.x.pub.code.list;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrew-Geng on 13:35 2022/12/2
 * 从链表中删去总和值为零的连续节点 lc1171
 *
 * 给你一个链表的头节点head，请你编写代码，反复删去链表中由 总和值为 0 的连续节点组成的序列，直到不存在这样的序列为止。
 *
 * 删除完毕后，请你返回最终结果链表的头节点。
 *
 *
 * 你可以返回任何满足题目要求的答案。
 *
 * （注意，下面示例中的所有序列，都是对ListNode对象序列化的表示。）
 *
 * 示例 1：
 *
 * 输入：head = [1,2,-3,3,1]
 * 输出：[3,1]
 * 提示：答案 [1,2,1] 也是正确的。
 * 示例 2：
 *
 * 输入：head = [1,2,3,-3,4]
 * 输出：[1,2,4]
 * 示例 3：
 *
 * 输入：head = [1,2,3,-3,-2]
 * 输出：[1]
 *
 */
public class RemoveZeroSumSublistSolution {

    /**
     * 类似 前缀和
     *
     * 核心思路：
     * 思路就基本确定了，我们一边遍历链表，一遍记录当前的和，如果之前存在这个和，那么就把他们中间的节点都断掉
     *
     * 步骤如下：
     *
     * 利用Map记录前缀和sum以及当前节点p
     * 如果Map中不存在该sum,则加入Map
     * 如果Map中存在该sum,则将该sum对应的节点node.next指向当前节点p.next
     * 处理完之后，需要更新Map，将被断开部分的节点和从Map中去除。
     * 注意事项：
     *
     * 利用虚拟头节点来防止第一个节点需要被删除而导致的空指针
     * map中初始化放入(0, dummy)
     * @param head
     * @return
     */
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        Map<Integer, ListNode> map = new HashMap<>();

        // 首次遍历建立 节点处链表和<->节点 哈希表
        // 若同一和出现多次会覆盖，即记录该sum出现的最后一次节点
        int sum = 0;
        for (ListNode d = dummy; d != null; d = d.next) {
            sum += d.val;
            map.put(sum, d);
        }

        // 第二遍遍历 若当前节点处sum在下一处出现了则表明两结点之间所有节点和为0 直接删除区间所有节点
        sum = 0;
        for (ListNode d = dummy; d != null; d = d.next) {
            sum += d.val;
            d.next = map.get(sum).next;
        }

        return dummy.next;
    }
}
