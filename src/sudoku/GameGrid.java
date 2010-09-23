package sudoku;

public class GameGrid {

	/**
	 * A two dimensional string array that contains the
	 * 9x9 sudoku game grid
	 */
	protected Integer[][] grid = new Integer[9][9];
	
	public GameGrid()
	{
		for (int row = 0; row < 9; row++)
		{
			for (int column = 0; column < 9; column++)
			{
				grid[row][column] = 1;
			}
		}
	}
	
	public String toString()
	{
		String output = "";
		for (int row = 0; row < 9; row++)
		{
			for (int column = 0; column < 9; column++)
			{
				output = output + Integer.toString(grid[row][column]);
				
				
			}
			output = output + "\n";
		}
		
		return output;
	}
	
}
