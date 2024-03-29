package com.martix.x.pub.code.binary;

/**
 * Created by Andrew-Geng on 12:21 上午 2021/4/30
 * 移除元素
 * lc 27
 * <p>
 * 给你一个数组nums和一个值val，你需要 原地 移除所有数值等于val的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * <p>
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 说明:
 * <p>
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * <p>
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * <p>
 * 你可以想象内部操作如下:
 * <p>
 * // nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
 * int len = removeElement(nums, val);
 * <p>
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
 * for (int i = 0; i < len; i++) {
 *   print(nums[i]);
 * }
 *
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,2,2,3], val = 3
 * 输出：2, nums = [2,2]
 * 解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。例如，函数返回的新长度为 2 ，而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。
 * 示例 2：
 * <p>
 * 输入：nums = [0,1,2,2,3,0,4,2], val = 2
 * 输出：5, nums = [0,1,4,0,3]
 * 解释：函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。注意这五个元素可为任意顺序。你不需要考虑数组中超出新长度后面的元素。
 */
public class RemoveElementSolution {

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,2,3};
        System.out.println(new RemoveElementSolution().removeElement(nums, 3));
    }

    /**
     * 时间复杂度O(n) 空间复杂度O(1)
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            if (nums[left] == val) {
                nums[left] = nums[right];
                right--;
            } else {
                left++;
            }
        }
        return left;
    }

    /**
     * 双指针的方式
     * <p>
     * 时间复杂度O(n) 空间复杂度O(1)
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement_1(int[] nums, int val) {
        if (nums.length == 0 || (nums.length == 1 && nums[0] == val)) {
            return 0;
        }

        int count = 0;
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            if (nums[left] != val) {
                left++;
            } else {
                while (left <= right && nums[right] == val) {
                    right--;
                }
                if (left > right) {
                    break;
                }

                nums[left] ^= nums[right];  //两个值做对换
                nums[right] ^= nums[left];
                nums[left] ^= nums[right];

                left++;
                right--;
            }

            count++;
        }

        return count;
    }

}
