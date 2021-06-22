package com.martix.x.arrays;

import java.util.Arrays;

/**
 * Created by Andrew-Geng on 12:41 上午 2021/6/9
 * 有序数组的平方 lc 977
 * <p>
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 解释：平方后，数组变为 [16,1,0,9,100]
 * 排序后，数组变为 [0,1,9,16,100]
 * 示例 2：
 * <p>
 * 输入：nums = [-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 已按 非递减顺序 排序
 *  
 * <p>
 * 进阶：
 * <p>
 * 请你设计时间复杂度为 O(n) 的算法解决本问题
 */
public class SortedSquaresSolution {

    /**
     * 双指针的方式
     * <p>
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)  除了存储答案的数组以外，我们只需要维护常量空间
     *
     * @param nums
     * @return
     */
    public int[] sortedSquares(int[] nums) {
        int low = 0, high = nums.length - 1;
        int index = nums.length - 1;

        int[] result = new int[nums.length];

        while (low <= high) {
            if (nums[low] * nums[low] > nums[high] * nums[high]) {
                result[index] = nums[low] * nums[low];
                low++;
            } else {
                result[index] = nums[high] * nums[high];
                high--;
            }

            index--;
        }

        return result;
    }

    /**
     * 双指针的方式
     * <p>
     * 如果数组nums 中的所有数都是非负数，那么将每个数平方后，数组仍然保持升序；如果数组nums 中的所有数都是负数，那么将每个数平方后，数组会保持降序
     * 如果我们能够找到数组nums 中负数与非负数的 分界线 ，那么就可以用类似「归并排序」的方法
     * <p>
     * nums[0] 到 nums[neg] 均为负数
     * nums[neg+1] 到 nums[n−1] 均为非负数
     * <p>
     * 当我们将数组nums 中的数平方后，那么nums[0] 到  nums[neg] 单调递减，nums[neg+1] 到 nums[n−1] 单调递增
     * <p>
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     *
     * @param nums
     * @return
     */
    public int[] sortedSquares_1(int[] nums) {
        int negative = -1; //负数 和 正数的分隔位置

        for (int i = 0; i <= nums.length - 1; i++) {
            if (nums[i] < 0) {
                negative = i;
            } else {
                break;
            }
        }

        int[] result = new int[nums.length];
        int index = 0, i = negative, j = negative + 1;

        while (i >= 0 || j < nums.length) {
            if (i < 0) {  //nums 全是正数
                result[index] = nums[j] * nums[j];
                j++;
            } else if (j == nums.length) {  //nums 里面全是负数
                result[index] = nums[i] * nums[i];
                i--;
            } else if (nums[i] * nums[i] < nums[j] * nums[j]) {
                result[index] = nums[i] * nums[i];
                i--;
            } else {
                result[index] = nums[j] * nums[j];
                j++;
            }

            index++;
        }

        return result;
    }

    /**
     * 直接排序的方式
     * <p>
     * 时间复杂度O(nlogn)
     * 空间复杂度O(logn)
     *
     * @param nums
     * @return
     */
    public int[] sortedSquares_2(int[] nums) {
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            ans[i] = nums[i] * nums[i];
        }

        Arrays.sort(ans);
        return ans;
    }
}
