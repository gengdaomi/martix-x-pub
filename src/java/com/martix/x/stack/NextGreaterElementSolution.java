package com.martix.x.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by Andrew-Geng on 12:45 下午 2021/5/16
 * 下一个更大元素 I lc 496
 * <p>
 * 给你两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。
 * <p>
 * 请你找出 nums1 中每个元素在 nums2 中的下一个比其大的值。
 * <p>
 * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 输出: [-1,3,-1]
 * 解释:
 * 对于 num1 中的数字 4 ，你无法在第二个数组中找到下一个更大的数字，因此输出 -1 。
 * 对于 num1 中的数字 1 ，第二个数组中数字1右边的下一个较大数字是 3 。
 * 对于 num1 中的数字 2 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
 * 示例 2:
 * <p>
 * 输入: nums1 = [2,4], nums2 = [1,2,3,4].
 * 输出: [3,-1]
 * 解释:
 *     对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
 * 对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
 */
public class NextGreaterElementSolution {

    public static void main(String[] args) {
        int[] num1 = new int[]{2, 4};
        int[] num2 = new int[]{1, 2, 3, 4};

        int[] result = new NextGreaterElementSolution().nextGreaterElement(num1, num2);
        System.out.println(result);

    }

    /**
     * 通过单调栈的思路
     * <p>
     * 简单而言 即 先对num2 从右到左 一次找到每个数的下一个最大值，并存入 map中
     * 然后遍历num1 去 map依次取 对应的最大值
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];

        if (nums1.length == 0) {
            return result;
        }

        Map<Integer, Integer> map = new HashMap<>(); //key num2的值，value num2值得下一个大值
        Stack<Integer> stack = new Stack<>();

        for (int i = nums2.length - 1; i >= 0; i--) {

            while (!stack.isEmpty() && stack.peek() < nums2[i]) {
                stack.pop();
            }

            map.put(nums2[i], stack.isEmpty() ? -1 : stack.peek());
            stack.push(nums2[i]);
        }

        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.get(nums1[i]);
        }

        return result;
    }

    /**
     * 暴力解法  O(N*M)
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement_1(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];

        if (nums1.length == 0) {
            return result;
        }

        for (int i = 0; i < nums1.length; i++) {
            int curVal = nums1[i];

            int j = 0;
            while (j < nums2.length && nums1[i] != nums2[j]) {
                j++;
            }

            j++;  //此时已经找到num2中相等的数

            while (j < nums2.length && curVal >= nums2[j]) { //继续遍历，当一次下一个数小于num1的值时
                j++;
            }

            if (j == nums2.length) {
                result[i] = -1;
                continue;
            }

            result[i] = nums2[j];
        }

        return result;
    }
}
