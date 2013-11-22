package game_objects;

import behavior.Explodable;
import thread.SharedThreadPool;
import core.Game;
import events.ExplodeEvent;

public class Bomb extends GameObject implements Explodable {
	
	protected static final int TIME_TO_EXPLODE = 3000;
	
	private int flameLevel;
	private int playerNumber;
	
	private boolean exploded;
	
	public Bomb(Game game, int flameLevel, Player player) {
		super(game, player.getX(), player.getY());
		this.flameLevel = flameLevel;
		this.playerNumber = player.getNumber();
		
		exploded = false;
	}
	
	public void explode() {
		if (exploded)
			return;
		
		System.out.println("Exploding " + this);
		
		Map map = getGame().getMap();
		
		exploded = true;
		
		//expand horizontally
		for (int i = getX() - flameLevel; i <= getX() + flameLevel; i++){
			if (i < 0 || i >= map.getxLimit())
				System.out.println("Position outside map borders - IGNORE");
			else{
				GameObject affected = map.objAt(i, getX());
				System.out.println("exploding at x: "+i+" y: "+getY());
				if (affected != null && affected instanceof Explodable) {
					((Explodable) affected).exploded(new ExplodeEvent(playerNumber));
					break;
				}
			}
			
		}
		
		//expand vertically
		for (int i = getY() - flameLevel; i <= getY() + flameLevel; i++){
			if (i < 0 || i >= map.getyLimit())
				System.out.println("Position outside map borders - IGNORE");
			else{
				GameObject affected = map.objAt(i, getY());
				System.out.println("exploding at x: "+getX()+" y: "+ i);
				if (affected != null && affected instanceof Explodable) {
					((Explodable) affected).exploded(new ExplodeEvent(playerNumber));
					break;
				}
			}
		}
		
		getGame().removeObject(this);
	}

	public void start() {
		final Bomb bomb = this;
		
		SharedThreadPool.getES().execute(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("Starting " + bomb);
				
				try {
					Thread.sleep(TIME_TO_EXPLODE);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				bomb.explode();
			}
		});
	}
	
	@Override
	public String toString() {
		return "Bomb> " + super.toString() + "| flameLevel: " + flameLevel + "; playerNumber: " +
			playerNumber;
	}

	@Override
	public void exploded(ExplodeEvent e) {
		explode();
	}
}
