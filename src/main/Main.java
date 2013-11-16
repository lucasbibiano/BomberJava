package main;

import loader.ImgLoader;
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
		
		ImgLoader img = new ImgLoader();
		img.load_map();
		img.load_bomb();
		img.load_players();
		if(img.map_sprite == null)
			System.out.println("NULL");
		else
			System.out.println("carregou mapa");
		
		if(img.bomb_spritesheet == null)
			System.out.println("NULL");
		else
			System.out.println("carregou bomba");
		
		if(img.player1_spritesheet != null && 
				img.player2_spritesheet != null && 
				img.player3_spritesheet != null &&
				img.player4_spritesheet != null)
			System.out.println("NULL");
		else
			System.out.println("carregou jogadores");
		
	};
}
