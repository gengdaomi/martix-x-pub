package com.martix.x.pub.code.merge;


/**
 * Created by Andrew-Geng on 2:38 上午 2021/5/9
 * 合并两个链表 lc 1669
 * <p>
 * 给你两个链表list1 和list2，它们包含的元素分别为n 个和m 个。
 * <p>
 * 请你将list1中第a个节点到第b个节点删除，并将list2接在被删除节点的位置。
 * <p>
 * 下图中蓝色边和节点展示了操作后的结果：
 * <p>
 * 请你返回结果链表的头指针。
 * <p>
 * 输入：list1 = [0,1,2,3,4,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
 * 输出：[0,1,2,1000000,1000001,1000002,5]
 * 解释：我们删除 list1 中第三和第四个节点，并将 list2 接在该位置。上图中蓝色的边和节点为答案链表。
 */
public class MergeInBetweenSolution {

    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode list2Tail = list2;

        while (list2Tail.next != null) { //寻找链表2的尾结点
            list2Tail = list2Tail.next;
        }

        ListNode list1A = list1, list1B = list1;
        int count = 1;
        while (count < a) { //找到a-1位置的节点，因为要从a到b之间都要删除
            list1A = list1A.next;
            list1B = list1B.next;
            count++;
        }

        count = 0;
        while (count + a -1<= b) { //注意找到b+1的节点，因为要从a到b之间都要删除
            list1B = list1B.next;
            count++;
        }

        list1A.next = list2;
        list2Tail.next = list1B;

        return list1;
    }
}
