package com.martix.x.pub.queue;

import java.util.LinkedList;

/**
 * Created by Andrew-Geng on 12:53 上午 2021/4/10
 * 单调队列  剑指 Offer 59 - II. 队列的最大值
 * <p>
 * 定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 * <p>
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 */
public class MonotonicQueueSolution {

    private LinkedList<Integer> oLinkedList;
    private LinkedList<Integer> mLinkedList;

    public MonotonicQueueSolution() {
        oLinkedList = new LinkedList<>();
        mLinkedList = new LinkedList<>();
    }

    public int max_value() {
        if (!mLinkedList.isEmpty()) {
            return mLinkedList.getFirst();
        }

        return -1;
    }

    public void push_back(int value) {
        while (!mLinkedList.isEmpty() && mLinkedList.getLast() < value) {
            mLinkedList.pollLast();
        }

        oLinkedList.addLast(value);
        mLinkedList.addLast(value);

    }

    public int pop_front() {
        if (oLinkedList.isEmpty()) {
            return -1;
        }

        int val = oLinkedList.pollFirst();
        if (val == mLinkedList.getFirst()) {
            mLinkedList.pollFirst();
        }

        return val;
    }
}
