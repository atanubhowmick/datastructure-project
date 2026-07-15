package dev.atanu.ds.java.dp.stock;

public class StickBuySell2 {

    /**
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
     *
     * @param prices
     * @return Max Profit
     */
    public int maxProfitII(int[] prices) {
        int profit = 0;
        for(int i = 0; i < prices.length - 1; i++) {
            profit += Math.max(0, prices[i+1] - prices[i]);
        }
        return profit;
    }
}
