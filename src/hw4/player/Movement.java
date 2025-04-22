package hw4.player;
//Needs to be an enum
public enum Movement {
	
	UP(1),
	DOWN(2),
	LEFT(3),
	RIGHT(4);
	
	private final int order;
	
	Movement (int i){
		this.order = i;
	}
	
	public int getOrder() {
		return this.order;
	}
	
}
