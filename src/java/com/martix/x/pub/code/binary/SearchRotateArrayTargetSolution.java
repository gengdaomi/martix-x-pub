package com.martix.x.pub.code.binary;

/**
 * Created by Andrew-Geng on 1:06 下午 2021/5/3
 * <p>
 * 搜索旋转排序数组 lc 33
 * <p>
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * <p>
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为[4,5,6,7,0,1,2] 。
 * <p>
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回-1。
 *
 *
 * 示例 1：
 * <p>
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * 示例2：
 * <p>
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：nums = [1], target = 0
 * 输出：-1
 *
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * nums 中的每个值都 独一无二
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * -10^4 <= target <= 10^4
 */
public class SearchRotateArrayTargetSolution {

    public static void main(String[] args) {
        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};

        int s = new SearchRotateArrayTargetSolution().search_1(nums, 6);
        System.out.println(s);
    }

    /**
     * 时间复杂度：O(logn)
     * 空间复杂度O(1)
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }

        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[0] <= nums[mid]) { //数组的首位 小于等于 数组的中间位置的时候
                if (nums[0] < target && target < nums[mid]) {
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
        return -1;
    }

    /**
     * 双指针的思路，本身已经是排序后的旋转数组，那么旋转后还是排序的
     *
     * @param nums
     * @param target
     * @return
     */
    public int search_1(int[] nums, int target) {
        int left = 0, right = nums.length;

        while (left < right) {
            if (nums[left] == target) {
                return left;
            } else if (nums[left] > target) {
                if (nums[right - 1] == target) {
                    return right - 1;
                } else {
                    right--;
                }
            } else if (nums[left] < target) {
                left++;
            }
        }

        return left == right ? -1 : left;
    }

}
