package com.martix.x.pub.dymaic;

/**
 * Created by Andrew-Geng on 12:42 上午 2021/5/26
 * <p>
 * 最佳买卖股票时机含冷冻期 lc 309
 * <p>
 * 给定一个整数数组，其中第i个元素代表了第i天的股票价格 。
 * <p>
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * <p>
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 示例:
 * <p>
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 */
public class StockSailFrozenSolution {

    /**
     * 一种常用的方法是将「买入」和「卖出」分开进行考虑：「买入」为负收益，而「卖出」为正收益。在初入股市时，你只有「买入」的权利，
     * 只能获得负收益。而当你「买入」之后，你就有了「卖出」的权利，可以获得正收益。
     * 显然，我们需要尽可能地降低负收益而提高正收益，因此我们的目标总是将收益值最大化。
     * 因此，我们可以使用动态规划的方法，维护在股市中每一天结束后可以获得的「累计最大收益
     * <p>
     * 我们目前持有一支股票，对应的「累计最大收益」记为
     * f[i][0]；
     * 我们目前不持有任何股票，并且处于冷冻期中，对应的「累计最大收益」记为
     * f[i][1]；
     * 我们目前不持有任何股票，并且不处于冷冻期中，对应的「累计最大收益」记为
     * f[i][2]
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int n = prices.length;
        int f0 = -prices[0];
        int f1 = 0;
        int f2 = 0;

        for (int i = 1; i < n; ++i) {
            int newF0 = Math.max(f0, f2 - prices[i]);
            int newF1 = f0 + prices[i];
            int newF2 = Math.max(f1, f2);

            f0 = newF0;
            f1 = newF1;
            f2 = newF2;
        }

        return Math.max(f1, f2);
    }

    /**
     * 空间资源 O(N),相对于第一种空间开销较大
     * @param prices
     * @return
     */
    public int maxProfit_1(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int n = prices.length;
        // f[i][0]: 手上持有股票的最大收益
        // f[i][1]: 手上不持有股票，并且处于冷冻期中的累计最大收益
        // f[i][2]: 手上不持有股票，并且不在冷冻期中的累计最大收益
        int[][] f = new int[n][3];
        f[0][0] = -prices[0];
        for (int i = 1; i < n; ++i) {
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][2] - prices[i]);
            f[i][1] = f[i - 1][0] + prices[i];
            f[i][2] = Math.max(f[i - 1][1], f[i - 1][2]);
        }
        return Math.max(f[n - 1][1], f[n - 1][2]);
    }
}
