package com.martix.x.pub.list;

import java.util.*;

/**
 * Created by Andrew-Geng on 5:15 下午 2021/6/20
 * 克隆图 lc 133
 * <p>
 * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
 * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）
 * <p>
 * <p>
 * 对于本题而言，我们需要明确图的深拷贝是在做什么，对于一张图而言，它的深拷贝即构建一张与原图结构，值均一样的图，但是其中的节点不再是原来图节点的引用。
 * 因此，为了深拷贝出整张图，我们需要知道整张图的结构以及对应节点的值。
 * <p>
 * 由于题目只给了我们一个节点的引用，因此为了知道整张图的结构以及对应节点的值，我们需要从给定的节点出发，进行「图的遍历」，并在遍历的过程中完成图的深拷贝。
 */
public class CloneGraphSolution {

    private Map<Node, Node> visitedHashMap = new HashMap<>(); //key 为旧的节点 ，value 为新的节点

    /**
     * 深度优先搜索
     *
     * 为了防止多次遍历同一个节点，陷入死循环，我们需要用一种数据结构记录已经被克隆过的节点
     * <p>
     * 核心思想：
     * 使用一个哈希表存储所有已被访问和克隆的节点。哈希表中的 key 是原始图中的节点，value 是克隆图中的对应节点；
     * 从给定节点开始遍历图。如果某个节点已经被访问过，则返回其克隆图中的对应节点。
     * 如果当前访问的节点不在哈希表中，则创建它的克隆节点并存储在哈希表中；
     * 注意：在进入递归之前，必须先创建克隆节点并保存在哈希表中。如果不保证这种顺序，可能会在递归中再次遇到同一个节点，再次遍历该节点时，陷入死循环；
     * <p>
     * 递归调用每个节点的邻接点。每个节点递归调用的次数等于邻接点的数量，每一次调用返回其对应邻接点的克隆节点，
     * 最终返回这些克隆邻接点的列表，将其放入对应克隆节点的邻接表中。
     * <p>
     * 这个是深度遍历
     * <p>
     * 时间复杂度 空间复杂度 O(N)
     *
     * @param node
     * @return
     */
    public Node cloneGraph(Node node) {
        if (node == null) {
            return node;
        }

        if (this.visitedHashMap.containsKey(node)) {  //如果已经处理过当前节点，那只需要返回克隆的版本
            return this.visitedHashMap.get(node);
        }

        Node cloneNode = new Node(node.val, new ArrayList());
        visitedHashMap.put(node, cloneNode);

        for (Node neighbor : node.neighbors) { //遍历该节点的邻居并更新克隆节点的邻居列表
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }

        return cloneNode;
    }

    /**
     * 广度遍历
     *
     * @param node
     * @return
     */
    public Node cloneGraph_1(Node node) {
        if (node == null) {
            return null;
        }

        Node clone = new Node(node.val, new ArrayList<>());
        visitedHashMap.put(node, clone);

        Deque<Node> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            Node oldNode = queue.poll();

            for (Node n : oldNode.neighbors) {
                if (!visitedHashMap.containsKey(n)) {
                    visitedHashMap.put(n, new Node(n.val, new ArrayList<>()));
                    queue.offer(n);
                }

                visitedHashMap.get(oldNode).neighbors.add(visitedHashMap.get(n));
            }
        }

        return clone;
    }

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
