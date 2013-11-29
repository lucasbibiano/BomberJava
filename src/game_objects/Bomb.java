package game_objects;

import behavior.Explodable;
import thread.SharedThreadPool;
import core.Game;
import events.ExplodeEvent;

public class Bomb extends GameObject implements Explodable {
	
	protected static final int TIME_TO_EXPLODE = 3000;
	
	private boolean started;
	
	private int flameLevel;
	private int playerNumber;
	
	private boolean exploded;
	
	public Bomb(Game game, int flameLevel, Player player) {
		super(game, player.getX(), player.getY());
		this.flameLevel = flameLevel;
		this.playerNumber = player.getNumber();
		
		setExploded(false);
	}
	
	public void explode() {
		if (isExploded())
			return;
		
		System.out.println("Exploding " + this);
		
		Map map = getGame().getMap();
		
		setExploded(true);
		
		//expand horizontally
		for (int i = getX() - flameLevel; i <= getX() + flameLevel; i++){
			if (i < 0 || i >= map.getWidth())
				System.out.println("Position outside map borders - IGNORE");
			else{
				GameObject affected = map.objAt(i, getY());
				System.out.println("exploding at x: "+i+" y: "+getY());
				if (affected != null && affected instanceof Explodable) {
					((Explodable) affected).exploded(new ExplodeEvent(playerNumber));
				}
			}
		}
		
		//expand vertically
		for (int i = getY() - flameLevel; i <= getY() + flameLevel; i++){
			if (i < 0 || i >= map.getHeight())
				System.out.println("Position outside map borders - IGNORE");
			else{
				GameObject affected = map.objAt(getX(), i);
				System.out.println("exploding at x: "+getX()+" y: "+ i);
				if (affected != null && affected instanceof Explodable) {
					((Explodable) affected).exploded(new ExplodeEvent(playerNumber));
				}
			}
		}
		getGame().removeObject(this);

	}

	public void start() {
		started = true;
	}
	
	@Override
	public String toString() {
		return "Bomb> " + super.toString() + "| flameLevel: " + flameLevel + "; playerNumber: " +
			playerNumber;
	}

	@Override
	public synchronized void exploded(ExplodeEvent e) {
		explode();
	}

	public boolean isExploded() {
		return exploded;
	}

	public void setExploded(boolean exploded) {
		this.exploded = exploded;
	}
}
