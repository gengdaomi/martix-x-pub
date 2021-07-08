package com.martix.x.pub.combine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew-Geng on 10:58 下午 2021/7/8
 * 组合总和 lc 39
 * <p>
 * 给定一个无重复元素的数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 * <p>
 * candidates中的数字可以无限制重复被选取。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括target）都是正整数。
 * 解集不能包含重复的组合。
 * 示例1：
 * <p>
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 * [7],
 * [2,2,3]
 * ]
 * 示例2：
 * <p>
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 */
public class CombinationSumSolution {

    /**
     * 搜索回溯
     * <p>
     * 核心思考：
     * 定义的dfs函数表示当前在 candidates 数组的第 idx 位，还剩 target 要组合，已经组合的列表为 combine；
     * 递归的终止条件为 target <= 0 或者 candidates 数组被全部用完；
     * 那么在当前的函数中，每次我们可以选择跳过不用第 idx 个数，即执行 dfs(target, combine, idx + 1)；
     * 也可以选择使用第 idx 个数，即执行 dfs(target - candidates[idx], combine, idx)，
     * 注意到每个数字可以被无限制重复选取，因此搜索的下标仍为 idx；
     * <p>
     * <p>
     * <p>
     * 时间复杂度:O(S); 其中S 为所有可行解的长度之和；从分析给出的搜索树我们可以看出时间复杂度取决于搜索树所有叶子节点的深度之和；
     * 空间复杂度：O(target)；除答案数组外，空间复杂度取决于递归的栈深度，在最差情况下需要递归O(target) 层
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();

        this.dfs(candidates, target, result, combine, 0);

        return result;
    }

    /**
     * 定义的dfs函数表示当前在 candidates 数组的第 idx 位，还剩 target 要组合，已经组合的列表为 combine；
     * 递归的终止条件为 target <= 0 或者 candidates 数组被全部用完；
     * 那么在当前的函数中，每次我们可以选择跳过不用第 idx 个数，即执行 dfs(target, combine, idx + 1)；
     * 也可以选择使用第 idx 个数，即执行 dfs(target - candidates[idx], combine, idx)，
     * 注意到每个数字可以被无限制重复选取，因此搜索的下标仍为 idx；
     */
    public void dfs(int[] candidates, int target, List<List<Integer>> result, List<Integer> combine, int idx) {
        if (idx == candidates.length) {
            return;
        }
        if (target == 0) {
            result.add(new ArrayList<Integer>(combine));
            return;
        }
        // 直接跳过
        dfs(candidates, target, result, combine, idx + 1);
        // 选择当前数
        if (target - candidates[idx] >= 0) {
            combine.add(candidates[idx]);

            dfs(candidates, target - candidates[idx], result, combine, idx);

            combine.remove(combine.size() - 1);
        }
    }

}
