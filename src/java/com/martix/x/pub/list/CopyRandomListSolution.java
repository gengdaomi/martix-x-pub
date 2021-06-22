package com.martix.x.pub.list;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrew-Geng on 2:58 下午 2021/6/17
 * <p>
 * 复制带随机指针的链表 lc 138
 * <p>
 * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
 * <p>
 * 构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。
 * <p>
 * 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
 * <p>
 * 返回复制链表的头节点。
 * <p>
 * 用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
 * <p>
 * val：一个表示 Node.val 的整数。
 * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
 * 你的代码 只 接受原链表的头节点 head 作为传入参数。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：head = [[1,1],[2,1]]
 * 输出：[[1,1],[2,1]]
 * 示例 3：
 * <p>
 * <p>
 * <p>
 * 输入：head = [[3,null],[3,0],[3,null]]
 * 输出：[[3,null],[3,0],[3,null]]
 * 示例 4：
 * <p>
 * 输入：head = []
 * 输出：[]
 * 解释：给定的链表为空（空指针），因此返回 null。
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= n <= 1000
 * -10000 <= Node.val <= 10000
 * Node.random 为空（null）或指向链表中的节点。
 * <p>
 * 链接：https://leetcode-cn.com/problems/copy-list-with-random-pointer
 */
public class CopyRandomListSolution {

    Map<Node, Node> visitedHashMap = new HashMap<Node, Node>(); //key 为旧的节点 ，value 为新的节点

    /**
     * 回溯：
     * 将链表想象成一张图。链表中每个节点都有 2 个指针；因为随机指针给图结构添加了随机性，所以我们可能会访问相同的节点多次，这样就形成了环；；
     * <p>
     * 核心思想：
     * 此方法中，我们只需要遍历整个图并拷贝它。拷贝的意思是每当遇到一个新的未访问过的节点，你都需要创造一个新的节点。遍历按照深度优先进行。
     * 我们需要在回溯的过程中记录已经访问过的节点，否则因为随机指针的存在我们可能会产生死循环；
     * <p>
     * <p>
     * 1. 从 头 指针开始遍历整个图。
     * 我们将链表看做一张图。下图对应的是上面的有向链表的例子，Head 是图的出发节点。
     * <p>
     * 2.当我们遍历到某个点时，如果我们已经有了当前节点的一个拷贝，我们不需要重复进行拷贝。
     * <p>
     * 3.如果我们还没拷贝过当前节点，我们创造一个新的节点，并把该节点放到已访问字典中，即：
     * visited_dictionary[current_node] = cloned_node_for_current_node.
     * <p>
     * 4.我们针对两种情况进行回溯调用：一个顺着 random 指针调用，另一个沿着 next 指针调用：
     * 步骤 1 中将 random 和 next 指针分别红红色和蓝色标注。然后我们分别对两个指针进行函数递归调调用；
     * cloned_node_for_current_node.next = copyRandomList(current_node.next);
     * cloned_node_for_current_node.random = copyRandomList(current_node.random);
     * <p>
     * <p>
     * 时间复杂度：O(N) ，其中N 是链表中节点的数目。
     * 空间复杂度：O(N)
     *
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        if (this.visitedHashMap.containsKey(head)) {  //如果已经处理过当前节点，那只需要返回克隆的版本
            return this.visitedHashMap.get(head);
        }

        Node node = new Node(head.val, null, null); //创建一个新节点，值和老的保持一致
        this.visitedHashMap.put(head, node);

        node.next = this.copyRandomList(head.next);
        node.random = this.copyRandomList(head.random);

        return node;
    }

    public Node copyRandomList_1(Node head) {
        if (head == null) {
            return null;
        }

        if (visitedHashMap.containsKey(head)) {
            return visitedHashMap.get(head);
        }

        Node root = new Node(head.val);
        visitedHashMap.put(head, root);

        root.next = copyRandomList(head.next);
        root.random = copyRandomList(head.random);

        return root;
    }


    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }

        public Node(int _val, Node _next, Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }
}
