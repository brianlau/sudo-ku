package gui;

/**
 * This class creates and provides a panel on which a Sudoku alike grid is shown. It initiates
 * this grid using a parameter of values provided through the constructor.
 * 
 * @author Alvin Moradi with contributions from the "AWESOME SAUCE" team
 * @version 1.0
 *
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import gui.element.Box;
import gui.element.Cell;
import gui.element.Line;
//import gui.element.Cross;
import gui.element.EditableCell;
import gui.element.StaticCell;

@SuppressWarnings("serial")
public class Grid extends JPanel implements FocusListener {

	private Box[][] boxes;
	private Cell[][] cells;
	private Line[][] lines;
	private int[][] allNumbers, assistedNumbers;
	
	/**
	 * The constructor for calling this grid class.
	 * 
	 * @param assistedNumbers A set of numbers for use with the creation of the Sudoku grid.
	 */
	public Grid(int[][] allNumbers, int[][] assistedNumbers) {
		this.allNumbers 	= allNumbers;
		this.assistedNumbers= assistedNumbers;
		this.cells 			= new Cell[9][9];
		this.boxes			= new Box[3][3];
		this.lines			= new Line[2][9];
		
		initComponent();
	}
	
	/**
	 * Initiate all of the components required for drafting a proper Sudoku board.
	 */
	public void initComponent() {	
		
		this.add(getGrid());
        this.add(getConfig());
	}
	
	public JPanel getGrid() {
		int bx=-1, by=-1, lx=0, ly=0;
		
		JPanel panel  		= new JPanel();		
		GridBagConstraints c= new GridBagConstraints();
		GridLayout layout 	= new GridLayout(9,9);
				
		panel.setPreferredSize(new Dimension(540,540));
		panel.setBorder(Config.EMPTY_B);
        panel.setLayout(layout);
		
        for (int x = 0; x < 9; x++) {   
        	
        	if(x%3 == 0) bx++;
        	
        	if(lines[0][x] == null) 
        		lines[0][x] = new Line(false);
        	
        	by = -1;
        	
        	for (int y = 0; y < 9; y++) {  
	        	if(y%3 == 0) by++;   
	        		        
	        	if(boxes[bx][by] == null) 
	        		boxes[bx][by] = new Box( (bx+by) % 2 == 0 );
	        	
	        	if(lines[1][y] == null) 
	        		lines[1][y] = new Line(false);
        		        	
	        	cells[x][y] = assistedNumbers[x][y] == 0? 
	        			new EditableCell(x,y, boxes[bx][by], new Line[]{lines[0][x], lines[1][y]}) : 
	        			new StaticCell(x,y,assistedNumbers[x][y], boxes[bx][by], new Line[]{lines[0][x], lines[1][y]});	   
	        	

//	    	    System.out.println(0 + ":" + x + " & " + 1 + ":" + y);
	        			
	        	cells[x][y].addFocusListener(this);
	    	        	
	        	boxes[bx][by].add(cells[x][y]);
	        	lines[0][x].add(cells[x][y]);
	        	lines[1][y].add(cells[x][y]);
	        	
	        	panel.add(cells[x][y], c);
			}
        	System.out.println("------");
		}
        
		return panel;
	}
	
	public JPanel getConfig() {
		JPanel panel = new JPanel();		
		
		panel.setPreferredSize(new Dimension(260,540));
		panel.setBackground(Color.lightGray);
		panel.setBorder(Config.EMPTY_B);
		
		return panel;
	}
	
	public void focusGained(FocusEvent fe) {	
		EditableCell cell = ((EditableCell)fe.getSource());

		cell.setValue(0);
		cell.setSelected(true);
		cell.setHighlightedDynamics(true);
	}
	
	public void focusLost(FocusEvent fe) {	
		EditableCell cell = ((EditableCell)fe.getSource());

		cell.setSelected(false);
		cell.setHighlightedDynamics(false);
		cell.setCorrect(cell.getValue() == allNumbers[cell.getLocation('x')][cell.getLocation('y')]);		
	}
}