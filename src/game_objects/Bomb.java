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
		
		checkPosition(getX(), getY());
	
		getGame().addObject(new Explosion(getGame(), getX() , getY()));
		
		System.out.println("exploding at x: "+getX()+" y: "+getY());
		
		for(int i = 1; i <= flameLevel; i++){
			if (getX() - i < 0)
				System.out.println("Position outside map width borders - IGNORE");
			else{
				getGame().addObject(new Explosion(getGame(), getX() - i, getY()));

				checkPosition(getX() - i, getY());
			}
			if(getX() + i >= map.getWidth())
				System.out.println("Position outside map width borders - IGNORE");
			else{
				getGame().addObject(new Explosion(getGame(), getX() + i, getY()));

				checkPosition(getX() + i, getY());
			}
			
			if (getY() - i < 0 )
				System.out.println("Position outside map h borders - IGNORE");
			else{
				getGame().addObject(new Explosion(getGame(), getX() , getY() - i));

				checkPosition(getX(), getY() - i);
			}
			if(getY() + i >= map.getHeight())
				System.out.println("Position outside map h borders - IGNORE");
			else{
				getGame().addObject(new Explosion(getGame(), getX() , getY() + i));
				
				checkPosition(getX(), getY() + i);
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

	private void checkPosition(int x, int y) {
		GameObject[] affecteds = getGame().getMap().objAt(x, y);
		
		for (GameObject affected: affecteds) {
			if (affected instanceof Explodable) {
				((Explodable) affected).exploded(new ExplodeEvent(playerNumber));
			}
		}
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
