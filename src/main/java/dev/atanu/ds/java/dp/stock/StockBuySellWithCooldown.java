package dev.atanu.ds.java.dp.stock;

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
public class StockBuySellWithCooldown {

    public static void main(String[] args) {
        StockBuySellWithCooldown solution = new StockBuySellWithCooldown();
        int[] prices = new int[] {1, 4, 3, 8, 4, 9};
        int maxProfit = solution.maxProfitWithCoolDown(prices);
        System.out.println("Max Profit : " + maxProfit);
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

}
