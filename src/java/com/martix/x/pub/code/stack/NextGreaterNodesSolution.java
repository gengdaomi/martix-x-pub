package com.martix.x.pub.code.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Andrew-Geng on 9:35 上午 2021/5/9
 * 链表中的下一个更大节点 lc 1019
 * <p>
 * 给出一个以头节点head作为第一个节点的链表。链表中的节点分别编号为：node_1, node_2, node_3, ... 。
 * <p>
 * 每个节点都可能有下一个更大值（next larger value）：对于node_i，如果其next_larger(node_i)是node_j.val，
 * 那么就有j > i且node_j.val > node_i.val，而j是可能的选项中最小的那个。如果不存在这样的j，那么下一个更大值为0。
 * <p>
 * 返回整数答案数组answer，其中answer[i] = next_larger(node_{i+1})。
 * <p>
 * 注意：在下面的示例中，诸如 [2,1,5] 这样的输入（不是输出）是链表的序列化表示，其头节点的值为2，第二个节点值为 1，第三个节点值为5 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[2,1,5]
 * 输出：[5,5,0]
 * 示例 2：
 * <p>
 * 输入：[2,7,4,3,5]
 * 输出：[7,0,5,5,0]
 * 示例 3：
 * <p>
 * 输入：[1,7,5,1,9,2,5,1]
 * 输出：[7,9,9,9,0,5,0,0]
 *  
 * <p>
 * 提示：
 * <p>
 * 对于链表中的每个节点，1 <= node.val <= 10^9
 * 给定列表的长度在 [0, 10000] 范围内
 */
public class NextGreaterNodesSolution {

    /**
     * 单调栈
     * @param head
     * @return
     */
    public int[] nextLargerNodes(ListNode head) {
        List<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack();

        for (ListNode node = head; node != null; node = node.next) {
            while (!stack.isEmpty() && node.val > list.get(stack.peek())) {
                list.set(stack.pop(), node.val);
            }

            stack.push(list.size());
            list.add(node.val);
        }

        for (int i : stack) {
            list.set(i, 0);
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }

        return result;
    }

    public int[] nextLargerNodes_3(ListNode head) {
        if (head == null && head.next == null) {
            return new int[]{0};
        }

        List<Integer> arr = new ArrayList<>();
        while (head != null) {
            arr.add(head.val);
            head = head.next;
        }

        int[] result = new int[arr.size()];

        /*
           栈中存储的是元素的下标，并且从栈底到栈顶元素在集合中对应的
           值是从大到小的
         */
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.size(); i++) {
            while (!stack.isEmpty() && arr.get(i) > arr.get(stack.peek())) { //如果栈顶元素对应的值小于当前值，说明栈顶元素遇到了比他小的
                int index = stack.pop();
                result[index] = arr.get(i);
            }
            stack.push(i);
        }

        return result;
    }


    /**
     * 传统的方式，通过两次循环来实现
     *
     * @param head
     * @return
     */
    public int[] nextLargerNodes_4(ListNode head) {
        if (head == null && head.next == null) {
            return new int[]{0};
        }

        List<Integer> result = new ArrayList<>();
        while (head != null) {
            ListNode cur = new ListNode(head.val);
            result.add(this.nextLarge(cur, head.next));

            head = head.next;
        }

        int[] arr = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            arr[i] = result.get(i);
        }

        return arr;
    }

    private int nextLarge(ListNode curNode, ListNode listNode) {
        while (listNode != null) {
            if (curNode.val < listNode.val) {
                return listNode.val;
            }

            listNode = listNode.next;
        }

        return 0;
    }

    class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}
