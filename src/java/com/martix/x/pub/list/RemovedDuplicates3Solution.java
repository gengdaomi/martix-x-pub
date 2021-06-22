package com.martix.x.pub.list;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Andrew-Geng on 11:28 下午 2021/5/8
 * 移除重复节点 面试题 02.01
 * <p>
 * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
 * <p>
 * 示例1:
 * <p>
 * 输入：[1, 2, 3, 3, 2, 1]
 * 输出：[1, 2, 3]
 * 示例2:
 * <p>
 * 输入：[1, 1, 1, 1, 2]
 * 输出：[1, 2]
 * 提示：
 * <p>
 * 链表长度在[0, 20000]范围内。
 * 链表元素在[0, 20000]范围内。
 * 进阶：
 * <p>
 * 如果不得使用临时缓冲区，该怎么解决？
 */
public class RemovedDuplicates3Solution {

    /**
     * 传统的方式，创建了一个临时缓冲区hash区，缓冲各个节点的值
     * 时间复杂度 空间复杂度都为O(n)
     *
     * @param head
     * @return
     */
    public ListNode removeDuplicateNodes(ListNode head) {
        ListNode result = new ListNode(0);

        ListNode cur = result;
        Set<Integer> set = new HashSet<>();
        while (head != null) {
            if (!set.contains(head.val)) {
                set.add(head.val);

                cur.next = new ListNode(head.val);
                cur = cur.next;
            }
            head = head.next;
        }

        return result.next;
    }

    /**
     * 进阶：在保证方法一时间复杂度 O(N) 的前提下，是不存在这样的方法的
     * <p>
     * 时间复杂度：O(N^2)，空间复杂度O(1)
     *
     * @param head
     * @return
     */
    public ListNode removeDuplicateNodes_1(ListNode head) {
        ListNode result = head;

        while (result != null) {
            ListNode temp = result;

            while (temp.next != null) {
                if (temp.next.val == result.val) {
                    temp.next = temp.next.next;
                } else {
                    temp = temp.next;
                }
            }

            result = result.next;
        }

        return head;
    }
}
