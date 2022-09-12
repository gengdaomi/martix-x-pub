package com.martix.x.pub.code.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Andrew-Geng on 11:27 下午 2021/7/15
 * 填充每个节点的下一个右侧节点指针 lc 116
 *
 * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 *
 * 初始状态下，所有next 指针都被设置为 NULL。
 *
 *  
 *
 * 进阶：
 *
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 *
 * 输入：root = [1,2,3,4,5,6,7]
 * 输出：[1,#,2,3,#,4,5,6,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由 next 指针连接，'#' 标志着每一层的结束。
 *
 */
public class ConnectRightNodeTreeSolution {

    /**
     * 层次遍历
     *
     * 核心：
     * 将二叉树的每一层节点都连接起来形成一个链表。
     * 因此直观的做法我们可以对二叉树进行层次遍历，在层次遍历的过程中将我们将二叉树每一层的节点拿出来遍历并连接；
     *
     * 时间 空间复杂度：O(N)；
     * 每个节点会被访问一次且只会被访问一次；
     * 这是一棵完美二叉树，它的最后一个层级包含N/2 个节点。广度优先遍历的复杂度取决于一个层级上的最大元素数量。这种情况下空间复杂度为O(N)
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }

        // 初始化队列同时将第一层节点加入队列中，即根节点
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);

        // 外层的 while 循环迭代的是层数
        while (!queue.isEmpty()) {

            // 记录当前队列大小
            int size = queue.size();

            // 遍历这一层的所有节点
            for (int i = 0; i < size; i++) {

                // 从队首取出元素
                Node node = queue.poll();

                // 连接
                if (i < size - 1) {
                    node.next = queue.peek();
                }

                // 拓展下一层节点
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }

        // 返回根节点
        return root;
    }

    /**
     * 使用已建立的next 指针
     * 思路：
     * 一棵树中，存在两种类型的next 指针。
     * 1.第一种情况是连接同一个父节点的两个子节点。它们可以通过同一个节点直接访问到，因此执行下面操作即可完成连接。
     * 即node.left.next = node.right
     *
     * 2.第二种情况在不同父亲的子节点之间建立连接，这种情况不能直接连接；
     * 如果每个节点有指向父节点的指针，可以通过该指针找到next 节点；
     *
     * 第N 层节点之间建立next 指针后，再建立第N+1 层节点的next 指针。可以通过next 指针访问同一层的所有节点，因此可以使用第N 层的next 指针，为第N+1 层节点建立next 指针；；
     *
     *
     * 算法：
     * 从根节点开始，由于第0 层只有一个节点，所以不需要连接，直接为第1层节点建立next 指针即可。
     * 该算法中需要注意的一点是，当我们为第N 层节点建立next 指针时，处于第N−1 层。
     * 当第N 层节点的next 指针全部建立完成后，移至第N 层，建立第 N+1 层节点的 next 指针。
     * 遍历某一层的节点时，这层节点的next 指针已经建立。因此我们只需要知道这一层的最左节点，就可以按照链表方式遍历，不需要使用队列

     * @param root
     * @return
     */
    public Node connect_1(Node root){
        if (root == null) {
            return root;
        }

        // 从根节点开始
        Node leftmost = root;

        while (leftmost.left != null) {

            // 遍历这一层节点组织成的链表，为下一层的节点更新 next 指针
            Node head = leftmost;

            while (head != null) {

                // CONNECTION 1
                head.left.next = head.right;

                // CONNECTION 2
                if (head.next != null) {
                    head.right.next = head.next.left;
                }

                // 指针向后移动
                head = head.next;
            }

            // 去下一层的最左的节点
            leftmost = leftmost.left;
        }

        return root;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
