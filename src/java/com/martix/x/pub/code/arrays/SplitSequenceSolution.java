package com.martix.x.pub.code.arrays;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Created by Andrew-Geng on 11:42 下午 2021/7/6
 * 分割数组为连续子序列
 * lc 659
 *
 * 给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个长度至少为 3 的子序列，其中每个子序列都由连续整数组成。
 * 如果可以完成上述分割，则返回 true ；否则，返回 false 。
 *
 *
 * 示例 1：
 *
 * 输入: [1,2,3,3,4,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 :
 * 1, 2, 3
 * 3, 4, 5
 * 示例 2：
 *
 * 输入: [1,2,3,3,4,4,5,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 :
 * 1, 2, 3, 4, 5
 * 3, 4, 5
 * 示例 3：
 *
 * 输入: [1,2,3,4,4,5]
 * 输出: False
 *
 */
public class SplitSequenceSolution {

    /**
     * 哈希表 + 最小堆
     * 核心思路：
     * 只要知道子序列的最后一个数字和子序列的长度，就能确定子序列；
     *
     * 当x 在数组中时，如果存在一个子序列以x−1 结尾，长度为k，则可以将x 加入该子序列中，
     * 得到长度为k+1 的子序列。如果不存在以x−1 结尾的子序列，则必须新建一个只包含 x 的子序列，长度为1；
     *
     * 当x 在数组中时，如果存在多个子序列以x−1 结尾，应该将x 加入其中的哪一个子序列？
     * 由于题目要求每个子序列的长度至少为3，显然应该让最短的子序列尽可能长，因此应该将x加入其中最短的子序列
     *
     * 哈希表的键为子序列的最后一个数字，值为最小堆，用于存储所有的子序列长度，最小堆满足堆顶的元素是最小的，因此堆顶的元素即为最小的子序列长度
     *
     * 时间复杂度O(nlogn) 需要遍历数组，对于数组中的每个数，都要对哈希表和最小堆进行更新;每次对最小堆的操作的时间复杂度是O(logn)，数组长度为n;
     * 空间复杂度O(n) 其中n 是数组的长度。需要使用哈希表和最小堆存储以每个数结尾的各个子序列的长度，哈希表和最小堆中的元素数量不会超过数组的长度
     * @param nums
     * @return
     */
    public boolean isPossible(int[] nums) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<Integer, PriorityQueue<Integer>>();

        for (int x : nums) {
            if (!map.containsKey(x)) {
                map.put(x, new PriorityQueue<Integer>());
            }

            if (map.containsKey(x - 1)) {
                int prevLength = map.get(x - 1).poll();
                if (map.get(x - 1).isEmpty()) {
                    map.remove(x - 1);
                }

                map.get(x).offer(prevLength + 1);
            } else {
                map.get(x).offer(1);
            }
        }

        Set<Map.Entry<Integer, PriorityQueue<Integer>>> entrySet = map.entrySet();
        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : entrySet) {
            PriorityQueue<Integer> queue = entry.getValue();

            if (queue.peek() < 3) {
                return false;
            }
        }

        return true;
    }
}
