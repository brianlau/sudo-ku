package sudoku;

//import java.io.*;
import java.io.IOException;
import jline.ConsoleReader;

public class App {

	public static void main(String[] args)
	{
		
		//Before the main game loop begins, create the game board.
		GameGrid grid = new GameGrid(9);
		
		//Display some text on the screen welcoming the user,
		//I decided to hard-code it into the file, because with everyone on
		//differant OS's, it would be a pain right now to read from a file.
		//TARGET CONSOLE SIZE IS 80x24
		System.out.println("\n\n\n");
		System.out.println("                            SUDO-KU                             ");
		System.out.println("                        ---------------                         ");
		System.out.println("                     By Team Awesome Sauce                      ");
		System.out.println("                         -Chadd Ingersoll                       ");
		System.out.println("                         -Brian Lau                             ");
		System.out.println("                         -Walter Trask                          ");
		System.out.println("                         -Alvin Muradi                          ");
		System.out.println("                        ---------------                         ");
		System.out.println("     Use the arrow keys to move around the grid and use the     ");
		System.out.println("     number keys to enter numbers in the game grid.             ");
		System.out.println("                        ---------------                         ");
		System.out.println("\n\n\n\n\n\n\n");
		System.out.println("Please press the Enter key to begin...                          ");
		
		//Wait for the user to hit any key before entering the game loop
		try {
	        ConsoleReader reader;
	        reader = new ConsoleReader();
	        String line;
	        line = Integer.toString(reader.readVirtualKey());
	    }
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}

		//The game loop will continue to run as long as bolRunning
		//is set to true
		boolean bolRunning = true;
		
		//Game loop
		do {
			//At the beginning of each lop, clear the terminal and redraw the board
			final String ESC = "\033[";
			System.out.print(ESC + "2J"); System.out.flush();
			System.out.print(grid.toString(true));
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("Use the arrow keys to move around, or the number keys to insert numbers");
			
			//get input from the user
			try {
		        ConsoleReader reader;
		        reader = new ConsoleReader();
		        Integer line;
		        line = reader.readVirtualKey();
		        switch (line) {
				case 2: //Left arrow
					//Decrease the selected number
					if (grid.selectedColumn == 0)
					{
						grid.selectedColumn = 8;
					}
					else
					{
						grid.selectedColumn--;
					}
					break;
				case 6: //Right arrow
					//Increase the selected number
					if (grid.selectedColumn == 8)
					{
						grid.selectedColumn = 0;
					}
					else
					{
						grid.selectedColumn++;
					}
					break;
				case 14: //Down arrow
					//Increase the selected row number
					if (grid.selectedRow == 8)
					{
						grid.selectedRow = 0;
					}
					else
					{
						grid.selectedRow++;
					}
					break;
				case 16: //Up arrow
					//decrease the selected row number
					if (grid.selectedRow == 0)
					{
						grid.selectedRow = 8;
					}
					else
					{
						grid.selectedRow--;
					}
					break;
				case 8: //backspace key
					//delete the number at currently selectd position
					grid.setNumber(0);
					break;
				case 48: //0 key
					grid.setNumber(0);
					break;
				case 49: //1 key
					grid.setNumber(1);
					break;
				case 50: //2 key
					grid.setNumber(2);
					break;
				case 51: //3 key
					grid.setNumber(3);
					break;
				case 52: //4 key
					grid.setNumber(4);
					break;
				case 53: //5 key
					grid.setNumber(5);
					break;
				case 54: //6 key
					grid.setNumber(6);
					break;
				case 55: //7 key
					grid.setNumber(7);
					break;
				case 56: //8 key
					grid.setNumber(8);
					break;
				case 57: //9 key
					grid.setNumber(9);
					break;
				case 113: //q key
					System.out.print(ESC + "2J"); System.out.flush();
					System.out.println("Thanks for playing!");
					bolRunning = false;
				case 81: //Q key
					System.out.print(ESC + "2J"); System.out.flush();
					System.out.println("Thanks for playing!");
					bolRunning = false;
					break;
				case 114: //r key
					//generate a new game board
					grid = new GameGrid(9);
					break;
				case 82: //R key
					//generate a new game board
					grid = new GameGrid(9);
					break;
				case 115: //s key
					grid.gridCopy();
					break;
				case 83: //S key
					grid.gridCopy();
					break;
				default:
					//Do nothing for keys we don't care about
					break;
				}
		        
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		} while (bolRunning);
		
		}
		

	}
