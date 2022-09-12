package com.martix.x.pub.code.set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrew-Geng on 11:53 下午 2021/7/10
 * <p>
 * 子集 II
 * lc 90
 * <p>
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * <p>
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 * 示例 2：
 * <p>
 * 输入：nums = [0]
 * 输出：[[],[0]]
 */
public class SubsetsWithDuplicateSolution {

    List<Integer> t = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    /**
     * 迭代法实现子集枚举
     * <p>
     * 时间复杂度：O(n×2^n)，其中n 是数组nums 的长度。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        for (int mask = 0; mask < (1 << n); ++mask) {
            t.clear();

            boolean flag = true;
            for (int i = 0; i < n; ++i) {
                if ((mask & (1 << i)) != 0) {

                    if (i > 0 && (mask >> (i - 1) & 1) == 0 && nums[i] == nums[i - 1]) {
                        flag = false;
                        break;
                    }

                    t.add(nums[i]);
                }
            }

            if (flag) {
                ans.add(new ArrayList<Integer>(t));
            }

        }
        return ans;
    }

}
