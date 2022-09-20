package com.martix.x.pub.code.binary;

/**
 * Created by Andrew-Geng on 12:23 上午 2021/6/8
 * <p>
 * 按奇偶排序数组 lc 905
 * <p>
 * 给定一个非负整数数组 A，返回一个数组，在该数组中，A 的所有偶数元素之后跟着所有奇数元素。
 * <p>
 * 你可以返回满足此条件的任何数组作为答案。
 * <p>
 * 示例：
 * <p>
 * 输入：[3,1,2,4]
 * 输出：[2,4,3,1]
 * 输出 [4,2,3,1]，[2,4,1,3] 和 [4,2,1,3] 也会被接受。
 *
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 5000
 * 0 <= A[i] <= 5000
 */
public class SortArrayByParity1Solution {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 1, 2, 4};
        int[] result = new SortArrayByParity1Solution().sortArrayByParity(nums);

        System.out.println(result);
    }

    /**
     * 双指针
     *
     * @param nums
     * @return
     */
    public int[] sortArrayByParity(int[] nums) {
        int low = 0, high = nums.length - 1;

        while (low <= high) {
            while (low <= high && (nums[low] & 1) == 0) { //判断是否是偶数
                low++;
            }

            while (low <= high && (nums[high] & 1) != 0) {
                high--;
            }

            if (low <= high) {
                nums[low] ^= nums[high];
                nums[high] ^= nums[low];
                nums[low] ^= nums[high];
            }
        }

        return nums;
    }
}
