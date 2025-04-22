package hw4.maze;

import java.util.ArrayList;
//Class that puts individual cells into a list which is viewed as a row.
public class Row {
	ArrayList<Cell> cellList = new ArrayList<>();
	//Constructor for Row needs to be passed an array list of cells to be copied into the cell list.
	public Row(ArrayList<Cell> cellList) {
		this.cellList.addAll(cellList);
	}
	//returns the cell list.
	public ArrayList<Cell> getCells() {
		return this.cellList;
	}
	//Resets the row to a new list of cells or to null.
	public void setCells(ArrayList<Cell> cellList) {
		this.cellList.clear();
		if(cellList == null) {
			this.cellList = null;
			return;
		}
		this.cellList.addAll(cellList);
	}
	//Returns a string of all cells in a row.
	@Override
	public String toString() {
		if(cellList == null) {
			return null;
		}
		String string = "Row [cells=[";
		for(int i = 0; i < this.cellList.size()-1; i++) {
			string = string + cellList.get(i).toString() + ", ";
		}
		return string + cellList.getLast().toString() + "]]";
	}
}
