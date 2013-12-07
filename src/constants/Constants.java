package constants;

public interface Constants {

	public static final int TILESIZE = 32;
	
	public static enum Movement {
	    UP, DOWN, LEFT, RIGHT, NONE
	}
	
	public static final boolean GRID = true;

	public static final int WIDTH = 40;
	public static final int HEIGHT = 20;
	
	public static final int PORT = 12345;
	public static final String HOST = "localhost"; 
	
	public static final char EMPTY_TILE = 0;
	public static final char FIXED_TILE = 1;
	public static final char EXPLODABLE_TILE = 2;
	public static final char EXPLODABLE_POWER_UP_TILE = 3;
}
