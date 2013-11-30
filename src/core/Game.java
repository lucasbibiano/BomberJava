package core;

import java.util.ArrayList;

import constants.Constants.Movement;
import game_objects.GameObject;
import game_objects.Map;
import game_objects.Player;

public class Game {
	private Player player;
	private ArrayList<GameObject> objects;
	private Map map;

	public Game() {
		objects = new ArrayList<GameObject>();
	}
	
	public void update(double delta) {
		for (int i = 0; i < objects.size(); i++) { 
			GameObject obj = objects.get(i);
			
			if (obj.isToRemove())
				objects.remove(i);
			else
				obj.update(delta);
		}
	}

	public ArrayList<GameObject> getObjects() {
		return objects;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public void addObject(GameObject obj) {
		objects.add(obj);
		map.addObject(obj);
	}
	
	public void removeObject(GameObject obj) {
		objects.remove(obj);
		map.removeObject(obj);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
}
