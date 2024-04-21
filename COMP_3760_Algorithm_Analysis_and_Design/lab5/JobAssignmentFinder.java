import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The JobAssignmentFinder class represents a solution to the Job Assignment
 * problem.
 * This class provides methods to read data from a file, perform exhaustive
 * search, and find a greedy assignment
 * for the maximum-valued job assignment problem.
 * 
 * The solution is based on the "decrease and conquer" approach to generate
 * permutations and apply a greedy algorithm
 * to find the optimal job assignments.
 * 
 * @author tmagliery
 * @author Yongeun Kwon / A01263922 / Set C
 * 
 *
 */
public class JobAssignmentFinder {

	/**
	 * This data is the input to the problem; it will be read from a data file.
	 */
	private int[][] benefitMatrix;
	private int problemSize;

	/**
	 * Reads the data file and stores the benefit matrix data for subsequent use.
	 * 
	 * @param fileName
	 */
	public void readDataFile(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		Scanner sc = new Scanner(file);
		this.problemSize = sc.nextInt();
		this.benefitMatrix = new int[this.problemSize][this.problemSize];
		for (int r = 0; r < this.problemSize; r++) {
			for (int c = 0; c < this.problemSize; c++) {
				this.benefitMatrix[r][c] = sc.nextInt();
			}
		}
		sc.close();
	}

	/**
	 * Basic getter for the problem size aka "N".
	 * 
	 * @return
	 */
	public int getInputSize() {
		return this.problemSize;
	}

	/**
	 * Basic getter for the benefit matrix (input).
	 * 
	 * @return
	 */
	public int[][] getBenefitMatrix() {
		return this.benefitMatrix;
	}

	/**
	 * Returns a string representation of the (input) benefit matrix.
	 * 
	 * @return
	 */
	public String benefitMatrixToString() {
		String result = "";
		result += "Matrix size is " + this.problemSize + " x " + this.problemSize + "\n";
		for (int row = 0; row < this.problemSize; row++) {
			result += "[";
			for (int col = 0; col < this.problemSize - 1; col++) {
				result += this.benefitMatrix[row][col] + " ";
			}
			result += this.benefitMatrix[row][this.problemSize - 1] + "]\n";
		}
		return result;
	}

	/**
	 * Do exhaustive search to find the maximum-valued assignment of jobs to people.
	 * This blindly performs the entire exhaustive search, even if this has already
	 * been done before on the current data set. It would (obvs) be smarter to
	 * "save" the result any time we calculate it, in order to avoid this
	 * recalculation. Oh well.
	 * 
	 * @return
	 */
	public ArrayList<Integer> getMaxAssignment() {

		/**
		 * First generate all the permutations of size N.
		 */
		ArrayList<ArrayList<Integer>> allPermutations = getPermutations(benefitMatrix.length);

		/**
		 * Loop over the permutations, checking the benefit of each one, and remembering
		 * the maximum.
		 */
		int maxBenefit = 0;
		int benefit;
		ArrayList<Integer> maxAssignment = null;
		maxAssignment = new ArrayList<Integer>();
		for (ArrayList<Integer> jobAssignment : allPermutations) {
			benefit = checkValueOfAssignment(jobAssignment, this.benefitMatrix);
			if (benefit > maxBenefit) {
				maxBenefit = benefit;
				maxAssignment = jobAssignment;
			}
		}

		/**
		 * Done!
		 */
		return maxAssignment;

	}

	/**
	 * Return the total value of the max assignment. This blindly performs the
	 * entire exhaustive search, even if this has already been done before. It would
	 * (obvs) be smarter to "save" the result any time we calculate it, in order to
	 * avoid this recalculation. Oh well.
	 * 
	 * @return
	 */
	public int getMaxAssignmentTotalValue() {

		/**
		 * First generate all the permutations of size N.
		 */
		ArrayList<ArrayList<Integer>> allPermutations = getPermutations(benefitMatrix.length);

		/**
		 * Loop over the permutations, checking the benefit of each one, and remembering
		 * the maximum.
		 */
		int maxBenefit = 0;
		int benefit;
		ArrayList<Integer> maxAssignment = null;
		maxAssignment = new ArrayList<Integer>();
		for (ArrayList<Integer> jobAssignment : allPermutations) {
			benefit = checkValueOfAssignment(jobAssignment, this.benefitMatrix);
			if (benefit > maxBenefit) {
				maxBenefit = benefit;
				maxAssignment = jobAssignment;
			}
		}

		/**
		 * Done!
		 */
		return maxBenefit;

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
			 * every permutation in every possible position.
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

	/**
	 * Helper function determines the total value of making the given job
	 * assignment.
	 * 
	 * @return
	 */
	private int checkValueOfAssignment(ArrayList<Integer> jobAssignment, int[][] benefitMatrix) {
		int total = 0;
		for (int person = 0; person < jobAssignment.size(); person++) {
			int job = jobAssignment.get(person);
			total = total + benefitMatrix[person][job];
		}
		return total;
	}

	/**
	 * Finds a greedy assignment of jobs to people.
	 * 
	 * @return the greedy assignment of jobs to people
	 */
	public ArrayList<Integer> getGreedyAssignment() {
		ArrayList<Integer> jobAssignment = new ArrayList<>();
		for (int i = 0; i < problemSize; i++) {
			jobAssignment.add(-1);
		}

		boolean[] assignedJobs = new boolean[problemSize];

		for (int person = 0; person < problemSize; person++) {
			int maxBenefit = Integer.MIN_VALUE;
			int bestJob = -1;

			for (int job = 0; job < problemSize; job++) {
				if (!assignedJobs[job] && benefitMatrix[person][job] > maxBenefit) {
					maxBenefit = benefitMatrix[person][job];
					bestJob = job;
				}
			}

			if (bestJob != -1) {
				jobAssignment.set(person, bestJob);
				assignedJobs[bestJob] = true;
			}
		}

		return jobAssignment;
	}

	/**
	 * Calculates the total value of the greedy job assignment.
	 * 
	 * @return the total value of the greedy job assignment
	 */
	public int greedyAssignmentTotalValue() {
		ArrayList<Integer> jobAssignment = getGreedyAssignment();
		int totalValue = 0;
		for (int person = 0; person < problemSize; person++) {
			int job = jobAssignment.get(person);
			if (job != -1) {
				totalValue += benefitMatrix[person][job];
			}
		}
		return totalValue;
	}

}
