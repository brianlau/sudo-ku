package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class Config {
    
    public static final String NAME = "Java Sudoku ";
    
    public static final String VERSION = "1.0.1";
    
    public static final String ABOUT = 
        "<HTML>" + 
        "<B>"+NAME+"</B> ver. "+VERSION+"</BR>" + 
        "<P><BR>Author: <FONT COLOR=\"#0000ff\">Samantha Yen</FONT></P>" + 
        "<P><BR>Web page: <FONT COLOR=\"#0000ff\">http://playsudoku.sourceforge.net</FONT></P>" +
        "<P><BR></P>This program is released under the GNU General Public License.<BR> " +
        "A copy of this is included with your copy of "+NAME+" and can also be found at<BR> " +
        "<FONT COLOR=\"#0000ff\">http://www.opensource.org/licenses/gpl-license.php</FONT>" + 
        "</HTML>";

    public static final Color BORDER_COLOR = new Color(184,207,229);
    
    public static final Color CELL_FONT_COLOR_BASE = new Color(0,0,0);
    
    public static final Color CELL_FONT_COLOR_ASSISTED = new Color(255,50,50);
    
    public static final Color CELL_FONT_COLOR_CORRECT = new Color(25,200,25);
    
    public static final Color CELL_FONT_COLOR_INCORRECT = CELL_FONT_COLOR_BASE;
    
    public static final Color CELL_HIGHLIGHTED_COLOR = new Color(Integer.parseInt( "DEDEDE",16));
    
    public final static Color CELL_REGULAR_COLOR = new Color(255,255,255);
    
    public final static Color CELL_SHADED_COLOR = new Color(230,230,255);

    public static final String IMG_FOLDER = "/res/img/";
    public static final String TITLE = "SUDOKU: By Team Awesome Sauce";
    
    public final static Font FONT_BIG = new Font("Tahoma", Font.BOLD, 40);
    public final static Font FONT_MID = new Font("Tahoma", Font.BOLD, 28);
    public final static Font FONT_SML = new Font("Tahoma", Font.BOLD, 12);

    public final static Border RAISED_B = BorderFactory.createBevelBorder(BevelBorder.RAISED);
    public final static Border LOWERED_B = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
    public final static Border EMPTY_B = BorderFactory.createEmptyBorder(); 
    
    public static enum NumberEntry {sctn, sntc};
    
    public final static int DIFF_EASY = 34;
    public final static int DIFF_NORMAL = 30;
    public static final int DIFF_HARD = 26;
    
    public enum NumDistributuon {evenlyDistributedNumbers, evenlyFilled3x3Square3, random};
    
    public final static String ERROR_LOAD= "Sudoku could not be loaded!";
    
    public final static String ERROR_SAVE= "Sudoku has not been saved!";
    
    public final static String INFO_SOLVED = "Sudoku is already solved.";
    
    //LOOK AND FEEL
    public enum LAF{METAL, SYSTEM};

    public static boolean isWin = false;
    
    public final static String METAL = "javax.swing.plaf.metal.MetalLookAndFeel";

    public final static String SYSTEM = UIManager.getSystemLookAndFeelClassName();
    
    //public final static String MOTIF = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
    //public final static String WIN = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
    //public final static String GTK = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel"; 
    
}
