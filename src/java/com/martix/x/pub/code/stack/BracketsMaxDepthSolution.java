package com.martix.x.pub.code.stack;

/**
 * Created by Andrew-Geng on 17:41 2022/11/30
 * 括号的最大嵌套深度 lc1614
 * <p>
 * 如果字符串满足以下条件之一，则可以称之为 有效括号字符串（valid parentheses string，可以简写为 VPS）：
 * <p>
 * 字符串是一个空字符串 ""，或者是一个不为 "(" 或 ")" 的单字符。
 * 字符串可以写为 AB（A 与 B字符串连接），其中 A 和 B 都是 有效括号字符串 。
 * 字符串可以写为 (A)，其中 A 是一个 有效括号字符串 。
 * <p>
 * 类似地，可以定义任何有效括号字符串S 的 嵌套深度 depth(S)：
 * <p>
 * depth("") = 0
 * depth(C) = 0，其中 C 是单个字符的字符串，且该字符不是 "(" 或者 ")"
 * depth(A + B) = max(depth(A), depth(B))，其中 A 和 B 都是 有效括号字符串
 * depth("(" + A + ")") = 1 + depth(A)，其中 A 是一个 有效括号字符串
 * 例如：""、"()()"、"()(()())" 都是 有效括号字符串（嵌套深度分别为 0、1、2），而 ")(" 、"(()" 都不是 有效括号字符串 。
 * <p>
 * 给你一个 有效括号字符串 s，返回该字符串的 s 嵌套深度 。
 *
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "(1+(2*3)+((8)/4))+1"
 * 输出：3
 * 解释：数字 8 在嵌套的 3 层括号中。
 * 示例 2：
 * <p>
 * 输入：s = "(1)+((2))+(((3)))"
 * 输出：3
 */
public class BracketsMaxDepthSolution {

    /**
     * 遍历  核心思路：栈
     *
     * 遍历字符串s，如果遇到了一个左括号，那么就将其入栈；如果遇到了一个右括号，那么就弹出栈顶的左括号，与该右括号匹配。
     * 这一过程中的栈的大小的最大值，即为s 的嵌套深度。
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param s
     * @return
     */
    public int maxDepth(String s) {
        int result = 0, size = 0;

        for (int i = 0; i < s.length();i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                size++;
                result = Math.max(result, size);
            } else if (ch == ')') {
                size--;
            }
        }

        return result;
    }
}
