package com.martix.x.pub.code.queue;

/**
 * Created by Andrew-Geng on 21:08 2022/12/19
 * 设计循环队列 lc 622
 *
 * 设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。
 *
 * 循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。
 *
 * 你的实现应该支持如下操作：
 *
 * MyCircularQueue(k): 构造器，设置队列长度为 k 。
 * Front: 从队首获取元素。如果队列为空，返回 -1 。
 * Rear: 获取队尾元素。如果队列为空，返回 -1 。
 * enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
 * deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
 * isEmpty(): 检查循环队列是否为空。
 * isFull(): 检查循环队列是否已满。
 *  
 *
 * 示例：
 *
 * MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
 * circularQueue.enQueue(1);  // 返回 true
 * circularQueue.enQueue(2);  // 返回 true
 * circularQueue.enQueue(3);  // 返回 true
 * circularQueue.enQueue(4);  // 返回 false，队列已满
 * circularQueue.Rear();  // 返回 3
 * circularQueue.isFull();  // 返回 true
 * circularQueue.deQueue();  // 返回 true
 * circularQueue.enQueue(4);  // 返回 true
 * circularQueue.Rear();  // 返回 4
 *  
 *
 * 提示：
 *
 * 所有的值都在 0 至 1000 的范围内；
 * 操作数将在 1 至 1000 的范围内；
 * 请不要使用内置的队列库
 */
public class CircularQueueListSolution {
    private ListNode head;
    private ListNode tail;
    private int capacity;
    private int size;

    /**
     * 可以用链表实现队列，用链表实现队列则较为简单，因为链表可以在O(1) 时间复杂度完成插入与删除。
     * 入队列时，将新的元素插入到链表的尾部；出队列时，将链表的头节点返回，并将头节点指向下一个节点。
     *
     * 循环队列的属性如下:
     * head：链表的头节点，队列的头节点。
     * tail：链表的尾节点，队列的尾节点。
     * capacity：队列的容量，即队列可以存储的最大元素数量。
     * size：队列当前的元素的数量
     *
     * 时间复杂度：初始化和每项操作的时间复杂度均为 O(1)。
     * 空间复杂度：O(k)，其中k 为给定的队列元素数目
     *
     * @param k
     */
    public CircularQueueListSolution(int k) {
        capacity = k;
        size = 0;
    }

    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        ListNode node = new ListNode(value);
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        ListNode node = head;
        head = head.next;
        size--;
        return true;
    }

    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return head.val;
    }

    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return tail.val;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public class ListNode {

        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
        }

        ListNode(int val,ListNode next) { this.val = val; this.next = next; }
    }
}
