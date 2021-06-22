package com.martix.x.binary;

/**
 * Created by Andrew-Geng on 3:11 下午 2021/5/3
 * <p>
 * 寻找旋转排序数组中的最小值 II
 * lc 154 hard
 * <p>
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,4,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,4]
 * 若旋转 7 次，则可以得到 [0,1,4,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 * <p>
 * 给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,3,5]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：nums = [2,2,2,0,1]
 * 输出：0
 *  
 * <p>
 * 提示：
 * <p>
 * n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 *  
 * <p>
 * 进阶：
 * <p>
 * 这道题是 寻找旋转排序数组中的最小值 的延伸题目。
 * 允许重复会影响算法的时间复杂度吗？会如何影响，为什么？
 */
public class SearchRotateArrayMinRepeatSolution {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 2, 2, 0, 1};

        int s = new SearchRotateArrayMinRepeatSolution().findMin(nums);
        System.out.println(s);
    }

    /**
     * 当 nums[mid] == nums[right] 时，是此题对比 153题 的难点（原因是此题中数组的元素可重复，难以判断分界点i 指针区间）
     *
     * 采用 right = right - 1 解决此问题，证明：
     * 此操作不会使数组越界：因为迭代条件保证了 right > left >= 0；
     * 此操作不会使最小值丢失：假设nums[right] 是最小值，有两种情况：nums[right] 是唯一最小值：那就不可能满足判断条件 nums[mid] == nums[right]，
     * 因为 mid < right（left != right 且 mid = (left + right) // 2 向下取整）；
     * 若 nums[right] 不是唯一最小值，由于 mid < right 而 nums[mid] == nums[right]，
     * 即还有最小值存在于 [left,right−1] 区间，因此不会丢失最小值。
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < nums[right]) {
                right = mid;
            } else if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = right - 1;
            }
        }

        return nums[left];
    }
}
