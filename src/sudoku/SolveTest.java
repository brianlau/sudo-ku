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

	@Test
	public void testValidGrid() {
		Solve grid = new Solve();
		int[][] gridGood = { { 4, 8, 3, 7, 6, 9, 2, 1, 5 },
				{ 5, 1, 6, 8, 2, 3, 4, 9, 7 }, { 7, 9, 2, 5, 1, 4, 6, 8, 3 },
				{ 6, 4, 8, 3, 0, 2, 5, 7, 1 }, { 1, 5, 9, 4, 8, 7, 3, 6, 2, },
				{ 3, 2, 7, 1, 5, 6, 9, 4, 8 }, { 2, 3, 4, 6, 7, 8, 1, 5, 9 },
				{ 8, 6, 1, 9, 3, 5, 7, 2, 4 }, { 9, 7, 5, 2, 0, 1, 8, 3, 6 } };
		assertTrue(grid.solve(9, gridGood) > 0);	
	}
	
	@Test
	public void testImposibleWithLessThan17Elements(){
		Solve grid = new Solve();
		int[][] gridNotSoGood = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0}, { 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0}, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 6, 1, 9, 3, 5, 7, 2, 4 }, { 9, 7, 5, 2, 0, 1, 8, 3, 6 } };
		assertEquals(0,grid.solve(9, gridNotSoGood));	
	}
	

}
