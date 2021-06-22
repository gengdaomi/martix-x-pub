package com.martix.x.cache;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * * source: bytedancer
 * * <p>
 * * 全 O(1) 的数据结构
 * * <p>
 * * 请你实现一个数据结构支持以下操作：
 * * <p>
 * * Inc(key) - 插入一个新的值为 1 的 key。或者使一个存在的 key 增加一，保证 key 不为空字符串。
 * * Dec(key) - 如果这个 key 的值是 1，那么把他从数据结构中移除掉。否则使一个存在的 key 值减一。如果这个 key 不存在，这个函数不做任何事情。key 保证不为空字符串。
 * * GetMaxKey() - 返回 key 中值最大的任意一个。如果没有元素存在，返回一个空字符串"" 。
 * * GetMinKey() - 返回 key 中值最小的任意一个。如果没有元素存在，返回一个空字符串""。
 * * <p>
 * * <p>
 * * 挑战：
 * * <p>
 * * 你能够以 O(1) 的时间复杂度实现所有操作吗
 * * <p>
 * * <p>
 * * * Your AllOne object will be instantiated and called as such:
 * * * AllOne obj = new AllOne();
 * * * obj.inc(key);
 * * * obj.dec(key);
 * * * String param_3 = obj.getMaxKey();
 * * * String param_4 = obj.getMinKey();
 * * <p>
 * * <p>
 * <p>
 * 解题思路：
 * <p>
 * 要做到添加，删除，取最大最小值 全O(1)那必然少不了map和list;而且每次节点的值只能+1或者-1,也就是说每次变化只和自己的前一个状态或者后一个状态进行比较(前一个比自己大或者后一个比自己小的数),通过双向链表可以在O(1)时间内访问前后节点。因为要取最大最小值那么我们要维护一个有序是双向链表。所以题目的核心就是要在O(1)的操作内维护一个有序双向链表(递增，递减都可以，看你如何对链表操作)。
 *   由上述可知，必须要创建DlinkList 和 Map 其中 map: key => val 的映射 因为val值是通过双向链表的节点维护也就等于map: key => listNode => listNode.val 的映射;当有新节点添加时直接添加DlinkList至头节点之后;现在可以使添加和删除是O(1)操作了。
 *   根据前面的操作还不能得到一个有序的双向链表。如果按一般的做法，当调用dec() || inc()我们要遍历链表的状态来维护当前节点的位置，显然这样做的时间复杂度是O(n)不符合要求。但是因为每次节点的值只会+1或者-1，因此我们只需要维护一个值对应其值的头尾节点集合,即是map: val => [head, tail] ，head.val == tail.val == val 的映射,-1时将操作节点插入当前值头节点之前，+1时插入当前值尾节点之后。并维护新值的映射即可。这样就能O(1)时间维护双向链表有序。
 * <p>
 * Created By Andrew-Geng on 2020/5/13 11:11 下午
 */
public class AllOneMainSolution {

    /*
     * map1保存key-value 的映射
     */
    private Map<String, Integer> map1;

    /*
     map2保存val->keys 的映射， DLinkedNode为双向链表节点

     map2的作用是为了O(1)时间拿到统计次数对应的链表节点
     链表中的所有操作只会涉及到前一个节点或者后一个节点，时间也为O(1)

     */
    private Map<Integer, DLinkedNode> map2;

    /*
       双向链表的头， 双向链表从head到tail的value值依次减小
     */
    private DLinkedNode head;

    /*
     双向链表的尾
     */
    private DLinkedNode tail;

    /**
     * Initialize your data structure here.
     */
    public AllOneMainSolution() {
        map1 = new HashMap<>();
        map2 = new HashMap<>();

        head = new DLinkedNode(0);
        tail = new DLinkedNode(0);

        head.next = tail;
        tail.pre = head;
    }

    /**
     * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
     */
    public void inc(String key) {
        if (!map1.containsKey(key)) { //map中不包含对应key
            map1.put(key, 1);
            DLinkedNode node = map2.get(1);

            // 如果当前没有统计次数为1的节点，则新建节点并插入到双向链表的尾部，因为统计次数最小为1
            // 并将1->newNode的映射加入到map2中
            if (node == null) {
                DLinkedNode newNode = new DLinkedNode(1);
                newNode.keys.add(key);
                newNode.next = tail;
                newNode.pre = tail.pre;
                tail.pre.next = newNode;
                tail.pre = newNode;

                map2.put(1, newNode);
            } else {
                node.keys.add(key);
            }

            return;
        }

        int val = map1.get(key);
        map1.put(key, val + 1);

        // 根据value拿到次数更新前的node
        DLinkedNode node = map2.get(val);

        // value加一后，从原node的Set中删除key
        node.keys.remove(key);
        DLinkedNode preNode = node.pre;

        // 当前一个node为head或前一个node的次数统计大于val+1时，
        // 表示还目前没有统计次数为val+1的key，
        // 此时应该新建一个DLinkedNode，将newNode插入到preNode和node之间，并把key加入到newNode的保存key的Set中
        // 同时，将新的统计次数（val+1）和新节点newNode的映射加入到map2中
        if (preNode == head || preNode.val > val + 1) {
            DLinkedNode newNode = new DLinkedNode(val + 1);
            newNode.keys.add(key);
            newNode.next = node;
            newNode.pre = preNode;
            preNode.next = newNode;
            node.pre = newNode;
            map2.put(val + 1, newNode);
            preNode = newNode;
        } else {    // 如果当前已经有统计次数为val+1的节点，只需key加入到Set中即可
            preNode.keys.add(key);
        }

        // 如果原节点在移除key后size为0，则删除该节点，并在map2中删除val->node的映射
        if (node.keys.size() == 0) {
            preNode.next = node.next;
            node.next.pre = preNode;
            map2.remove(val);
        }

        return;
    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
     */
    public void dec(String key) {
        if (map1.containsKey(key)) { //如果存在
            int val = map1.get(key); //获取当前val的值

            DLinkedNode node = map2.get(val);//当前统计次数对应的节点
            node.keys.remove(key); //从节点的keys set中移除当前key

            //如果原统计次数为1，从map1中移除当前key
            if (val == 1) {
                map1.remove(key);
            } else {// 更新map1中的统计次数
                map1.put(key, val - 1);

                DLinkedNode nextNode = node.next; //拿到当前节点的下一个节点

                // 如果下一个节点为链表尾部或下一个节点的统计次数小于val-1
                // 则新建一个节点，统计次数为val-1，将当前key加入到keys Set中
                // 并将新节点插入到当前节点的后面，同时更新map2
                if (nextNode == tail || nextNode.val < val - 1) {
                    DLinkedNode newNode = new DLinkedNode(val - 1);
                    newNode.keys.add(key);
                    newNode.pre = node;
                    newNode.next = nextNode;
                    node.next = newNode;
                    nextNode.pre = newNode;
                    map2.put(val - 1, newNode);
                } else {
                    // 下一个节点的统计次数为val-1，将key加到下一节点的keys Set中
                    nextNode.keys.add(key);
                }
            }
        }
    }

    /**
     * // 按照双向链表的定义，如果链表中存在节点（head和tail不算，dummy节点），则对应最大value的keys为head的下一个节点
     * <p>
     * Returns one of the keys with maximal value.
     */
    public String getMaxKey() {
        if (head.next == tail) {
            return "";
        }

        return head.next.keys.iterator().next();
    }

    /**
     * Returns one of the keys with Minimal value
     * <p>
     * 按照双向链表的定义，如果链表中存在节点（head和tail不算，dummy节点），则对应最小value的keys为tail的前一个节点
     *
     * @return
     */
    public String getMinKey() {
        if (tail.pre == head) {
            return "";
        }

        return tail.pre.keys.iterator().next();
    }

    private class DLinkedNode {
        int val;
        Set<String> keys;

        DLinkedNode pre;
        DLinkedNode next;

        public DLinkedNode(int val) {
            this.val = val;
            this.keys = new HashSet<>();
        }
    }
}
