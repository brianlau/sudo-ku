package gui.element;

/**
* This class is for storing references to perpendicularly connected lines through a given cell.
 * 
 * @author Alvin Moradi with contributions from the "AWESOME SAUCE" team
 * @version 1.0
 *
 */
@SuppressWarnings("serial")
public class Line {
	
	private Cell cells[];
	private int count = 0;
	private boolean isShaded;
	
	/**
	 * Constructor for instantiating a new Line object for cells to claim parental
	 * relation to; that later will be called to highlight all related cells dynamically.
	 * 
	 * @param flag Boolean flag used to impose shade on this line of cells (should generally instantiate with false);
	 */
	public Line(boolean flag) {
		cells = new Cell[9];
		isShaded = flag;
	}
	
	/**
	 * Function to add an additional cell on to the stack of cells linked along this line.
	 * 
	 * @param cell The cell to be added as an inclusion to this line of cells
	 */
	public void add(Cell cell) {
		cells[count] = cell;
		count++;
	}
	
	/**
	 * Function to manipulating the highlighted background state of this cell.
	 */
	public void setHighlighted(boolean flag) {
		for(Cell cell: cells) cell.setHighlighted(flag);
	}
	
	/**
	 * Function to manipulating the shaded background state of this cell.
	 */
	public void setShaded(boolean flag) {
		for(Cell cell: cells) cell.setShaded(flag);
	}
	
	/**
	 * Getter method for the shaded state of current cell;
	 * 
	 * @return A boolean value denoting the shaded state of this 
	 */
	public boolean isShaded() {
		return isShaded;
	}
}