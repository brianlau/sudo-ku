package sudoku;

/**
 * 
 * @author Brian Lau
 * 
 *         Attempts to solve a sudoku puzzle
 */

public class Solve {

	private static final int METHOD1_LOOP = 0;
	private static final int METHOD1_STOP = 3;
	private static final int METHOD2_LOOP = 1;
	private static final int METHOD2_STOP = 2;

	int N; // Dimensions of the puzzle
	int grid[][]; // The puzzle grid
	Legals L;
	int diff;

	private boolean[][] checked;

	// Constructor
	Solve() {
	}

	/**
	 * 
	 * Solves a grid with the given numbers, also checks for a unique solution
	 * if found returns an integer with the difficulty of the puzzle, if not
	 * returns 0.
	 * 
	 * @return Difficulty of the puzzle
	 */
	public int solve(int dimension, int[][] gridCopy) {
		// Copy of the original grid
		grid = new int[dimension][dimension];
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				grid[i][j] = gridCopy[i][j];
			}
		}
		N = dimension;
		L = new Legals(3);
		checked = new boolean[dimension][dimension];
		int diff = 1;

		for (int i = 0; i < dimension; i++)
			for (int j = 0; j < dimension; j++)
				if (grid[i][j] != 0)
					fillSquare(i, j, grid[i][j]);

		int count = this.countNumKnown();
		int countOld = -1;
		while (count < 81 && count != countOld) {

			// Method #1
			// Fill in all squares for which there is only 1 option available:
			countOld = count;
			for (int i = 0; i < dimension; i++) {
				for (int j = 0; j < dimension; j++) {
					if (!checked[i][j]) {
						int[] poss = L.countCandidates(i, j); // poss[0] =
						// number of
						// candidates, poss[1]
						// = one of those
						// candidates
						if (poss[0] == 1) {
							diff += Solve.METHOD1_STOP;
							this.fillSquare(i, j, poss[1] + 1);
						} else {
							diff += Solve.METHOD1_LOOP;
						}
					}
				}
			}

			// Method #2 part a
			// Fill in numbers for which there is only 1 valid square in a ROW

			// For each row...
			for (int i = 0; i < dimension; i++) {
				// for each number
				for (int k = 0; k < dimension; k++) {
					int r = 0, rj = -1;
					// for each square in the row
					for (int j = 0; j < dimension; j++) {
						// count the number of options
						if (L.get(i, j, k)) {
							r++;
							rj = j;
						}
					}
					if (r == 1) {
						// and if there's only one, we go one step further
						// to
						// a solution.
						diff += Solve.METHOD2_STOP;
						fillSquare(i, rj, k + 1);
					} else {
						diff += Solve.METHOD2_LOOP;
					}
				}
			}
			// Method #2 part b
			// Fill in numbers for which there is only 1 legal square in a
			// COLUMN
			// For each column
			for (int j = 0; j < dimension; j++) {
				// for each number
				for (int k = 0; k < dimension; k++) {
					int r = 0, ri = -1;
					// for each square in the row
					for (int i = 0; i < dimension; i++) {
						// count the number of options
						if (L.get(i, j, k)) {
							r++;
							ri = i;
						}
					}
					if (r == 1) {
						// and if there's only one, we got one step further
						// to a solution.
						diff += Solve.METHOD2_STOP;
						fillSquare(ri, j, k + 1);
					} else {
						diff += Solve.METHOD2_LOOP;
					}
				}
			}

			// Method #2 part c
			// Fill in numbers for which there is only 1 valid square in a BOX

			// For each box
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					// for each number
					for (int k = 0; k < dimension; k++) {
						int r = 0, ri = -1, rj = -1;
						// for each square in the box
						for (int l = 0; l < 3; l++) {
							for (int m = 0; m < 3; m++) {
								// count the number of available options
								if (L.get(i * 3 + l, j * 3 + m, k)) {
									r++;
									ri = i * 3 + l;
									rj = j * 3 + m;
								}
							}
						}
						if (r == 1) {
							// and if there's only one, we got one step
							// further to a solution.
							diff += Solve.METHOD2_STOP;
							fillSquare(ri, rj, k + 1);
						} else {
							diff += Solve.METHOD2_LOOP;
						}
					}
				}
			}

			diff += 3;
			count = countNumKnown();
		}

		if (count == 81)
			return diff;
		else
			return 0;
	}

	/**
	 * Method returns the 3x3 box that the space currently occupies.
	 * 
	 * @param i
	 *            A particular row
	 * @param j
	 *            A particular column
	 * @return The 3x3 box the space is in
	 */
	public static int[] rc2box(int i, int j) {
		int[] r = { 6, 6 };
		if (i < 6) {
			r[0] = 3;
			if (i < 3)
				r[0] = 0;
		}
		if (j < 6) {
			r[1] = 3;
			if (j < 3)
				r[1] = 0;
		}
		return r;
	}

	/**
	 * When K is found on the grid[i][j] it is passed to this function so the L
	 * and the grid are updated.
	 * 
	 * @param i
	 *            The row that is being checked
	 * @param j
	 *            The column being checked
	 * @param K
	 *            The number that grid[i][j] will have
	 */
	private void fillSquare(int i, int j, int K) {
		// Eliminates candidate K from row, column and box:
		L.set(i, j, K);
		// Put K on place (i,j) in the grid:
		grid[i][j] = K;
		// Note that (i,j) has been filled and does
		// not have to be checked again.
		checked[i][j] = true;
	}

	/**
	 * This method counts the number of known values are in the grid
	 * 
	 * @return Integer of how many values are known
	 */
	private int countNumKnown() {
		int numKnown = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (this.grid[i][j] != 0)
					numKnown++;
		return numKnown;
	}
}
