package com.martix.x.pub.code.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by Andrew-Geng on 1:30 上午 2021/7/4
 * 主持人调度
 * 有n个活动即将举办，每个活动都有活动的开始时间与活动的结束时间;
 * 求为了成功举办这n个活动，最少需要多少名主持人
 * <p>
 * 示例1
 * <p>
 * 输入：
 * 2,[[1,2],[2,3]]
 * 复制
 * 返回值：
 * 1
 * 复制
 * 说明：
 * 只需要一个主持人就能成功举办这两个活动
 */
public class MinmumNumberOfHostSolution {

    /**
     * 优先级队列
     * 首先：要对活动进行排序：
     * 1.开始时间相等的，结束时间从小到大
     * 2.开始时间不相等的，开始时间从小到大
     * <p>
     * 其次：建立一个优先级队列：默认升序，同时处理活动
     * 1.只提供结束时间，如果当前的开始时间小于队首的结束时间，说明没空闲
     * 2.如果当前的开始时间大于队首的结束时间，说明可以空闲一个，队首出队
     *
     * @param n
     * @param startEnd
     * @return
     */
    public int minmumNumberOfHost(int n, int[][] startEnd) {
        /*
        排序:
         头相等的，尾从小到大
         头不相等的头从小到大
         */
        // 头不相等的头从小到大
        Arrays.sort(startEnd, new Comparator<int[]>() {
            public int compare(int[] arr1, int[] arr2) {
                if (arr1[0] == arr2[0]) {
                    return arr1[1] - arr2[1];
                }
                return arr1[0] - arr2[0];
            }
        });

        // 默认升序
        int min = Integer.MIN_VALUE;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.offer(min);

        for (int i = 0; i < n; i++) {
            /*
             只提供结束时间，如果当前的开始时间小于队首的结束时间，说明没空闲
             如果当前的开始时间大于队首的结束时间，说明可以空闲一个，队首出队
             */
            if (queue.peek() <= startEnd[i][0]) {
                queue.poll();
            }

            queue.offer(startEnd[i][1]);
        }
        return queue.size();
    }

    /**
     * 循环遍历
     * <p>
     * 对活动开始时间进行排序
     * 对活动结束时间进行排序
     * <p>
     * starts[start] >= ends[end]时end++;
     * 否则count++即需要增加一个主持人
     *
     * @param n
     * @param startEnd
     * @return
     */
    public int minmumNumberOfHost_1(int n, int[][] startEnd) {
        int[] starts = new int[startEnd.length];
        int[] ends = new int[startEnd.length];

        for (int i = 0; i < startEnd.length; i++) {
            starts[i] = startEnd[i][0];
            ends[i] = startEnd[i][1];
        }

        Arrays.sort(starts);
        Arrays.sort(ends);

        int count = 0;
        int end = 0;

        for (int start = 0; start < startEnd.length; start++) {
            if (starts[start] >= ends[end]) {
                end++;
            } else {
                count++;
            }
        }

        return count;
    }
}
