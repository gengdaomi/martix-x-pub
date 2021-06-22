package com.martix.x.dymaic;

/**
 * Created by Andrew-Geng on 12:52 上午 2021/5/26
 * 买卖股票的最佳时机含手续费 lc 714
 * <p>
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
 * <p>
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * <p>
 * 返回获得利润的最大值。
 * <p>
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 * <p>
 * 示例 1:
 * <p>
 * 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出: 8
 * 解释: 能够达到的最大利润:
 * 在此处买入 prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * 注意:
 * <p>
 * 0 < prices.length <= 50000.
 * 0 < prices[i] < 50000.
 * 0 <= fee < 50000.
 */
public class StockSailFeeSolution {

    /**
     * 贪心算法
     * 看成当前手上拥有一支买入价格为price[i]的股票，将buyPrice更新为price[i],这样一来，
     * 如果下一天股票价格继续上升，我们会获得prices[i+1]−prices[i]的收益，加上这一天prices[i]−buy,
     * 的收益，恰好就等于在这一天不进行任何操作，而在下一天卖出股票的收益
     */
    public int maxProfit(int[] prices, int fee) {
        int profit = 0;
        int buyPrice = prices[0] + fee;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] + fee < buyPrice) {
                buyPrice = prices[i] + fee;
            } else if (prices[i] > buyPrice) {
                profit += prices[i] - buyPrice;
                buyPrice = prices[i];
            }
        }

        return profit;
    }

    /**
     * 动态规划
     *
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit_1(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];

        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < n; ++i) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }
}
