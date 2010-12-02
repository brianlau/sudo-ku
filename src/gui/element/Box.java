package gui.element;

@SuppressWarnings("serial")
public class Box {

	private int x, gridx;
	private int y, gridy;
	private int count;
	private Cell cells[];
	private boolean isShaded;
	
	public Box(boolean setShaded) {
		count = 0;
		isShaded = setShaded;
		cells = new Cell[9];
	}
	
	public void setHighlighted(boolean flag) {
		for(Cell cell: cells) cell.setHighlighted(flag);
	}
	
	public void setShaded(boolean flag) {
		for(Cell cell: cells) cell.setShaded(flag);
	}
	
	public void add(Cell cell) {
		cells[count] = cell;
		count++;
	}
	
	public boolean isShaded() {
		return isShaded;
	}
	
	public String toString() {
		return gridx + ":" + gridy;
	}
}