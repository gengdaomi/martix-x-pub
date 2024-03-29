package com.martix.x.pub.code.list;

import java.util.Random;

/**
 * Created by Andrew-Geng on 4:58 下午 2021/7/20
 * 跳表的实现
 */
public class SkipListSolution<T> {

    private SkipListNode<T> head, tail;
    private int nodes;//节点总数
    private int listLevel;//层数
    private Random random;// 用于投掷硬币
    private static final double PROBABILITY = 0.5;//向上提升一个的概率

    public SkipListSolution() {
        random = new Random();
        clear();
    }

    /**
     * 清空跳跃表
     */
    public void clear() {
        head = new SkipListNode<T>(SkipListNode.HEAD_KEY, null);
        tail = new SkipListNode<T>(SkipListNode.TAIL_KEY, null);

        horizontalLink(head, tail);

        listLevel = 0;
        nodes = 0;
    }

    public boolean isEmpty() {
        return nodes == 0;
    }

    public int size() {
        return nodes;
    }

    /**
     * 在最下面一层，找到要插入的位置前面的那个key
     */
    private SkipListNode<T> findNode(int key) {
        SkipListNode<T> p = head;
        while (true) {
            while (p.right.key != SkipListNode.TAIL_KEY && p.right.key <= key) {
                p = p.right;
            }

            if (p.down != null) {
                p = p.down;
            } else {
                break;
            }
        }

        return p;
    }

    /**
     * 查找是否存储key，存在则返回该节点，否则返回null
     */
    public SkipListNode<T> search(int key) {
        SkipListNode<T> p = this.findNode(key);

        if (key == p.getKey()) {
            return p;
        } else {
            return null;
        }
    }

    /**
     * 向跳跃表中添加key-value
     */
    public void put(int k, T v) {
        SkipListNode<T> p = findNode(k);

        //如果key值相同，替换原来的value即可结束
        if (k == p.getKey()) {
            p.value = v;
            return;
        }

        SkipListNode<T> q = new SkipListNode<T>(k, v);
        backLink(p, q);
        int currentLevel = 0;//当前所在的层级是0
        //抛硬币
        while (random.nextDouble() < PROBABILITY) {
            //如果超出了高度，需要重新建一个顶层
            if (currentLevel >= listLevel) {
                listLevel++;
                SkipListNode<T> p1 = new SkipListNode<T>(SkipListNode.HEAD_KEY, null);
                SkipListNode<T> p2 = new SkipListNode<T>(SkipListNode.TAIL_KEY, null);
                horizontalLink(p1, p2);
                vertiacallLink(p1, head);
                vertiacallLink(p2, tail);
                head = p1;
                tail = p2;
            }

            //将p移动到上一层
            while (p.up == null) {
                p = p.left;
            }
            p = p.up;

            SkipListNode<T> e = new SkipListNode<T>(k, null);//只保存key就ok
            backLink(p, e);//将e插入到p的后面
            vertiacallLink(e, q);//将e和q上下连接
            q = e;
            currentLevel++;
        }

        nodes++;//层数递增
    }

    //node1后面插入node2
    private void backLink(SkipListNode<T> node1, SkipListNode<T> node2) {
        node2.left = node1;
        node2.right = node1.right;
        node1.right.left = node2;
        node1.right = node2;
    }

    /**
     * 水平双向连接
     */
    private void horizontalLink(SkipListNode<T> node1, SkipListNode<T> node2) {
        node1.right = node2;
        node2.left = node1;
    }

    /**
     * 垂直双向连接
     */
    private void vertiacallLink(SkipListNode<T> node1, SkipListNode<T> node2) {
        node1.down = node2;
        node2.up = node1;
    }


    /**
     * 跳表的节点
     *
     * @param <T>
     */
    class SkipListNode<T> {
        public int key;
        public T value;
        public SkipListNode<T> up, down, left, right; // 上下左右 四个指针

        public static final int HEAD_KEY = Integer.MIN_VALUE; // 负无穷
        public static final int TAIL_KEY = Integer.MAX_VALUE; // 正无穷

        public SkipListNode(int k, T v) {
            key = k;
            value = v;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (o == null) {
                return false;
            }

//            if (!(o instanceof SkipListNode<?>)) {
//                return false;
//            }

            SkipListNode<T> ent;
            try {
                ent = (SkipListNode<T>) o; // 检测类型
            } catch (ClassCastException ex) {
                return false;
            }

            return (ent.getKey() == key) && (ent.getValue() == value);
        }

        @Override
        public String toString() {
            return "key-value:" + key + "-" + value;
        }
    }
}
