package com.martix.x.pub.dymaic;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by Andrew-Geng on 1:32 上午 2021/4/29
 * 最长有效括号
 * <p>
 * lc 32 hard
 * <p>
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 * 示例 2：
 * <p>
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 * 示例 3：
 * <p>
 * 输入：s = ""
 * 输出：0
 */
public class LongestValidParenthesesSolution {

    /**
     * 动态规划
     * 时空复杂度 都为O(n)
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int maxans = 0;
        int[] dp = new int[s.length()];

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {

                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }

                maxans = Math.max(maxans, dp[i]);
            }
        }

        return maxans;
    }

    /**
     * 使用栈
     * <p>
     * 具体做法是我们始终保持栈底元素为当前已经遍历过的元素中「最后一个没有被匹配的右括号的下标」，
     * 这样的做法主要是考虑了边界条件的处理，栈里其他元素维护左括号的下标
     * <p>
     * 对于遇到的每个
     * ‘(’，我们将它的下标放入栈中
     * 对于遇到的每个‘)’ ，我们先弹出栈顶元素表示匹配了当前右括号：
     * 如果栈为空，说明当前的右括号为没有被匹配的右括号，我们将其下标放入栈中来更新我们之前提到的「最后一个没有被匹配的右括号的下标」
     * 如果栈不为空，当前右括号的下标减去栈顶元素即为「以该右括号为结尾的最长有效括号的长度」
     * 我们从前往后遍历字符串并更新答案即可。
     * <p>
     * 需要注意的是，如果一开始栈为空，第一个字符为左括号的时候我们会将其放入栈中，这样就不满足提及的「最后一个没有被匹配的右括号的下标」，
     * 为了保持统一，我们在一开始的时候往栈中放入一个值为−1 的元素。
     *
     * 时空复杂度O(n)
     * @param s
     * @return
     */
    public int longestValidParentheses_1(String s) {
        int maxans = 0;
        Deque<Integer> stack = new LinkedList<>();

        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();

                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }

        return maxans;
    }

    /**
     * 双指针
     *
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     *
     * @param s
     * @return
     */
    public int longestValidParentheses_2(String s){
        int left = 0, right = 0, maxlength = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }

        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }

        return maxlength;
    }
}
