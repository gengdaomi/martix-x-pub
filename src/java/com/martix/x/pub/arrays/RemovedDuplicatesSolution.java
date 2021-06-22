package com.martix.x.pub.arrays;

/**
 * Created by Andrew-Geng on 12:55 上午 2021/4/2
 * 删除有序数组中的重复项
 * <p>
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成
 * <p>
 * 输入：nums = [1,1,2]
 * 输出：2, nums = [1,2]
 * 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
 * 输出：5, nums = [0,1,2,3,4]
 * 解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
 * <p>
 * <p>
 * lc 26
 */
public class RemovedDuplicatesSolution {

    public static void main(String[] args){
        int[] nums = new int[]{0,0,0,1,2,2,4};
        System.out.println(new RemovedDuplicatesSolution().removeDuplicates(nums));
    }

    /**
     * 双指针的方式
     * <p>
     * 数组完成排序后，我们可以放置两个指针i 和j，其中
     * i 是慢指针，而j 是快指针。只要nums[i]=nums[j]，我们就增加
     * j 以跳过重复项。
     * <p>
     * 当我们遇到
     * nums[j]
     * <p>
     * =nums[i] 时，跳过重复项的运行已经结束，因此我们必须把它（nums[j]）的值复制到
     * <p>
     * nums[i+1]。然后递增
     * i，接着我们将再次重复相同的过程，直到
     * j 到达数组的末尾为止
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int i = 0;

        for (int j = 0; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }

        return i + 1;
    }
}
