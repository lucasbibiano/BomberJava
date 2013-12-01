package game_objects;

import core.Game;
import events.ExplodeEvent;
import behavior.Explodable;

public class Block extends GameObject implements Explodable {
	
	private boolean explodable;
	private PowerUp powerUp;
	
	public Block(Game game, int x, int y) {
		super(game, x, y);
	}
	
	@Override
	public void exploded(ExplodeEvent e) {
		if (!explodable)
			getGame().removeObject(this);
		return;
	}

	public boolean isExplodable() {
		return explodable;
	}

	public void setExplodable(boolean explodable) {
		this.explodable = explodable;
	}

	public PowerUp getPowerUp() {
		return powerUp;
	}

	public void setPowerUp(PowerUp powerUp) {
		this.powerUp = powerUp;
	}

	@Override
	public void update(double delta) {
	}

}
