package com.martix.x.pub.binary;

import java.util.Arrays;

/**
 * Created by Andrew-Geng on 4:12 下午 2021/5/3
 * <p>
 * 在排序数组中查找元素的第一个和最后一个位置
 * lc 34
 * <p>
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * <p>
 * 进阶：
 * <p>
 * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 * <p>
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums 是一个非递减数组
 * -109 <= target <= 109
 */
public class SearchRangeArraySolution {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 2};
        System.out.println(new SearchRangeArraySolution().searchRange(nums, 2));
    }

    /**
     * 考虑target 开始和结束位置，其实我们要找的就是数组中「第一个等于target 的位置」（记为leftIdx）和「第一个大于target 的位置减一」（记rightIdx）。
     * <p>
     * 二分查找中，寻找
     * leftIdx 即为在数组中寻找第一个大于等于
     * target 的下标，寻找
     * rightIdx 即为在数组中寻找第一个大于
     * target 的下标，然后将下标减一。
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};

        if (nums.length == 0) {
            return result;
        }
        if (nums.length == 1) {
            if (target == nums[0]) {
                Arrays.fill(result, 0);
            }

            return result;
        }

        int leftIndex = this.search(nums, target, true);
        int rightIndex = this.search(nums, target, false) - 1;

        if (leftIndex <= rightIndex && rightIndex < nums.length
                && nums[leftIndex] == target && nums[rightIndex] == target) {
            result[0] = leftIndex;
            result[1] = rightIndex;
        }

        return result;
    }

    /**
     * 二分查找
     *
     * @param nums
     * @param target
     * @param isLower 如果isLower 为true，则查找第一个大于等于target 的下标，
     *                否则查找第一个大于target 的下标
     * @return
     */
    private int search(int[] nums, int target, boolean isLower) {
        int left = 0, right = nums.length - 1;
        int result = nums.length;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > target || (isLower && nums[mid] >= target)) {
                right = mid - 1;
                result = mid;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }
}
