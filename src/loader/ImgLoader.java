package loader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImgLoader {
	
	HashMap<String, BufferedImage> images = null;
	
	BufferedImage map_sprite = null;
	BufferedImage bomb_spritesheet = null;
	
	BufferedImage player1_spritesheet = null;
	BufferedImage player2_spritesheet = null;
	BufferedImage player3_spritesheet = null;
	BufferedImage player4_spritesheet = null;
	
	public HashMap<String, BufferedImage> loadAll(){
		load_map();
		load_bomb();
		load_players();
		images.put("map", map_sprite);
		images.put("bomb", bomb_spritesheet);
		images.put("player1", player1_spritesheet);
		images.put("player2", player2_spritesheet);
		images.put("player3", player3_spritesheet);
		images.put("player4", player4_spritesheet);
			
		return images;
	}
	
	public void load_map(){
		try {
		    map_sprite = ImageIO.read(new File("map.jpg"));
		} catch (IOException e) {
		}
	}
	
	public void load_bomb(){
		try {
			bomb_spritesheet = ImageIO.read(new File("bomb.jpg"));
		} catch (IOException e) {
		}
	}
	
	public void load_players(){
		try {
			player1_spritesheet = ImageIO.read(new File("character1.jpg"));
			player2_spritesheet = ImageIO.read(new File("character2.jpg"));
			player3_spritesheet = ImageIO.read(new File("character3.jpg"));
			player4_spritesheet = ImageIO.read(new File("character4.jpg"));	
		} catch (IOException e) {
		}
	}
}