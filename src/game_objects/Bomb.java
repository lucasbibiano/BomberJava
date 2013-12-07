package game_objects;

import java.awt.Point;
import java.util.HashMap;

import thread.SharedThreadPool;
import behavior.Explodable;
import core.Game;
import events.ExplodeEvent;

public class Bomb extends GameObject implements Explodable {
	
	public static final HashMap<Point, Bomb> bombs = new HashMap<Point, Bomb>();
	
	protected static final long TIME_TO_EXPLODE = 3000;
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
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public void start() {
		final Bomb bomb = this;
		bombs.put(new Point(getX(), getY()), this);
		
		SharedThreadPool.getES().execute(new Runnable() {
			
			@Override
			public void run() {
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

	public boolean isExploded() {
		return exploded;
	}

	public void setExploded(boolean exploded) {
		this.exploded = exploded;
	}

	@Override
	public void update(double delta) {
	}
}
