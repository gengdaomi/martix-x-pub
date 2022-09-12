package com.martix.x.pub.code.set;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew-Geng on 21:57 2022/9/12
 * 字母大小写全排列 lc 784
 *
 * 给定一个字符串 s ，通过将字符串 s 中的每个字母转变大小写，我们可以获得一个新的字符串。
 *
 * 返回 所有可能得到的字符串集合 。以 任意顺序 返回输出
 *
 * 输入：s = "a1b2"
 * 输出：["a1b2", "a1B2", "A1b2", "A1B2"]
 *
 * 输入: s = "3z4"
 * 输出: ["3z4","3Z4"]
 */
public class LetterCasePermutationSolution {

    /**
     * 这一类搜索问题是在一个隐式的树上进行的搜索问题，即「树形问题;
     * 这个问题所求的解，是这棵树的叶子结点上的值。因此，可以使用深度优先遍历，收集 所有 叶子结点的值，深度优先遍历用于搜索也叫回溯算法;
     * 回溯算法因为有回头的过程，因此其显著特征是 状态重置
     *
     * 特殊技巧：使用异或运算转换字母大小写
     * 大写字符与其对应的小写字符的 ASCII 的差为 32，32 这个值如果敏感的话，它是2的5次方，在编程语言中，可以表示为 1 << 5。
     * 所以变换大小写这件事等价于：
     * 1.如果字符是小写字符，减去 32 得到大写字符；
     * 2.如果字符是大写字符，加上 32 得到小写字符
     *
     * 而这两者合并起来，就是给这个字符做一次不进位的加法，即异或上 1 << 5
     *
     * @param s
     * @return
     */
    public List<String> letterCasePermutation(String s) {
        List<String> result= new ArrayList<>();
        char[] charArray = s.toCharArray();
        dfs(charArray, 0, result);

        return result;
    }

    private void dfs(char[] charArray, int index, List<String> res) {
        if (index == charArray.length) {
            res.add(new String(charArray));
            return;
        }

        dfs(charArray, index + 1, res);

        if (Character.isLetter(charArray[index])) {
            charArray[index] ^= 1 << 5;
            dfs(charArray, index + 1, res);
        }
    }

    /**
     * 遍历
     *
     * 思路：
     * 从左往右依次遍历字符，过程中保持 ans 为已遍历过字符的字母大小全排列。
     * 例如，当 S = "abc" 时，考虑字母 "a", "b", "c"，初始令 ans = [""]，
     * 依次更新 ans = ["a", "A"]， ans = ["ab", "Ab", "aB", "AB"]， ans = ["abc", "Abc", "aBc", "ABc", "abC", "AbC", "aBC", "ABC"]
     *
     * 算法：
     * 如果下一个字符 c 是字母，将当前已遍历过的字符串全排列复制两份，在第一份的每个字符串末尾添加 lowercase(c)，在第二份的每个字符串末尾添加 uppercase(c)。
     *
     * 如果下一个字符 c 是数字，将 c 直接添加到每个字符串的末尾
     *
     * @param s
     * @return
     */
    public List<String> letterCasePermutation_1(String s){
        List<StringBuilder> stringBuilderList = new ArrayList();
        stringBuilderList.add(new StringBuilder());

        for (char c: s.toCharArray()) {
            int n = stringBuilderList.size();
            if (Character.isLetter(c)) {
                for (int i = 0; i < n; i++) {
                    stringBuilderList.add(new StringBuilder(stringBuilderList.get(i)));
                    stringBuilderList.get(i).append(Character.toLowerCase(c));
                    stringBuilderList.get(n+i).append(Character.toUpperCase(c));
                }
            } else {
                for (int i = 0; i < n; i++)
                    stringBuilderList.get(i).append(c);
            }
        }

        List<String> result = new ArrayList();
        for (StringBuilder sb: stringBuilderList)
            result.add(sb.toString());
        return result;
    }

}
