package hw4.maze;

//import java.util.ArrayList;

public class Cell {
	
	CellComponents left;
	CellComponents right;
	CellComponents up;
	CellComponents down;
	
	public Cell(CellComponents left,CellComponents right, CellComponents up, CellComponents down) {
		this.left = left;
		this.right = right;
		this.up = up;
		this.down = down;
	}

	/*
	public ArrayList<Cell> getCells() {
		// TODO Auto-generated method stub
		return null;
	}
	*/

	
	public CellComponents getLeft() {
		return this.left;
	}
	
	public void setLeft(CellComponents left) {
		this.left = left;
	}
	
	public CellComponents getRight() {
		return this.right;
	}
	
	public void setRight(CellComponents right) {
		this.right = right;
	}
	
	public CellComponents getUp() {
		return this.up;
	}
	
	public void setUp(CellComponents up) {
		this.up = up;
	}
	
	public CellComponents getDown() {
		return this.down;
	}
	
	public void setDown(CellComponents down) {
		this.down = down;
	}
	
}
