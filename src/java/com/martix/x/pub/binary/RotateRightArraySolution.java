package com.martix.x.pub.binary;

/**
 * Created by Andrew-Geng on 1:17 下午 2021/5/1
 * 旋转数组 lc 189
 * <p>
 * 给定一个数组，将数组中的元素向右移动k个位置，其中k是非负数。
 * <p>
 * 进阶：
 * <p>
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 你可以使用空间复杂度为O(1) 的原地算法解决这个问题吗？
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 * 示例2:
 * <p>
 * 输入：nums = [-1,-100,3,99], k = 2
 * 输出：[3,99,-1,-100]
 * 解释:
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 */
public class RotateRightArraySolution {

    public static void main(String[] args) {
        int[] arr = new int[]{-1, -100, 3, 99};

        new RotateRightArraySolution().rotate(arr, 2);
        System.out.println(arr);
    }

    /**
     * 数组翻转
     * <p>
     * 当k的位置是0 或者是数组的整数倍，相当于不用反转
     * 首先整体反转数组
     *
     * 分两部分进行数组翻转，两个区间[0,k % nums.length-1],,[k % nums.length,nums.length-1]
     *
     * <p>
     * 最优解
     * 时间复杂度O(n),空间复杂度O(1)
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int partition = k % nums.length;

        if (partition == 0) {
            return;
        }

        this.reverse(nums, 0, nums.length - 1);
        this.reverse(nums, 0, partition - 1);
        this.reverse(nums, partition, nums.length - 1);
    }

    /**
     * 反转数组
     *
     * 用异或的方式交换 效率会更快
     * nums[start] ^= nums[end];
     * nums[end] ^= nums[start];
     * nums[start] ^= nums[end];
     *
     * @param nums
     * @param start
     * @param end
     */
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;

            start++;
            end--;
        }
    }

    /**
     * 借助一个数组，依次将现有数组nums，根据(i+k)%nums.length算出在新数组的位置
     * <p>
     * 时间复杂度O(n),空间复杂度O(n)
     *
     * @param nums
     * @param k
     */
    public void rotate_0(int[] nums, int k) {
        int[] result = new int[nums.length];

        if (k == 0) {
            return;
        }

        int partition = k % nums.length;
        if (partition == 0) {  //先判断整除的，如果是则直接返回，不必进行判断
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            result[(i + k) % nums.length] = nums[i];
        }

        System.arraycopy(result, 0, nums, 0, result.length);
    }


}
