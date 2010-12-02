package gui.element;

/**
* This class instantiates an editable cell for the Sudoku board.
 * 
 * @author Alvin Moradi with contributions from the "AWESOME SAUCE" team
 * @version 1.0
 *
 */

import gui.Config;

@SuppressWarnings("serial")
public class EditableCell extends Cell {

	public boolean isSelected;
	public boolean isCorrect;
	
	/**
	 * Constructor is used to create a cell. Although not necessary for the current implementation
	 * of the game, the x and y locations become paramount once game implements dynamic actions.
	 * 
	 * @param x The 'X' location of this cell in the Sudoku grid.
	 * @param y The 'Y' location of this cell in the Sudoku grid.
	 */
	public EditableCell(int x, int y, Box box, Line[] lines) {
		this.gridx = x;
		this.gridy = y;
		this.parentBox = box;
		this.parentLines = lines;
		this.color = Config.CELL_FONT_COLOR_BASE;
		
		this.editable 	= true;	
		this.focusable 	= true;
		this.isSelected = false;
		this.isCorrect	= false;
		
		initElement();
	}
	
	public void setSelected(boolean flag) {
		isSelected = flag;		
		setBorder(flag? Config.LOWERED_B: Config.RAISED_B);
	}
		
	public void setCorrect(boolean flag) {
		isCorrect = flag;		
		setForeground(flag? Config.CELL_FONT_COLOR_CORRECT: Config.CELL_FONT_COLOR_INCORRECT);		
	}
}
