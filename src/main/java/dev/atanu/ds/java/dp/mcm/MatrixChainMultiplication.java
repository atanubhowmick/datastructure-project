/**
 * 
 */
package dev.atanu.ds.java.dp.mcm;

/**
 * https://hackthedeveloper.com/matrix-chain-multiplication-algorithm/
 * https://www.geeksforgeeks.org/matrix-chain-multiplication-dp-8/
 * 
 * @author Atanu Bhowmick
 *
 */
public class MatrixChainMultiplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// If inputs are five then there are 4 matrix
		// For the input { 3, 2, 5, 4, 7 }
		// Dimension of A1 = 3 * 2
		// Dimension of A2 = 2 * 5
		// Dimension of A3 = 5 * 4
		// Dimension of A4 = 4 * 7

		int[] dimensions = { 3, 2, 5, 4, 7 };
		MatrixChainMultiplication mcm = new MatrixChainMultiplication();
		System.out.println(mcm.matrixChainMultiplicationRecurrsion(dimensions));
	}

	/**
	 * 
	 * @param arr
	 * @return
	 */
	public int matrixChainMultiplicationRecurrsion(int[] arr) {
		return matrixChainMultiplicationRecurrsion(arr, 1, arr.length - 1);
	}

	private int matrixChainMultiplicationRecurrsion(int[] arr, int i, int j) {
		if (i >= j) {
			return 0;
		}

		int minCost = Integer.MAX_VALUE;
		for (int k = i; k < j; k++) {
			// cost of ABCDE = (cost of AB) * (cost of CDE)
			// cost = cost of group_1 + cost of group_2 + cost of (group1 * group2)
			// cost = recursion(group_1) + recursion(group_2) + (row_group_1 * column_group_1 * column_group_2)
			int cost = matrixChainMultiplicationRecurrsion(arr, i, k)
					+ matrixChainMultiplicationRecurrsion(arr, k + 1, j) 
					+ arr[i - 1] * arr[k] * arr[j];

			minCost = Math.min(minCost, cost);
		}

		return minCost;
	}
	
	
	/**
	 * 
	 * @param arr
	 * @return
	 */
	public int matrixChainMultiplicationMemoization(int[] arr) {
		return matrixChainMultiplicationMemoization(arr, 1, arr.length - 1);
	}

	private int matrixChainMultiplicationMemoization(int[] arr, int i, int j) {
		if (i >= j) {
			return 0;
		}

		int minCost = Integer.MAX_VALUE;
		for (int k = i; k < j; k++) {
			// cost of ABCDE = (cost of AB) * (cost of CDE)
			// cost = cost of group_1 + cost of group_2 + cost of (group1 * group2)
			// cost = recursion(group_1) + recursion(group_2) + (row_group_1 * column_group_1 * column_group_2)
			int cost = matrixChainMultiplicationRecurrsion(arr, i, k)
					+ matrixChainMultiplicationRecurrsion(arr, k + 1, j) 
					+ arr[i - 1] * arr[k] * arr[j];

			minCost = Math.min(minCost, cost);
		}

		return minCost;
	}
}
