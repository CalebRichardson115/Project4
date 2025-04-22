package hw4.simulation;

import java.util.*;

import hw4.maze.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cell cell1 = new Cell(CellComponents.WALL, CellComponents.APERTURE, CellComponents.WALL, CellComponents.WALL);
		Cell cell2 = new Cell(CellComponents.APERTURE, CellComponents.WALL, CellComponents.WALL, CellComponents.WALL);
		Cell cell3 = new Cell(CellComponents.WALL, CellComponents.EXIT, CellComponents.WALL, CellComponents.WALL);
		
		ArrayList<Cell> Cells = new ArrayList<>();
		Cells.add(cell1);
		Cells.add(cell2);
		
		Row row1 = new Row(Cells);
		
		System.out.println("Start");
		cell1.setUp(null);
		System.out.println(cell1.toString());
		System.out.println(row1.toString());
		System.out.println(row1.getCells().get(0).toString());
		Cells.add(cell3);
		row1.setCells(Cells);
		System.out.println(row1.toString());
		row1.setCells(null);
		System.out.println(row1.toString());
		
	}
}
