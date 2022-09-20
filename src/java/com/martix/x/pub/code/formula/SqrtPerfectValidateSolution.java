package com.martix.x.pub.code.formula;

/**
 * Created by Andrew-Geng on 10:51 2022/9/12
 * 有效的完全平方数 lc 367
 * <p>
 * 给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
 * <p>
 * 进阶：不要 使用任何内置的库函数，如 sqrt 。
 * <p>
 * 输入：num = 16
 * 输出：true
 * <p>
 * 输入：num = 14
 * 输出：false
 */
public class SqrtPerfectValidateSolution {

    /**
     * 二分查找
     * <p>
     * 考虑使用二分查找来优化方法二中的搜索过程；因为num为正整数，若正整数x满足x*x=num, 则x一定满足1<=x<=num;
     * 于是我们可以将1和num作为二分查找搜索区间的初始边界；
     * <p>
     * 注意点：
     * <p>
     * 因为我们在移动左侧边界 left 和右侧边界 right 时，新的搜索区间都不会包含被检查的下标mid，所以搜索区间的边界始终是我们没有检查过的。
     * 因此，当left=right 时，我们仍需要检查mid=(left+right)/2
     *
     * 时间复杂度：O(logn)，其中 n 为正整数num 的最大值。
     * 空间复杂度：O(1)。
     *
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
        int left = 0, right = num;

        while (left <= right) {
            int mid = (right - left) / 2 + left;
            long square = (long) mid * mid;

            if (square < num) {
                left = mid + 1;
            } else if (square > num) {
                right = mid - 1;
            } else {
                return true;
            }
        }

        return false;
    }

    /**
     * 根据完全平方数的性质，我们只需要直接判断num的平方根x是否是整数即可；若sqrt(num)为正整数，则x^2=sqrt(num)^2=num
     *
     * @param num
     * @return
     */
    public boolean isPerfectSquare_1(int num) {
        int x = (int) Math.sqrt(num);
        return x * x == num;
    }
}
