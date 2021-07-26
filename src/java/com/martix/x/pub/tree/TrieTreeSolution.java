package com.martix.x.pub.tree;

/**
 * Created by Andrew-Geng on 9:48 下午 2021/7/26
 * 实现 Trie (前缀树) lc 208
 *
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 *
 * 请你实现 Trie 类：
 *
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 *  
 *
 * 示例：
 *
 * 输入
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * 输出
 * [null, null, true, false, true, null, true]
 *
 * 解释
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 True
 * trie.search("app");     // 返回 False
 * trie.startsWith("app"); // 返回 True
 * trie.insert("app");
 * trie.search("app");     // 返回 True
 *
 */
public class TrieTreeSolution {

    private TrieTreeSolution[] children;
    private boolean isEnd;

    /**
     * Initialize your data structure here.
     *
     * Trie，又称前缀树或字典树，是一棵有根树，其每个节点包含以下字段:
     * 1.指向子节点的指针数组children。对于本题而言，数组长度为26，即小写英文字母的数量。此时children[0] 对应小写字母a，children[1] 对应小写字母b，…，children[25] 对应小写字母z。
     * 2.布尔字段isEnd，表示该节点是否为字符串的结尾。
     *
     */
    public TrieTreeSolution() {
        children = new TrieTreeSolution[26];
        isEnd = false;
    }

    /**
     * Inserts a word into the trie.
     *
     * 1.子节点存在。沿着指针移动到子节点，继续处理下一个字符。
     * 2.子节点不存在。创建一个新的子节点，记录在children 数组的对应位置上，然后沿着指针移动到子节点，继续搜索下一个字符。
     */
    public void insert(String word) {
        TrieTreeSolution node = this;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';

            if (node.children[index] == null) {
                node.children[index] = new TrieTreeSolution();
            }
            node = node.children[index];
        }
        node.isEnd = true;

    }

    /**
     *
     * Returns if the word is in the trie.
     *
     * 子节点存在。沿着指针移动到子节点，继续搜索下一个字符。
     * 子节点不存在。说明字典树中不包含该前缀，返回空指针。
     * */
    public boolean search(String word) {
        TrieTreeSolution node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private TrieTreeSolution searchPrefix(String prefix) {
        TrieTreeSolution node = this;

        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            int index = ch - 'a';

            if (node.children[index] == null) {
                return null;
            }

            node = node.children[index];
        }

        return node;
    }
}
