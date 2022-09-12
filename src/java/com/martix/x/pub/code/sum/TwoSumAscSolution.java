package com.martix.x.pub.code.sum;

import java.util.Arrays;

/**
 * Created by Andrew-Geng on 10:51 下午 2021/4/14
 * 两数之和 II - 输入有序数组
 * lc 167
 * <p>
 * 给定一个已按照 升序排列的整数数组numbers ，请你从数组中找出两个数满足相加之和等于目标数target 。
 * <p>
 * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。
 * numbers 的下标 从 1 开始计数 ，所以答案数组应当满足 1 <= answer[0] < answer[1] <= numbers.length 。
 * <p>
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * <p>
 *  
 * 示例 1：
 * <p>
 * 输入：numbers = [2,7,11,15], target = 9
 * 输出：[1,2]
 * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 * 示例 2：
 * <p>
 * 输入：numbers = [2,3,4], target = 6
 * 输出：[1,3]
 * 示例 3：
 * <p>
 * 输入：numbers = [-1,0], target = -1
 * 输出：[1,2]
 */
public class TwoSumAscSolution {

    public static void main(String[] args) {
        int[] a = new int[]{2, 7, 11, 15};

        Arrays.stream(new TwoSumAscSolution().twoSum_1(a, 9))
                .forEach(System.out::println);
    }

    /**
     * 因为是有序的，所以可以考虑双指针
     * <p>
     * 时间复杂度O(N) 空间复杂度O(1)
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[]{0, 0};

        int i = 0, j = numbers.length - 1;
        while (i < j) {
            int sum = numbers[i] + numbers[j];
            if (sum > target) {
                j--;
            }

            if (sum < target) {
                i++;
            }

            if (sum == target) {
                result[0] = i + 1;
                result[1] = j + 1;

                return result;
            }
        }

        return new int[]{-1, -1};
    }

    /**
     * 因为是有序的，可以考虑二分查找
     * <p>
     * 在数组中找到两个数，使得它们的和等于目标值，可以首先固定第一个数，然后寻找第二个数，第二个数等于目标值减去第一个数的差。
     * 利用数组的有序性质，可以通过二分查找的方法寻找第二个数。为了避免重复寻找，在寻找第二个数时，只在第一个数的右侧寻找。
     * <p>
     * <p>
     * 时间复杂度：
     * O(nlogn)，其中
     * n 是数组的长度。需要遍历数组一次确定第一个数，时间复杂度是
     * O(n)，寻找第二个数使用二分查找，时间复杂度是
     * <p>
     * O(logn)，因此总时间复杂度是
     * O(nlogn)。
     * 空间复杂度：
     * <p>
     * O(1)。
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum_1(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {

            int low = i + 1, high = numbers.length - 1;

            while (low <= high) {
                int mid = (high - low) / 2 + low;

                if (numbers[mid] == target - numbers[i]) {
                    return new int[]{i + 1, mid + 1};
                } else if (numbers[mid] > target - numbers[i]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return new int[]{-1, -1};
    }
}
