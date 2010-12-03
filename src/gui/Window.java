package gui;

/**
 * This class instantiates a graphical window or container frame for displaying
 * java graphical buttons.
 * 
 * @author Alvin Moradi with contributions from the "AWESOME SAUCE" team
 * @version 1.0
 *
 */

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Window extends JFrame {
	
	public static Display grid;
	
	/**
	 * Constructor for initializing a new window frame within which to display components of this game;
	 * 
	 * @param title The title of the Window or application.
	 * @param allNumbers The randomized set of numbers to use for populating the board grid.
	 * @param assistedNumbers A random subset of numbers derived from 'allNumbers'
	 */
	public Window(String title, int[][] allNumbers, int[][] assistedNumbers) {
		
		grid = new Display(allNumbers, assistedNumbers);
		
		this.setTitle(title);
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setPreferredSize(new Dimension(816,574));
		this.setCursor(Cursor.HAND_CURSOR);
		
		this.setContentPane( grid ); 
		
	}
}