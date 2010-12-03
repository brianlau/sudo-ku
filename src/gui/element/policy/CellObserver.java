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
	
	//identifies the longest string allowable in a Cell
	private final int MAX_FIELD_LENGTH = 1;
    
	/**
	 * Constructor for building an object of this class; meant for restricting the text fields inputs.
	 */
	public CellObserver() {
        super();
    }
	
    /* (non-Javadoc)
     * @see javax.swing.text.PlainDocument#insertString(int, java.lang.String, javax.swing.text.AttributeSet)
     */
    @Override
	public void insertString(int offset, String  str, AttributeSet attr) throws BadLocationException {
        
    	if(str == null || !(49<=str.charAt(0) && str.charAt(0)<=57 && str.charAt(0)!='0') ) {
    		return;
        } else {
	        if ((getLength() + str.length()) <= this.MAX_FIELD_LENGTH) {
	            	super.insertString(offset, str, attr);
	        } 
        }    	
    }
}