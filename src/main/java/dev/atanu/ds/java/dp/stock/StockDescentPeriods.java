package dev.atanu.ds.java.dp.stock;

public class StockDescentPeriods {

    /**
     * https://leetcode.com/problems/number-of-smooth-descent-periods-of-a-stock/
     *
     * @param prices
     * @return all decent periods
     */
    public long getDescentPeriods(int[] prices) {
        long res = 1, cnt = 1;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i - 1] == prices[i] + 1) {
                ++cnt;
            } else {
                cnt = 1;
            }
            res += cnt;
        }
        return res;
    }
}
