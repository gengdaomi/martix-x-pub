package com.martix.x.pub.code.arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrew-Geng on 10:03 2022/12/20
 * 多数元素 lc169
 *
 * 给定一个大小为 n 的数组nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于⌊ n/2 ⌋的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 *
 * 示例1：
 *
 * 输入：nums = [3,2,3]
 * 输出：3
 * 示例2：
 *
 * 输入：nums = [2,2,1,1,1,2,2]
 * 输出：2
 *
 * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 */
public class MajorityElementHalfArraySolution {

    /**
     * 摩尔投票法  最优解
     *
     * 候选人(cand_num)初始化为nums[0]，票数count初始化为1。
     * 当遇到与cand_num相同的数，则票数count = count + 1，否则票数count = count - 1。
     * 当票数count为0时，更换候选人，并将票数count重置为1。
     * 遍历完数组后，cand_num即为最终答案。
     *
     * 为何这行得通呢？
     * 投票法是遇到相同的则票数 + 1，遇到不同的则票数 - 1。
     * 且“多数元素”的个数> ⌊ n/2 ⌋，其余元素的个数总和<= ⌊ n/2 ⌋。
     * 因此“多数元素”的个数 - 其余元素的个数总和 的结果 肯定 >= 1。
     * 这就相当于每个“多数元素”和其他元素 两两相互抵消，抵消到最后肯定还剩余至少1个“多数元素”。
     *
     * 无论数组是1 2 1 2 1，亦或是1 2 2 1 1，总能得到正确的候选人。
     *
     * 时间复杂度：O(n)。Boyer-Moore 算法只对数组进行了一次遍历。
     * 空间复杂度：O(1)。Boyer-Moore 算法只需要常数级别的额外空间
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int cand_num = nums[0], count = 1;

        for (int i = 1; i < nums.length; i++) {
            if (cand_num == nums[i]){
                ++count;
            } else if (--count == 0) {
                cand_num = nums[i];
                count = 1;
            }
        }

        return cand_num;
    }






    /**
     * 哈希表
     *
     * 思路
     *
     * 我们知道出现次数最多的元素大于n/2次，所以可以用哈希表来快速统计每个元素出现的次数。
     *
     * 算法
     *
     * 我们使用哈希映射（HashMap）来存储每个元素以及出现的次数。对于哈希映射中的每个键值对，键表示一个元素，值表示该元素出现的次数。
     *
     * 我们用一个循环遍历数组 nums 并将数组中的每个元素加入哈希映射中。
     * 在这之后，我们遍历哈希映射中的所有键值对，返回值最大的键。
     * 我们同样也可以在遍历数组 nums 时候使用打擂台的方法，维护最大的值，这样省去了最后对哈希映射的遍历
     *
     * 时间复杂度O(n) 空间复杂度O(n)
     *
     * @param nums
     * @return
     */
    public int majorityElement_1(int[] nums) {
        Map<Integer, Integer> counts = countNums(nums);

        Map.Entry<Integer, Integer> majorityEntry = null;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
                majorityEntry = entry;
            }
        }

        return majorityEntry.getKey();
    }

    private Map<Integer, Integer> countNums(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
        for (int num : nums) {
            if (!counts.containsKey(num)) {
                counts.put(num, 1);
            } else {
                counts.put(num, counts.get(num) + 1);
            }
        }
        return counts;
    }





    /**
     * 排序
     *
     * 如果将数组 nums 中的所有元素按照单调递增或单调递减的顺序排序，那么下标为n/2的元素（下标从 0 开始）一定是众数。
     *
     *
     * 时间复杂度O(nlogn)
     * 空间复杂度O(logn),如果是自己实现堆排序的方式，则O(1)
     */
    public int majorityElement_2(int[] nums) {
        sort(nums);
        return nums[nums.length / 2];
    }

    private static void sort(int[] arr) {
        //1.构建大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr, i, arr.length);
        }

        //2.调整堆结构+交换堆顶元素与末尾元素
        for (int j = arr.length - 1; j > 0; j--) {
            swap(arr, 0, j);//将堆顶元素与末尾元素进行交换
            adjustHeap(arr, 0, j);//重新对堆进行调整
        }

    }

    /**
     * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
     *
     * @param arr
     * @param parentIndex
     * @param length
     */
    private static void adjustHeap(int[] arr, int parentIndex, int length) {
        int temp = arr[parentIndex];//先取出当前元素parentIndex

        for (int childIndex = parentIndex * 2 + 1; childIndex < length; childIndex = childIndex * 2 + 1) {//从parentIndex结点的左子结点开始，也就是2*parentIndex+1处开始
            if (childIndex + 1 < length && arr[childIndex] < arr[childIndex + 1]) {//如果左子结点小于右子结点，k指向右子结点
                childIndex++;
            }

            if (arr[childIndex] > temp) {//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                arr[parentIndex] = arr[childIndex];
                parentIndex = childIndex;
            } else {
                break;
            }
        }
        arr[parentIndex] = temp;//将temp值放到最终的位置
    }

    /**
     * 交换元素
     *
     * @param arr
     * @param a
     * @param b
     */
    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }













    /**
     * 分治法
     * 如果数 a 是数组 nums 的众数，如果我们将 nums 分成两部分，那么 a 必定是至少一部分的众数；
     * 将数组分成左右两部分，分别求出左半部分的众数 a1 以及右半部分的众数 a2，随后在 a1 和 a2 中选出正确的众数
     *
     * 时间复杂度O(nlogn)
     * 空间复杂度O(logn)
     */
    public int majorityElement_3(int[] nums) {
        return fork(nums, 0, nums.length - 1);
    }

    private int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    private int fork(int[] nums, int lo, int hi) {
        if (lo == hi) {
            return nums[lo];
        }

        // recurse on left and right halves of this slice.
        int mid = (hi - lo) / 2 + lo;
        int left = fork(nums, lo, mid);
        int right = fork(nums, mid + 1, hi);

        // if the two halves agree on the majority element, return it.
        if (left == right) {
            return left;
        }

        // otherwise, count each element and return the "winner".
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);

        return leftCount > rightCount ? left : right;
    }

}
