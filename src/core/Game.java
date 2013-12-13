package core;

import events.ExplodeEvent;
import game_objects.Bomb;
import game_objects.Explosion;
import game_objects.Map;
import game_objects.Player;

import java.util.LinkedList;
import java.util.List;

import networking.GameMessage;

public class Game {
	private Map map;
	
	private Player[] players;
	protected int nPlayers;
	
	protected LinkedList<GameMessage> messages;
	protected LinkedList<Bomb> bombs;
	protected LinkedList<Explosion> explosions;
	
	public Game() {
		messages = new LinkedList<GameMessage>();
		players = new Player[4];
		bombs = new LinkedList<Bomb>();
		explosions = new LinkedList<Explosion>();
	}
	
	public boolean canStart() {
		return nPlayers >= 2;
	}

	public void update() {
		for (int i = 0; i < messages.size(); i++) {
			GameMessage msg = messages.get(i);
			
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
	
	public synchronized void newPlayer(Player player) {
		players[nPlayers++] = player;
	}

	public void process(GameMessage msg) {
		messages.add(msg);
	}
	
	public Player[] getPlayers() {
		return players;
	}

	public void checkExplosion(Explosion explosion) {
		for (Player player: players) {
			if (player.getX() == explosion.getX() && player.getY() == explosion.getY())
				player.exploded(new ExplodeEvent(explosion.getBomb().getPlayerNumber()));
		}
		
		map.checkExplosion(explosion);
	}
	
	public void addBomb(Bomb bomb) {
		bombs.add(bomb);
	}
	
	public void addExplosion(Explosion exp) {
		explosions.add(exp);
	}
	
	public int getNPlayers() {
		return nPlayers;
	}

	public List<Bomb> getBombs() {
		return bombs;
	}
	
	public List<Explosion> getExplosions() {
		return explosions;
	}
}
