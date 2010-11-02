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
public class SudokuFieldRestrictor extends PlainDocument {
	
	private int max = 1;
    
	public SudokuFieldRestrictor() {
        super();
    }
	
    @Override
	public void insertString(int offset, String  str, AttributeSet attr) throws BadLocationException {
        
//    	try {
	    	if(str == null || !(49<=str.charAt(0) && str.charAt(0)<=57) ) {
	    		return;
//	        	throw new RestrictionError();
	        } else {
		        if ((getLength() + str.length()) <= this.max) {
		            	super.insertString(offset, str, attr);
		        }
	        }
//    	} catch(RestrictionError error) {
//    		
//    	}
    	
    }
}