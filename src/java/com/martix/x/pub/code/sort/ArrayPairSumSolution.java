package com.martix.x.pub.code.sort;

import java.util.Arrays;

/**
 * Created by Andrew-Geng on 21:39 2022/9/12
 * 数组拆分 lc 561
 * <p>
 * 给定长度为2n的整数数组 nums ，你的任务是将这些数分成n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从 1 到n 的 min(ai, bi) 总和最大。
 * <p>
 * 返回该 最大总和 。
 * <p>
 * 输入：nums = [1,4,3,2]
 * 输出：4
 * 解释：所有可能的分法（忽略元素顺序）为：
 * 1. (1, 4), (2, 3) -> min(1, 4) + min(2, 3) = 1 + 2 = 3
 * 2. (1, 3), (2, 4) -> min(1, 3) + min(2, 4) = 1 + 2 = 3
 * 3. (1, 2), (3, 4) -> min(1, 2) + min(3, 4) = 1 + 3 = 4
 * 所以最大总和为 4
 * <p>
 * 输入：nums = [6,2,6,5,1,2]
 * 输出：9
 * 解释：最优的分法为 (2, 1), (2, 5), (6, 6). min(2, 1) + min(2, 5) + min(6, 6) = 1 + 2 + 6 = 9
 */
public class ArrayPairSumSolution {

    /**
     * 核心思路：排序
     *
     * 每一组二元组(ai,bi)满足ai<=bi,(若不满足，交换即可)
     * 这样我们要求的总和 min(a0,b0)+min(a1,b1)+...+min(ai,bi)+...+min(an-1,bn-1)+min(an,bn)，即等于ai的所有总和，a0+a1+...+ai+..+an
     * 接下来我们将所有(ai,bi)按照升序排序，使a0<=a1<=...<=an
     *
     *
     *
     *
     * 时间复杂度：O(nlogn)，即为对数组  nums 进行排序的时间复杂度。
     * 空间复杂度：O(logn)，即为排序需要使用的栈空间
     * @param nums
     * @return
     */
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int result = 0;
        for (int i = 0; i < nums.length; i += 2) {
            result += nums[i];
        }
        return result;

    }
}
