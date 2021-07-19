package com.martix.x.pub.backtrack;

import java.util.*;

/**
 * Created by Andrew-Geng on 11:17 下午 2021/7/8
 *  组合总和 II lc 40
 *
 *  给定一个数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 *
 * candidates中的每个数字在每个组合中只能使用一次。
 *
 * 注意：解集不能包含重复的组合;
 *
 *
 * 示例1:
 *
 * 输入: candidates =[10,1,2,7,6,1,5], target =8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 * 示例2:
 *
 * 输入: candidates =[2,5,2,1,2], target =5,
 * 输出:
 * [
 * [1,2,2],
 * [5]
 * ]
 */
public class CombinationSum2Solution {

    /**
     * 核心思路：
     *
     * 如何去掉重复的集合（重点）:
     *
     *使用 哈希表 天然的去重功能，但是编码相对复杂；
     * 这里我们使用和第 39 题和第 15 题（三数之和）类似的思路：不重复就需要按 顺序 搜索， 在搜索的过程中检测分支是否会出现重复结果；
     * 注意：这里的顺序不仅仅指数组 candidates 有序，还指按照一定顺序搜索结果。
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> result = new ArrayList<>();
        if (len == 0) {
            return result;
        }

        // 关键步骤
        Arrays.sort(candidates);

        Deque<Integer> path = new ArrayDeque<>(len);
        dfs(candidates, len, 0, target, path, result);
        return result;
    }

    private void dfs(int[] candidates, int len, int begin, int target, Deque<Integer> path, List<List<Integer>> result){
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < len; i++) {
            // 大剪枝：减去 candidates[i] 小于 0，减去后面的 candidates[i + 1]、candidates[i + 2] 肯定也小于 0，因此用 break
            if (target - candidates[i] < 0) {
                break;
            }

            // 小剪枝：同一层相同数值的结点，从第 2 个开始，候选数更少，结果一定发生重复，因此跳过，用 continue
            if (i > begin && candidates[i] == candidates[i - 1]) {
                continue;
            }

            path.addLast(candidates[i]);
            // 调试语句 ①
            // System.out.println("递归之前 => " + path + "，剩余 = " + (target - candidates[i]));

            // 因为元素不可以重复使用，这里递归传递下去的是 i + 1 而不是 i
            dfs(candidates, len, i + 1, target - candidates[i], path, result);

            path.removeLast();
            // 调试语句 ②
            // System.out.println("递归之后 => " + path + "，剩余 = " + (target - candidates[i]));
        }
    }
}
