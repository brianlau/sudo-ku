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
	private JPanel settingsPanel;
	
	private Box[][] boxes;
	private Cell[][] cells;
	private Line[][] lines;
	private int[][] allNumbers, assistNumbers;
	
	private JButton newButton;
	private JButton restartButton;
	private JButton correctButton;
	private JButton clearButton;
	private JButton aboutButton;
	private JSeparator separator;
	private JSlider levelSlider;
	
	/**
	 * The constructor for calling this grid class.
	 * 
	 * @param assistNumbers A set of numbers for use with the creation of the Sudoku grid.
	 */
	public Display(int[][] allNumbers, int[][] assistNumbers) {
		this.allNumbers 	= allNumbers;
		this.assistNumbers	= assistNumbers;
		
		initGrid();
		initSettings();
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
	
	public void initSettings() {		
		newButton    = new JButton("New Game");
		correctButton= new JButton("Correct It!");
		restartButton= new JButton("Restart");
		clearButton  = new JButton("Clear Board");
		aboutButton  = new JButton("About");
		
		separator  = new JSeparator();
		levelSlider= new JSlider(0,100);
		
		this.settingsPanel = getSettingsPanel();
		
        this.add(settingsPanel);		
	}
	
	public JPanel getGridPanel() {
		int bx=-1, by=-1;
		
		JPanel panel  		= new JPanel();		
		GridBagConstraints c= new GridBagConstraints();
		GridLayout layout 	= new GridLayout(9,9);
				
		panel.setPreferredSize(new Dimension(540,540));
		panel.setBorder(Config.EMPTY_B);
        panel.setLayout(layout);
		
        for (int x = 0; x < 9; x++) {   
        	
        	if(x%3 == 0) bx++;        	
        	if(lines[0][x] == null) lines[0][x] = new Line(false);        	
        	by = -1;
        	
        	for (int y = 0; y < 9; y++) {  
	        	if(y%3 == 0) by++;   
	        		        
	        	if(boxes[bx][by] == null) boxes[bx][by] = new Box( (bx+by) % 2 == 0 );	        	
	        	if(lines[1][y] == null) lines[1][y] = new Line(false);
        		        	
	        	cells[x][y] = assistNumbers[x][y] == 0? new EditableCell(x,y, boxes[bx][by], new Line[]{lines[0][x], lines[1][y]}) : new StaticCell(x,y,assistNumbers[x][y], boxes[bx][by], new Line[]{lines[0][x], lines[1][y]});	        			
	        	cells[x][y].addFocusListener(this);
	    	        	
	        	boxes[bx][by].add(cells[x][y]);
	        	lines[0][x].add(cells[x][y]);
	        	lines[1][y].add(cells[x][y]);
	        	
	        	panel.add(cells[x][y], c);
			}
		}
        
		return panel;
	}
	
	public JPanel getSettingsPanel() {
		JPanel panel = new JPanel();		
		
		panel.setPreferredSize(new Dimension(260,540));
		panel.setBackground(Color.lightGray);
		panel.setBorder(Config.EMPTY_B);
		
		levelSlider.setBorder(new TitledBorder(Config.ETCHED_B, "Difficulty"));
		
		newButton.addActionListener(this);		
		correctButton.addActionListener(this);
		restartButton.addActionListener(this);
		clearButton.addActionListener(this);
		aboutButton.addActionListener(this);
		
//		panel.add(newButton);
		panel.add(correctButton);
		panel.add(restartButton);
		panel.add(clearButton);
		panel.add(aboutButton);
//		panel.add(separator);
//		panel.add(levelSlider);
		
		return panel;
	}
	
	public void focusGained(FocusEvent fe) {	
		EditableCell cell = ((EditableCell)fe.getSource());

		cell.setValue(0);
		cell.setSelected(true);
		
		if(Config.DYNAMIC_HIGHLIGHT)
			cell.setHighlightedDynamics(true);
	}
	
	public void focusLost(FocusEvent fe) {	
		EditableCell cell = ((EditableCell)fe.getSource());

		cell.setSelected(false);
		cell.setHighlightedDynamics(false);
		
		if(Config.DYNAMIC_CORRECTION)
			cell.setCorrect(cell.getValue() == allNumbers[cell.getLocation('x')][cell.getLocation('y')]);		
	}

	public void clearGrid() {
		for(Cell[] celli: cells) 
			for(Cell cellj: celli) {
				cellj.clear();
			}
	}
	
	public void restartGrid() {
		for(Cell[] celli: cells) 
			for(Cell cellj: celli) {
				cellj.reset();
			}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == newButton) {
			//Calculate a random grid
			GameGrid matrix = new GameGrid(9);
			
			allNumbers 	  = matrix.grid;
			assistNumbers = matrix.given;
	        
			clearGrid();

			this.cells = new Cell[9][9];
			this.boxes = new Box[3][3];
			this.lines = new Line[2][9];
			
			this.gridPanel = getGridPanel();
		}		

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