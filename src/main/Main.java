package main;

import constants.Constants.Movement;
import core.Game;
import game_objects.Block;
import game_objects.Map;
import game_objects.Player;
import graphics.window.GameWindow;

public class Main{
	
	public static void main(String[] args) {		
		Map map = new Map();
		Game game = new Game(map);
		Player p = new Player(game, 2, 2, 1);
		
		Block b = new Block(game, 2, 3);
		Block c = new Block(game, 2, 1);
		Block d = new Block(game, 1, 2);
		Block e = new Block(game, 3, 2);
		
		map.addObject(b);
		map.addObject(c);
		map.addObject(d);
		map.addObject(e);
		
		//p.placeBomb();

		
		//p.placeBomb();	
		
		System.out.println(p.toString());
		
		p.move(Movement.DOWN);
		System.out.println(p.toString());
		
		p.move(Movement.LEFT);
		System.out.println(p.toString());
		
		p.move(Movement.RIGHT);
		System.out.println(p.toString());
		
		p.move(Movement.UP);
		System.out.println(p.toString());
		
		p.move(Movement.DOWN);		
		System.out.println(p.toString());
		
	};
}
