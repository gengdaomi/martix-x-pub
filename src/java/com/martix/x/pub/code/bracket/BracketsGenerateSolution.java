package com.martix.x.pub.code.bracket;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Andrew-Geng on 12:28 上午 2021/7/8
 * 括号生成
 * lc22
 *
 * 数字 n代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：["()"]
 */
public class BracketsGenerateSolution {

    /**
     * 深度优先遍历(回溯算法)
     * 核心思路：
     *
     * 当前左右括号都有大于0个可以使用的时候，才产生分支；
     * 产生左分支的时候，只看当前是否还有左括号可以使用；
     * 产生右分支的时候，还受到左分支的限制，右边剩余可以使用的括号数量一定得在严格大于左边剩余的数量的时候，才可以产生分支；
     * 在左边和右边剩余的括号数都等于0 的时候结算
     *
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        // 特判
        if (n == 0) {
            return result;
        }

        // 执行深度优先遍历，搜索可能的结果
        dfs("", n, n, result);
        return result;
    }

    /**
     * @param curStr 当前递归得到的结果
     * @param left   左括号还有几个可以使用
     * @param right  右括号还有几个可以使用
     * @param result    结果集
     */
    private void dfs(String curStr, int left, int right, List<String> result) {
        // 因为每一次尝试，都使用新的字符串变量，所以无需回溯
        // 在递归终止的时候，直接把它添加到结果集即可，注意与「力扣」第 46 题、第 39 题区分
        if (left == 0 && right == 0) {
            result.add(curStr);
            return;
        }

        // 剪枝（如图，左括号可以使用的个数严格大于右括号可以使用的个数，才剪枝，注意这个细节）
        if (left > right) {
            return;
        }

        if (left > 0) {
            dfs(curStr + "(", left - 1, right, result);
        }

        if (right > 0) {
            dfs(curStr + ")", left, right - 1, result);
        }
    }

    /**
     * @param curStr 当前递归得到的结果
     * @param left   左括号已经用了几个
     * @param right  右括号已经用了几个
     * @param n      左括号、右括号一共得用几个
     * @param res    结果集
     */
    private void dfs(String curStr, int left, int right, int n, List<String> res) {
        if (left == n && right == n) {
            res.add(curStr);
            return;
        }

        // 剪枝
        if (left < right) {
            return;
        }

        if (left < n) {
            dfs(curStr + "(", left + 1, right, n, res);
        }
        if (right < n) {
            dfs(curStr + ")", left, right + 1, n, res);
        }
    }

    /**
     * 广度遍历
     * @param n
     * @return
     */
    public List<String> generateParenthesis_1(int n) {
        List<String> result = new ArrayList<>();

        if (n == 0) {
            return result;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node("", n, n));

        while (!queue.isEmpty()) {

            Node curNode = queue.poll();
            if (curNode.left == 0 && curNode.right == 0) {
                result.add(curNode.result);
            }

            if (curNode.left > 0) {
                queue.offer(new Node(curNode.result + "(", curNode.left - 1, curNode.right));
            }

            if (curNode.right > 0 && curNode.left < curNode.right) {
                queue.offer(new Node(curNode.result + ")", curNode.left, curNode.right - 1));
            }
        }

        return result;
    }

    class Node {
        /**
         * 当前得到的字符串
         */
        private String result;
        /**
         * 剩余左括号数量
         */
        private int left;
        /**
         * 剩余右括号数量
         */
        private int right;

        public Node(String result, int left, int right) {
            this.result = result;
            this.left = left;
            this.right = right;
        }
    }

}
