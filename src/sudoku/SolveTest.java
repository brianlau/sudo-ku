package sudoku;

import static org.junit.Assert.*;

import org.junit.Test;

public class SolveTest {
//In order to use Solve.java, the grid to test must have at least one zero
//else it will just return the difficulty, which default is one.
	
@Test
public void testInvalidGrid() {
Solve grid1 = new Solve();
int[][] grid = { { 4, 8, 3, 7, 6, 9, 2, 1, 5 },
{ 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
{ 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
{ 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
{ 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 1, 2, 3, 4, 5, 6, 7, 8, 0 } };
assertEquals(0, grid1.solve(9, grid));
}

/**
 * 
 * @author Walter
 * Creates an instance of the Solve Class.
 * Creates an instance of the GameGrid Class.
 * Uses assertTrue to test to see if the GameGrid Class
 * is generating a valid sudoku grid.
 */
@Test
public void testGameGrid() {
Solve grid1 = new Solve();
GameGrid grid = new GameGrid(9);
assertTrue(0< grid1.solve(9, grid.grid));

}
}

