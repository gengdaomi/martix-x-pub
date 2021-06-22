package com.martix.x.sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrew-Geng on 10:25 下午 2021/4/15
 * N sum 相加 可以作为相加的通用算法
 * 非lc 题
 */
public class NSumSolution {

    public static void main(String[] args) {
        int[] nums = new int[]{1,0,-1,0,-2,2};
        Arrays.sort(nums);
        System.out.println(new NSumSolution().nSumTarget(nums,4,0,0));
    }

    /**
     * 调用函数前 需要进行排序Arrays.sort(nums)
     *
     * @param nums   这个数组需要排序过
     * @param n      几数相加
     * @param start
     * @param target
     * @return
     */
    public List<List<Integer>> nSumTarget(int[] nums, int n, int start, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (n < 2 || nums.length < n) { //至少是2sum，且数组大小不应该小于n
            return result;
        }

        if (n == 2) { //2sum 是递归的bad case

            int low = start, high = nums.length - 1;
            while (low < high) {
                int sum = nums[low] + nums[high];
                int left = nums[low], right = nums[high];

                if (sum == target) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(left);
                    temp.add(right);

                    result.add(temp);

                    while (low < high && nums[low] == left) {
                        low++;
                    }
                    while (low < high && nums[high] == right) {
                        high--;
                    }
                } else if (sum > target) {
                    while (low < high && nums[high] == right) {
                        high--;
                    }
                } else { //sum<target
                    while (low < high && nums[low] == left) {
                        low++;
                    }
                }
            }
        } else { //n>2 nSum的处理方式，采用递归的方式
            for (int i = start; i < nums.length; i++) {
                List<List<Integer>> subResult = nSumTarget(nums, n - 1, i + 1, target - nums[i]);

                for (List<Integer> list : subResult) {
                    list.add(nums[i]);
                    result.add(list);
                }

                while (i < nums.length - 1 && nums[i] == nums[i + 1]) { //跳过第一个数字的重复情况，否则结果子数组中会出现重复，而sum自行解决重复的数组问题
                    i++;
                }
            }
        }

        return result;
    }
}
