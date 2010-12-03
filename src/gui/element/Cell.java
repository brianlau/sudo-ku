package gui.element;

/**
 * This class provides an abstract celltype for creation of static and editable
 * cells within the Sudoku puzzle.
 * 
 * @author Alvin Moradi with contributions from the "AWESOME SAUCE" team
 * @version 1.0
 *
 */

import java.awt.*;
import javax.swing.*;
import gui.Config;
import gui.element.policy.CellObserver;

@SuppressWarnings("serial")
public abstract class Cell extends JTextField {
	
	protected Color color, bgColor;
	protected int gridx, gridy;
	protected boolean editable, focusable;
	protected Box parentBox;
	protected Line parentLines[];
	
	/**
	 * In order to use this Cell object, it has to be initialized with a call to this function
	 * 
	 */
	public void initElement() {
		bgColor = parentBox.isShaded()? Config.CELL_SHADED_COLOR: Config.CELL_REGULAR_COLOR;
		
		//adjust common cell attributes
		this.setDocument(new CellObserver());
		this.setHorizontalAlignment(JTextField.CENTER);
		this.setFont(Config.FONT_BIG);
		
		//adjust dynamic cell attributes
		this.setFocusable(focusable);
		this.setForeground(color);
		this.setEditable(editable);
		this.setBorder(Config.RAISED_B);
		this.setBackground(bgColor);
		this.setCaretColor(this.getBackground());
	}
			
	/**
	 * By setting this value, one may highlight this entire cell.
	 * 
	 * @param flag Boolean flag to impose if highlighting is needed
	 */
	public void setHighlighted(boolean flag) {
		setBackground(flag? Config.CELL_HIGHLIGHTED_COLOR: bgColor);
	}
	
	/**
	 * Used when calculated for, in order to darken the background of a (and all) cells if within every alternate 3x3 box
	 * 
	 * @param flag Boolean flag for use to impose a darker background shade
	 */
	public void setShaded(boolean flag) {
		setBackground(flag? Config.CELL_SHADED_COLOR: Config.CELL_REGULAR_COLOR);
	}
	
	/**
	 * Used to highlight the entire 3x3 parent box AND additional inline cells of current cell.
	 * 
	 * @param flag Boolean to indicate highlighted intention
	 */
	public void setHighlightedDynamics(boolean flag) {
		parentBox.setHighlighted(flag);		
		parentLines[0].setHighlighted(flag);
		parentLines[1].setHighlighted(flag);
	}
	
	/**
	 * Used to darken the entire 3x3 parent box AND additional inline cells of current cell.
	 * 
	 * @param flag Boolean to indicate shaded intention
	 */
	@Deprecated
	public void setShadedDynamics(boolean flag) {
		parentBox.setShaded(flag);
		parentLines[0].setShaded(flag);
		parentLines[1].setShaded(flag);
	}
	
	/**
	 * Setter function for the cell text.
	 * 
	 * @param n The integer to be stored
	 */
	public void setValue(int n) {
		setText(n==0? "": n + "");
	}
	
	/**
	 * Used to set and maintain a concept of correct guess for this square.
	 * 
	 * @param flag Boolean flag to signify a correct guess
	 */
	public void setCorrect(boolean flag) {
		if(editable) setForeground(flag? Config.CELL_FONT_COLOR_CORRECT: Config.CELL_FONT_COLOR_INCORRECT);
	}
	
	/**
	 * Use this function to set 
	 * 
	 * @param flag Boolean used to impose a selected state on this Cell;
	 */
	public void setSelected(boolean flag) {
		if(editable) setBorder(flag? Config.LOWERED_B: Config.RAISED_B);
	}

	/**
	 * Getter function for the X and Y position in the 9x9 game grid.
	 * 
	 * @param axis The axis to retrieve location for
	 * @return The location along provided axis
	 */
	public int getLocation(char axis) {
		return axis=='y'? gridy: gridx;
	}
	
	/**
	 * Getter for the value stored in this cell
	 * 
	 * @return The Integer value stored in this cell
	 */
	public int getValue() {
		return getText().length() > 0? Integer.parseInt(getText()): 0;
	}
	
	/**
	 * Aims to reset all of the editable cells, e.g. set to 0
	 * 
	 */
	public void reset() {		
		if(editable) setValue(0);
	}
	
	/**
	 * Aims to clear all of the cells, e.g. set to 0
	 * 
	 */
	public void clear() {		
		setValue(0);		
	}
}
