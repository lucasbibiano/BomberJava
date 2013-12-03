package core;

import game_objects.GameObject;
import game_objects.Map;
import game_objects.Player;

import java.util.ArrayList;
import java.util.LinkedList;

import networking.Message;

public abstract class Game {
	private Player player;
	private ArrayList<GameObject> objects;
	private Map map;
	
	private LinkedList<Message> messages;

	public Game() {
		objects = new ArrayList<GameObject>();
		messages = new LinkedList<Message>();
	}

	public void update(double delta) {
		for (int i = 0; i < messages.size(); i++) {
			Message msg = messages.get(i);
			
			this.getPlayer().move(msg.moves);
			
			if (msg.placeBomb) {
				this.getPlayer().placeBomb();
			}
			
			if (msg.placeBlock) {
				this.getPlayer().placeBlock();
			}
		}
		
		messages.clear();
		
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

	public void process(Message msg) {
		messages.add(msg);
	}
}
