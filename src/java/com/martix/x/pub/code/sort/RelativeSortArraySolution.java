package com.martix.x.pub.code.sort;

/**
 * Created by Andrew-Geng on 21:22 2022/9/12
 * 数组的相对排序 lc1122
 * <p>
 * 给你两个数组，arr1 和 arr2，arr2中的元素各不相同，arr2 中的每个元素都出现在arr1中。
 * <p>
 * 对 arr1中的元素进行排序，使 arr1 中项的相对顺序和arr2中的相对顺序相同。未在arr2中出现过的元素需要按照升序放在arr1的末尾
 * <p>
 * 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * 输出：[2,2,2,1,4,3,3,9,6,7,19]
 * <p>
 * 输入：arr1 = [28,6,22,8,44,17], arr2 = [22,28,8,6]
 * 输出：[22,28,8,6,17,44]
 */
public class RelativeSortArraySolution {

    /**
     * 计数排序是一个非基于比较的排序算法,优势在于在对一定范围内的整数排序时，它的复杂度为Ο(n+k)（其中k是整数的范围），快于任何比较排序算法
     *
     * 算法过程：
     * 根据待排序集合中最大元素和最小元素的差值范围，申请额外空间；
     * 遍历待排序集合，将每一个元素出现的次数记录到元素值对应的额外空间内；
     * 对额外空间内数据进行计算，得出每一个元素的正确位置；
     * 将待排序集合每一个元素移动到计算得出的正确位置上
     */

    /**
     * 本题中元素的范围为[0,1000]，这个范围不是很大, 核心思路：计数排序
     * <p>
     * 我们使用一个长度为1001（下标从0 到 1000）的数组frequency，记录每一个元素在数组arr1中出现的次数。
     * 随后我们遍历数组arr2，当遍历到元素x 时，我们将frequency[x] 个x 加入答案中，并将frequency[x] 清零。当遍历结束后，所有在arr2。
     * 此时还剩下没有在arr2中出现过的元素，因此我们还需要对整个数组frequency 进行一次遍历。当遍历到元素x 时，如果frequency[x] 不为0，我们就将frequency[x] 个x 加入答案中。
     * <p>
     * 优化项：
     * 可以对空间复杂度进行一个小优化。实际上，我们不需要使用长度为1001 的数组，而是可以找出数组arr1中的最大值upper，使用长度为upper+1 的数组即可。
     * <p>
     * 时间复杂度：O(m+n+upper)，其中m 和n 分别是数组arr1  和arr2  的长度，
     * upper 是数组 arr1 中的最大值，在本题中upper 不会超过1000。
     * 遍历数组 arr2 的时间复杂度为 O(n)，遍历数组 frequency 的时间复杂度为 O(upper)，
     * 而在遍历的过程中，我们一共需要 O(m) 的时间得到答案数组；
     * <p>
     * 空间复杂度：O(upper)
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int upper = 0;
        for (int x : arr1) {
            upper = Math.max(upper, x);
        }

        int[] frequency = new int[upper + 1];
        for (int x : arr1) {
            ++frequency[x];
        }

        int[] result = new int[arr1.length];
        int index = 0;
        for (int x : arr2) {
            for (int i = 0; i < frequency[x]; ++i) {
                result[index++] = x;
            }

            frequency[x] = 0;
        }

        for (int x = 0; x <= upper; x++) {
            for (int i = 0; i < frequency[x]; i++) {
                result[index++] = x;
            }
        }

        return result;
    }

    /**
     * 计数排序 另一种形式
     * <p>
     * 1、定义一个长度为 1001 的数组 hash，用于模拟哈希表。
     * <p>
     * 2、将 arr1 中的所有元素存放在 hash 数组中，其中的键为 arr1 中的元素，值为 arr1 中的元素出现的次数；
     * <p>
     * 3、定义索引 i，初始化为 0，用于重置数组 arr1 中的元素值。
     * <p>
     * 4、遍历数组 arr2，只要 arr2 中的元素在数组 hash 中存在，则将其赋值给 arr1，并对该元素在 hash 中出现的频次减一；
     * <p>
     * 5、针对不是数组 arr2 中的元素，遍历整个数组 hash，只要其元素出现的次数在一次及以上，将其赋值给 arr1，并将该元素在 hash 中出现的频次减一。
     * <p>
     * 时间复杂度：O(m + n)，其中 m 和 n 分别为数组的长度，需要遍历一遍两个数组。
     * 空间复杂度：O(1)，额外开辟常数级别的存储空间
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public int[] relativeSortArray_1(int[] arr1, int[] arr2) {
        int i = 0;
        int[] hash = new int[1001];
        for (int n : arr1) {
            hash[n]++;
        }

        for (int n : arr2) {
            while (hash[n] > 0) {
                arr1[i++] = n;
                hash[n]--;
            }
        }

        for (int n = 0; n < hash.length; n++) {
            while (hash[n] > 0) {
                arr1[i++] = n;
                hash[n]--;
            }
        }

        return arr1;
    }
}
