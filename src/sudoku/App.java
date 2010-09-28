package sudoku;

//import java.io.*;
import java.io.IOException;
import jline.ANSIBuffer;
import jline.ConsoleReader;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class App {

	public static void main(String[] args)
	{
		
		GameGrid grid = new GameGrid();
		System.out.println(grid.toString(false));
	}

}
