package sudoku;

import java.util.Random;

public class GameGrid {

	public int diff;

	/**
	 * A two dimensional string array that contains the 9x9 sudoku game grid
	 */
	protected int[][] grid;
	protected Random r = new Random();
	private int N;

	public int[][] given;
	public int countGiven;
	
	//Remembers which numer is selected
	Integer selectedColumn = 0;
	Integer selectedRow = 0;

	private boolean[][] boxes;
	private boolean[][] rows;
	private boolean[][] cols;

	// Gives the 3x3 box grid[i][j] is currently in.
	private int[][] currentBox = { { 0, 0, 0, 1, 1, 1, 2, 2, 2 },
			{ 0, 0, 0, 1, 1, 1, 2, 2, 2 }, { 0, 0, 0, 1, 1, 1, 2, 2, 2 },
			{ 3, 3, 3, 4, 4, 4, 5, 5, 5 }, { 3, 3, 3, 4, 4, 4, 5, 5, 5 },
			{ 3, 3, 3, 4, 4, 4, 5, 5, 5 }, { 6, 6, 6, 7, 7, 7, 8, 8, 8 },
			{ 6, 6, 6, 7, 7, 7, 8, 8, 8 }, { 6, 6, 6, 7, 7, 7, 8, 8, 8 } };

	/**
	 * Constructor initializes the variables and arrays for other methods,
	 * attempts to create a grid tries at most 50 times, takes the best grid and
	 * uses it for the puzzle.
	 * 
	 * @param dimension
	 *            The dimensions of a sudoku grid, i.e. 9 = 9x9 grid
	 */
	public GameGrid(int dimension) {
		N = dimension;
		grid = new int[dimension][dimension];
		given = new int[dimension][dimension];
		boxes = new boolean[dimension][dimension];
		rows = new boolean[dimension][dimension];
		cols = new boolean[dimension][dimension];

		diff = 0;

		// Fill a grid with numbers:
		cleanGrid();
		create(grid, boxes, rows, cols, 0);

		// Keep track of the best grid we've found:
		int[][] best = new int[N][N];
		int bestDifficulty = 0;
		int bestGiven = N * N + 1;

		int tries = 0;
		// Generate 50 puzzles and pick the most difficult one
		while (tries < 50) {
			countGiven = 0;
			diff = 0;
			// Cleans the given numbers, but keeps the grid intact
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					given[i][j] = 0;
				}
			}

			// Select 17 random numbers to show
			// 17 is the absolute minimum. Any less and the puzzle is guaranteed
			// to be unsolvable.
			// randomGiven(17);
			diff = solvable();

			// Keep adding numbers until the puzzle is solvable
			while (diff == 0) {
				addRandomGiven();
				diff = solvable();
			}
			if (countGiven <= bestGiven) {
				// If this puzzle is better then anything we've found so far,
				// store it
				bestGiven = countGiven;
				bestDifficulty = diff;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						best[i][j] = this.given[i][j];
					}
				}
				if (bestGiven < 35 && bestDifficulty > 960)
					break;
			}
			tries++;
		}

		// Restore the best grid:
		countGiven = bestGiven;
		diff = bestDifficulty;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				this.given[i][j] = best[i][j];
			}
		}

	}

	/**
	 * Changes the specified number in the "givens" array
	 * @param x The column position of the number
	 * @param y The row position of the number
	 * @param number The number to be inserted into the array
	 */
	public void setNumber(Integer x, Integer y, Integer number)
	{
		given[x][y] = number;
	}
	
	/**
	 * Sets the currently selected number
	 * @param number The number to set to
	 */
	public void setNumber(Integer number)
	{
		given[selectedRow][selectedColumn] = number;
	}
	
	/**
	 * Method attempts to create a solvable grid
	 * 
	 * @param grid_
	 *            The 2d array that will hold the puzzle
	 * @param box
	 *            2d boolean array of a 3x3 box
	 * @param row
	 *            2d boolean array of possible values at for the row
	 * @param column
	 *            2d boolean array of possible values at for the column
	 * @param level
	 *            Starting difficulty of the puzzle
	 * @return If true is returned then a puzzle has been successfully made,
	 *         otherwise it hasn't.
	 */
	public boolean create(int[][] grid_, boolean[][] box, boolean[][] row,
			boolean[][] column, int level) {
		boolean legalFound;
		boolean emptySquare = false;
		boolean b, r, c;

		// Make sure the grid is random
		int[] randomList = permutateList();

		// For each row i
		for (int i = 0; i < N; i++) {
			// and each column j
			for (int j = 0; j < N; j++) {
				if (grid_[i][j] == 0) {
					emptySquare = true;
					legalFound = false;
					// and for each value 1-9
					for (int k = 0; k < N; k++) {
						b = box[currentBox[i][j]][randomList[k]];
						r = row[i][randomList[k]];
						c = column[j][randomList[k]];
						// if k is a valid value for grid[i,j]
						if (b && r && c) {
							// fill it in
							box[currentBox[i][j]][randomList[k]] = false;
							row[i][randomList[k]] = false;
							column[j][randomList[k]] = false;
							grid_[i][j] = randomList[k] + 1;

							// and try to fill the rest of the grid,
							// recursively
							if (create(grid_, box, row, column, level + 1)) {
								return true;
							}

							grid_[i][j] = 0;
							box[currentBox[i][j]][randomList[k]] = b;
							row[i][randomList[k]] = r;
							column[j][randomList[k]] = c;
						}
					}
					if (!legalFound) {
						return false;
					}
				}
			}
		}

		if (!emptySquare) {
			grid = grid_;
			return true;
		}
		return false;
	}

	/**
	 * Method validates whether or not the grid is solvable
	 * 
	 * @return An integer is returned representing the difficulty of the puzzle,
	 *         if it is not solvable 0 is returned
	 */
	private int solvable() {
		return new Solve().solve(N, given);
	}

	/**
	 * Helps to make the grid more random
	 * 
	 * @return List of numbers 1-9
	 */
	private int[] permutateList() {
		int[] a = new int[N];
		for (int i = 0; i < N; i++)
			a[i] = i;
		for (int i = 0; i < N; i++) {
			int r = (int) (Math.random() * N);
			int swap = a[r];
			a[r] = a[i];
			a[i] = swap;
		}
		return a;
	}

	/**
	 * Method clears the grid all values are set to zero, boolean arrays are set
	 * to true
	 */
	private void cleanGrid() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				grid[i][j] = 0;
				boxes[i][j] = true;
				rows[i][j] = true;
				cols[i][j] = true;
				given[i][j] = 0;
			}
		}
	}

	private void randomGiven(int showHowMany) {
		// Erase all given numbers, of previous tries.
		for (int i = 0; i < this.N; i++) {
			for (int j = 0; j < this.N; j++) {
				given[i][j] = 0;
			}
		}
		while (countGiven < showHowMany)
			addRandomGiven();
	}

	private void addRandomGiven() {
		int i = (int) (Math.random() * this.N);
		int j = (int) (Math.random() * this.N);
		while (this.given[i][j] != 0) {
			i = (int) (Math.random() * this.N);
			j = (int) (Math.random() * this.N);
		}

		given[i][j] = grid[i][j];
		countGiven++;

	}

	/**
	 * Runs through the grid array and formats the stored values into a 9x9 grid
	 * of numbers
	 * 
	 * @param ansiFormatted
	 *            Returns a formatted board if true
	 * @return The sudoku game grid as a formatted String
	 * @see StringBuffer
	 */
	public String toString(Boolean ansiFormatted) {
		if (!ansiFormatted) {
			StringBuffer strOutput = new StringBuffer();
			for (int row = 0; row < 9; row++) {
				for (int column = 0; column < 9; column++) {
					strOutput.append(Integer.toString(given[row][column]));
				}
				strOutput.append("\n");
			}
			return strOutput.toString();
		}



		// Generates ANSI formatted game grid
		//http://en.wikipedia.org/wiki/Box-drawing_characters
		StringBuffer strOutput = new StringBuffer();
		char chrHorizontal = '\u2501';
		char chrVertical = '\u2503';
		char chrTbar = '\u2533';
		char chrTinverted = '\u2537';
		char chrTleft = '\u2523';
		char chrTright = '\u252a';
		char chrCrossbar = '\u254a';

		//corner characters
		char chrULcorner = '\u250f';
		char chrURcorner = '\u2513';
		char chrLLcorner = '\u2517';
		char chrLRcorner = '\u251b';

		//Print the top row
		strOutput.append(chrULcorner);

		for (int i = 0; i < 3; i++) {
			strOutput.append(chrHorizontal);
			strOutput.append(chrHorizontal);
			strOutput.append(chrHorizontal);
			strOutput.append(chrHorizontal);
			strOutput.append(chrHorizontal);
			strOutput.append(chrHorizontal);
			strOutput.append(chrHorizontal);
			strOutput.append(chrHorizontal);
			strOutput.append(chrHorizontal);
			if(i != 2)
			{
				strOutput.append(chrTbar);
			}
		}
		strOutput.append(chrURcorner);

		for (int row = 0; row < 9; row++)
		{
			strOutput.append("\n");
			strOutput.append(chrVertical);
			for (int column = 0; column < 9; column++)
			{
				if((row==selectedRow)&&(column==selectedColumn))
				{
					strOutput.append(">");
				}
				else
				{
					strOutput.append(" ");
				}
				
				//Insert a blank space if the number is zero
				if(given[row][column]==0)
				{
					strOutput.append(" ");
				}
				else
				{
					strOutput.append(Integer.toString(given[row][column]));
				}
								
				if((row==selectedRow)&&(column==selectedColumn))
				{
					strOutput.append("<");
				}
				else
				{
					strOutput.append(" ");
				}

				//Add horizontal line to the boxes that need it
				switch (column)
				{
				case 2:
					strOutput.append(chrVertical);
					break;
				case 5:
					strOutput.append(chrVertical);
					break;
				default:
					break;
				}
			}

			if(row != 8)
			{
			strOutput.append(chrVertical);
			strOutput.append("\n");
			}

			//Horizontal bars


			switch (row) {
			case 2:
				strOutput.append(chrTleft);
				for (int i = 0; i < 3; i++) {
					if(i != 0)
					{
						strOutput.append(chrCrossbar);
					}
					strOutput.append(chrHorizontal);
					strOutput.append(chrHorizontal);
					strOutput.append(chrHorizontal);
					strOutput.append(chrHorizontal);
					strOutput.append(chrHorizontal);
					strOutput.append(chrHorizontal);
					strOutput.append(chrHorizontal);
					strOutput.append(chrHorizontal);
					strOutput.append(chrHorizontal);
				}
				strOutput.append(chrTright);
				break;
			case 5:
				strOutput.append(chrTleft);
				for (int i = 0; i < 3; i++) {
					if(i != 0)
					{
						strOutput.append(chrCrossbar);
					}
					strOutput.append(chrHorizontal);
					strOutput.append(chrHorizontal);
					strOutput.append(chrHorizontal);
					strOutput.append(chrHorizontal);
					strOutput.append(chrHorizontal);
					strOutput.append(chrHorizontal);
					strOutput.append(chrHorizontal);
					strOutput.append(chrHorizontal);
					strOutput.append(chrHorizontal);
				}
				strOutput.append(chrTright);
				break;
			case 8:
				strOutput.append(chrVertical);
				break;
			default:
				for (int i = 0; i < 3; i++) {
					strOutput.append(chrVertical);
					strOutput.append("         ");
				}
				strOutput.append(chrVertical);
				break;
			}


		}

		//Print the bottom row
		strOutput.append("\n");
		strOutput.append(chrLLcorner);

		for (int i = 0; i < 3; i++) {
			strOutput.append(chrHorizontal);
			strOutput.append(chrHorizontal);
			strOutput.append(chrHorizontal);
			strOutput.append(chrHorizontal);
			strOutput.append(chrHorizontal);
			strOutput.append(chrHorizontal);
			strOutput.append(chrHorizontal);
			strOutput.append(chrHorizontal);
			strOutput.append(chrHorizontal);
			if(i != 2)
			{
				strOutput.append(chrTinverted);
			}
		}
		strOutput.append(chrLRcorner);
		return strOutput.toString();
	}
	
	public void gridCopy()
	{
		for (int x = 0; x < 9; x++)
		{
			for (int y = 0; y < 9; y++)
			{
				given[x][y] = grid[x][y];
			}
		}
	}
	
}
