package com.martix.x.pub.sum;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrew-Geng on 10:16 下午 2021/4/15
 * 四数相加 II lc 454
 * <p>
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
 * <p>
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。
 * <p>
 * 输入:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 * <p>
 * 输出:
 * 2
 * <p>
 * 解释:
 * 两个元组如下:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 */
public class FourSumCountSolution {

    public static void main(String[] args) {
        int[] A = new int[]{1,2};
        int[] B = new int[]{-2,-1};
        int[] C = new int[]{-1,2};
        int[] D = new int[]{0,2};

        System.out.println(new FourSumCountSolution().fourSumCount(A,B,C,D));
    }

    /**
     * 对于
     * A 和
     * <p>
     * B，我们使用二重循环对它们进行遍历，得到所有
     * <p>
     * A[i]+B[j] 的值并存入哈希映射中。对于哈希映射中的每个键值对，每个键表示一种
     * <p>
     * A[i]+B[j]，对应的值为
     * <p>
     * A[i]+B[j] 出现的次数。
     * 对于
     * <p>
     * C 和
     * <p>
     * D，我们同样使用二重循环对它们进行遍历。当遍历到
     * <p>
     * C[k]+D[l] 时，如果
     * <p>
     * −(C[k]+D[l]) 出现在哈希映射中，那么将
     * −(C[k]+D[l]) 对应的值累加进答案中。
     *
     * @param nums1
     * @param nums2
     * @param nums3
     * @param nums4
     * @return
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map = new HashMap<>(); //map的key为 num1和num2 所有组合的相加值，value是出现的次数

        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int sumNum1AndNum2 = nums1[i] + nums2[j];

                map.put(sumNum1AndNum2, map.getOrDefault(sumNum1AndNum2, 0) + 1);
            }
        }

        int result = 0;
        /*
         * 因为num1[i]+num2[j]+num3[k]+num4[d]=0, 所以  num1[i]+num2[j]=-(num3[k]+num4[d])
         */
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int sumNum3AndNum4 = nums3[i] + nums4[j];

                result += map.getOrDefault(-sumNum3AndNum4, 0);

            }
        }

        return result;
    }
}
