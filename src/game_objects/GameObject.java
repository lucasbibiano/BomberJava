package game_objects;

import core.Game;

public abstract class GameObject {
	private int x;
	private int y;
	
	protected boolean trepassable = false;
	private boolean toRemove;
	
	private Game game;
	
	public GameObject(Game game, int x, int y) {
		this.game = game;
		this.x = x;
		this.y = y;
		setToRemove(false);
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
	
	public abstract void update(double delta);

	public boolean isToRemove() {
		return toRemove;
	}

	public void setToRemove(boolean toRemove) {
		this.toRemove = toRemove;
	}
}
