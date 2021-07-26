package com.martix.x.pub.prefix;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrew-Geng on 9:58 下午 2021/7/26
 * 单词替换 lc 648
 *
 * 在英语中，我们有一个叫做 词根(root)的概念，它可以跟着其他一些词组成另一个较长的单词——我们称这个词为 继承词(successor)。例如，词根an，跟随着单词 other(其他)，可以形成新的单词 another(另一个)。
 *
 * 现在，给定一个由许多词根组成的词典和一个句子。你需要将句子中的所有继承词用词根替换掉。如果继承词有许多可以形成它的词根，则用最短的词根替换它。
 *
 * 你需要输出替换之后的句子。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
 * 输出："the cat was rat by the bat"
 * 示例 2：
 *
 * 输入：dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
 * 输出："a a b c"
 * 示例 3：
 *
 * 输入：dictionary = ["a", "aa", "aaa", "aaaa"], sentence = "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa"
 * 输出："a a a a a a a a bbb baba a"
 * 示例 4：
 *
 * 输入：dictionary = ["catt","cat","bat","rat"], sentence = "the cattle was rattled by the battery"
 * 输出："the cat was rat by the bat"
 * 示例 5：
 *
 * 输入：dictionary = ["ac","ab"], sentence = "it is abnormal that this solution is accepted"
 * 输出："it is ab that this solution is ac"
 *
 */
public class WordReplaceSolution {

    /**
     * 前缀树
     *
     * 核心思路：
     * 所有的词根放入前缀树中，在树上查找每个单词的最短词根，该操作可在线性时间内完成。
     * @param dictionary
     * @param sentence
     * @return
     */
    public String replaceWords(List<String> dictionary, String sentence) {
        TrieNode trie = new TrieNode();

        for (String root: dictionary) {
            TrieNode cur = trie;

            for (char letter: root.toCharArray()) {
                if (cur.children[letter - 'a'] == null) {
                    cur.children[letter - 'a'] = new TrieNode();
                }

                cur = cur.children[letter - 'a'];
            }

            cur.word = root;
        }

        StringBuilder result = new StringBuilder();

        for (String word: sentence.split("\\s+")) {
            if (result.length() > 0) {
                result.append(" ");
            }

            TrieNode cur = trie;
            for (char letter: word.toCharArray()) {
                if (cur.children[letter - 'a'] == null || cur.word != null) {
                    break;
                }

                cur = cur.children[letter - 'a'];
            }

            result.append(cur.word != null ? cur.word : word);
        }

        return result.toString();
    }

    class TrieNode { //前缀树
        TrieNode[] children;
        String word;
        TrieNode() {
            children = new TrieNode[26];
        }
    }

    /**
     * 前缀哈希
     *
     * 核心思路：
     * 遍历句子中每个单词，查看单词前缀是否为词根
     *
     * 将所有词根 roots 存储到集合 Set 中。遍历所有单词，判断其前缀是否为词根。如果是，则使用前缀代替该单词；否则不改变该单词。
     *
     * 时间复杂度O(sum(w^2)) ,单个单词的花费时间O(w^2)
     * @param dictionary
     * @param sentence
     * @return
     */
    public String replaceWords_1(List<String> dictionary, String sentence) {
        Set<String> rootSet = new HashSet();

        for (String root: dictionary){
            rootSet.add(root);
        }

        StringBuilder result = new StringBuilder();

        for (String word: sentence.split("\\s+")) {
            String prefix = "";
            for (int i = 1; i <= word.length(); ++i) {
                prefix = word.substring(0, i);

                if (rootSet.contains(prefix)){
                    break;
                }
            }

            if (result.length() > 0) {
                result.append(" ");
            }

            result.append(prefix);
        }

        return result.toString();
    }
}
