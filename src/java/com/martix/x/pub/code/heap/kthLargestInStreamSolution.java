package com.martix.x.pub.code.heap;

/**
 * Created by Andrew-Geng on 12:27 上午 2021/5/31
 * <p>
 * 数据流中的第 K 大元素
 * lc 703
 * <p>
 * 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
 * <p>
 * 请实现 KthLargest类：
 * <p>
 * KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
 * int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。
 * <p>
 * 示例：
 * <p>
 * 输入：
 * ["KthLargest", "add", "add", "add", "add", "add"]
 * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 * 输出：
 * [null, 4, 5, 5, 8, 8]
 * <p>
 * 解释：
 * KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 * kthLargest.add(3);   // return 4
 * kthLargest.add(5);   // return 5
 * kthLargest.add(10);  // return 5
 * kthLargest.add(9);   // return 8
 * kthLargest.add(4);   // return 8
 *  
 * <p>
 * 提示：
 * 1 <= k <= 104
 * 0 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * -104 <= val <= 104
 * 最多调用 add 方法 104 次
 * 题目数据保证，在查找第 k 大元素时，数组中至少有 k 个元素
 */
public class kthLargestInStreamSolution {

    public static void main(String[] args) {
        int[] nums = new int[]{4,5,8,2};
        kthLargestInStreamSolution kthLargestInStreamSolution = new kthLargestInStreamSolution(3, nums);
        kthLargestInStreamSolution.add(3);
        kthLargestInStreamSolution.add(5);
        kthLargestInStreamSolution.add(10);
        kthLargestInStreamSolution.add(9);
        kthLargestInStreamSolution.add(4);

        System.out.println(nums);
    }

    private int[] heap;
    private int count = 0;

    /**
     * 运用小顶堆的思路（小顶堆：父节点小于左右孩子节点）
     * @param k
     * @param nums
     */
    public kthLargestInStreamSolution(int k, int[] nums) {
        heap = new int[k + 1];
        count = 0;
        for (int num : nums) {
            add(num);
        }

    }

    public int add(int val) {
        if (count < heap.length - 1) {
            heap[++count] = val;
            shiftUp(count);
        } else {
            if (val > heap[1]) {
                heap[1] = val;
                shiftDown(1);
            }
        }
        return heap[1];

    }

    private void shiftUp(int index) {
        while (index > 1) {
            int p = index >> 1;
            if (heap[p] <= heap[index]) {
                break;
            }
            heap[p] ^= heap[index];
            heap[index] ^= heap[p];
            heap[p] ^= heap[index];

            index = p;
        }
    }

    private void shiftDown(int index) {
        while (index < count) {
            int t = index;

            if ((index << 1) <= count && heap[index << 1] < heap[t]) {
                t = index << 1;
            }

            if ((index << 1) + 1 <= count && heap[(index << 1) + 1] < heap[t]) {
                t = (index << 1) + 1;
            }

            if (t == index) {
                break;
            }

            heap[t] ^= heap[index];
            heap[index] ^= heap[t];
            heap[t] ^= heap[index];

            index = t;
        }
    }
}
