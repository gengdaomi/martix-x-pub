package com.martix.x.pub.code.sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrew-Geng on 1:25 上午 2021/4/15
 * 给定一个已按照 升序排列的整数数组numbers ，请你从数组中找出两个数满足相加之和等于目标数target 。
 * <p>
 * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。
 * numbers 的下标 从 1 开始计数 ，所以答案数组应当满足 1 <= answer[0] < answer[1] <= numbers.length 。

 * 还是两数相加，大体要求跟TwoSumAscSolution的要求一致，只是要保证返回的数组集合中不存在重复的数组
 * 因为nums中可能存在多对子数组的和为target，
 * 比如[1,2] [2,1] target是3，但是这两个数组重复
 */
public class TwoSumNoRepeatSolution {

    public List<List<Integer>> twoSumTarget(int[] nums, int target) {
        Arrays.sort(nums); //对其排序

        return this.twoSumTarget(nums, 0, target);
    }

    /**
     * 获取nums中所有符合两数相加为target的子数组，且去重
     *
     * @param nums
     * @param start
     * @param target
     * @return
     */
    public List<List<Integer>> twoSumTarget(int[] nums, int start, int target) {
        List<List<Integer>> result = new ArrayList<>();

        int left = start, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            int leftVal = nums[left], rightVal = nums[right]; //左右下标对应的数值

            if (sum == target) {
                List<Integer> temp = new ArrayList<>();
                temp.add(leftVal);
                temp.add(rightVal);

                result.add(temp);

                while (left < right && nums[left] == leftVal) { //每次添加完一个子数组后，都持续判断后续数字是否和前一个相等
                    left++;
                }
                while (left < right && nums[right] == rightVal) {//每次添加完一个子数组后，都持续判断后续数字是否和前一个相等
                    right--;
                }
            } else if (sum > target) {
                while (left < right && nums[right] == rightVal) {//每次添加完一个子数组后，都持续判断后续数字是否和前一个相等
                    right--;
                }
            } else { //sum<target
                while (left < right && nums[left] == leftVal) { //每次添加完一个子数组后，都持续判断后续数字是否和前一个相等
                    left++;
                }
            }
        }

        return result;
    }
}
