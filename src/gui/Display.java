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
import javax.swing.border.TitledBorder;

import sudoku.GameGrid;

import java.awt.event.*;

import gui.element.Box;
import gui.element.Cell;
import gui.element.Line;
import gui.element.EditableCell;
import gui.element.StaticCell;

@SuppressWarnings("serial")
public class Display extends JPanel implements FocusListener, ActionListener {

	private JPanel gridPanel; 
	private JPanel optionsPanel;
	
	private Box[][] boxes;
	private Cell[][] cells;
	private Line[][] lines;
	private int[][] allNumbers, assistNumbers;
	
	private JButton restartButton;
	private JButton correctButton;
	private JButton clearButton;
	private JButton aboutButton;
	
	/**
	 * The constructor for calling this grid class.
	 * 
	 * @param allNumbers The set of numbers to represent the complete Sudoku grid.
	 * @param assistNumbers A set of numbers for use with the creation of the Sudoku grid.
	 */
	public Display(int[][] allNumbers, int[][] assistNumbers) {
		this.allNumbers 	= allNumbers;
		this.assistNumbers	= assistNumbers;
		
		initGrid();
		initOptions();
	}
	
	/**
	 * Initiate all of the components required for drafting a proper Sudoku board.
	 */
	public void initGrid() {		
		this.cells = new Cell[9][9];
		this.boxes = new Box[3][3];
		this.lines = new Line[2][9];
		
		this.gridPanel = getGridPanel();
		
		this.add(gridPanel);
	}
	
	/**
	 * Initiate miscellaneous components to complete our sudoku game window with an options panel.
	 */
	public void initOptions() {		
		correctButton= new JButton("Correct It!");
		restartButton= new JButton("Restart");
		clearButton  = new JButton("Clear Board");
		aboutButton  = new JButton("About");
		
		this.optionsPanel = getOptionsPanel();
		
        this.add(optionsPanel);		
	}
	
	/**
	 * The following returns a reference to an extrapolated panel representing the entire set of grid board cells;
	 * 
	 * @return A reference to the cell container panel that encompasses all cells;
	 */
	public JPanel getGridPanel() {
		int bx=-1, by=-1;
		
		JPanel panel  		= new JPanel();		
		GridBagConstraints c= new GridBagConstraints();
		GridLayout layout 	= new GridLayout(9,9);
				
		panel.setPreferredSize(new Dimension(540,540));
		panel.setBorder(Config.EMPTY_B);
        panel.setLayout(layout);
		
        //the following algorithm/loop applies static attributes to cells != 0 (meaning assisted REDs)
        //while passing ownership references according to dynamics needed to remain unique
        //with respect to applicable cell.
        //
        //[bx,by] grow from [0,0] to [2,2] in order to store 3x3 boxes while lines grow to [{x,y}][9], 
        //i.e. 18 lines total
        for (int x = 0; x < 9; x++) {   
        	
        	if(x%3 == 0) bx++;        	
        	if(lines[0][x] == null) lines[0][x] = new Line(false);        	
        	by = -1;
        	
        	for (int y = 0; y < 9; y++) {  
	        	if(y%3 == 0) by++;   
	        		        
	        	//(bx+by) % 2 == 0 >>> Calculates alternate boxes for background shading purposes
	        	if(boxes[bx][by] == null) boxes[bx][by] = new Box( (bx+by) % 2 == 0 );	        	
	        	if(lines[1][y] == null) lines[1][y] = new Line(false);
        		
	        	cells[x][y] = assistNumbers[x][y] == 0? new EditableCell(x,y, boxes[bx][by], new Line[]{lines[0][x], lines[1][y]}) : new StaticCell(x,y,assistNumbers[x][y], boxes[bx][by], new Line[]{lines[0][x], lines[1][y]});	        			
	        	cells[x][y].addFocusListener(this);
	    	    
	        	//pass reference to parentBoxes AND parentLines, accordingly
	        	boxes[bx][by].add(cells[x][y]);
	        	lines[0][x].add(cells[x][y]);
	        	lines[1][y].add(cells[x][y]);
	        	
	        	panel.add(cells[x][y], c);
			}
		}        
		return panel;
	}
	
	/**
	 * This function creates and returns a reference to a panel with miscellaneous option buttons for controlling the game.
	 * 
	 * @return The reference to an options panel;
	 */
	public JPanel getOptionsPanel() {
		JPanel panel = new JPanel();		
		
		panel.setPreferredSize(new Dimension(260,540));
		panel.setBackground(Color.lightGray);
		panel.setBorder(Config.EMPTY_B);
				
		correctButton.addActionListener(this);
		restartButton.addActionListener(this);
		clearButton.addActionListener(this);
		aboutButton.addActionListener(this);
		
		panel.add(correctButton);
		panel.add(restartButton);
		panel.add(clearButton);
		panel.add(aboutButton);
		
		return panel;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
	 */
	public void focusGained(FocusEvent fe) {	
		EditableCell cell = ((EditableCell)fe.getSource());

		cell.setValue(0);
		cell.setSelected(true);
		
		//should the cell boxes AND lines highlight dynamically
		if(Config.DYNAMIC_HIGHLIGHT)
			cell.setHighlightedDynamics(true);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
	 */
	public void focusLost(FocusEvent fe) {	
		EditableCell cell = ((EditableCell)fe.getSource());

		cell.setSelected(false);
		cell.setHighlightedDynamics(false);
		
		//should the cell boxes AND lines highlight dynamically
		if(Config.DYNAMIC_CORRECTION)
			cell.setCorrect(cell.getValue() == allNumbers[cell.getLocation('x')][cell.getLocation('y')]);
	}

	/**
	 * Used to clear out the entire Sudoku board. Incomplete though additional functionality was not required as per the project.
	 */
	public void clearGrid() {
		for(Cell[] celli: cells) 
			for(Cell cellj: celli) {
				cellj.clear();
			}
	}
	
	/**
	 * Used to clear out the entire Sudoku board. Incomplete though additional functionality was not required as per the project.
	 */
	public void restartGrid() {
		for(Cell[] celli: cells) 
			for(Cell cellj: celli) {
				cellj.reset();
			}
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {		

		if(e.getSource() == correctButton) {
			for(Cell[] celli: cells) 
				for(Cell cellj: celli) {
					cellj.setCorrect(cellj.getValue() == allNumbers[cellj.getLocation('x')][cellj.getLocation('y')]);	
				}				
		}
		
		if(e.getSource() == restartButton) {
			restartGrid();
		}

		if(e.getSource() == clearButton) {
			clearGrid();
		}

		if(e.getSource() == aboutButton) {
			JOptionPane.showMessageDialog(this,Config.ABOUT, 
	                "About", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
}