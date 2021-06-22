package com.martix.x.list;

/**
 * Created By Andrew-Geng on 2020/11/7 10:27 下午
 * <p>
 * 给定一个链表，判断链表中是否有环。
 * lc 141
 * <p>
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * <p>
 * 如果链表中存在环，则返回 true 。 否则，返回 false
 */
public class CycleCheckListSolution {

    /**
     * 定义两个指针，p1和p2，指针p1每次走一步，指针p2每次走两步，如果链表中存在环，则必然会在某个时刻满足p1==p2
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            if (slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        return false;
    }
}
