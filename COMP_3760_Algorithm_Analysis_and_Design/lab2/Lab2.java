import java.util.ArrayList;

/**
 * Author: Yongeun Kwon (A01263922)
 * 
 * Generates palindrome sequences of length N consisting of the characters
 * 'A','B', and 'C'.
 * Palindrome sequences are sequences that read the same forwards and backwards.
 *
 * @param N The length of palindrome sequences to generate.
 * @return An ArrayList containing all palindrome sequences of length N.
 * 
 */

public class Lab2 {
    // General case: Recursion for N > 2
    // Generate palindrome sequences recursively
    public ArrayList<String> generatePalindromeSequences(int N) {
        ArrayList<String> result = new ArrayList<>();

        // Base case: N = 1
        if (N == 1) {
            // Single-character palindrome sequences
            result.add("A");
            result.add("B");
            result.add("C");
            return result;
        }

        // Base case: N = 2
        if (N == 2) {
            // Double-character palindrome sequences
            for (char c = 'A'; c <= 'C'; c++) {
                result.add(String.valueOf(c) + c);
            }
            return result;
        }

        // General case: Recursion for N > 2
        // Generate palindrome sequences recursively
        ArrayList<String> prevPalindromes = generatePalindromeSequences(N - 2);

        for (String palindrome : prevPalindromes) {
            for (char c = 'A'; c <= 'C'; c++) {
                String newPalindrome = c + palindrome + c;
                // Check if the new palindrome is indeed a palindrome
                if (newPalindrome.equals(new StringBuilder(newPalindrome).reverse().toString())) {
                    // Find the correct position to insert the new palindrome while maintaining
                    // sorted order
                    int insertIndex = 0;
                    while (insertIndex < result.size() && result.get(insertIndex).compareTo(newPalindrome) < 0) {
                        insertIndex++;
                    }
                    result.add(insertIndex, newPalindrome);
                }
            }
        }
        return result;
    }
}
