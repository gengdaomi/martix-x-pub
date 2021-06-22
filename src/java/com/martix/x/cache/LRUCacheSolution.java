package com.martix.x.cache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Andrew-Geng 2020/3/24-3:41 下午
 * <p>
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * <p>
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * <p>
 * 进阶:
 * <p>
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * <p>
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // 返回  1
 * cache.put(3, 3);    // 该操作会使得密钥 2 作废
 * cache.get(2);       // 返回 -1 (未找到)
 * cache.put(4, 4);    // 该操作会使得密钥 1 作废
 * cache.get(1);       // 返回 -1 (未找到)
 * cache.get(3);       // 返回  3
 * cache.get(4);       // 返回  4
 */
public class LRUCacheSolution {
    private int capacity;
    private ConcurrentHashMap<Integer, Node> caches;
    private Node first;
    private Node last;

    public LRUCacheSolution(int capacity) {
        this.capacity = capacity;
        caches = new ConcurrentHashMap<>(capacity);
    }

    public void put(int key, int value) {
        Node node = caches.get(key);
        // 首先得先判断是否存在元素
        if (node == null) {
            // 如果不存在，先判断容量
            if (capacity <= caches.size()) {
                // 不够，先移除最后一个
                removeLast();
            }
            node = new Node();
            node.key = key;
        }
        node.value = value;
        moveNodeToFirst(node);
        caches.put(key, node);
    }

    public int get(int key) {
        Node node = caches.get(key);
        if (node == null) return -1;
        moveNodeToFirst(node);
        return node.value;
    }

    private void removeLast() {
        if (last != null) { //检查双向链表的尾结点是否存在具体节点

            caches.remove(last.key);
            last = last.pre;

            if (last != null) {
                last.next = null;
            } else { //判断最后一个节点是否为空
                first = null;
            }
        }
    }

    private void moveNodeToFirst(Node node) {
        if (node == first || node == null) {  //如果当前节点是首节点
            return;
        }

        if (node.pre != null) {
            node.pre.next = node.next;
        }
        if (node.next != null) { //如果当前移动节点 后置有节点，则将后置节点的pre指向当前节点的上一个节点
            node.next.pre = node.pre;
        }
        if (node == last) {//如果要移动的节点是最后一个节点
            last = last.pre;
        }
        if (last == null || first == null) { //如果双向链表为空
            last = first = node;
            return;
        }

        node.next = first;
        first.pre = node;
        first = node;
        node.pre = null;
    }

    class Node {
        Node next;
        Node pre;
        int key;
        int value;
    }

}

