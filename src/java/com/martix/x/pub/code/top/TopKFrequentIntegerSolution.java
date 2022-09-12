package com.martix.x.pub.code.top;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by Andrew-Geng on 12:37 上午 2021/5/29
 * <p>
 * 前 K 个高频元素 lc 347
 * <p>
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * <p>
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 *  
 * <p>
 * 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
 */
public class TopKFrequentIntegerSolution {
    public static void main(String[] args) {
        int[] nums= new int[]{1,1,1,2,2,3};
        int [] result =new TopKFrequentIntegerSolution().topKFrequent(nums,2);
        System.out.println(result);
    }

    /**
     * 结合优先级队列 堆的思路
     * <p>
     * 时间复杂度O(Nlogk)  空间复杂度O(n)
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(); // key:某个数字，value:次数

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        /**
         * int[] int[0]表示为数字，int[1]次数
         */
        PriorityQueue<Integer[]> priorityQueue = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return o1[1] - o2[1];
            }
        });

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey(), count = entry.getValue();

            if (priorityQueue.size() == k) {
                if (priorityQueue.peek()[1] < count) { //优先级队列 第一个数字的次数
                    priorityQueue.poll();
                    priorityQueue.offer(new Integer[]{key, count});
                }
            } else {
                priorityQueue.offer(new Integer[]{key, count});
            }
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = priorityQueue.poll()[0];
        }

        return result;
    }
}
