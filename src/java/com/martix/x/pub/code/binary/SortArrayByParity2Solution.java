package com.martix.x.pub.code.binary;

/**
 * Created by Andrew-Geng on 12:39 上午 2021/6/8
 * <p>
 * 按奇偶排序数组 II lc 922
 * <p>
 * 给定一个非负整数数组A， A 中一半整数是奇数，一半整数是偶数。
 * <p>
 * 对数组进行排序，以便当A[i] 为奇数时，i也是奇数；当A[i]为偶数时， i 也是偶数。
 * <p>
 * 你可以返回任何满足上述条件的数组作为答案。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：[4,2,5,7]
 * 输出：[4,5,2,7]
 * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 *  
 * <p>
 * 提示：
 * <p>
 * 2 <= A.length <= 20000
 * A.length % 2 == 0
 * 0 <= A[i] <= 1000
 */
public class SortArrayByParity2Solution {

    /**
     * 一次遍历
     *
     * @param nums
     * @return
     */
    public int[] sortArrayByParityII_0(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        int i = 0, j = 1;
        for (int x : nums) { //一次遍历
            if (x % 2 == 0) {  //偶数的
                result[i] = x;
                i += 2;
            } else {  //奇数的
                result[j] = x;
                j += 2;
            }
        }

        return result;
    }

    /**
     * 两次遍历 这个不如第一个方法
     * 遍历一遍数组把所有的偶数放进 ans[0]，ans[2]，ans[4]，依次类推。
     * <p>
     * 再遍历一遍数组把所有的奇数依次放进 ans[1]，ans[3]，ans[5]，依次类推。
     * 时间复杂度 O(N) 空间复杂度O(1)
     *
     * @param nums
     * @return
     */
    public int[] sortArrayByParityII(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        int i = 0;
        for (int x : nums) {
            if (x % 2 == 0) {
                result[i] = x;
                i += 2;
            }
        }

        i = 1;
        for (int x : nums) {
            if (x % 2 == 1) {
                result[i] = x;
                i += 2;
            }
        }

        return result;
    }

    /**
     * 双指针的方式
     * <p>
     * 设置两个指针，j i分别初始化为奇数和偶数的指针，
     * 依次遍历数组，步长都为2
     * 用i遍历数组的时候，将j依次遍历，
     *
     * @param nums
     * @return
     */
    public int[] sortArrayByParityII_1(int[] nums) {
        int n = nums.length;
        int j = 1; //j作为奇数的下标

        for (int i = 0; i < n; i += 2) { //i作为偶数的下标
            if (nums[i] % 2 == 1) {

                while (nums[j] % 2 == 1 && j < n) {
                    j += 2;
                }

                swap(nums, i, j);
            }
        }

        return nums;
    }

    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public static void main(String[] args){
        int[] nums = new int[]{4,2,3,6,5,7};
        int[] result = new SortArrayByParity2Solution().sortArrayByParityII_1(nums);
        System.out.println(result);
    }
}
