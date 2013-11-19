package core;

import constants.Constants.Movement;
import game_objects.Map;
import game_objects.Player;

public class Game {
	private Map map;
	private Player player;

	public Game(Map map) {
		this.map = map;
		player = new Player(this, 10, 10, 1);
	}
	
	public Map getMap() {
		return map;
	}
	
	public void update(double delta) {
	}
	
	public void receivedInput(Movement mov) {
		player.setX((player.getX() + 50) % 500);
		player.setY((player.getY() + 50) % 500);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
}
