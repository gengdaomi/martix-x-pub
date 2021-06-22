package com.martix.x.pub.reverse;

import java.util.Arrays;

/**
 * Created by Andrew-Geng on 1:15 上午 2021/3/25
 * <p>
 * 反转数组
 */
public class ReversedArraySolution {

    /**
     * 借助 左右指针的算法来进行
     *
     * @param nums
     */
    public void reverse(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;

            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};
        new ReversedArraySolution().reverse(arr);

        Arrays.stream(arr)
                .forEach(System.out::println);
    }
}
