package com.martix.x.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created By Andrew-Geng on 2020/11/7 10:02 下午
 * 通过队列实现栈
 * <p>
 * 实现方法：两个队列一个用作工作队列，另一个用作临时队列。
 * <p>
 * 插入数据：将该数据插入到工作队列队尾；
 * <p>
 * 弹出数据：首先将工作队列的前n-1个元素弹出到临时队列中，并返回最后一个元素，实现栈“后进先出”的功能。最后临时队列变为工作队列，工作队列变为临时队列，任务完成。
 */
public class QueueToStackSolution<T> {

    private Queue<T> queue1;
    private Queue<T> queue2;
    boolean flag;

    public QueueToStackSolution() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
        flag = true;
    }

    public void push(T t) {
        if (flag) {
            queue1.add(t);
        } else {
            queue2.add(t);
        }
    }

    public T pop() {
        if (flag) {
            return getT(queue1, queue2);
        } else {
            return getT(queue2, queue1);
        }
    }

    private T getT(Queue<T> queue2, Queue<T> queue1) {
        if (queue2.size() == 0) {
            return null;
        } else {
            while (queue2.size() != 1) {
                queue1.add(queue2.remove());
            }
            flag = !flag;
            return queue2.remove();
        }
    }

    public boolean isEmpty() {
        return flag ? queue1.isEmpty() : queue2.isEmpty();
    }

}
