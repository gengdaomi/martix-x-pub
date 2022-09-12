package com.martix.x.pub.code.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ayue on 上午11:25 2018/6/28
 *
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * lc 78
 *
 * 说明：解集不能包含重复的子集。
 *
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */
public class SubSetSolution {

    public static void main(String[] args){
        int[] nums= new int[]{1,2,3};

        System.out.println(new SubSetSolution().subsets(3));
    }

    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> subSet(int[] nums) {
        robot(0, nums);
        return result;
    }

    // 记录是否选择该元素
    private boolean[] visited = new boolean[100];

    // 此处的index代表的是数组的索引
    private void robot(int index, int[] nums) {
        if (index >= nums.length) {

            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {

                if (visited[i]) {
                    temp.add(nums[i]);
                }
            }

            result.add(temp);
            return;
        }

        // 以下两种情况中 true代表选择该元素   false代表不选择该元素
        visited[index] = true;
        robot(index + 1, nums);
        visited[index] = false;
        robot(index + 1, nums);
    }


    /**
     * 回溯算法
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(0, nums, res, new ArrayList<Integer>());
        return res;

    }

    private void backtrack(int i, int[] nums, List<List<Integer>> res, ArrayList<Integer> tmp) {
        res.add(new ArrayList<>(tmp));
        for (int j = i; j < nums.length; j++) {
            tmp.add(nums[j]);
            backtrack(j + 1, nums, res, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }


    public List<List<Integer>> subsets(int nums) {
        List<List<Integer>> result = new ArrayList<>();

        backtrack(0, nums, result, new ArrayList<>());
        return result;

    }

    private void backtrack(int i, int nums, List<List<Integer>> result, ArrayList<Integer> tmp) {
        result.add(new ArrayList<>(tmp));
        for (int j = i+1; j <= nums; j++) {
            tmp.add(j);

            backtrack(j , nums, result, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }

}
