package loader;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImgLoader {
	
	HashMap<String, BufferedImage> images = null;
	
	public BufferedImage map_sprite = null;
	public BufferedImage bomb_spritesheet = null;
	
	public BufferedImage player1_spritesheet = null;
	public BufferedImage player2_spritesheet = null;
	public BufferedImage player3_spritesheet = null;
	public BufferedImage player4_spritesheet = null;
	
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
			java.net.URL url = this.getClass().getResource("../sprites/sprites.PNG");
		    map_sprite = ImageIO.read(url);
		} catch (IOException e) {
		}
	}
	
	public void load_bomb(){
		try {
			java.net.URL url = this.getClass().getResource("../sprites/bomb.PNG");
			bomb_spritesheet = ImageIO.read(url);
		} catch (IOException e) {
		}
	}
	
	public void load_players(){
		try {
			java.net.URL urlPlayer1 = this.getClass().getResource("./../sprites/character1.PNG");
			player1_spritesheet = ImageIO.read(urlPlayer1);
			
			java.net.URL urlPlayer2 = this.getClass().getResource("../sprites/character2.PNG");
			player1_spritesheet = ImageIO.read(urlPlayer2);
			
			java.net.URL urlPlayer3 = this.getClass().getResource("../sprites/character3.PNG");
			player3_spritesheet = ImageIO.read(urlPlayer3);
			
			java.net.URL urlPlayer4 = this.getClass().getResource("../sprites/character4.PNG");
			player4_spritesheet = ImageIO.read(urlPlayer4);
		} catch (IOException e) {
		}
	}
}