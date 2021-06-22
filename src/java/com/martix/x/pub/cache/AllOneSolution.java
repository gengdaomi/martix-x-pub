package com.martix.x.pub.cache;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * source: bytedancer
 * <p>
 * 全 O(1) 的数据结构
 * <p>
 * 请你实现一个数据结构支持以下操作：
 * <p>
 * Inc(key) - 插入一个新的值为 1 的 key。或者使一个存在的 key 增加一，保证 key 不为空字符串。
 * Dec(key) - 如果这个 key 的值是 1，那么把他从数据结构中移除掉。否则使一个存在的 key 值减一。如果这个 key 不存在，这个函数不做任何事情。key 保证不为空字符串。
 * GetMaxKey() - 返回 key 中值最大的任意一个。如果没有元素存在，返回一个空字符串"" 。
 * GetMinKey() - 返回 key 中值最小的任意一个。如果没有元素存在，返回一个空字符串""。
 * <p>
 * <p>
 * 挑战：
 * <p>
 * 你能够以 O(1) 的时间复杂度实现所有操作吗
 * <p>
 * <p>
 * * Your AllOne object will be instantiated and called as such:
 * * AllOne obj = new AllOne();
 * * obj.inc(key);
 * * obj.dec(key);
 * * String param_3 = obj.getMaxKey();
 * * String param_4 = obj.getMinKey();
 * <p>
 * <p>
 * Created By Andrew-Geng on 2020/5/13 9:53 下午
 */
public class AllOneSolution {

    public static void main(String[] args) {

    }

    HashMap<String, Integer> map;

    /**
     * Initialize your data structure here.
     */
    public AllOneSolution() {
        map = new HashMap<>();
    }

    /**
     * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
     */
    public void inc(String key) {
        if (key == null || key.length() == 0) {
            return;
        }

        if (map.containsKey(key)) {
            map.put(key, map.get(key) + 1);
        } else {
            map.put(key, 1);
        }
    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
     */
    public void dec(String key) {
        if (!map.containsKey(key)) {
            return;
        }
        int val = map.get(key);
        if (val == 1) {
            map.remove(key);
        } else {
            map.put(key, val - 1);
        }
        return;
    }

    /**
     * Returns one of the keys with maximal value.
     */
    public String getMaxKey() {
        if (map.size() == 0) {
            return "";
        }
        int max = Integer.MIN_VALUE;
        String maxKey = "";
        for (String key : map.keySet()
        ) {
            if (map.get(key) > max) {
                max = map.get(key);
                maxKey = key;
            }

        }
        return maxKey;
    }

    /**
     * Returns one of the keys with Minimal value.
     */
    public String getMinKey() {
        if (map.size() == 0)
            return "";
        int min = Integer.MAX_VALUE;
        String minKey = "";
        for (String key : map.keySet()) {
            if (map.get(key) < min) {
                min = map.get(key);
                minKey = key;
            }
        }
        return minKey;
    }

    private class DLinkedNode {
        int val;
        Set<String> keys;
        DLinkedNode pre, next;

        public DLinkedNode(int val) {
            this.val = val;
            this.keys = new HashSet<>();
        }
    }
}
