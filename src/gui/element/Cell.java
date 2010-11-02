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

import gui.element.policies.*;

@SuppressWarnings("serial")
public abstract class Cell extends JTextField {
	
	protected int gridx;
	protected int gridy;
	protected int value;
	
	protected Color color;
	protected boolean editable;
	
	protected void initElement() {
		//set cell font
		Font font = new Font("Verdana", Font.PLAIN, 60);
		this.setFont(font);
		
		//adjust common cell attributes
		this.setDocument(new CellPolicy(1));
		this.setBackground(Color.white);
		this.setHorizontalAlignment(JTextField.CENTER);
		this.setBackground(Color.white);
		
		//adjust unique cell attributes
		this.setEditable(editable);
		this.setForeground(color);	
		
		if(value > 0) 
			this.setText(value + "");
	}
	
	/**
	 * Tags the value currently displayed in this cell. Not really used unless an implementation
	 * of listeners becomes necessary.
	 * 
	 * @param data A 2D array of elements for use in randomly populating the Sudoky grid.
	 */
	protected void setValue(int data) {
		this.value = data;
		
	}
	
	/**
	 * Returns the value currently stored for this cell. Not really used unless an implementation
	 * of listeners becomes necessary.
	 * 
	 */
	protected int getValue() {
		return this.value;
	}
}
