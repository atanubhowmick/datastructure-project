package dev.atanu.ds.java.dp.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/decode-ways/
 *
 * @author Atanu Bhowmick
 */
public class DecodeWays {

    public static void main(String[] args) {
        DecodeWays decodeWays = new DecodeWays();
        System.out.println(decodeWays.numDecodingsRecursion("226"));
        System.out.println(decodeWays.numDecodingsMemoization("226"));
        System.out.println(decodeWays.numDecodingsBottomUp("226"));
    }

    /**
     * @param s
     * @return
     */
    public int numDecodingsRecursion(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        return numDecodingsRecursion(0, s);
    }

    private int numDecodingsRecursion(int i, String s) {
        int n = s.length();
        if (i == n) {
            return 1; // Reaching the length will have length 1
        }

        if (s.charAt(i) == '0') {
            return 0; // sub string starting with 0 is not a valid encoding
        }

        int res = numDecodingsRecursion(i + 1, s);
        if (i < n - 1 && (s.charAt(i) == '1' || (s.charAt(i) == '2' && s.charAt(i + 1) < '7'))) {
            res += numDecodingsRecursion(i + 2, s);
        }
        return res;
    }

    public int numDecodingsMemoization(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int[] dp = new int[s.length() + 1];
        Arrays.fill(dp, -1);
        return numDecodingsMemoization(0, s, dp);
    }

    private int numDecodingsMemoization(int i, String s, int[] dp) {
        int n = s.length();
        if (i == n) {
            return 1; // Reaching the length will have length 1
        }

        if (s.charAt(i) == '0') {
            return 0; // sub string starting with 0 is not a valid encoding
        }

        if (dp[i] != -1) {
            return dp[i];
        }

        int res = numDecodingsMemoization(i + 1, s, dp);
        if (i < n - 1 && (s.charAt(i) == '1' || (s.charAt(i) == '2' && s.charAt(i + 1) < '7'))) {
            res += numDecodingsMemoization(i + 2, s, dp);
        }
        dp[i] = res;
        return dp[i];
    }

    public int numDecodingsBottomUp(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                continue;
            }
            int res = dp[i + 1];
            if (i < n - 1 && (s.charAt(i) == '1' || (s.charAt(i) == '2' && s.charAt(i + 1) < '7'))) {
                res += dp[i + 2];
            }
            dp[i] = res;
        }
        return dp[0];
    }

    public int numDecodingsBottomUpConstantSpace(String s) {
        int dp1 = 1, dp2 = 0;
        int n = s.length();
        for(int i = n - 1; i >= 0; i--) {
            int dp = s.charAt(i)=='0' ? 0 : dp1;
            if(i<n-1 && (s.charAt(i) == '1' || s.charAt(i) == '2' && s.charAt(i+1) < '7'))
                dp += dp2;
            dp2 = dp1;
            dp1 = dp;
        }
        return dp1;
    }
}
