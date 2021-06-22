package com.martix.x.stack;

import java.util.Stack;

/**
 * Created By Andrew-Geng on 2020/11/7 10:05 下午
 * 两个栈实现一个队列 lc 232
 */
public class StackToQueueSolution<T> {

    private Stack<T> stack1;
    private Stack<T> stack2;

    public StackToQueueSolution() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(T t) {
        stack1.push(t);
    }

    public T pop() {
        if (stack2.isEmpty()) {
            if (stack1.isEmpty()) {
                return null;
            }

            this.transfer();
        }

        return stack2.pop();
    }

    public T peek() {
        if (stack2.isEmpty()) {
            if (stack1.isEmpty()) {
                return null;
            }

            this.transfer();
        }

        return stack2.peek();
    }

    public boolean isEmpty() {
        return stack1.empty() && stack2.empty();
    }

    private void transfer() {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
    }

}
