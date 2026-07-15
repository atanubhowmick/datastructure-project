package dev.atanu.ds.java.dp.stock;

import java.util.Arrays;

public class StickBuySell3 {

    /**
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/solutions/135704/detail-explanation-of-dp-solution-by-men-de64/
     *
     * @param prices
     * @return
     */
    public int maxProfitIII(int[] prices) {
        int n = prices.length;
        if(n == 0) {
            return 0;
        }

        int k = 2;
        int[] dp = new int[k + 1];
        int[] min = new int[k + 1];

        Arrays.fill(min,prices[0]);

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                min[j]= Math.min(min[j], prices[i] - dp[j-1]);
                dp[j] = Math.max(dp[j], prices[i] - min[j]);
            }
        }
        return dp[k];
    }
}
