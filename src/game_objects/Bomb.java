package game_objects;

import behavior.Explodable;
import core.Game;
import events.ExplodeEvent;

public class Bomb extends GameObject implements Explodable {
	
	protected static final double TIME_TO_EXPLODE = 3000;
	private double timeElapsed; 
	
	private boolean started;
	
	private int flameLevel;
	private int playerNumber;
	
	private boolean exploded;
	
	public Bomb(Game game, int flameLevel, Player player) {
		super(game, player.getX(), player.getY());
		this.flameLevel = flameLevel;
		this.playerNumber = player.getNumber();
		timeElapsed = 0;
		
		setExploded(false);
	}
	
	public void explode() {
		if (isExploded())
			return;
		
		System.out.println("Exploding " + this);
		
		Map map = getGame().getMap();
		
		setExploded(true);
		for(int i = 1; i <= flameLevel; i++){
			if (getX() - i < 0 || getX() + i >= map.getWidth())
				System.out.println("Position outside map width borders - IGNORE");
			else{
				GameObject affected = map.objAt(getX() - i, getY());
				System.out.println("exploding at x: "+(getX()-i)+" y: "+getY());
				if (affected != null && affected instanceof Explodable) {
					((Explodable) affected).exploded(new ExplodeEvent(playerNumber));
				}
				affected = map.objAt(getX() + i, getY());
				System.out.println("exploding at x: "+(getX()+i)+" y: "+getY());
				if (affected != null && affected instanceof Explodable) {
					((Explodable) affected).exploded(new ExplodeEvent(playerNumber));
				}
			}
			
			if (getY() - i < 0 || getY() + i >= map.getHeight())
				System.out.println("Position outside map h borders - IGNORE");
			else{
				GameObject affected = map.objAt(getX() , getY() - i);
				System.out.println("exploding at x: "+(getX())+" y: "+(getY()-i));
				if (affected != null && affected instanceof Explodable) {
					((Explodable) affected).exploded(new ExplodeEvent(playerNumber));
				}
				affected = map.objAt(getX() , getY()+ i);
				System.out.println("exploding at x: "+(getX())+" y: "+(getY()+i));
				if (affected != null && affected instanceof Explodable) {
					((Explodable) affected).exploded(new ExplodeEvent(playerNumber));
				}
			}
			
		}
		/*
		//expand horizontally
		for (int i = getX() - flameLevel; i <= getX() + flameLevel; i++){
			if (i < 0 || i >= map.getWidth() || i>= map.getHeight())
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
		}*/
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
	public void exploded(ExplodeEvent e) {
		explode();
	}

	public boolean isExploded() {
		return exploded;
	}

	public void setExploded(boolean exploded) {
		this.exploded = exploded;
	}

	@Override
	public void update(double delta) {
		if (started) {
			timeElapsed += delta * 28;
			
			if (timeElapsed >= TIME_TO_EXPLODE)
				explode();
		}
	}
}
