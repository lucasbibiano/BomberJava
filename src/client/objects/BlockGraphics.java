package client.objects;

import static constants.Constants.TILESIZE;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game_objects.Block;
import game_objects.GameObject;

public class BlockGraphics extends GameObjectGraphics {
	private Block block;
	
	public BlockGraphics(GameObject block) {
		super(block);
		this.block = (Block) block;
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		BufferedImage img = null;
		String url = System.getProperty("user.dir");
		url+="/src/sprites/block.gif";
		
		try {
		    img = ImageIO.read(new File(url));
		} catch (IOException e) {
		}
		
		g2d.drawImage(img, block.getX() * TILESIZE, block.getY() * TILESIZE, null);
		
		//g2d.setColor(new Color(105,112,96));
		
	}
}
