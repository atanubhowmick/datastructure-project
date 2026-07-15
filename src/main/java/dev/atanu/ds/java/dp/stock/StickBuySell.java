package dev.atanu.ds.java.dp.stock;

public class StickBuySell {

    /**
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
     *
     * @param prices
     * @return Max Profit
     */
    public int maxProfitI(int[] prices) {
        int min = prices[0], max = 0, maxProfit = 0;
        for(int i = 0; i < prices.length; i++) {
            if(min > prices[i]) {
                min = prices[i];
                max = min;
            } else if(max < prices[i]) {
                max = prices[i];
            }
            maxProfit = Math.max(maxProfit, max - min);
        }
        return maxProfit;
    }
}
