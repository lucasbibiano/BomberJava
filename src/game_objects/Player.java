package game_objects;

import behavior.Explodable;
import core.Game;
import events.ExplodeEvent;

public class Player extends GameObject implements Explodable {
	private int number;
	
	private int speed;
	
	private int flameLevel;
	private int maxBombs;
	private int activeBombs;
	
	private boolean dead;
	
	public Player(Game game, int x, int y, int number) {
		super(game, x, y);
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
	
	@Override
	public void exploded(ExplodeEvent e) {
		dead = true;
		System.out.println("Player #" + number + " has been killed by player #" + e.getPlayerNumber());
	}

	public int getNumber() {
		return number;
	}

	public boolean isDead() {
		return dead;
	}
}
