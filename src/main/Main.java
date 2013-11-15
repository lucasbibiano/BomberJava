package main;

import core.Game;
import game_objects.Map;
import game_objects.Player;

public class Main {
	public static void main(String[] args) {
		Map map = new Map();
		Game game = new Game(map);
		Player p = new Player(game, 1);
		p.setX(10);
		p.setY(15);
		
		p.placeBomb();
		
		p.setX(9);
		p.setY(4);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.placeBomb();
			
		p.setX(0);
		p.setY(0);
	}
}
