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
public class CellObserver extends PlainDocument {
	
	private final int MAX_FIELD_LENGTH = 1;
    
	public CellObserver() {
        super();
    }
	
    @Override
	public void insertString(int offset, String  str, AttributeSet attr) throws BadLocationException {
        
//    	try {
	    	if(str == null || !(49<=str.charAt(0) && str.charAt(0)<=57 && str.charAt(0)!='0') ) {
	    		return;
//	        	throw new RestrictionError();
	        } else {
		        if ((getLength() + str.length()) <= this.MAX_FIELD_LENGTH) {
		            	super.insertString(offset, str, attr);
		        } 
	        }
//    	} catch(RestrictionError error) {
//    		
//    	}
    	
    }
}