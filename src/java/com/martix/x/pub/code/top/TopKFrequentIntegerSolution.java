package com.martix.x.pub.code.top;

import java.util.*;

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

    /**
     * 基于快速排序
     *
     * 使用基于快速排序的方法，求出「出现次数数组」的前k 大的值。
     *
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent_1(int[] nums, int k) {
        Map<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
        for (int num : nums) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }

        List<int[]> values = new ArrayList<int[]>();
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            values.add(new int[]{num, count});
        }

        int[] ret = new int[k];
        quickSort(values, 0, values.size() - 1, ret, 0, k);
        return ret;
    }

    private void quickSort(List<int[]> values, int start, int end, int[] ret, int retIndex, int k) {
        int picked = (int) (Math.random() * (end - start + 1)) + start;
        Collections.swap(values, picked, start);

        int pivot = values.get(start)[1];
        int index = start;
        for (int i = start + 1; i <= end; i++) {
            if (values.get(i)[1] >= pivot) {
                Collections.swap(values, index + 1, i);
                index++;
            }
        }
        Collections.swap(values, start, index);

        if (k <= index - start) {
            quickSort(values, start, index - 1, ret, retIndex, k);
        } else {
            for (int i = start; i <= index; i++) {
                ret[retIndex++] = values.get(i)[0];
            }
            if (k > index - start + 1) {
                quickSort(values, index + 1, end, ret, retIndex, k - (index - start + 1));
            }
        }
    }

}
