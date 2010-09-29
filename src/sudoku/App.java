package sudoku;

//import java.io.*;
import java.io.IOException;
import jline.ANSIBuffer;
import jline.ConsoleReader;

public class App {

	public static void main(String[] args)
	{
		//AnsiConsole.systemInstall();
		GameGrid grid = new GameGrid();
		System.out.println(grid.toString(true));
		
		
	}

}
