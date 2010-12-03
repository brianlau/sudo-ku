package gui;

/**
 * The following static class is used to store configuration variables for the puzzle/game.
 * 
 * @author Alvin G. Moradi with contributions from team "Awesome Sauce" members.
 * @version 1.0
 * 
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class Config {    

	public static final String NAME = "CST-Sudoku";
    public static final String TITLE = "SUDOKU: By Team Awesome Sauce";
    public static final String VERSION = "Snow Lemer";
    
    public static final String ABOUT = 
        "<HTML>" + 
        "<B>"+NAME+"</B> "+VERSION+"</BR>" + 
        "<P><BR>Author: <FONT COLOR=\"#0000ff\">Team Awesome Sauce</FONT></P>" + 
        "<P><BR>Members: <FONT COLOR=\"#00aa00\">Walter Trask, Alvin Moradi, Brian Lau, William Agyepong</FONT></P>" + 
        "<P><BR>Web page: <FONT COLOR=\"#0000ff\">http://www.saucyteams.com/cst200goofs.php?nerdlevel=10</FONT></P>" +
        "<P><BR></P>Blooper's License.<BR> " +
        "A copy of this is included with your copy of "+NAME+" and can also be found at<BR> " +
        "<FONT COLOR=\"#0000ff\">http://www.opensource.org/licenses/non-existant.php</FONT>" + 
        "</HTML>";

    public static final Color CELL_FONT_COLOR_BASE = new Color(0,0,0);    
    public static final Color CELL_FONT_COLOR_ASSISTED = new Color(255,50,50);    
    public static final Color CELL_FONT_COLOR_CORRECT = new Color(25,200,25);    
    public static final Color CELL_FONT_COLOR_INCORRECT = CELL_FONT_COLOR_BASE;
      
    public final static Color CELL_REGULAR_COLOR = new Color(255,255,255);    
    public final static Color CELL_SHADED_COLOR = new Color(250,200,200);
    public static final Color CELL_HIGHLIGHTED_COLOR = new Color(Integer.parseInt( "DEDEDE",16));  

    public final static Font FONT_SML = new Font("Tahoma", Font.BOLD, 12);
    public final static Font FONT_MID = new Font("Tahoma", Font.BOLD, 28);
    public final static Font FONT_BIG = new Font("Tahoma", Font.BOLD, 40);

    public final static Border EMPTY_B = BorderFactory.createEmptyBorder(); 
    public final static Border RAISED_B = BorderFactory.createBevelBorder(BevelBorder.RAISED);
    public final static Border LOWERED_B = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
    public final static Border ETCHED_B = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
    
    public static boolean DYNAMIC_CORRECTION = true; 
    public static boolean DYNAMIC_HIGHLIGHT = true; 
    
}
