package com.martix.x.pub.code.dymaic;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by Andrew-Geng on 12:21 上午 2021/3/30
 * <p>
 * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
 * <p>
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * <p>
 * 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * <p>
 * 注意：不允许旋转信封。
 * <p>
 * 输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出：3
 * 解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 * <p>
 * lc 354 hard
 */
public class MaxEnvelopesSolution {

    /**
     * 运用动态规划的方式
     *
     * @param envelopes
     * @return
     */
    public int maxEnvelopes(int[][] envelopes) {
        List<int[]> list = Arrays.stream(envelopes)
                .sorted((o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0])
                .collect(Collectors.toList());  //对二维数组整体 进行排序，即 按宽度先进行升序，当宽度一致的时候，对高度进行降序；当然也可以反过来对高度进行

        int[] height = new int[envelopes.length];//对高度进行最长递增子序列
        for (int i = 0; i < envelopes.length; i++) {
            height[i] = list.get(i)[1];
        }


        return this.LIS(height);
    }

    private int LIS(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int[] dp = new int[arr.length];
        Arrays.fill(dp, 1);

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            result = Math.max(result, dp[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] envelopes = new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        int result = new MaxEnvelopesSolution().maxEnvelopes(envelopes);

        System.out.println(result);
    }
}
