package main;

import constants.Constants.Movement;
import core.Game;
import game_objects.Map;
import game_objects.Player;

public class Main{
	
	public static void main(String[] args) {
		
		Map map = new Map();
		Game game = new Game(map);
		Player p = new Player(game, 0, 0, 1);
		p.setX(10);
		p.setY(15);
		
		p.placeBomb();
		
		p.setX(11);
		p.setY(15);
		
		p.placeBomb();

		
		
		p.setX(0);
		p.setY(0);
			
		p.setX(1);
		p.setY(1);
		
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
