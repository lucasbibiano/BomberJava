package game_objects;


import behavior.Explodable;
import constants.Constants.Movement;
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
	
	public void changePosition(int x, int y){		
		map.moveObject(this, x, y);
	}
	
	public void changeY(int y){
		if (isValidY(y)){
			changePosition(getX(), y);
			setY(y);
		}		
	}
	
	public void changeX(int x){
		if (isValidX(x)){ 
			changePosition(x, getY());	
			setX(x);
		}
	}
	
	public void move(Movement move){		
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
	}
	
	@Override
	public String toString(){
		return "Player> " + super.toString() + "; playerNumber: " +
				number;
	}
}
