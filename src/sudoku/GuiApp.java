package sudoku;

/**
 * This program initiates an optional graphical user interface for the Sudoku game.
 * 
 * @author Alvin Moradi with contributions from the "TEAM AWESOME SAUCE"
 * @version 1.0
 *
 */

import gui.Config;
import gui.Window;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GuiApp {
	
	/**
	 * Call, create and show the GUI window. 
	 * 
	 */
	private static void createAndShowGUI() {
        //Calculate a random grid
		GameGrid numbers = new GameGrid(9);
		int[][] matrix = new int[9][9];
			
		String str = JOptionPane.showInputDialog(null, "Please enter difficulty level [retard,cool,avengers]", "The MONSTEROUS input box... yours truly :^)", 1);
				
		switch(str.charAt(0)) {
			case 'a':
				Config.DYNAMIC_HIGHLIGHT = false;
				Config.DYNAMIC_CORRECTION = false;
				
				matrix = numbers.given;
				break;
			case 'c':
				Config.DYNAMIC_HIGHLIGHT = true;
				Config.DYNAMIC_CORRECTION = false;
				
				matrix = numbers.normalGiven;
				break;
			default:
				Config.DYNAMIC_HIGHLIGHT = true;
				Config.DYNAMIC_CORRECTION = true;
				
				matrix = numbers.hardGiven;
				break;
		}
		
		//Create and set up the window.
        Window display = new Window(Config.TITLE, numbers.grid, matrix);
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Display the window.
        display.pack();
        display.setVisible(true);        
        
	}	
	
    /**
     * This is the driver for the entire application, while using the GUI 
     * as a interactive display rather than console.
     * 
     * @param args An array of arguments from the command line.
     */
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
			public void run() {
                createAndShowGUI();
            }
        });
    }
}
