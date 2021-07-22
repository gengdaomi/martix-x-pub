package com.martix.x.pub.combine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew-Geng on 11:22 下午 2021/7/8
 * 组合总和 III lc216
 *
 * 找出所有相加之和为n 的k个数的组合。组合中只允许含有 1 -9 的正整数，并且每种组合中不存在重复的数字。
 *
 * 说明：
 *
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 *
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 示例 2:
 *
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 *
 */
public class CombinationSumNoRepeatSolution {

    List<Integer> temp = new ArrayList<Integer>();
    List<List<Integer>> result = new ArrayList<List<Integer>>();

    /**
     * 组合枚举
     * 我们需要在9 个数中选择k 个数，让它们的和为n。
     * 这样问题就变成了一个组合枚举问题。组合枚举有两种处理方法——递归法和字典序法;
     * 要做的是做一个「在9 个数中选择k 个数」的组合枚举，对于枚举到的所有组合，判断这个组合内元素之和是否为n;
     *
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        dfs(1, 9, k, n);
        return result;
    }

    public void dfs(int cur, int n, int k, int sum) {
        if (temp.size() + (n - cur + 1) < k || temp.size() > k) {
            return;
        }
        if (temp.size() == k) {
            int tempSum = 0;
            for (int num : temp) {
                tempSum += num;
            }
            if (tempSum == sum) {
                result.add(new ArrayList<Integer>(temp));
                return;
            }
        }

        temp.add(cur);
        dfs(cur + 1, n, k, sum);
        temp.remove(temp.size() - 1);
        dfs(cur + 1, n, k, sum);
    }
}
