package dev.atanu.ds.java.dp.one.dimension;


/**
 * https://leetcode.com/problems/integer-replacement/
 *
 */
public class IntegerReplacement {

    public int integerReplacement(int n) {
        int count = 0;
        while(n > 1) {
            if(n == 2) {
                count += 1;
                return count;
            }

            if(n == 3) {
                count += 2;
                return count;
            }

            if (n == Integer.MAX_VALUE) {
                return 32;
            }

            if(n % 2 == 0) {
                n = n / 2;
                count += 1;
            } else if(((n - 1) / 2) % 2 == 0) {
                n = n - 1;
                n = n / 2;
                count += 2;
            } else {
                n = n + 1;
                n = n / 2;
                count += 2;
            }
        }
        return count;
    }
}
