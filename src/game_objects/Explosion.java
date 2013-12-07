package game_objects;

import thread.SharedThreadPool;
import core.Game;

public class Explosion extends GameObject {

	private static long LIFETIME = 500;
	
	private Bomb bomb;
	
	public Explosion(Game game, int x, int y, Bomb bomb) {
		super(game, x, y);
		this.trepassable = true;
		this.bomb = bomb;
	}
	
	public void start() {
		final Explosion explosion = this;
		
		SharedThreadPool.getES().execute(new Runnable() {
			
			@Override
			public void run() {
				long startedAt = System.nanoTime();
				
				while (true) {
					if (System.nanoTime() < startedAt + LIFETIME) {
						getGame().checkExplosion(explosion);
						Thread.yield();
					} else {
						explosion.setToRemove(true);
					}
				}
			}
		});
	}

	@Override
	public void update(double delta) {
	}

	public Bomb getBomb() {
		return bomb;
	}
}
