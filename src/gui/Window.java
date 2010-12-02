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
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class Window extends JFrame implements MouseMotionListener {
	
	public static Grid grid;
	
	/**
	 * @param title The title of the Window or application.
	 * @param data The randomized set of numbers to use for populating the board grid.
	 */
	public Window(String title, int[][] allNumbers, int[][] assistedNumbers) {
		
		grid = new Grid(allNumbers, assistedNumbers);
		
		this.setTitle(title);
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setPreferredSize(new Dimension(816,574));
		this.setCursor(Cursor.HAND_CURSOR);
				
		this.setContentPane( grid ); 
		
	}
	
	public void mouseMoved(MouseEvent e) {
		
	}
	
	public void mouseDragged(MouseEvent e) {}
}