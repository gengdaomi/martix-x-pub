package com.martix.x.pub.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created By Andrew-Geng on 2020/5/19 4:27 下午
 * 自己实现一个阻塞对列
 */
public class BlockingQueue<T> {

    private int size;
    private Object[] queue;

    private Lock lock = new ReentrantLock();
    private Condition full = lock.newCondition();
    private Condition empty = lock.newCondition();

    private int index;
    private int removeIndex;
    private int currLen;

    public BlockingQueue() {
        this(10);
    }

    public BlockingQueue(int size) {
        this.index = 0;
        this.removeIndex = 0;
        this.currLen = 0;
        this.size = size;
        queue = new Object[size];
    }

    public void push(T element) throws InterruptedException {
        lock.lock();
        try {
            while (currLen == size) {
                System.out.println("队列满。。。");
                full.await();
            }
            queue[index] = element;
            if (++index == size) {
                index = 0;
            }
            currLen++;
            empty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T pop() throws InterruptedException {
        lock.lock();
        try {
            while (currLen == 0) {
                System.out.println("队列空。。。");
                empty.await();
            }
            Object obj = queue[removeIndex];
            if (++removeIndex == size) {
                removeIndex = 0;
            }
            currLen--;
            full.signal();
            return (T) obj;
        } finally {
            lock.unlock();
        }
    }
}
