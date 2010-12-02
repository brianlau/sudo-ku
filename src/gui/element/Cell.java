package gui.element;

/**
 * This class provides an abstract celltype for creation of static and editable
 * cells within the Sudoku game.
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
	
	public void setCorrect(boolean flag) {
		if(editable) setForeground(flag? Config.CELL_FONT_COLOR_CORRECT: Config.CELL_FONT_COLOR_INCORRECT);
	}
		
	public void setHighlighted(boolean flag) {
		setBackground(flag? Config.CELL_HIGHLIGHTED_COLOR: bgColor);
	}
	
	public void setShaded(boolean flag) {
		setBackground(flag? Config.CELL_SHADED_COLOR: Config.CELL_REGULAR_COLOR);
	}
	
	public void setHighlightedDynamics(boolean flag) {
		parentBox.setHighlighted(flag);		
		parentLines[0].setHighlighted(flag);
		parentLines[1].setHighlighted(flag);
	}
	
	public void setShadedDynamics(boolean flag) {
		parentBox.setShaded(flag);
		parentLines[0].setShaded(flag);
		parentLines[1].setShaded(flag);
	}
	
	public void setValue(int n) {
		setText(n==0? "": n + "");
	}

	public int getLocation(char direction) {
		return direction=='y'? gridy: gridx;
	}
	
	public int getValue() {
		return getText().length() > 0? Integer.parseInt(getText()): 0;
	}
	
	public void reset() {		
		if(editable) setValue(0);
	}
	
	public void clear() {		
		setValue(0);		
	}
}
