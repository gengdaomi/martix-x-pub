package com.martix.x.pub.code.window;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Andrew-Geng on 2:19 上午 2021/4/10
 * <p>
 * 滑动窗口最大值 lc 239
 * <p>
 * 给你一个整数数组 nums，有一个大小为k的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k个数字。滑动窗口每次只向右移动一位。
 * <p>
 * 返回滑动窗口中的最大值。
 * <p>
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 */
public class MaxSlidingWindowSolution {

    /**
     * 借助单调队列来实现 + 滑动窗口
     * 时间复杂度O(n) 空间复杂度 O(k)
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue monotonicQueue = new MonotonicQueue();
        List<Integer> resultList = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) { //表示窗口还没有满
                monotonicQueue.push_back(nums[i]);
            } else {//表示一个窗口已经满了
                monotonicQueue.push_back(nums[i]);
                resultList.add(monotonicQueue.max_value()); //将当前窗口的最大值装入
                monotonicQueue.pop_front();
            }
        }

        int[] arr = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            arr[i] = resultList.get(i);
        }

        return arr;
    }

    /**
     * 单调递减队列
     */
    class MonotonicQueue {

        private LinkedList<Integer> oLinkedList;
        private LinkedList<Integer> mLinkedList;

        public MonotonicQueue() {
            oLinkedList = new LinkedList<>();
            mLinkedList = new LinkedList<>();
        }

        public int max_value() {
            if (!mLinkedList.isEmpty()) {
                return mLinkedList.getFirst();
            }

            return -1;
        }

        public void push_back(int value) {
            while (!mLinkedList.isEmpty() && mLinkedList.getLast() < value) {
                mLinkedList.pollLast();
            }

            oLinkedList.addLast(value);
            mLinkedList.addLast(value);

        }

        public int pop_front() {
            if (oLinkedList.isEmpty()) {
                return -1;
            }

            int val = oLinkedList.pollFirst();
            if (val == mLinkedList.getFirst()) {
                mLinkedList.pollFirst();
            }

            return val;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, -1, -3, 5, 3, 6, 7};

        int[] result = new MaxSlidingWindowSolution().maxSlidingWindow(arr, 3);

        Arrays.stream(result)
                .forEach(System.out::println);
    }
}
