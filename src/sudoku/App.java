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
		//NOTE: target console size is 80x24
		AnsiConsole.systemInstall();
		System.out.println(ansi().eraseScreen());
		System.out.println(ansi().cursor(0,0));
		System.out.println( ansi().eraseScreen().bg(BLUE).render("@|white SUDO-KU -- By Team Awesome Sauce! |@"));
		GameGrid grid = new GameGrid();
		System.out.println(grid.toString(true));
		
		//TODO: Input method
		/*
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
		*/
	}

}
