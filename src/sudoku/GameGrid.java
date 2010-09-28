package sudoku;

import java.util.Random;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class GameGrid {

	/**
	 * A two dimensional string array that contains the 9x9 sudoku game grid
	 */
	protected Integer[][] grid = new Integer[9][9];
	protected Random r = new Random();

	public GameGrid() {
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				// Make 10 attempts to generate a valid random number
				Boolean valid = false;
				for (int i = 1; i < 11; i++) {
					Integer intRandom = r.nextInt(9) + 1;
					valid = validate(column, row, intRandom);

					if (valid)
					{
						grid[column][row] = intRandom;
						i = 11;
					}
				}

				// If that fails, lets try something a bit differant
				// It seems that the random very rarely returns 9, so we'll
				// start there and count backwards until we get a valid number
				if (!valid) {
					for (int i = 9; i > 0; i--) {
						valid = validate(column, row, i);
						if (valid) {
							grid[column][row] = i;
							i = 0;
						}
					}
				}

				if (!valid)
				{
					System.out.println("Number not valid at " + "[" + column
							+ "]" + "[" + row + 1 + "]");
					grid[column][row] = 0;
				}

			}
		}
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
					strOutput.append(Integer.toString(grid[row][column]));
				}
				strOutput.append("\n");
			}

			return strOutput.toString();
		}

		// Generates ANSI formatted game grid
		StringBuffer strOutput = new StringBuffer();
		final String bgColor = "";
		final String fgColor = "";
		
		AnsiConsole.systemInstall();		
		
		strOutput.append(ansi().bg(BLACK));
		strOutput.append("@|bold ----------------------                                                         |@");
		strOutput.append(ansi().bg(BLACK));

			strOutput.append("\n");
			strOutput.append("@|bold |@");
			strOutput.append(ansi().bg(BLACK));

		strOutput.append(ansi().bg(BLACK));
		strOutput.append(ansi().render("@|white @|bold~~~~~~~~~~~~~~~~~~~~~~ @|"));

		for (int row = 0; row < 9; row++)
		{
			strOutput.append("\n");
			for (int column = 0; column < 9; column++)
			{
				strOutput.append(" ");
				strOutput.append(Integer.toString(grid[row][column]));
				strOutput.append(" ");
			}
		}

		return strOutput.toString();

	}

	/**
	 * This method takes the x,y and value of a number that you would like to
	 * place into the game grid and returns whether or not it is a valid number
	 * for that position. *
	 * 
	 * @author Chadd Ingersoll <cjingers@asu.edu>
	 * 
	 * @param x
	 *            The column the number will be placed in.
	 * @param y
	 *            The row the number will be placed in.
	 * @param num
	 *            The number to be tested.
	 * @return True if the number works for the specified location.
	 */
	private Boolean validate(Integer col, Integer row, Integer num) {
		// Right away, the passed value is not valid if it is zero
		if (num == 0) {
			return false;
		}

		// Compare to everything in the same row
		for (int i = 0; i < 9; i++) {
			if (grid[col][i] == num) {
				return false;
			}
		}

		// Check all numbers in the same column
		for (int i = 0; i < 9; i++) {
			if (grid[i][row] == num) {
				return false;
			}
		}

		/*
		 * Determine which group the number is going to be placed inand test all
		 * the numbers in that group against the given number
		 */

		// Determine group
		Integer group = 0;
		Integer colGroup = 0;
		Integer rowGroup = 0;

		// Test for column group
		if (col < 3) {
			colGroup = 0;
		} else if (col < 6) {
			colGroup = 3;
		} else if (col < 9) {
			colGroup = 6;
		} else {
			// The value must be out of range or something
			return false;
		}

		// Test for row group
		if (row < 3) {
			rowGroup = 0;
		} else if (row < 6) {
			rowGroup = 3;
		} else if (row < 9) {
			rowGroup = 6;
		} else {
			// The value must be out of range or something
			return false;
		}

		// now we have to put all this information together
		// for the search
		for (int rowSearch = rowGroup; rowSearch < rowGroup + 3; rowSearch++) {
			for (int colSearch = colGroup; colSearch < colGroup + 3; colSearch++) {
				if (num == grid[colSearch][rowSearch]) {
					return false;
				}
			}
		}
		return true;
	}

}
