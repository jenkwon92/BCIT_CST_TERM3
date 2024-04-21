import java.util.concurrent.TimeUnit;
import java.math.BigInteger;

/**
 * This class contains methods for calculating and analyzing various values
 * related to the Smith-Waterman algorithm.
 * 
 * Author: Yongeun Kwon (A01263922)
 */
public class Lab6 {

    /**
     * Recursively calculates the Smith-Waterman value for given parameters.
     *
     * @param m The first parameter.
     * @param n The second parameter.
     * @return The Smith-Waterman value for the given parameters.
     */
    public long SW_Recursive(int m, int n) {
        if (m == 0 || n == 0) {
            return 1;
        }
        return SW_Recursive(m - 1, n) + SW_Recursive(m, n - 1);
    }

    /**
     * Runs the recursive Smith-Waterman algorithm for a range of values and prints
     * the results.
     *
     * @param lowerBound The lower bound of the range.
     * @param upperBound The upper bound of the range.
     */
    public void RunRecursive(int lowerBound, int upperBound) {
        for (int i = lowerBound; i <= upperBound; i++) {
            long startTime = System.nanoTime();
            long result = SW_Recursive(i, i);
            long endTime = System.nanoTime();
            long duration = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
            System.out.println("SW_Recursive(" + i + "," + i + ") = " + result + ", time is " + duration + " ms");
        }
    }

    /**
     * Calculates the Smith-Waterman value using dynamic programming.
     *
     * @param m The first parameter.
     * @param n The second parameter.
     * @return The Smith-Waterman value for the given parameters.
     */
    public long SW_DynamicProg(int m, int n) {
        long[][] dp = new long[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m][n];
    }

    /**
     * Runs the dynamic programming Smith-Waterman algorithm for a range of values
     * and prints the results.
     *
     * @param lowerBound The lower bound of the range.
     * @param upperBound The upper bound of the range.
     */
    public void RunDynamicProg(int lowerBound, int upperBound) {
        for (int i = lowerBound; i <= upperBound; i++) {
            long startTime = System.nanoTime();
            long result = SW_DynamicProg(i, i);
            long endTime = System.nanoTime();
            long duration = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
            System.out
                    .println("SW_DynamicProg(" + i + "," + i + ") = " + result + ", time is " + duration + " ms");
        }
    }

    /**
     * Virtual donut 1 method
     * 
     * Calculates the Smith-Waterman value using a combinatorial approach.
     *
     * @param m The first parameter.
     * @param n The second parameter.
     * @return The Smith-Waterman value for the given parameters.
     */
    public BigInteger calculateSW(int m, int n) {
        BigInteger numerator = factorial(m + n);
        BigInteger denominator = factorial(m).multiply(factorial(n));
        return numerator.divide(denominator);
    }

    /**
     * 
     * Calculates the factorial of a given number.
     *
     * @param n The number for which to calculate the factorial.
     * @return The factorial of the given number.
     */
    public BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    /**
     * Virtual donut 2 method
     * 
     * Calculates the sum of the factorials of integers up to a specified maximum
     * value.
     *
     * @param maxValue The maximum value.
     * @return The sum of the factorials.
     */
    public BigInteger calculateLargestSW(int maxValue) {
        BigInteger factorial = BigInteger.ONE;
        BigInteger sum = BigInteger.ZERO;

        for (int i = 1; i <= maxValue; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
            sum = sum.add(factorial);
        }

        return sum;
    }
}