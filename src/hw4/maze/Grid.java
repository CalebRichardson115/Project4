package hw4.maze;

import java.util.ArrayList;
//Grid class is an assembly of rows made into a list.
public class Grid {
	public ArrayList<Row> rowList = new ArrayList<>();
	//A grid is constructed by assembling a list of rows. 
	public Grid(ArrayList<Row> rowList) {
		this.rowList.addAll(rowList);
	}
	//Constructor to be used for the game class
	public Grid(Grid grid) {
		this.rowList.addAll(grid.rowList);
	}
	//returns the list of rows.
	public ArrayList<Row> getRows() {
		return this.rowList;
	}
	//Resets the grid to a new set of rows.
	public void setRows(ArrayList<Row> rowList) {
		this.rowList.clear();
		if(rowList == null) {
			this.rowList = null;
			return;
		}
		this.rowList.addAll(rowList);
	}
	//Returns a string of all cells in a row for every row in the grid in order.
	@Override
	public String toString() {
		if(rowList == null) {
			return null;
		}
		String string = "Grid [rows=[";
		for(int i = 0; i < this.rowList.size()-1; i++) {
			string = string + rowList.get(i).toString() + ", ";
		}
		return string + rowList.getLast().toString() + "]]";
	}

}
