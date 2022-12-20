package com.martix.x.pub.code.arrays;

/**
 * Created by Andrew-Geng on 23:21 2022/12/20
 *
 * 在排序数组中查找数字 I
 *
 * 剑指 Offer 53
 *
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: 2
 * 示例 2:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: 0
 *
 */
public class CountTargetInArraySolution {

    /**
     * 二分查找
     *
     * 考虑target 在数组中出现的次数，其实我们要找的就是数组中「第一个等于target 的位置」
     * （记为leftIdx）和「第一个大于target 的位置减一」（记为rightIdx）。
     * 当target 在数组中存在时，target 在数组中出现的次数为 rightIdx−leftIdx+1
     *
     * 时间复杂度O(logn)
     * 空间复杂度O(1)
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;

        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return rightIdx - leftIdx + 1;
        }

        return 0;
    }

    public int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, result = nums.length;

        while (left <= right) {
            int mid = (left + right) / 2;
            
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                result = mid;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }
}
