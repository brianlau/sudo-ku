package gui.element;

@SuppressWarnings("serial")
public class Line {
	
	private Cell cells[];
	private int count = 0;
	private boolean isShaded;
	
	public Line(boolean setShaded) {
		cells = new Cell[9];
		isShaded = setShaded;
	}
	
	public void add(Cell cell) {
		cells[count] = cell;
		count++;
	}
	public void setHighlighted(boolean flag) {
		for(Cell cell: cells) cell.setHighlighted(flag);
	}
	
	public void setShaded(boolean flag) {
		for(Cell cell: cells) cell.setShaded(flag);
	}
	
	public boolean isShaded() {
		return isShaded;
	}
}