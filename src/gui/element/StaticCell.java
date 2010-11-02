package gui.element;

/**
 * This class instantiates a static (already given) cell for the Sudoku board.
 * 
 * @author Alvin Moradi with contributions from the "AWESOME SAUCE" team
 * @version 1.0
 *
 */

import java.awt.*;

@SuppressWarnings("serial")
public class StaticCell extends Cell {
	
	/**
	 * Constructor is used to create a cell. Although not necessary for the current implementation
	 * of the game, the x and y locations become paramount once game implements dynamic actions.
	 * 
	 * @param x The 'X' location of this cell in the Sudoku grid.
	 * @param y The 'Y' location of this cell in the Sudoku grid.
	 * @param n The number value to initiate this cells text with.
	 */
	public StaticCell(int x, int y, int n){
		this.gridx = x;
		this.gridy = y;
		this.value = n;
		this.color = Color.RED;
		this.editable = false;
	
		initElement();
	}
}