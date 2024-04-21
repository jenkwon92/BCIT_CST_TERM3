/**
 * A class representing a hash simulation for various hash functions.
 * 
 * The class provides methods to simulate the process of hashing a list of keys
 * into a hash table
 * using different hash functions (H1, H2, and H3) and calculates the number of
 * collisions and probes
 * encountered during the process.
 * 
 * 
 * Author: Yongeun Kwon / A01263922 / Set C
 */
public class HashSimulator {
    /**
     * Runs the hash simulation for the specified keys and hash table size using
     * different hash functions (H1, H2, H3).
     * 
     * @param keys   an array of strings representing the keys to be hashed
     * @param HTsize the size of the hash table
     * @return an array containing the number of collisions and probes encountered
     *         for each hash function:
     *         - index 0: number of collisions for H1
     *         - index 1: number of probes for H1
     *         - index 2: number of collisions for H2
     *         - index 3: number of probes for H2
     *         - index 4: number of collisions for H3
     *         - index 5: number of probes for H3
     */
    public int[] runHashSimulation(String[] keys, int HTsize) {
        int[] results = new int[6];

        // Hash simulation for H1
        int[] h1Results = simulateHash(keys, HTsize, "H1");
        results[0] = h1Results[0]; // collisions
        results[1] = h1Results[1]; // probes

        // Hash simulation for H2
        int[] h2Results = simulateHash(keys, HTsize, "H2");
        results[2] = h2Results[0]; // collisions
        results[3] = h2Results[1]; // probes

        // Hash simulation for H3
        int[] h3Results = simulateHash(keys, HTsize, "H3");
        results[4] = h3Results[0]; // collisions
        results[5] = h3Results[1]; // probes

        return results;
    }

    /**
     * Calculates the hash value using the H1 hash function, which sums up the
     * character values
     * of the string and takes the modulo of the sum with the hash table size.
     * 
     * @param key    the string to be hashed
     * @param HTsize the size of the hash table
     * @return the hash value
     */
    public int H1(String key, int HTsize) {
        long sum = 0;

        // Iterate through each character in the key
        for (char ch : key.toCharArray()) {
            // Convert the character to its ASCII value and subtract the ASCII value of 'A'
            // + 1
            // to get the position of the character in the alphabet, then add it to the sum
            sum = (sum + (ch - 'A' + 1)) % (long) HTsize;
        }
        // Take the modulo of the sum with the hash table size
        return (int) sum % HTsize;
    }

    /**
     * Calculates the hash value using the H2 hash function, which multiplies the
     * character values
     * of the string with increasing powers of 26 and takes the modulo of the result
     * with the hash table size.
     * 
     * @param key    the string to be hashed
     * @param HTsize the size of the hash table
     * @return the hash value
     */
    public int H2(String key, int HTsize) {
        long result = 0;
        long base = 1;

        // Iterate through each character in the key
        for (int i = 0; i < key.length(); i++) {
            // Convert the character to uppercase to ensure uniformity
            char ch = Character.toUpperCase(key.charAt(i));
            // Calculate the character value based on its position in the alphabet
            long charValue = (long) (ch - 'A' + 1);

            // Multiply the character value by the base (26^i) and add to the result
            result += charValue * base;
            // Update the base for the next iteration
            base *= 26;
        }
        // Take the modulo of the result with the hash table size to get the final hash
        // value
        return (int) (result % (long) HTsize);
    }

    /**
     * Calculates the hash value using the H3 hash function, which is based on the
     * Java hashCode() method.
     * This method converts the hash code to a long value and performs a bitwise AND
     * operation with 0xffffffffL
     * to ensure it is treated as an unsigned 32-bit integer. This helps handle
     * negative hash codes.
     * 
     * This code was implemented based on my own understanding.
     * 
     * @param key    the string to be hashed
     * @param HTsize the size of the hash table
     * @return the hash value
     */
    public int H3(String key, int HTsize) {
        long hasedkey = (long) key.hashCode() & 0xffffffffL;
        return (int) (hasedkey % (long) HTsize);
    }

    /**
     * Helper function
     * Simulates the process of hashing a list of keys into a hash table using a
     * specified hash function
     * and calculates the number of collisions and probes encountered during the
     * process.
     * 
     * @param keys         an array of strings representing the keys to be hashed
     * @param size         the size of the hash table
     * @param hashFunction the hash function to be used ("H1", "H2", or "H3")
     * @return an array containing the number of collisions and probes encountered
     *         during hashing
     *         - index 0: number of collisions
     *         - index 1: number of probes
     * @throws IllegalArgumentException if an invalid hash function is provided
     */
    private int[] simulateHash(String[] keys, int size, String hashFunction) {
        int collisions = 0;
        int probes = 0;

        String[] hashTable = new String[size];
        for (String key : keys) {
            int hashValue;
            switch (hashFunction) {
                case "H1":
                    hashValue = H1(key, size);
                    break;
                case "H2":
                    hashValue = H2(key, size);
                    break;
                case "H3":
                    hashValue = H3(key, size);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid hash function: " + hashFunction);
            }

            // Linear probing
            if (hashTable[hashValue] != null) {
                collisions++;
            }
            while (hashTable[hashValue] != null) {
                probes++;
                hashValue = (hashValue + 1) % size;
            }

            hashTable[hashValue] = key;
        }

        return new int[] { collisions, probes };
    }
}