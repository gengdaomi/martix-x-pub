package com.martix.x.pub.code.sum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ayue on 下午4:26 2018/6/27
 * <p>
 * lc 1
 * <p>
 * 两数之和
 * <p>
 * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 的那两个整数，并返回它们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * <p>
 * 你可以按任意顺序返回答案。
 * <p>
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * <p>
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 */
public class TwoSumSolution {

    /**
     * 借助hashmap
     * <p>
     * 时间复杂度O(N),空间复杂度O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] solution(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int x = target - nums[i];

            if (map.containsKey(x) && map.get(x) != i) {
                return new int[]{i, map.get(x)};
            }
        }

        return new int[]{-1, -1};
    }

    /**
     * 给定一个整数数组 nums一个整数目标值 target，请你在该数组中找出 和为目标值 的那两个整数，并返回它们的数组下标。
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     * <p>
     * <p>
     * 暴力解决方法
     * <p>
     * 时间复杂度O(N^2),空间复杂度O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] solution_1(int[] nums, int target) {
        int[] result = {0, 0}; //结果索引的集合

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }

        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[] params = new int[]{3, 1, 3, 6};
        System.out.println(Arrays.toString(new TwoSumSolution().solution(params, 6)));
    }
}
