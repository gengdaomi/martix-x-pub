package com.martix.x.pub.set;

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

        System.out.println(new SubSetSolution().subSet(nums));
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
}
