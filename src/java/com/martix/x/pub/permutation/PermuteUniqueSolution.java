package com.martix.x.pub.permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrew-Geng on 1:13 上午 2021/7/9
 * 全排列 II lc 47
 *
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 * 示例 2：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */
public class PermuteUniqueSolution {

    private boolean[] visited;

    /**
     * 要求我们返回不重复的全排列，那么我们依然可以选择使用搜索回溯的方法来做;
     *
     * 看作有n 个排列成一行的空格，我们需要从左往右依次填入题目给定的n 个数，每个数只能使用一次。
     * 那么很直接的可以想到一种穷举的算法，即从左往右每一个位置都依此尝试填入一个数，
     * 看能不能填完这n 个空格，在程序中我们可以用「回溯法」来模拟这个过程;
     *
     *
     * 定义递归函数 backtrack(idx, permList) 表示当前排列为permList,
     * 下一个待填入的位置是第idx 个位置（下标从0 开始;
     *
     * 如果idx==n，说明我们已经填完了n 个位置，找到了一个可行的解，我们将perm 放入答案数组中，递归结束。
     * 如果idx<n，我们要考虑第idx 个位置填哪个数。根据题目要求我们肯定不能填已经填过的数，
     * 因此很容易想到的一个处理手段是我们定义一个标记数组vis 来标记已经填过的数，那么在填第idx 个数的时候我们遍历题目给定的n 个数，如果这个数没有被标记过，我们就尝试填入，
     * 并将其标记，继续尝试填下一个位置，即调用函数 backtrack(idx + 1, permList)。搜索回溯的时候要撤销该个位置填的数以及标记，并继续尝试其他没被标记过的数。
     *
     *
     * 时间复杂度：O(n×n!)
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> permList = new ArrayList<Integer>();
        visited = new boolean[nums.length];

        Arrays.sort(nums);
        backtrack(nums, result, 0, permList);
        return result;
    }

    public void backtrack(int[] nums, List<List<Integer>> result, int idx, List<Integer> permList) {
        if (idx == nums.length) {
            result.add(new ArrayList<Integer>(permList));
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])) {
                continue;
            }

            permList.add(nums[i]);
            visited[i] = true;
            backtrack(nums, result, idx + 1, permList);
            visited[i] = false;
            permList.remove(idx);
        }
    }

}
