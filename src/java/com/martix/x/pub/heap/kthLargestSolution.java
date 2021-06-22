package com.martix.x.pub.heap;

/**
 * Created by ayue on 上午9:56 2018/6/28
 * <p>
 * 数组中的第K个最大元素
 * lc 215
 * <p>
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素
 * <p>
 * <p>
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * <p>
 * 说明:
 * <p>
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 */
public class kthLargestSolution {

    public static void main(String[] args) {
        int[] integers = new int[]{3,2,1,5,6,4};

        System.out.println(new kthLargestSolution().findKthLargest(integers, 2));
    }

    /**
     * 运用小顶堆的思路（小顶堆：父节点小于左右孩子节点）
     * <p>
     * 这个方法的时间复杂度是多少呢？
     * <p>
     * 1.构建堆的时间复杂度是 O（k）
     * 2.遍历剩余数组的时间复杂度是O（n-k）
     * 3.每次调整堆的时间复杂度是 O（logk）
     * 其中2和3是嵌套关系，1和2,3是并列关系，所以总的最坏时间复杂度是O（（n-k）logk + k）。当k远小于n的情况下，也可以近似地认为是O（nlogk）
     * <p>
     * <p>
     * 空间复杂度：我们可以把数组的前k个元素“原地交换”来构建成二叉堆，这样就免去了开辟额外的存储空间。 所以复杂度O(1)
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        Heap heap = new Heap();
        heap.buildHeap(nums, k); //构建小顶堆，堆的大小为k

        for (int i = k; i < nums.length; i++) {
            if (nums[0] < nums[i]) {
                nums[0] = nums[i];
                heap.downAdjust(nums, 0, k);
            }
        }

        return nums[0];//返回堆顶，刚好是大小为k的小顶堆中最小值，也就是整个nums中最k大的值
    }

    /**
     * 分治法 类似快速排序，最差情况的时间复杂度O(nk)
     * 快速排序利用分治法，每一次把数组分成较大和较小的两部分。
     * 以某个元素A为基准，把大于于A的元素都交换到数组左边，小于A的元素都交换到数组右边。
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest_1(int[] nums, int k) {
        return sort(nums, nums.length - k, 0, nums.length - 1);
    }

    private int sort(int[] nums, int k, int left, int right) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int i = left;
        int j = right;

        int p = nums[i];

        while (i < j) {
            while (i < j && p <= nums[j]) {
                j--;
            }

            if (p > nums[j]) {
                nums[i++] = nums[j];
            }

            while (i < j && p >= nums[i]) {
                i++;
            }

            if (p < nums[i]) {
                nums[j--] = nums[i];
            }
        }

        nums[i] = p;

        if (left < i && k < i) {
            return sort(nums, k, left, i - 1);
        }
        if (right > j && k > j) {
            return sort(nums, k, j + 1, right);
        }

        return nums[i];
    }

    /**
     * 小顶堆
     */
    class Heap {
        /**
         * 构建小顶堆，
         *
         * @param array
         * @param length 小顶堆的长度
         */
        public void buildHeap(int[] array, int length) {
            for (int i = (length - 2) / 2; i >= 0; i--) {
                downAdjust(array, i, length);
            }
        }

        /**
         * 向下调整
         *
         * @param array  待调整的堆
         * @param index  需要下沉的节点下标 可以当父节点
         * @param length 堆的大小
         */
        public void downAdjust(int[] array, int index, int length) {
            int temp = array[index]; //用于交换

            int childIndex = 2 * index + 1;

            while (childIndex < length) {
                if (childIndex + 1 < length && array[childIndex + 1] < array[childIndex]) { //如果有右孩子，且右孩子小于左孩子的值，则定位到右孩子
                    childIndex++;
                }

                if (temp < array[childIndex]) { //如果父节点小于任何一个孩子的值，直接跳出
                    break;
                }

                array[index] = array[childIndex];
                index = childIndex;
                childIndex = 2 * childIndex + 1;
            }

            array[index] = temp;
        }
    }
}
