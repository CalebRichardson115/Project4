package hw4.game;

import java.util.*;
import hw4.maze.*;
import hw4.player.*;
import java.util.random.*;
//Game class supports movement on a grid and generates a randomized grid to play on.
public class Game {
	Grid grid;
	int win;
	Player player;

	public Game(Grid grid) {
		this.grid = grid;
	}
	//Retrieves the grid.
	public Grid getGrid() {
		return this.grid;
	}
	//Returns true when the game has been won.
	public boolean getWin() {
		if(this.win == 1) return true;
		else return false;
	}
	/*
	 * Returns true if the player was able to move after
	 * moving the player. Moves player by finding the players' exact row and column
	 * in relation to the rest of the grid and then inserts a new player into 
	 * the spot where the player is to move.
	 */
	public boolean play(Movement move, Player player) {
		if(move == null || player == null) {
			return false;
		}
		
		Cell currCell = player.getCurrentCell();
		//Row currRow = player.getCurrentRow();
		//Row and column to be found in the grid so that the player may move.
		int row = -1;
		int col = -1;
		for(int i = 0; i < this.getGrid().getRows().size(); i++) {
			for(int j = 0; j < this.getGrid().getRows().get(i).getCells().size() ; j++) {
				if(this.getGrid().getRows().get(i).getCells().get(j).equals(currCell)) {
					row = i;
					col = j;
				}
			}
			
		}
		System.out.println(row +" "+ col);
		if(col == -1 || row == -1) {
			return false;
		}
		Row newRow = player.getCurrentRow();
		Cell newCell = player.getCurrentCell();
		
		
		switch(move) {
		case UP:
			if(player.getCurrentCell().getUp().equals(CellComponents.APERTURE)) {
				newRow = this.getGrid().getRows().get(row-1);
				newCell = this.getGrid().getRows().get(row-1).getCells().get(col);
				player.setCurrentRow(newRow);
				player.setCurrentCell(newCell);
				System.out.println(player.toString());
				return true;
			}
			else {
				return false;
			}
		case DOWN:
			if(player.getCurrentCell().getDown().equals(CellComponents.APERTURE)) {
				newRow = this.getGrid().getRows().get(row+1);
				newCell = this.getGrid().getRows().get(row+1).getCells().get(col);
				player.setCurrentRow(newRow);
				player.setCurrentCell(newCell);
				return true;
			}
			else {
				return false;
			}
		case LEFT:
			if(player.getCurrentCell().getLeft().equals(CellComponents.APERTURE)) {
				newRow = this.getGrid().getRows().get(row);
				newCell = this.getGrid().getRows().get(row).getCells().get(col-1);
				player.setCurrentRow(newRow);
				player.setCurrentCell(newCell);
				return true;
			}
			else if(player.getCurrentCell().getLeft().equals(CellComponents.EXIT)) {
				win = 1;
				return true;
			}
			else {
				return false;
			}
		case RIGHT:
			if(player.getCurrentCell().getRight().equals(CellComponents.APERTURE)) {
				newRow = this.getGrid().getRows().get(row);
				newCell = this.getGrid().getRows().get(row).getCells().get(col+1);
				player.setCurrentCell(newCell);
				player.setCurrentRow(newRow);
				
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
		this.grid.rowList.clear();
		if(rowList == null) {
			this.grid.rowList = null;
			this.grid = null;
			return;
		}
		this.grid = new Grid(rowList);
	}
	//Returns a string of the Game's grid.
	@Override
	public String toString() {
		String string = "Game [grid=";
		string = string + grid.toString() + "]";
		return string;
	}
	/*
	 * Generates a random grid for the game to be played on.
	 * Rules:
	 * 1. Only one exit may be generated.
	 * 2. No apertures along the sides.
	 * 3. Every cell must have one aperture.
	 * 4. Size must be between 3-7.
	 */
	public Grid createRandomGrid(int n) {
		//Check for valid input and return if invalid.
		if(n < 3 || n > 7) {
			return null;
		}
		Random rand = new Random();
		//Generate grid to be all walls
		ArrayList<Row> rowList = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			ArrayList<Cell> cellList = new ArrayList<>();
			for(int j = 0; j < n; j++) {
				Cell newCell = new Cell(CellComponents.WALL,CellComponents.WALL,CellComponents.WALL,CellComponents.WALL);
				cellList.add(newCell);
			}
			Row row = new Row(cellList);
			rowList.add(row);
		}
		Grid grid = new Grid(rowList);
		
		//Generate corners before top and bottom rows
		boolean sideOpen = rand.nextBoolean();
		boolean bottomOpen = rand.nextBoolean();
		if(!sideOpen && !bottomOpen) {
			if(rand.nextBoolean()) {
				sideOpen = true;
			}
			else {
				bottomOpen = false;
			}
		}
		//Top left corner
		if(sideOpen) grid.getRows().get(0).getCells().get(0).setRight(CellComponents.APERTURE);
		if(bottomOpen) grid.getRows().get(0).getCells().get(0).setDown(CellComponents.APERTURE);
		//Top right corner
		if(sideOpen) grid.getRows().get(0).getCells().get(n-1).setLeft(CellComponents.APERTURE);
		if(bottomOpen) grid.getRows().get(0).getCells().get(n-1).setDown(CellComponents.APERTURE);
		
		sideOpen = rand.nextBoolean();
		boolean topOpen = rand.nextBoolean();
		if(!sideOpen && !topOpen) {
			if(rand.nextBoolean()) {
				sideOpen = true;
			}
			else {
				topOpen = true;
			}
		}
		//Bottom left corner
		if(sideOpen) grid.getRows().get(n-1).getCells().get(0).setRight(CellComponents.APERTURE);
		if(topOpen) grid.getRows().get(n-1).getCells().get(0).setUp(CellComponents.APERTURE);
		//Bottom right
		if(sideOpen) grid.getRows().get(n-1).getCells().get(n-1).setLeft(CellComponents.APERTURE);
		if(topOpen) grid.getRows().get(n-1).getCells().get(n-1).setUp(CellComponents.APERTURE);
		
		//Randomize top row
		for(int i = 1; i < n-1; i++) {
			boolean bottom = rand.nextBoolean();
			boolean left = rand.nextBoolean();
			boolean right = rand.nextBoolean();
			//Calling next boolean to guarantee at least one more opening.
			if(!bottom && !left && !right) {
				if(rand.nextBoolean()) {
					left = true;
				}
				else {
					right = true;
				}
				bottom = rand.nextBoolean();
			}
			if(left) grid.getRows().get(0).getCells().get(i).setLeft(CellComponents.APERTURE);
			if(right) grid.getRows().get(0).getCells().get(i).setRight(CellComponents.APERTURE);
			if(bottom) grid.getRows().get(0).getCells().get(i).setDown(CellComponents.APERTURE);
		}
		//Randomize bottom row
		for(int i = 1; i < n-1; i++) {
			boolean top = rand.nextBoolean();
			boolean left = rand.nextBoolean();
			boolean right = rand.nextBoolean();
			//Calling next boolean to guarantee at least one more opening.
			if(!top && !left && !right) {
				if(rand.nextBoolean()) {
					left = true;
					//right = rand.nextBoolean();
				}
				else {
					right = true;
				}
				top = rand.nextBoolean();
			}
			if(left) grid.getRows().get(n-1).getCells().get(i).setLeft(CellComponents.APERTURE);
			if(right) grid.getRows().get(n-1).getCells().get(i).setRight(CellComponents.APERTURE);
			if(top) grid.getRows().get(n-1).getCells().get(i).setUp(CellComponents.APERTURE);
		}
		//Randomize middle rows at the sides.
		for(int i = 1; i < n-1; i++) {
			//generate left first
			boolean top = rand.nextBoolean();
			boolean bottom = rand.nextBoolean();
			boolean right = rand.nextBoolean();
			if(!top && !bottom && !right) {
				if(rand.nextBoolean()) {
					bottom = true;
				}
				else {
					right = true;
				}
				top = rand.nextBoolean();
			}
			if(bottom) grid.getRows().get(i).getCells().get(0).setDown(CellComponents.APERTURE);
			if(right) grid.getRows().get(i).getCells().get(0).setRight(CellComponents.APERTURE);
			if(top) grid.getRows().get(i).getCells().get(0).setUp(CellComponents.APERTURE);
			//Generating right
			top = rand.nextBoolean();
			bottom = rand.nextBoolean();
			boolean left = rand.nextBoolean();
			if(!top && !bottom && !left) {
				if(rand.nextBoolean()) {
					bottom = true;
				}
				else {
					left = true;
				}
				top = rand.nextBoolean();
			}
			if(bottom) grid.getRows().get(i).getCells().get(n-1).setDown(CellComponents.APERTURE);
			if(left) grid.getRows().get(i).getCells().get(n-1).setLeft(CellComponents.APERTURE);
			if(top) grid.getRows().get(i).getCells().get(n-1).setUp(CellComponents.APERTURE);
		}
		//Randomize center.
		for(int i = 1; i <= n-1; i++) {
			for(int  j = 1; j <= n-1; j++) {
				boolean top = rand.nextBoolean();
				boolean bottom = rand.nextBoolean();
				boolean left = rand.nextBoolean();
				boolean right = rand.nextBoolean();
				if(!top && !bottom && !left && !right) {
					if(rand.nextBoolean()) {
						top = true;
					}
					else {
						bottom = true;
					}
					if(rand.nextBoolean()) {
						left = true;
					}else {
						right = true;
					}
				}
				if(bottom) grid.getRows().get(i).getCells().get(j).setDown(CellComponents.APERTURE);
				if(top) grid.getRows().get(i).getCells().get(j).setUp(CellComponents.APERTURE);
				if(left) grid.getRows().get(i).getCells().get(j).setLeft(CellComponents.APERTURE);
				if(top) grid.getRows().get(i).getCells().get(j).setUp(CellComponents.APERTURE);
			}
		}
		//Generate exit
		int exit = rand.nextInt(0, n);
		grid.rowList.get(exit).getCells().get(0).setLeft(CellComponents.EXIT);
		return grid;
	}
	//Creates a random grid given a size n.
	public Game(int n) {
		this.grid = createRandomGrid(n);
		this.win = 0;
	}
	//Prints the grid for the game cycle.
	public void printGrid(Player player) {
		for(int i = 0; i < this.grid.getRows().size(); i++) {
			ArrayList<String> strings = new ArrayList<>();
			for(int j = 0; j < this.grid.getRows().size(); j++) {
				if(this.grid.getRows().get(i).getCells().get(j).getLeft() == CellComponents.EXIT) strings.add("E");
				else if(this.getGrid().getRows().get(i).getCells().get(j) == player.getCurrentCell() && this.grid.getRows().get(i) == player.getCurrentRow()) {
					strings.add("A");
					System.out.println("Found");
				}
				else {
					strings.add("S");
				}
			}
			System.out.println(strings);
		}
	}

}
