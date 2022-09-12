package com.martix.x.pub.code.binary;

/**
 * Created by Andrew-Geng on 1:38 下午 2021/5/3
 * 搜索旋转排序数组 II
 * lc 81
 * <p>
 * 已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
 * <p>
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，
 * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
 * <p>
 * 给你 旋转后 的数组 nums 和一个整数 target ，
 * 请你编写一个函数来判断给定的目标值是否存在于数组中。如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
 * <p>
 * <p>
 * 示例1：
 * <p>
 * 输入：nums = [2,5,6,0,0,1,2], target = 0
 * 输出：true
 * 示例2：
 * <p>
 * 输入：nums = [2,5,6,0,0,1,2], target = 3
 * 输出：false
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 5000
 * -104 <= nums[i] <= 104
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * -104 <= target <= 104
 *  
 * <p>
 * 进阶：
 * <p>
 * 这是 搜索旋转排序数组的延伸题目，本题中的nums可能包含重复元素。
 * 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
 */
public class SearchRotateArrayRepeatTargetSolution {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 0, 1, 1, 1};

        boolean s = new SearchRotateArrayRepeatTargetSolution().search_1(nums, 0);
        System.out.println(s);
    }

    /**
     * 时间复杂度：O(logn)
     * 空间复杂度O(1)
     * @param nums
     * @param target
     * @return
     */
    public boolean search(int[] nums, int target) {
        if (nums.length == 1) {
            return nums[0] == target ? true : false;
        }

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return true;
            }

            if (nums[left] == nums[mid] && nums[mid] == nums[right]) { //因为存在重复的
                left++;
                right--;
            } else if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[nums.length - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return false;
    }

    public boolean search_1(int[] nums, int target) {
        int left = 0, right = nums.length;

        while (left < right) {
            if (nums[left] == target) {
                return true;
            } else if (nums[left] > target) {
                if (nums[right - 1] == target) {
                    return true;
                } else {
                    right--;
                }
            } else if (nums[left] < target) {
                left++;
            }
        }

        return left == right ? false : true;
    }

}
