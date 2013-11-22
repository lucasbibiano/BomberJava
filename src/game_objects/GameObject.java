package game_objects;

import core.Game;

public abstract class GameObject {
	private int x;
	private int y;
	
	protected boolean trepassable = false;
	
	private Game game;
	
	public GameObject(Game game, int x, int y) {
		this.game = game;
		this.x = x;
		this.y = y;
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

	
	public boolean isTrepassable() {		
		return trepassable;
	}
}
