package com.martix.x.pub.code.sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrew-Geng on 12:40 上午 2021/4/16
 * 最接近的三数之和
 * <p>
 * lc 16
 * <p>
 * 给定一个包括n个整数的数组nums和 一个目标值target。找出nums中的三个整数，使得它们的和与target最接近。
 * 返回这三个数的和。假定每组输入只存在唯一答案。
 * <p>
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 */
public class ThreeSumClosestSolution {

    public static void main(String[] args) {
        int[] a = new int[]{-1, 0, 1, 1, 55};
        System.out.println(new ThreeSumClosestSolution().threeSumClosest(a, 3));
    }

    /**
     * 排序+双指针
     * 通过排序 降低一次循环
     *
     * 时间复杂度o(N^2)
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int result = 0;
        int abs = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) { //如果相邻数字出现重复的，跳过
                continue;
            }

            int low = i + 1, high = nums.length - 1;
            while (low < high) {
                int sum = nums[i] + nums[low] + nums[high];

                if (sum == target) {
                    return target;
                }

                if (Math.abs(sum - target) < abs) {
                    abs = Math.abs(sum - target);
                    result = sum;
                }

                if (sum > target) {
                    high--;
                }
                if (sum < target) {
                    low++;
                }
            }
        }

        return result;
    }

    /**
     * 穷举下所有的三数之和，且比较其和target的绝对值大小，找出最小的那个
     * <p>
     * 时间复杂度O(N^3)
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest_1(int[] nums, int target) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int sum = nums[i] + nums[j] + nums[k];

                    list.add(sum);
                }
            }
        }

        int result = list.get(0);
        int abs = Math.abs(list.get(0) - target);
        for (int i = 1; i < list.size(); i++) {
            if (Math.abs(list.get(i) - target) < abs) {
                result = list.get(i);
                abs = Math.abs(list.get(i) - target);
            }
        }

        return result;
    }
}
