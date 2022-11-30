package com.martix.x.pub.code.sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrew-Geng on 9:58 下午 2021/4/15
 * <p>
 * 四数之和 lc 18
 * <p>
 * 给定一个包含n 个整数的数组nums和一个目标值target，判断nums中是否存在四个元素 a，b，c和d，使得a + b + c + d的值与target相等？
 * 找出所有满足条件且不重复的四元组。
 * <p>
 * 注意：答案中不可以包含重复的四元组。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * 示例 2：
 * <p>
 * 输入：nums = [], target = 0
 * 输出：[]
 * <p>
 */
public class FourSumSolution {

    public static void main(String[] args) {
        int[] nums = new int[]{1,0,-1,0,-2,2};
        System.out.println(new FourSumSolution().fourSum(nums,0));
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums); //排序

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) { //穷举4sum的第一个数
            List<List<Integer>> threeSumResult = this.threeSumTarget(nums, i + 1, target - nums[i]);

            for (List<Integer> list : threeSumResult) {
                list.add(nums[i]);
                result.add(list);
            }

            while (i < nums.length - 1 && nums[i] == nums[i + 1]) { //跳过第一个数字的重复情况，否则结果子数组中会出现重复，而3sum自行解决重复的数组问题
                i++;
            }
        }

        return result;
    }

    /**
     * a+b+c=target
     *
     * @param nums
     * @param start
     * @param target
     * @return
     */
    private List<List<Integer>> threeSumTarget(int[] nums, int start, int target) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = start; i < nums.length; i++) { //穷举3sum的第一个数
            List<List<Integer>> twoSumResult = this.twoSumTarget(nums, i + 1, target - nums[i]);

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
     * 获取nums中所有符合两数相加为target的子数组，且去重
     *
     * @param nums
     * @param start
     * @param target
     * @return
     */
    private List<List<Integer>> twoSumTarget(int[] nums, int start, int target) {
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
