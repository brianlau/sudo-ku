package gui.element.policies;

/**
 * This class retains and sets the policies for an editable document object 
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
public class CellPolicy extends PlainDocument {
	
	private int max;
    
	public CellPolicy(int limit) {
        super();
        this.max = limit;
    }
	
    @Override
	public void insertString(int offset, String  str, AttributeSet attr) throws BadLocationException {
        if (str == null) return;
        
        if ((getLength() + str.length()) <= this.max) {
            super.insertString(offset, str, attr);
        }
    }
}