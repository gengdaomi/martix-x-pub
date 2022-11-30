package com.martix.x.pub.code.stack;

import java.util.Stack;

/**
 * * 最小栈
 * * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * * <p>
 * * push(x) —— 将元素 x 推入栈中。
 * * pop() —— 删除栈顶的元素。
 * * top() —— 获取栈顶元素。
 * * getMin() —— 检索栈中的最小元素
 * Created By Andrew-Geng on 2020/5/15 9:39 下午
 */
public class MinStackSolution {

    Stack<Integer> dataStack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    public MinStackSolution() {

    }

    public void push(int x) {
        dataStack.push(x);

        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }

    }

    public void pop() {
        if (dataStack.peek().equals(minStack.peek())) {
            minStack.pop();
        }

        dataStack.pop();
    }

    public int top() {
        return dataStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
