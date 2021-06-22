package com.martix.x.list;

import java.util.Stack;

/**
 * Created by Andrew-Geng on 1:14 上午 2021/5/9
 * 从尾到头打印链表  剑指 Offer 06
 *
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 *  
 *
 * 限制：
 *
 * 0 <= 链表长度 <= 10000
 *
 */
public class ReversePrintSolution {

    public int[] reversePrint(ListNode head) {
        Stack<Integer>stack = new Stack<>();

        int count=0;
        while (head!=null){
            stack.push(head.val);
            head = head.next;
            count++;
        }

        int[] result = new int[count];
        for (int i=0;i<count;i++){
            result[i] = stack.pop();
        }

        return result;
    }
}
