package com.martix.x.pub.code.palindrome;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew-Geng on 20:51 2022/9/16
 * 寻找最近的回文数 lc564 hard
 *
 * 给定一个表示整数的字符串n ，返回与它最近的回文整数（不包括自身）。如果不止一个，返回较小的那个。
 *
 * “最近的”定义为两个整数差的绝对值最小。
 *
 *
 * 示例 1:
 *
 * 输入: n = "123"
 * 输出: "121"
 * 示例 2:
 *
 * 输入: n = "1"
 * 输出: "0"
 * 解释: 0 和 2是最近的回文，但我们返回最小的，也就是 0。
 *
 */
public class NearestPalindromicSolution {

    /**
     * 思路：模拟的方式，主要是找各种边界
     *
     * 构造回文整数有一个直观的方法：用原数的较高位的数字替换其对应的较低位。例如，对于数12345，我们可以用1 替换 5，用2 替换4，这样原数即变为回文整数12321。
     *
     * 在这种方案中，我们修改的都是较低位的数字，因此构造出的新的整数与原数较为接近。大部分情况下，这种方案是最优解，但还有部分情况我们没有考虑到。
     *
     * 1.构造的回文整数大于原数时，我们可以减小该回文整数的中间部分来缩小回文整数和原数的差距。例如，对于数99321，我们将构造出回文整数99399，实际上 99299 更接近原数。
     *   当我们减小构造的回文整数时，可能导致回文整数的位数变化。例如，对于数100，我们将构造出回文整数101，减小其中间部分将导致位数减少。得到的回文整数形如999…999
     *
     * 2.构造的回文整数小于原数时，我们可以增大该回文整数的中间部分来缩小回文整数和原数的差距。例如，对于数12399，我们将构造出回文整数12321，实际上 12421 更接近原数。
     *   当我们增大构造的回文整数时，可能导致回文整数的位数变化。例如，对于数998，我们将构造出回文整数999，增大其中间部分将导致位数增加。得到的回文整数形如
     *
     * 3.构造的回文整数等于原数时，由于题目约定，我们需要排除该回文整数，在其他的可能情况中寻找最近的回文整数。
     * 考虑到以上所有的可能，我们可以给出最终的方案：分别计算出以下每一种可能的方案对应的回文整数，在其中找到与原数最近且不等于原数的数即为答案。
     *
     * 用原数的前半部分替换后半部分得到的回文整数。
     * 用原数的前半部分加一后的结果替换后半部分得到的回文整数。
     * 用原数的前半部分减一后的结果替换后半部分得到的回文整数。
     * 为防止位数变化导致构造的回文整数错误，因此直接构造999…999 和100…001 作为备选答案。
     *
     * 时间 空间复杂度：(logn)；；n 为给定整数的大小
     * @param n
     * @return
     */
    public String nearestPalindromic(String n) {
        long selfNumber = Long.parseLong(n), ans = -1;
        List<Long> candidates = getCandidates(n);

        for (long candidate : candidates) {
            if (candidate != selfNumber) {
                if (ans == -1 ||
                        Math.abs(candidate - selfNumber) < Math.abs(ans - selfNumber) ||
                        Math.abs(candidate - selfNumber) == Math.abs(ans - selfNumber) && candidate < ans) {
                    ans = candidate;
                }
            }
        }
        return Long.toString(ans);
    }

    public List<Long> getCandidates(String n) {
        int len = n.length();
        List<Long> candidates = new ArrayList<>();
        candidates.add((long) Math.pow(10, len - 1) - 1);
        candidates.add((long) Math.pow(10, len) + 1);


        long selfPrefix = Long.parseLong(n.substring(0, (len + 1) / 2));
        for (long i = selfPrefix - 1; i <= selfPrefix + 1; i++) {
            StringBuffer sb = new StringBuffer();
            String prefix = String.valueOf(i);
            sb.append(prefix);
            StringBuffer suffix = new StringBuffer(prefix).reverse();
            sb.append(suffix.substring(len & 1));
            String candidate = sb.toString();

            try {
                candidates.add(Long.parseLong(candidate));
            } catch (NumberFormatException ex) {
                continue;
            }
        }

        return candidates;
    }

}
