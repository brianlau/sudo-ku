package gui.element;

/**
* This class instantiates an editable cell for the Sudoku board.
 * 
 * @author Alvin Moradi with contributions from the "AWESOME SAUCE" team
 * @version 1.0
 *
 */

import java.awt.*;

@SuppressWarnings("serial")
public class EditableCell extends Cell{

	/**
	 * Constructor is used to create a cell. Although not necessary for the current implementation
	 * of the game, the x and y locations become paramount once game implements dynamic actions.
	 * 
	 * @param x The 'X' location of this cell in the Sudoku grid.
	 * @param y The 'Y' location of this cell in the Sudoku grid.
	 */
	public EditableCell(int x, int y){
		this.gridx = x;
		this.gridy = y;
		this.value = 0;
		this.color = Color.BLACK;
		this.editable = true;
	
		initElement();
	}
}
