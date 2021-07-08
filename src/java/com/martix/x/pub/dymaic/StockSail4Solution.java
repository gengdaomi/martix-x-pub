package com.martix.x.pub.dymaic;

/**
 * Created by Andrew-Geng on 12:38 上午 2021/5/26
 * <p>
 * 买卖股票的最佳时机 IV lc 188
 * <p>
 * 给定一个整数数组prices ，它的第 i 个元素prices[i] 是一支给定的股票在第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * <p>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：k = 2, prices = [2,4,1]
 * 输出：2
 * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 * 示例 2：
 * <p>
 * 输入：k = 2, prices = [3,2,6,5,0,3]
 * 输出：7
 * 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 * 随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= k <= 100
 * 0 <= prices.length <= 1000
 * 0 <= prices[i] <= 1000
 */
public class StockSail4Solution {

    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0 || k <= 0) {
            return 0;
        }
        //表格的最大行数
        int n = prices.length;
        //表格的最大列数
        int m = k * 2 + 1;
        //使用一维数组记录数据
        int[] resultTable = new int[m];
        //填充初始状态
        for (int i = 1; i < m; i += 2) {
            resultTable[i] = -prices[0];
        }
        //自底向上，填充数据
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if ((j & 1) == 1) {
                    resultTable[j] = Math.max(resultTable[j], resultTable[j - 1] - prices[i]);
                } else {
                    resultTable[j] = Math.max(resultTable[j], resultTable[j - 1] + prices[i]);
                }
            }
        }
        //返回最终结果
        return resultTable[m - 1];
    }
}
