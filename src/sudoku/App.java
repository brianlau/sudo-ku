package sudoku;

//import java.io.*;
import java.io.IOException;
import jline.ANSIBuffer;
import jline.ConsoleReader;

public class App {

	public static void main(String[] args)
	{
		String redOnBlack = ANSIBuffer.ANSICodes.attrib(31) + ANSIBuffer.ANSICodes.attrib(40);
		String reset = ANSIBuffer.ANSICodes.attrib(0);
		System.out.print(redOnBlack + "Danger, Will Robinson!" + reset);
		GameGrid grid = new GameGrid();
		System.out.println(grid.toString());
		
		try {
        ConsoleReader reader;
		reader = new ConsoleReader();
        System.out.println("STA");
        String line;
        line = Integer.toString(reader.readVirtualKey());
        System.out.println("STO");
        System.out.println(line);
        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
