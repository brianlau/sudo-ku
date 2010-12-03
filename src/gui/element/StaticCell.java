package gui.element;

/**
 * This class instantiates a static (already given) cell for the Sudoku board.
 * 
 * @author Alvin Moradi with contributions from the "AWESOME SAUCE" team
 * @version 1.0
 *
 */

import gui.Config;

@SuppressWarnings("serial")
public class StaticCell extends Cell {
	
	/**
	 * Constructor is used to create a cell. Although not necessary for the current implementation
	 * of the game, the x and y locations become paramount once game implements dynamic actions.
	 * 
	 * @param x 	The 'X' location of this cell in the Sudoku grid.
	 * @param y 	The 'Y' location of this cell in the Sudoku grid.
	 * @param n 	The number value to initiate this cells text with.
	 * @param box 	The imaginary box within which this cell will be located.
	 * @param lines The imaginary lines that intersect through this cell.
	 * 
	 */
	public StaticCell(int x, int y, int n, Box box, Line[] lines){
		this.gridx = x;
		this.gridy = y;
		this.parentBox = box;
		this.parentLines = lines;
		this.color = Config.CELL_FONT_COLOR_ASSISTED;
		
		this.editable = false;
		this.focusable= false;
	
		initElement();
		setValue(n);
	}
}