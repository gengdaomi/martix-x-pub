package com.martix.x.pub.code.binary;

/**
 * Created by Andrew-Geng on 23:57 2022/9/15
 * 切割木头
 * 给定长度为n的数组，每个元素代表一个木头的长度，木头可以任意截断，从这堆木头中截出至少k个相同长度为m的木块。已知k，求max(m)。
 * <p>
 * 输入两行，第一行n, k，第二行为数组序列。输出最大值
 * <p>
 * 输入
 * 5 5
 * 4 7 2 10 5
 * 输出
 * 4
 * 解释：如下图，最多可以把它分成5段长度为4的木头
 * <p>
 * 4   4 3   2   4 4 2   4 1
 */
public class CutWoodSolution {

    public static void main(String[] args) {
        int s = new CutWoodSolution().cutWood(new int[]{4,7,2,10,5},5);
        System.out.println(s);
    }

    /**
     * 二分
     * 在[1,max]寻找最大长度是顺序遍历，所以可以二分来检索结果；果能截出来k个长度为x的木块，说明答案肯定 >= x，则接下来只需在[x,max]中找m最大满足条件的长度。反之则说明答案 < x，则在[1,x-1]中寻找结果。
     * 这样我们每次可以舍弃1/2的情况，因此使用二分的时间复杂度是O(n * log Len)。
     * <p>
     * 例如: 如果长度为 x 截不出来 k个木头, 说明比 x 大的长度也不行, 只能从小于 x 的长度截取木头
     * 实际编写代码时, 相当于找到最后一个 cnt >= k 的长度 len
     * <p>
     * 1.求的是 最后一个 cnt >= k 的长度, 所以每次要向右移动, 于是向上取整, 有 mid = left + right + 1 >> 1,
     *   有+1;
     * 2.考虑两个元素的情况, 如果 mid 落在右边那一个元素, 这时 cnt < k, 所以 right = mid - 1;
     *   若 right= mid , 则下一次 mid 仍然不变, 无法跳出循环
     * <p>
     * <p>
     * 时间复杂度O(n*logLen)
     *
     * @param nums
     * @param k
     * @return
     */
    public int cutWood(int[] nums, int k) {
        int maxLength = 0;// 找到最大长度的木头

        for (int i = 0; i < nums.length; i++) {
            maxLength = Math.max(maxLength, nums[i]);
        }
        int left = 1, right = maxLength;

        while (left < right) {
            int mid = left + right + 1 >> 1;

            int cnt = 0;// 以 mid 长度截取木头, 查找能截取的最多段
            for (int i = 0; i < nums.length; i++) {
                cnt += nums[i] / mid;
            }

            if (cnt >= k) {// 如果能截出 k段以上, 则更新结果
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    /**
     * 第一种:(一般用于取 第一个 符合题意的值)
     *
     * int mid = left + right + 1 >> 1;
     * left = mid;
     * right = mid - 1;

     * 第二种:(一般用于 取 最后一个符合题意的值)
     *
     * int mid = left + right + 1 >> 1;
     * left = mid;
     * right = mid - 1;
     */


    /**
     * 暴力解法 不推荐
     *
     * 遍历 1 到 木棍最长的长度, 每次遍历的长度作为 m,
     * 以 m 为长度截取木头, 若能截取出 k个长度为m的木块，
     * 则更新最大值，最后输出最大值即可
     *
     * 时间复杂度O(n * len), len为木头中最大的长度
     * @param nums
     * @param k
     * @return
     */
    public int cutWood_1(int[] nums, int k){
        int maxLen = 0;// 找到最大长度的木头
        for (int i = 0; i < nums.length; i++) {
            maxLen = Math.max(maxLen, nums[i]);
        }

        int result = 0;
        int curLength = 1;
        while (curLength <= maxLen) {
            int cnt = 0;
            for (int i = 0; i < nums.length; i++) {// 以 curLen 截取木头, 查找能截取的最多段
                cnt += nums[i] / curLength;
            }

            if (cnt >= k) {// 如果能截出 k段以上, 则更新结果
                result = Math.max(result, curLength);
            }
            curLength++;
        }
        return result;
    }
}
