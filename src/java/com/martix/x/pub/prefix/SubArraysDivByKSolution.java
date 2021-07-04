package com.martix.x.pub.prefix;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrew-Geng on 1:46 上午 2021/5/19
 * 和可被 K 整除的子数组 lc 974
 * <p>
 * 给定一个整数数组 A，返回其中元素之和可被 K整除的（连续、非空）子数组的数目。
 * <p>
 * 示例：
 * <p>
 * 输入：A = [4,5,0,-2,-3,1], K = 5
 * 输出：7
 * 解释：
 * 有 7 个子数组满足其元素之和可被 K = 5 整除：
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 30000
 * -10000 <= A[i] <= 10000
 * 2 <= K <= 10000
 */
public class SubArraysDivByKSolution {

    public static void main(String[] args) {
        int[] num = new int[]{-1, 2, 9};

        int result = new SubArraysDivByKSolution().subarraysDivByK(num, 2);


    }

    /**
     * 时间复杂度O(N) 空间复杂度O(k)
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(); //key:前缀和模K的值为键，value：出现次数
        map.put(0, 1);

        int result = 0, sum_i = 0;
        for (int i = 0; i < nums.length; i++) {
            sum_i = sum_i + nums[i];

            /*
               数组中有可能出现负数，我们需要将其加 K 从而使其 %K 之后的值为正数
               纠正 Java 除余操作会导致负数的情况
             */
            int mod = (sum_i % k + k) % k;

            result = result + map.getOrDefault(mod, 0);
            map.put(mod, map.getOrDefault(mod, 0) + 1);
        }

        return result;
    }
}
