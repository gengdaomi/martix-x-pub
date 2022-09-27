package com.martix.x.pub.code.string;

import java.util.Stack;

/**
 * Created by Andrew-Geng on 00:34 2022/9/24
 * 去除重复字母 lc 316
 *
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 *
 *
 * 示例 1：
 *
 * 输入：s = "bcabc"
 * 输出："abc"
 * 示例 2：
 *
 * 输入：s = "cbacdcbc"
 * 输出："acdb"
 */
public class RemoveDuplicateCharSolution {

    /**
     * 单调栈的思路
     *
     * 题目的要求总结出来有三点：
     *
     * 要求一、要去重。
     * 要求二、去重字符串中的字符顺序不能打乱 s 中字符出现的相对顺序。
     * 要求三、在所有符合上一条要求的去重字符串中，字典序最小的作为最终结果。
     *
     *
     * 我们的算法在 stk.peek() > c 时才会 pop 元素，其实这时候应该分两种情况：
     * 情况一、如果 stk.peek() 这个字符之后还会出现，那么可以把它 pop 出去，反正后面还有嘛，后面再 push 到栈里，刚好符合字典序的要求。
     * 情况二、如果 stk.peek() 这个字符之后不会出现了，前面也说了栈中不会存在重复的元素，那么就不能把它 pop 出去，否则你就永远失去了这个字符。
     *
     *用了一个计数器 count，当字典序较小的字符试图「挤掉」栈顶元素的时候，在 count 中检查栈顶元素是否是唯一的，只有当后面还存在栈顶元素的时候才能挤掉，否则不能挤掉。
     *
     * 时间空间复杂度O(n)
     * @param s
     * @return
     */
    public String removeDuplicateLetters(String s) {
        Stack<Character> stk = new Stack<>();

        // 维护一个计数器记录字符串中字符的数量
        // 因为输入为 ASCII 字符，大小 256 够用了
        int[] count = new int[256];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]++;
        }

        boolean[] inStack = new boolean[256];
        for (char c : s.toCharArray()) {
            count[c]--;// 每遍历过一个字符，都将对应的计数减一

            if (inStack[c]){
                continue;
            }

            while (!stk.isEmpty() && stk.peek() > c) {
                // 若之后不存在栈顶元素了，则停止 pop
                if (count[stk.peek()] == 0) {
                    break;
                }
                // 若之后还有，则可以 pop
                inStack[stk.pop()] = false;
            }

            stk.push(c);
            inStack[c] = true;
        }

        StringBuilder sb = new StringBuilder();
        while (!stk.empty()) {
            sb.append(stk.pop());
        }

        return sb.reverse().toString();
    }
}
