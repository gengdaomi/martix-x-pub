package com.martix.x.palindrome;

/**
 * Created by Andrew-Geng on 2:34 下午 2021/4/13
 * <p>
 * 判断一个链表是否是回文链表 lc 234
 * <p>
 * 请判断一个链表是否为回文链表。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2
 * 输出: false
 * <p>
 * 输入: 1->2->2->1
 * 输出: true
 */
public class PalindromeListValidateSolution {

    private ListNode left; //左侧指针

    /**
     * 时间复杂度是O(N), 空间复杂度O(N)
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        left = head;
        return this.traverse(head);
    }

    /**
     * 时间复杂度是O(N), 空间复杂度O(1)
     * <p>
     * 借助双指针(快慢指针)的思路，找到中间节点，需要注意的是整个链表的个数可能是奇数 也可能是偶数
     * 当奇数时，fast指针最后是没有指向null的，这个时候需要slow指针再继续向前一步；
     * 当偶数时，fast指针是最后指向null的，不需要特殊处理
     * <p>
     * 从slow的位置开始反转后半段链表
     * 然后依次前后比较节点的值是否相等
     *
     * @param head
     * @return
     */
    public boolean isPalindrome_1(ListNode head) {
        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        if (fast != null) {  //链表为奇数时，需要慢指针继续向前一步
            slow = slow.next;
        }

        ListNode left = head;
        ListNode right = reverse(slow);

        while (right != null) {
            if (left.val != right.val) {
                return false;
            }

            left = left.next;
            right = right.next;
        }

        return true;
    }

    /**
     * 这种方式是借助二叉树后序遍历的思路，倒序遍历链表
     * <p>
     * 时间复杂度O(N)，由于使用的是递归，即使用的是栈形式，所以空间复杂度是O（N）
     *
     * @param head
     * @return
     */
    private boolean traverse(ListNode head) {
        if (head == null) {
            return true;
        }

        boolean result = traverse(head.next);

        result = result && (head.val == left.val);
        left = left.next;

        return result;
    }

    /**
     * 反转链表，借助lc 206的迭代的方式 可以保障在时间复杂度为O（N）的前提下，空间复杂度是O(1)
     *
     * @param head
     * @return
     */
    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    class ListNode {

        ListNode next;
        Integer val;

        public ListNode(Integer val) {
            this.val = val;
            this.next = null;
        }
    }
}
