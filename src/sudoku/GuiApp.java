package sudoku;

/**
 * This program initiates an optional graphical user interface for the Sudoku game.
 * 
 * @author Alvin Moradi with contributions from the "TEAM AWESOME SAUCE"
 * @version 1.0
 *
 */

import gui.Window;
import javax.swing.JFrame;

public class GuiApp {
	
	/**
	 * Call, create and show the GUI window. 
	 * 
	 */
	private static void createAndShowGUI() {
        //Calculate a random grid
		GameGrid numbers = new GameGrid(9);
		
		//Create and set up the window.
        Window window = new Window("Sudoku", numbers.given);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Display the window.
        window.pack();
        window.setVisible(true);
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
