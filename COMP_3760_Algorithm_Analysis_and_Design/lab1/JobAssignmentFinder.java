import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class represents a JobAssignmentFinder which is capable of finding
 * maximum job assignments based on a benefit matrix.
 * 
 * The class also includes a recursive decrease-and-conquer algorithm to
 * generate a list of all permutations of the numbers 0 to N-1,
 * where N is the size of the benefit matrix. This algorithm is used internally
 * to find the maximum job assignment.
 * 
 * Author: Yongeun Kwon (A01263922)
 */
public class JobAssignmentFinder {
    private int[][] benefitMatrix;
    private int inputSize;

    /**
     * Reads data from a file to initialize the benefit matrix, which is used
     * forfinding maximum job assignments.
     * The method takes a file name as an argument and reads the data from the
     * specified file.
     * 
     * @param fileName The name of the file containing the data.
     */
    public void readDataFile(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            inputSize = Integer.parseInt(reader.readLine());
            benefitMatrix = new int[inputSize][inputSize];
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(" ");
                for (int col = 0; col < inputSize; col++) {
                    benefitMatrix[row][col] = Integer.parseInt(values[col]);
                }
                row++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the size of the input data, which corresponds to the dimension of
     * the benefit matrix.
     * If the benefit matrix has been initialized, returns the input size;
     * otherwise, returns -1.
     * 
     * @return The size of the input data or -1 if the benefit matrix has not been
     *         initialized.
     */
    public int getInputSize() {
        if (benefitMatrix == null) {
            return -1;
        }
        return inputSize;
    }

    /**
     * Retrieves the benefit matrix that is used for determining job assignments.
     * 
     * @return The benefit matrix, where each element represents the benefit of
     *         assigning a job to a worker.
     */
    public int[][] getBenefitMatrix() {
        return benefitMatrix;
    }

    /**
     * Converts the benefit matrix to a string representation.
     * 
     * @return A string representation of the benefit matrix, where each row is
     *         separated by a newline character.
     *         Within each row, the elements of the benefit matrix are separated by
     *         spaces.
     */
    public String benefitMatrixToString() {
        StringBuilder result = new StringBuilder();
        for (int[] row : benefitMatrix) {
            for (int value : row) {
                result.append(value).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }

    /**
     * Calculates and returns the job assignment that maximizes the total benefit.
     * 
     * @return An ArrayList representing the job assignment that maximizes the total
     *         benefit.
     *         Each element in the ArrayList corresponds to a worker index, and its
     *         value represents the assigned job index.
     */
    public ArrayList<Integer> getMaxAssignment() {
        ArrayList<ArrayList<Integer>> allAssignments = getPermutations(inputSize);
        int maxBenefit = Integer.MIN_VALUE;
        ArrayList<Integer> maxAssignment = null;

        for (ArrayList<Integer> assignment : allAssignments) {
            int totalBenefit = 0;
            for (int i = 0; i < assignment.size(); i++) {
                int jobIndex = assignment.get(i);
                totalBenefit += benefitMatrix[i][jobIndex];
            }
            if (totalBenefit > maxBenefit) {
                maxBenefit = totalBenefit;
                maxAssignment = assignment;
            }
        }

        return maxAssignment;
    }

    /**
     * Calculates and returns the total value of the maximum job assignment.
     * 
     * @return The total value of the maximum job assignment, which is the sum of
     *         benefits for all assigned jobs.
     */
    public int getMaxAssignmentTotalValue() {
        ArrayList<Integer> maxAssignment = getMaxAssignment();
        int totalValue = 0;
        if (maxAssignment != null) {
            for (int i = 0; i < maxAssignment.size(); i++) {
                int person = i;
                int job = maxAssignment.get(i);
                totalValue += getBenefit(person, job);
            }
        }
        return totalValue;
    }

    /**
     * Retrieves the benefit of assigning a specific job to a particular person.
     * 
     * @param person The index of the person.
     * @param job    The index of the job.
     * @return The benefit of assigning the specified job to the specified person.
     */
    public int getBenefit(int person, int job) {
        return benefitMatrix[person][job];
    }

    /**
     * Recursive decrease-and-conquer algorithm to generate a list of all
     * permutations of the numbers 0..N-1. This follows the "decrease by 1" pattern
     * of decrease and conquer algorithms.
     * 
     * This method returns an ArrayList of ArrayLists. One permutation is an
     * ArrayList containing 0,1,2,...,N-1 in some order. The final result is an
     * ArrayList containing N! of those permutations.
     * 
     * @param N
     * @return
     */
    private ArrayList<ArrayList<Integer>> getPermutations(int N) {
        ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();

        /**
         * This isn't a "base case", it's a "null case". This function does not call
         * itself with an argument of zero, but we can't prevent another caller from
         * doing so. It's a weird result, though. The list of permutations has one
         * permutation, but the one permutation is empty (0 elements).
         */
        if (N == 0) {
            ArrayList<Integer> emptyList = new ArrayList<Integer>();
            results.add(emptyList);

        } else if (N == 1) {
            /**
             * Now THIS is the base case. Create an ArrayList with a single integer, and add
             * it to the results list.
             */
            ArrayList<Integer> singleton = new ArrayList<Integer>();
            singleton.add(0);
            results.add(singleton);

        } else {
            /**
             * And: the main part. First a recursive call (this is a decrease and conquer
             * algorithm) to get all the permutations of length N-1.
             */
            ArrayList<ArrayList<Integer>> smallList = getPermutations(N - 1);

            /**
             * Iterate over the list of smaller permutations and insert the value 'N-1' into
             * every permutation in every possible position, adding each new permutation to
             * the big list of permutations.
             */
            for (ArrayList<Integer> perm : smallList) {

                /**
                 * Add 'N-1' -- the biggest number in the new permutation -- at each of the
                 * positions from 0..N-1.
                 */
                for (int i = 0; i < perm.size(); i++) {
                    ArrayList<Integer> newPerm = (ArrayList<Integer>) perm.clone();
                    newPerm.add(i, N - 1);
                    results.add(newPerm);
                }

                /**
                 * Add 'N-1' at the end (i.e. at position "size").
                 */
                ArrayList<Integer> newPerm = (ArrayList<Integer>) perm.clone();
                newPerm.add(N - 1);
                results.add(newPerm);

            }

        }

        /**
         * Nothing left to do except:
         */
        return results;
    }

}
