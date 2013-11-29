package game_objects;


import behavior.Explodable;
import behavior.MoveListener;
import constants.Constants.Movement;
import core.Game;
import events.ExplodeEvent;
import events.MoveEvent;

public class Player extends GameObject implements Explodable {
	private static final long BOMB_COOLDOWN_NS = 0;
	
	private long lastBomb = 0;
	
	private int number;
	
	private int speed;
	
	private int flameLevel;
	private int maxBombs;
	private int activeBombs;
	
	private boolean dead;
	
	private MoveListener moveListener;
	
	public Player(Game game, int x, int y, int number) {
		super(game, x, y);
		this.number = number;
		
		flameLevel = 1;
		maxBombs = 1;
		activeBombs = 0;
		
		trepassable = true;
		this.moveListener = game.getMap();
	}
	
	public void placeBomb() {
		long now = System.nanoTime();
		
		if (now - lastBomb < BOMB_COOLDOWN_NS)
			return;
		
		lastBomb = System.nanoTime();
		
		Bomb bombToAdd = new Bomb(getGame(), flameLevel, this);
		
		getGame().addObject(bombToAdd);
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
	
	public void move(Movement move) {		
		int x = this.getX();
		int y = this.getY();
		
		if (move == Movement.UP){
			changeY(y-1);
		}
		else if(move == Movement.DOWN){
			changeY(y+1);
		}
		else if(move == Movement.LEFT){
			changeX(x-1);
		}
		else if(move == Movement.RIGHT){
			changeX(x+1);
		}
		
		if (x != getX() || y != getY())
			moveListener.objectMoved(new MoveEvent(x, y, this));
	}
	
	private void changeY(int y) {
		if (getGame().getMap().isValid(getX(), y)) {
			this.setY(y);
		}
	}
	
	private void changeX(int x) {
		if (getGame().getMap().isValid(x, getY())) {
			this.setX(x);
		}
	}

	@Override
	public String toString() {
		return "Player> " + super.toString() + "; playerNumber: " +
				number;
	}
}
