package hw4.maze;

public enum CellComponents {
	
	WALL(1),
	APERTURE(2),
	EXIT(3);
	
	private final int order;
	
	CellComponents (int i){
		this.order = i;
	}
	
	public int getOrder() {
		return this.order;
	}
	
}
