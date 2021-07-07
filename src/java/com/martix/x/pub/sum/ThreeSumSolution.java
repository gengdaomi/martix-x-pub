package com.martix.x.pub.sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created By Andrew-Geng on 2020/11/9 11:38 下午
 * 三数之和
 * lc 15
 * <p>
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * <p>
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 */
public class ThreeSumSolution {

    /**
     * 本题需要找到三个和为 0 的元素，对数组排序后，遍历数组，以当前元素的相反数为两数和，然后在当前元素后的所有元素范围内使用双指针算法寻找另两个元素。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum_1(int[] nums) {
        Arrays.sort(nums);//排序

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; //避免重复三元组
            }

            int lo = i + 1, hi = nums.length - 1, sum = 0 - nums[i];

            while (lo < hi) {//有序数组找特定和的两元素，双指针算法
                if (nums[lo] + nums[hi] == sum) {
                    res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));

                    while (lo < hi && nums[lo] == nums[lo + 1]) {
                        lo++;//避免重复三元组
                    }

                    while (lo < hi && nums[hi] == nums[hi - 1]) {
                        hi--;//避免重复三元组
                    }

                    lo++;
                    hi--;
                } else if (nums[lo] + nums[hi] < sum) {
                    lo++;
                } else {
                    hi--;
                }
            }
        }
        return res;
    }

    /**
     * 结合两数相加 依次对数组进行穷举
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums); //排序

        return this.threeSumTarget(nums, 0);
    }

    /**
     * a+b+c=target
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> threeSumTarget(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) { //穷举3sum的第一个数
            List<List<Integer>> twoSumResult = new TwoSumNoRepeatSolution().twoSumTarget(nums, i + 1, target - nums[i]);

            for (List<Integer> list : twoSumResult) {
                list.add(nums[i]);
                result.add(list);
            }

            while (i < nums.length - 1 && nums[i] == nums[i + 1]) { //跳过第一个数字的重复情况，否则结果子数组中会出现重复，而2sum自行解决重复的数组问题
                i++;
            }
        }

        return result;
    }

    /**
     * 不排序
     * <p>
     * 这个题目的难点之一，就是要求输出的三元组不重复。如果想要在不对原始数据进行排序的情况下解这个题目，就要考虑分情况将结果集分为一个完备集合组，即所有情况之间没有交集，且合起来构成完整的结果集。
     * 不排序下的分情况讨论：
     * <p>
     * 三元组中包含重复元素，例如 {2, 2, -4}，{0, 0, 0} 这些情况。
     * 两正一负，例如 {1, 2, -3} 这种。
     * 两负一正，例如 {-1, -2, 3} 这种。
     * 一正一负，例如 {-1, 1, 0} 这种。
     * 其中第四种情况可以合并到第二种，第三种中的任意一种情况中去
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum_2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 3) return res;
        ArrayList<Integer> l0 = new ArrayList<>(nums.length);//存所有去重后的负数
        ArrayList<Integer> g0 = new ArrayList<>(nums.length);//存所有去重后的正数和0
        HashMap<Integer, Integer> hashMap = new HashMap<>();//存所有的数

        for (int i = 0; i < nums.length; ++i) {
            if (hashMap.containsKey(nums[i])) {
                hashMap.put(nums[i], hashMap.get(nums[i]) + 1);
            } else if (nums[i] < 0) {
                l0.add(nums[i]);
                hashMap.put(nums[i], 1);
            } else {
                g0.add(nums[i]);
                hashMap.put(nums[i], 1);
            }
        }
        //处理包含重复元素的情况
        for (Integer e : hashMap.keySet()) {
            if (hashMap.get(e) > 1) {
                int target = -2 * e;
                if ((e != 0 && hashMap.containsKey(target)) || (e == 0 && hashMap.get(0) > 2)) {
                    res.add(Arrays.asList(e, e, target));
                }
            }
        }
        //两负一正的情况
        for (int i = 0; i < l0.size(); ++i) {
            for (int j = i + 1; j < l0.size(); ++j) {
                int e1 = l0.get(i), e2 = l0.get(j);
                int target = -(e1 + e2);
                if (target != e1 && target != e2 && hashMap.containsKey(target)) {
                    res.add(Arrays.asList(e1, e2, target));
                }
            }
        }
        //两正一负或一正一负
        for (int i = 0; i < g0.size(); ++i) {
            for (int j = i + 1; j < g0.size(); ++j) {
                int e1 = g0.get(i), e2 = g0.get(j);
                int target = -(e1 + e2);
                if (target != e1 && target != e2 && hashMap.containsKey(target)) {
                    res.add(Arrays.asList(e1, e2, target));
                }
            }
        }
        return res;
    }
}
