package com.martix.x.pub.code.number;

/**
 * Created by Andrew-Geng on 09:01 2022/9/10
 * 寻找两个正序数组的中位数 lc 4
 *
 *
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组nums1 和nums2。请你找出并返回这两个正序数组的 中位数 。
 * 算法的时间复杂度应该为 O(log (m+n))
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 */
public class FindMedianSortArraySolution {

    public static void main(String[] args) {
        int[] a = new int[]{1, 2};
        int[] b = new int[]{3, 4,6};
        double s = new FindMedianSortArraySolution().findMedianSortedArrays(a, b);
        System.out.println(s);
    }

    /**
     * 中位数 及代表一个数值，其可将数值集合划分为相等的上下两部分，所以我们只需要将数组进行切；
     * <p>
     * 比如：
     * 我们有一个长度为 m 的数组，有 0 到 m 总共 m + 1 个位置可以切；
     * 我们把数组 A 和数组 B 分别在 i 和 j 进行切割；将 i 的左边和 j 的左边组合成「左半部分」，将 i 的右边和 j 的右边组合成「右半部分」：
     * 1.当 A 数组和 B 数组的总长度是偶数时，如果我们能够保证:
     * 1.1 左半部分的长度等于右半部分: i + j = m - i  + n - j  , 也就是 j = ( m + n ) / 2 - i
     * 1.2 左半部分最大的值小于等于右半部分最小的值 max ( A [ i - 1 ] , B [ j - 1 ]）） <= min ( A [ i ] , B [ j ]））
     * <p>
     * 那么，中位数就可以表示如下:（左半部分最大值 + 右半部分最小值 ）/ 2。max ( A [ i - 1 ] , B [  j  - 1 ]）+ min ( A [ i ] , B [ j ]）） /  2
     * <p>
     * 2.当 A 数组和 B 数组的总长度是奇数时，如果我们能够保证
     * 2.1 左半部分的长度比右半部分大 1:  i + j = m - i  + n - j  + 1也就是 j = ( m + n + 1) / 2 - i
     * 2.2 左半部分最大的值小于等于右半部分最小的值 max ( A [ i - 1 ] , B [ j - 1 ]）） <= min ( A [ i ] , B [ j ]））
     * <p>
     * 那么，中位数就是左半部分最大值，也就是左半部比右半部分多出的那一个数。max ( A [ i - 1 ] , B [  j - 1 ]）
     * <p>
     * 时间复杂度：我们对较短的数组进行了二分查找，所以时间复杂度O(log（min（m，n））
     * 空间复杂度：只有一些固定的变量，和数组长度无关，所以空间复杂度是 O(1)
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) {
            return findMedianSortedArrays(nums2, nums1); // 保证 m <= n
        }
        int iMin = 0, iMax = m;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = (m + n + 1) / 2 - i;
            if (j != 0 && i != m && nums2[j - 1] > nums1[i]) { // i 需要增大
                iMin = i + 1;
            } else if (i != 0 && j != n && nums1[i - 1] > nums2[j]) { // i 需要减小
                iMax = i - 1;
            } else { // 达到要求，并且将边界条件列出来单独考虑
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = nums2[j - 1];
                } else if (j == 0) {
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                } // 奇数的话不需要考虑右半部分

                int minRight = 0;
                if (i == m) {
                    minRight = nums2[j];
                } else if (j == n) {
                    minRight = nums1[i];
                } else {
                    minRight = Math.min(nums2[j], nums1[i]);
                }

                return (maxLeft + minRight) / 2.0; //如果是偶数的话返回结果
            }
        }
        return 0.0;
    }

    /**
     * 思路：
     * 不需要将两个数组真的合并，我们只需要找到中位数在哪里就可以；
     * <p>
     * 首先是怎么将奇数和偶数的情况合并一下：
     * <p>
     * 用 len 表示合并后数组的长度，
     * 如果是奇数，我们需要知道第 （len+1）/2 个数就可以了，如果遍历的话需要遍历 int(len/2 ) + 1 次。
     * 如果是偶数，我们需要知道第 len/2和 len/2+1 个数，也是需要遍历 len/2+1 次。所以遍历的话，奇数和偶数都是 len/2+1 次。
     * <p>
     * 返回中位数的话，奇数需要最后一次遍历的结果就可以了，偶数需要最后一次和上一次遍历的结果。
     * 所以我们用两个变量 left 和 right，right 保存当前循环的结果，在每次循环前将 right 的值赋给 left。
     * 这样在最后一次循环的时候，left 将得到 right 的值，也就是上一次循环的结果，接下来 right 更新为最后一次的结果。
     * <p>
     * 循环中该怎么写，什么时候 A 数组后移，什么时候 B 数组后移。用 aStart 和 bStart 分别表示当前指向 A 数组和 B 数组的位置。
     * 如果 aStart 还没有到最后并且此时 A 位置的数字小于 B 位置的数组，那么就可以后移了。也就是aStart＜m&&A[aStart]< B[bStart]。
     * 但如果 B 数组此刻已经没有数字了，继续取数字 B[ bStart ]，则会越界，所以判断下 bStart 是否大于数组长度了，这样 || 后边的就不会执行了，也就不会导致错误了，
     * 所以增加为 aStart＜m&&(bStart) >= n||A[aStart]<B[bStart]) 。
     * <p>
     * <p>
     * 时间复杂度：遍历 len/2+1 次，len=m+n，所以时间复杂度依旧是O(m+n)
     * 空间复杂度：申请的常量个变量，所以是O(1)
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays_1(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int len = m + n;
        int left = -1, right = -1;
        int aStart = 0, bStart = 0;

        for (int i = 0; i <= len / 2; i++) {
            left = right;
            if (aStart < m && (bStart >= n || nums1[aStart] < nums2[bStart])) {
                right = nums1[aStart++];
            } else {
                right = nums2[bStart++];
            }
        }

        if ((len & 1) == 0) {
            return (left + right) / 2.0;
        } else {
            return right;
        }
    }

}
