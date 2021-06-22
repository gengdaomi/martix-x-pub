package com.martix.x.pub.stack;

import java.util.Stack;

/**
 * 最小栈
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素
 * Created By Andrew-Geng on 2020/5/15 9:31 下午
 */
public class MinStackMainSolution {

    Stack<Integer> stack = new Stack<>();

    int min = Integer.MAX_VALUE;

    /**
     * initialize your data structure here.
     */
    public MinStackMainSolution() {

    }

    public void push(int x) {
        if (x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        Integer result = stack.pop();
        if (result == min) {
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }

}
