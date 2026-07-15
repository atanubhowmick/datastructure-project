package dev.atanu.ds.java.dp.stock;

public class StickBuySellWithTransactionFee {

    public static void main(String[] args) {
        StickBuySellWithTransactionFee solution = new StickBuySellWithTransactionFee();
        int[] prices = new int[] {1, 4, 3, 8, 4, 9};
        int maxProfit = solution.maxProfitWithTransactionFee(prices, 1);
        System.out.println("Max Profit : " + maxProfit);
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
}
