package hw4.game;

import java.util.*;
import hw4.maze.*;
import hw4.player.*;

public class Game {
	Grid grid;
	

	public Game(Grid grid) {
		this.grid = grid;
	}

	public Object getGrid() {
		return this.grid;
	}
	/*
	 * Returns true if the player was able to move after
	 * moving the player. Moves player by finding the players' exact row and column
	 * in relation to the rest of the grid and then inserts a new player into 
	 * the spot where the player is to move.
	 */
	public boolean play(Movement move, Player player) {
		Cell currCell = player.getCurrentCell();
		Row currRow = player.getCurrentRow();
		//Row and column to be found in the grid so that the player may move.
		int row = -1;
		int col = -1;
		for(int i = 0; i < grid.rowList.size(); i++) {
			for(int j = 0; j < grid.rowList.get(i).getCells().size() ; j++) {
				if(grid.rowList.get(i).getCells().get(j) == currCell) {
					row = i;
					col = j;
				}
			}
			
		}
		if(col == -1 || row == -1) {
			return false;
		}
		switch(move) {
		case UP:
			if(player.getCurrentCell().getUp().equals(CellComponents.APERTURE) && row != 0) {
				player = new Player(grid.getRows().get(row-1), grid.getRows().get(row-1).getCells().get(col));
				return true;
			}
			else {
				return false;
			}
		case DOWN:
			if(player.getCurrentCell().getDown().equals(CellComponents.APERTURE) && row != grid.getRows().size()) {
				player = new Player(grid.getRows().get(row+1), grid.getRows().get(row+1).getCells().get(col));
				return true;
			}
			else {
				return false;
			}
		case LEFT:
			if(player.getCurrentCell().getLeft().equals(CellComponents.APERTURE) && col != 0 && col != grid.getRows().size()) {
				player = new Player(grid.getRows().get(row), grid.getRows().get(row).getCells().get(col-1));
				return true;
			}
			else {
				return false;
			}
		case RIGHT:
			if(player.getCurrentCell().getRight().equals(CellComponents.APERTURE) && col != 0 && col != grid.getRows().size()) {
				return true;
			}
			else {
				return false;
			}
		default:
			return false;
		}
	}
	//Sets the game grid to a new list of rows.
	public void setGrid(ArrayList<Row> rowList) {
		grid.rowList.clear();
		if(rowList == null) {
			this.grid = null;
			return;
		}
		grid.rowList.addAll(rowList);
	}
	
	@Override
	public String toString() {
		String string = "Game [grid=";
		string = string + grid.toString() + "]";
		return string;
	}

}
