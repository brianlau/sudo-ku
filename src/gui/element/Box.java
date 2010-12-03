package gui.element;

/**
 * This class stores a reference of all the cells contained within a 3x3 area.
 * 
 * @author Alvin Moradi with contributions from the "AWESOME SAUCE" team.
 * @version 1.0
 *
 */

@SuppressWarnings("serial")
public class Box {

	private int gridx, gridy;
	private boolean isShaded;
	private Cell cells[];
	private int count;
	 
	/**
	 * Constructor to build a Box object for storage of 3x3 cells.
	 * 
	 * @param setShaded A boolean value to indicate the initial BG state of this Box
	 */
	public Box(boolean setShaded) {
		count = 0;
		isShaded = setShaded;
		cells = new Cell[9];
	}
	
	/**
	 * Setter for manipulating the highlighted state of this box.
	 * 
	 * @param flag Boolean to impose sought highlighted state.
	 */
	public void setHighlighted(boolean flag) {
		for(Cell cell: cells) cell.setHighlighted(flag);
	}
	
	/**
	 * Setter for manipulating the shaded state of this box.
	 * 
	 * @param flag Boolean to impose sought shaded state.
	 */
	public void setShaded(boolean flag) {
		for(Cell cell: cells) cell.setShaded(flag);
	}
	
	/**
	 * Function to add an additional cell on to the stack of cells contained within this box.
	 * 
	 * @param cell The cell to be added as an inclusion to this 3x3 box
	 */
	public void add(Cell cell) {
		cells[count] = cell;
		count++;
	}
	
	/**
	 * Function to retrieve the shaded background state of this cell.
	 * 
	 * @return Boolean value to indicate the shaded state of this box
	 */
	public boolean isShaded() {
		return isShaded;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Deprecated
	public String toString() {
		return gridx + ":" + gridy;
	}
}