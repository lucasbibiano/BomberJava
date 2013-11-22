package main;

import loader.ImgLoader;
import constants.Constants.Movement;
import core.Game;
import game_objects.Block;
import game_objects.Map;
import game_objects.Player;
import graphics.window.GameWindow;

public class Main{
	
	public static void main(String[] args) {		
		Game game = new Game();
		Map map = new Map(game);
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
