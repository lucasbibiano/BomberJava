package game_objects;

import core.Game;

public abstract class GameObject {
	private int x;
	private int y;
	
	protected boolean trepassable = false;
	
	private Game game;
	
	Map map;
	
	public GameObject(Game game, int x, int y) {
		this.game = game;
		this.x = x;
		this.y = y;
		this.map = game.getMap();
	}

	@Override
	public String toString() {
		return "x: " + x + "; y: " + y;
	}
	
	public Game getGame() {
		return game;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public boolean isValidY(int y){
		return (-1 < y && y < map.getyLimit() && map.isMovableSpace(getX(), y));
	}
	
	public boolean isTrepassable() {		
		return trepassable;
	}

	public boolean isValidX(int x){
		return (-1 < x && x < map.getxLimit() && map.isMovableSpace(x, getY()));
	}
	
}
