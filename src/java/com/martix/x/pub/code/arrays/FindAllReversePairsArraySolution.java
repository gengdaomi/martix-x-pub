package com.martix.x.pub.code.arrays;

/**
 * Created by Andrew-Geng on 22:51 2022/9/16
 *
 * 数组中的逆序对  剑指 Offer 51  hard
 *
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 *
 *
 * 示例 1:
 *
 * 输入: [7,5,6,4]
 * 输出: 5
 *
 */
public class FindAllReversePairsArraySolution {

    /**
     *  思路： 借用归并排序
     *  归并的思路主要是分治思想，
     *  1.分：不断将数组从中点位置划分开（即二分法），将整个数组的排序问题转化为子数组的排序问题；
     *  2.治： 划分到子数组长度为 1 时，开始向上合并，不断将 较短排序数组 合并为 较长排序数组，直至合并至原数组时完成排序；
     *
     *  合并阶段 本质上是 合并两个排序数组 的过程，而每当遇到 左子数组当前元素 > 右子数组当前元素 时，
     *  意味着 「左子数组当前元素 至 末尾元素」 与 「右子数组当前元素」 构成了若干 「逆序对」 。
     *
     *  因此，考虑在归并排序的合并阶段统计「逆序对」数量，完成归并排序时，也随之完成所有逆序对的统计；
     *
     *  时间复杂度O(NlogN), N为数组长度，归并排序使用O(NlogN)
     *  空间复杂度O(N), 辅助数组temp占用O(N)的时间
     * @param nums
     * @return
     */

    int count;
    public int reversePairs(int[] nums) {
        this.count = 0;
        merge(nums, 0, nums.length - 1);
        return count;
    }

    public void merge(int[] nums, int left, int right) {
        int mid = left + ((right - left) >> 1);

        if (left < right) {
            merge(nums, left, mid);
            merge(nums, mid + 1, right);
            mergeSort(nums, left, mid, right);
        }
    }

    public void mergeSort(int[] nums, int left, int mid, int right) {
        int[] tempArray = new int[right - left + 1];
        int index = 0;
        int temp1 = left, temp2 = mid + 1;

        while (temp1 <= mid && temp2 <= right) {
            if (nums[temp1] <= nums[temp2]) {
                tempArray[index++] = nums[temp1++];
            } else {
                //用来统计逆序对的个数
                count += (mid - temp1 + 1);
                tempArray[index++] = nums[temp2++];
            }
        }

        while (temp1 <= mid) { //把左边剩余的数移入数组
            tempArray[index++] = nums[temp1++];
        }

        while (temp2 <= right) {//把右边剩余的数移入数组
            tempArray[index++] = nums[temp2++];
        }

        for (int k = 0; k < tempArray.length; k++) {//把新数组中的数覆盖nums数组
            nums[k + left] = tempArray[k];
        }
    }

}
