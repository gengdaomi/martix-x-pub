package com.martix.x.pub.permutation;

/**
 * Created by Andrew-Geng on 12:39 上午 2021/5/12
 * 下一个排列 lc 31
 * <p>
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * <p>
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * <p>
 * 必须 原地 修改，只允许使用额外常数空间。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 * <p>
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * 示例 3：
 * <p>
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 * 示例 4：
 * <p>
 * 输入：nums = [1]
 * 输出：[1]
 */
public class NextPermutationSolution {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 5, 4};

        new NextPermutationSolution().nextPermutation(nums);
        System.out.println(nums);
    }

    /**
     * 思路：
     * 从后往前查看逆序区域，找到逆序前一位，即数字交换的边界
     * 让逆序区域的前一位和逆序区域中大于ta的最小数字交换位置
     * 把原来逆序区域转为顺序的
     * <p>
     * 时间复杂度O(N),空间复杂度O(1)
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if (nums.length == 1) {
            return;
        }

        int index = this.getReverseIndex(nums);
        if (index == 0) { //表示当前数组已经是完全逆序，如321，最大了，那就返回它的最小值
            reverse(nums, 0);
            return;
        }

        this.exchange(nums, index);
        this.reverse(nums, index);
    }

    /**
     * 从后往前 获取到数组中第一个逆序的位置
     *
     * @param nums
     * @return
     */
    private int getReverseIndex(int[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 将从尾部到index子数组中仅仅大于index-1位置的值，与其交换
     *
     * @param nums
     * @param index
     */
    private void exchange(int[] nums, int index) {

        int head = nums[index - 1];

        for (int i = nums.length - 1; i >= index; i--) {
            if (head < nums[i]) {
                nums[index - 1] = nums[i];
                nums[i] = head;

                break;
            }
        }
    }

    /**
     * 表示从index位置开始进行数组倒序
     *
     * @param nums
     * @param index
     * @return
     */
    private int[] reverse(int[] nums, int index) {
        int left = index, right = nums.length - 1;

        while (left < right) {
            nums[left] ^= nums[right];
            nums[right] ^= nums[left];
            nums[left] ^= nums[right];

            left++;
            right--;
        }

        return nums;
    }
}
