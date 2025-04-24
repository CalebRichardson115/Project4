package hw4.maze;

public class Cell {
	
	CellComponents left;
	CellComponents right;
	CellComponents up;
	CellComponents down;
	//Assuming order based on the order of the test class.
	//Constructs a cell given different CellComponents.
	public Cell(CellComponents left,CellComponents right, CellComponents up, CellComponents down) {
		this.left = left;
		this.right = right;
		this.up = up;
		this.down = down;
	}

	@Override
	public String toString() {
		return "Cell [left=" + this.getLeft() + ", right=" + this.getRight() + ", up=" + this.getUp() + ", down=" + this.getDown() + "]";
	}

	//Getters and setters for the Cell class.
	public CellComponents getLeft() {
		return this.left;
	}
	
	public void setLeft(CellComponents left) {
		if(left == null) {
			return;
		}
		this.left = left;
	}
	
	public CellComponents getRight() {
		return this.right;
	}
	
	public void setRight(CellComponents right) {
		if(right == null) {
			return;
		}
		else {
			this.right = right;
		}
	}
	
	public CellComponents getUp() {
		return this.up;
	}
	
	public void setUp(CellComponents up) {
		if(up == null) {
			return;
		}
		else {
			this.up = up;
		}
	}
	
	public CellComponents getDown() {
		return this.down;
	}
	
	public void setDown(CellComponents down) {
		if(down == null) {
			return;
		}
		else {
			this.down = down;
		}
	}
	
}
