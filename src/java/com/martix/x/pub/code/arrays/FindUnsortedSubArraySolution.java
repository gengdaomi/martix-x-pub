package com.martix.x.pub.code.arrays;

import java.util.Arrays;

/**
 * Created by Andrew-Geng on 01:02 2022/9/16
 * 最短无序连续子数组 lc581
 *
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 *
 * 请你找出符合题意的 最短 子数组，并输出它的长度。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,6,4,8,10,9,15]
 * 输出：5
 * 解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 * 示例 2：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：0
 * 示例 3：
 *
 * 输入：nums = [1]
 * 输出：0
 *
 */
public class FindUnsortedSubArraySolution {

    /**
     * 双指针
     *
     * 可以假设把这个数组分成三段，左段和右段是标准的升序数组，中段数组虽是无序的，但满足最小值大于左段的最大值，最大值小于右段的最小值；
     *
     * 那么我们目标就很明确了，找中段的左右边界，我们分别定义为begin 和 end;
     * 分两头开始遍历:
     *
     * 从左到右维护一个最大值max,在进入右段之前，那么遍历到的nums[i]都是小于max的，我们要求的end就是遍历中最后一个小于max元素的位置；
     * 同理，从右到左维护一个最小值min，在进入左段之前，那么遍历到的nums[i]也都是大于min的，要求的begin也就是最后一个大于min元素的位置。
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param nums
     * @return
     */
    public int findUnsortedSubarray(int[] nums) {
        int length = nums.length;
        int min = nums[length-1];
        int max = nums[0];
        int begin = 0, end = -1;


        for(int i = 0; i < length; i++){//遍历
            if(nums[i] < max){      //从左到右维持最大值，寻找右边界end
                end = i;
            }else{
                max = nums[i];
            }

            if(nums[length-i-1] > min){    //从右到左维持最小值，寻找左边界begin
                begin = length-i-1;
            }else{
                min = nums[length-i-1];
            }
        }
        return end-begin+1;
    }

    /**
     * 双指针 + 排序
     *
     * 最终目的是让整个数组有序，那么我们可以先将数组拷贝一份进行排序，
     * 然后使用两个指针 i 和 j 分别找到左右两端第一个不同的地方，那么[i,j] 这一区间即是答案
     *
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(n)
     * @param nums
     * @return
     */
    public int findUnsortedSubarray_1(int[] nums) {
        int n = nums.length;
        int[] arr = nums.clone();

        Arrays.sort(arr);

        int i = 0, j = n - 1;
        while (i <= j && nums[i] == arr[i]) {
            i++;
        }
        while (i <= j && nums[j] == arr[j]){
            j--;
        }

        return j - i + 1;
    }
}
