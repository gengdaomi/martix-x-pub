package com.martix.x.pub.sort;

/**
 * Created by Andrew-Geng on 10:45 下午 2021/5/10
 * 冒泡排序
 */
public class BubbleSortSolution {

    /**
     * 平均时间复杂度O(N^2)
     * @param nums
     */
    public void sort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                int temp = 0;

                if (nums[j] > nums[j + 1]) {
                    temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 优化版，因为在每次循环的过程中，有可能出现还有遍历完的时候，就已经有序了，这个时候其实不需要后续的遍历
     * @param nums
     */
    public void sort_1(int[] nums){
        for (int i = 0; i < nums.length - 1; i++) {
            boolean isSort=true;

            for (int j = 0; j < nums.length - 1 - i; j++) {
                int temp = 0;

                if (nums[j] > nums[j + 1]) {
                    temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;

                    isSort=false;
                }
            }
            if(isSort){
                break;
            }
        }
    }
}
