package dev.atanu.ds.java.dp.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 *
 *       buy
 * rest --------> hold
 *   ^             |
 *   |             | sell
 *   |             v
 *   <----------- sold
 *
 * @author Atanu Bhowmick
 */
public class StockBuySell {

    public static void main(String[] args) {
        StockBuySell solution = new StockBuySell();
        int[] prices = new int[] {1, 4, 3, 8, 4, 9};
        int maxProfit = solution.maxProfitWithTransactionFee(prices, 1);
        System.out.println("Max Profit : " + maxProfit);
    }

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


    /**
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
     *
     * @param k
     * @param prices
     * @return Max Profit
     */
    public int maxProfitIV(int k, int[] prices) {
        int n = prices.length;
        if(n == 0) {
            return 0;
        }

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


    /**
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/solutions/108870/most-consistent-ways-of-dealing-with-the-dits/
     *
     * @param prices
     * @param fee
     * @return Max Profit
     */
    public int maxProfitWithTransactionFee(int[] prices, int fee) {
        long currentNetProfit = 0, grossProfit = Integer.MIN_VALUE;

        for (int price : prices) {
            long prevNetProfit = currentNetProfit;
            currentNetProfit = Math.max(currentNetProfit, grossProfit + price - fee);
            grossProfit = Math.max(grossProfit, prevNetProfit - price);
        }

        return (int) currentNetProfit;
    }


    /**
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
     *
     * Has 3 states as follows
     *
     *         buy
     *  rest --------> hold
     *    ^             |
     *    |             | sell
     *    |             v
     *    <----------- sold
     *
     * @param prices
     * @return max profit
     */
    public int maxProfitWithCoolDown(int[] prices) {
        int buyHold = Integer.MIN_VALUE;
        int sold = 0;
        int rest = 0; // cooldown

        for (int i = 0; i < prices.length; i++) {
            int prevSold = sold;
            sold = buyHold + prices[i];
            buyHold = Math.max(buyHold, rest - prices[i]);
            rest = Math.max(rest, prevSold);
        }

        return Math.max(sold, rest);
    }


    /**
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
     *
     * @param prices
     * @return
     */
    public int maxProfitWithCoolDown1(int[] prices) {
        int buyHold = -prices[0];
        int sold = 0;
        int rest = 0; // cooldown

        for (int i = 1; i < prices.length; i++) {
            int prevSold = sold;
            sold = buyHold + prices[i];
            buyHold = Math.max(buyHold, rest - prices[i]);
            rest = Math.max(rest, prevSold);
        }

        return Math.max(sold, rest);
    }


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
