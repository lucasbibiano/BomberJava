package core;

import events.ExplodeEvent;
import game_objects.Explosion;
import game_objects.Map;
import game_objects.Player;

import java.util.LinkedList;

import networking.Message;

public abstract class Game {
	private Map map;
	
	private Player[] players;
	private int nPlayers;
	
	private LinkedList<Message> messages;
	
	public Game() {
		messages = new LinkedList<Message>();
		players = new Player[4];
	}
	
	public boolean canStart() {
		return this.nPlayers >= 2;
	}

	public void update() {
		for (int i = 0; i < messages.size(); i++) {
			Message msg = messages.get(i);
			
			players[msg.playerNumber].move(msg.moves);
			
			if (msg.placeBomb) {
				players[msg.playerNumber].placeBomb();
			}
			
			if (msg.placeBlock) {
				players[msg.playerNumber].placeBlock();
			}
		}
		
		messages.clear();
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}
	
	public void newPlayer(Player player) {
		players[nPlayers++] = player;
	}

	public void process(Message msg) {
		messages.add(msg);
	}

	public void checkExplosion(Explosion explosion) {
		for (Player player: players) {
			if (player.getX() == explosion.getX() && player.getY() == explosion.getY())
				player.exploded(new ExplodeEvent(explosion.getBomb().getPlayerNumber()));
		}
		
		map.checkExplosion(explosion);
	}
}
