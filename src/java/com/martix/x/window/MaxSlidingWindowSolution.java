package com.martix.x.window;

import com.martix.x.queue.MonotonicQueueSolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrew-Geng on 2:19 上午 2021/4/10
 * <p>
 * 滑动窗口最大值 lc 239
 * <p>
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * <p>
 * 返回滑动窗口中的最大值。
 * <p>
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 */
public class MaxSlidingWindowSolution {

    /**
     * 借助单调队列来实现
     * 时间复杂度O(n) 空间复杂度 O(k)
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueueSolution monotonicQueueSolution = new MonotonicQueueSolution();
        List<Integer> resultList = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) { //表示窗口还没有满
                monotonicQueueSolution.push_back(nums[i]);
            } else {//表示一个窗口已经满了
                monotonicQueueSolution.push_back(nums[i]);
                resultList.add(monotonicQueueSolution.max_value()); //将当前窗口的最大值装入
                monotonicQueueSolution.pop_front();
            }
        }

        int[] arr = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            arr[i] = resultList.get(i);
        }

        return arr;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, -1, -3, 5, 3, 6, 7};

        int[] result = new MaxSlidingWindowSolution().maxSlidingWindow(arr, 3);

        Arrays.stream(result)
                .forEach(System.out::println);
    }
}
