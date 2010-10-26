package sudoku;

/**
 * 
 * @author Brian Lau
 * 
 *         Creates a N*N*N matrix that contains booleans. L[i,j,k] is true if an
 *         option is allowed on the grid[i,j].
 * 
 */
public class Legals {

	private boolean[][][] L;
	private int N;

	Legals(int n) {
		this.N = n * n;
		L = new boolean[N][N][N];
		clear();
	}

	Legals(int n, int[][] grid) {
		this(n);
		this.update(grid);
	}

	/*
	 * Method sets all elements to true
	 */
	private void clear() {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				for (int k = 0; k < N; k++)
					L[i][j][k] = true;

	}

	/**
	 * 
	 * @param grid
	 *            A grid is added to the boolean grid
	 */
	public void update(int[][] grid) {
		clear();
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (grid[i][j] != 0)
					this.set(i, j, grid[i][j]);
	}

	/**
	 * Method takes a value at a specific space and makes sure that this value
	 * cannot exist more than once in the same row or column
	 * 
	 * @param i
	 *            Row for the boolean array
	 * @param j
	 *            Column for the boolean array
	 * @param notPossible
	 *            Value to eliminate from other columns and rows
	 */
	public void set(int i, int j, int notPossible) {
		// Eliminate candidate notPossible from row, column and box:
		for (int a = 0; a < N; a++) {
			this.L[i][a][notPossible - 1] = false; // Eliminate notPossible from
			// row i
			this.L[a][j][notPossible - 1] = false; // and column j
			this.L[i][j][a] = false; // and eliminate other candidates for (i,j)
		}
		// and from the box (i,j) is in:
		int[] box = Solve.rc2box(i, j);
		for (int a = 0; a < 3; a++) {
			for (int b = 0; b < 3; b++) {
				this.L[box[0] + a][box[1] + b][notPossible - 1] = false;
			}
		}

		// The derived value notPossible is now also eliminated, so we must
		// restore it:
		this.L[i][j][notPossible - 1] = true;
	}

	/**
	 * 
	 * @param i
	 *            The specific row
	 * @param j
	 *            The specific column
	 * @param k
	 *            A possible number for the particular row and column
	 * @return Returns true or false for the value, if true the element is a
	 *         possible value for the specific space, otherwise it is not a
	 *         possibility.
	 */
	public boolean get(int i, int j, int k) {
		return this.L[i][j][k];
	}

	/**
	 * Method counts the number of possibilities at a particular row and column
	 * 
	 * @param i
	 *            The numbered row
	 * @param j
	 *            The numbered column
	 * @return The number of possible values at the specific column and row
	 */
	public int[] countCandidates(int i, int j) {
		int r = 0;
		int r2 = -1;
		for (int a = 0; a < N; a++) {
			if (this.L[i][j][a]) {
				r++; // the number of possible values
				r2 = a; // one of the possible values
			}
		}
		int[] _r = { r, r2 };
		return _r;
	}
}
