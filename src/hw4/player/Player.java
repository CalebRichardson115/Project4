package hw4.player;

import hw4.maze.*;
//The player class occupies a cell within the grid which will then move throughout the grid based on valid movements.
public class Player {
	Row currRow;
	Cell currCell;
	//Takes a row and a cell as arguments for where the player will start on the grid.
	public Player(Row row, Cell cell) {
		this.currRow = row;
		this.currCell = cell;
	}
	//Getters for the current cell and current row that the player is occupying.
	public Cell getCurrentCell() {
		return currCell;
	}

	public Row getCurrentRow() {
		return currRow;
	}
	//Returns a string of the players' position on the grid.
	@Override
	public String toString() {
		String string = "Player [currentCell=";
		string = string + currCell.toString() + ", currentRow=" + currRow.toString() + "]";
		return string;
	}

}
