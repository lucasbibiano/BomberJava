package core;

import game_objects.Map;

public class Game {
	private Map map;

	public Game(Map map) {
		this.map = map;
	}
	
	public Map getMap() {
		return map;
	}
}
