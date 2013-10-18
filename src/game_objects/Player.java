package game_objects;

import core.Game;

public class Player extends GameObject {
	private int number;
	
	private int speed;
	
	private int flameLevel;
	private int maxBombs;
	private int activeBombs;
	
	public Player(Game game, int number) {
		super(game);
		this.number = number;
		
		flameLevel = 1;
		maxBombs = 1;
		activeBombs = 0;
	}
	
	public void placeBomb() {
		Map map = getGame().getMap();
		Bomb bombToAdd = new Bomb(getGame(), flameLevel, this);
		
		map.addObject(bombToAdd);
		bombToAdd.start();
	}

	public int getNumber() {
		return number;
	}
}
