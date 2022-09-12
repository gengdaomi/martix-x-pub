package com.martix.x.pub.code.validate;

import java.util.Stack;

/**
 * Created by Andrew-Geng on 11:28 下午 2021/4/20
 * 有效的括号 lc 20
 * <p>
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "()"
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：s = "()[]{}"
 * 输出：true
 * 示例 3：
 * <p>
 * 输入：s = "(]"
 * 输出：false
 * 示例 4：
 * <p>
 * 输入：s = "([)]"
 * 输出：false
 * 示例 5：
 * <p>
 * 输入：s = "{[]}"
 * 输出：true
 */
public class BracketsValidateSolution {

    public static void main(String[] args) {
        String s = "]";
        System.out.println(new BracketsValidateSolution().isValid(s));
    }

    /**
     * 借助栈的数据结构
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
            } else {
                if (!stack.isEmpty() && this.leftBrackets(c) == stack.peek()) {
                    stack.pop();
                }else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    /**
     * 返回右括号对应的左括号
     *
     * @param right
     * @return
     */
    private char leftBrackets(char right) {
        if (right == ']') {
            return '[';
        }

        if (right == '}') {
            return '{';
        }

        return '(';
    }
}
