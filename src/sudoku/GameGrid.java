package sudoku;

import java.util.Random;

public class GameGrid {

	/**
	 * A two dimensional string array that contains the
	 * 9x9 sudoku game grid
	 */
	protected Integer[][] grid = new Integer[9][9];
	protected Random r = new Random();
	
	public GameGrid()
	{
		for (int row = 0; row < 9; row++)
		{
			for (int column = 0; column < 9; column++)
			{
				grid[row][column] = r.nextInt(9) + 1;
			}
		}
	}
	
	/**
	 * Runs through the grid array and formats the stored values into
	 * a 9x9 grid of numbers
	 * 
	 * @return The sudoku game grid as a formatted String
	 * @see StringBuffer
	 */
	public String toString()
	{
		StringBuffer strOutput = new StringBuffer();
		for (int row = 0; row < 9; row++)
		{
			for (int column = 0; column < 9; column++)
			{
				strOutput.append(Integer.toString(grid[row][column]));				
			}
			strOutput.append("\n");
		}
		
		return strOutput.toString();
	}
	
}
