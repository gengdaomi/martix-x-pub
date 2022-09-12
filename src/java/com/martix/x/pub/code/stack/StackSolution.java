package com.martix.x.pub.code.stack;

/**
 * Created By Andrew-Geng on 2020/6/4 12:24 上午
 * 实现一个栈
 */
public class StackSolution<T> {

    private Node<T> root;

    private Node<T> tail;

    /**
     * 有前驱指针的节点
     * <p>
     * root <- tail
     * root.pre = null
     * tail.pre = root
     */
    public T push(T t) {
        if (root == null) {
            root = new Node<T>(t);
            tail = root;
            return t;
        }
        Node<T> node = new Node<T>(t);
        node.pre = tail;
        tail = node;
        return t;
    }

    public T pop() {
        if (null == root) {
            throw new RuntimeException("stack is empty");
        }

        T ret = null;
        if (root == tail) {
            ret = root.value;
            root = null;
            tail = null;
            return ret;
        }
        ret = tail.value;
        tail = tail.pre;
        return ret;
    }

    @Override
    public String toString() {
        return tail.toString();
    }

    static class Node<T> {
        T value;
        Node<T> pre;

        Node(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            if (this.pre == null) {
                return String.valueOf(this.value);
            }
            return this.pre.toString() + " <- " + this.value;
        }
    }
    
    public static void main(String[] args) {
    }
}
