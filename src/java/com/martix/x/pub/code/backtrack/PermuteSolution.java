package com.martix.x.pub.code.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew-Geng on 11:12 下午 2021/7/8
 * 全排列
 * lc 46
 * <p>
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 示例 2：
 * <p>
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 示例 3：
 * <p>
 * 输入：nums = [1]
 * 输出：[[1]]
 */
public class PermuteSolution {

    /**
     * 回溯
     * 一种通过探索所有可能的候选解来找出所有的解的算法。如果候选解被确认不是一个解（或者至少不是最后一个解）
     * ，回溯算法会通过在上一步进行一些变化抛弃该解，即回溯并且再次尝试。
     * <p>
     * 时间复杂度O(n*n!))
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        List<Integer> output = new ArrayList<Integer>();
        for (int num : nums) {
            output.add(num);
        }

        int n = nums.length;
        backtrack(n, output, result, 0);
        return result;
    }

    public void backtrack(int n, List<Integer> output, List<List<Integer>> result, int first) {
        // 所有数都填完了
        if (first == n) {
            result.add(new ArrayList<Integer>(output));
        }

        for (int i = first; i < n; i++) {
            // 动态维护数组
            swap(output, first, i);
            // 继续递归填下一个数
            backtrack(n, output, result, first + 1);
            // 撤销操作
            swap(output, first, i);
        }
    }

    private void swap(List<Integer> list, int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

}
