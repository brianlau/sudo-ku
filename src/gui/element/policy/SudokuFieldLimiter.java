package gui.element.policy;

/**
 * This class retains and sets the policies for an editable field 
 * such as a cell for the Sudoku game.
 * 
 * @author Alvin Moradi with contributions from the "AWESOME SAUCE" team
 * @version 1.0
 *
 */

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

@SuppressWarnings("serial")
public class SudokuFieldLimiter extends PlainDocument {
	
	private int max = 1;
    
	public SudokuFieldLimiter() {
        super();
    }
	
    @Override
	public void insertString(int offset, String  str, AttributeSet attr) throws BadLocationException {
        if (str == null) return;
        
        if ((getLength() + str.length()) <= this.max) {
            super.insertString(offset, str, attr);
        }
    }
}