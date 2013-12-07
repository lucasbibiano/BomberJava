package graphics.objects;

import game_objects.Map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import static constants.Constants.*;

public class MapGraphics implements Drawable {

	private Map map;

	public MapGraphics(Map map) {
		this.map = map;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		BufferedImage img1 = null;
		String url1 = System.getProperty("user.dir");
		url1+="/src/sprites/grass.gif";
		
		
		BufferedImage img2 = null;
		String url2 = System.getProperty("user.dir");
		url2+="/src/sprites/block.gif";
		
		try {
		    img1 = ImageIO.read(new File(url1));
		    img2 = ImageIO.read(new File(url2));
		} catch (IOException e) {
		}
		
		for (int i = 0; i < map.getHeight(); i++) {
			for (int j = 0; j < map.getWidth(); j++) {
				//g.drawRect(j * TILESIZE, i * TILESIZE, TILESIZE, TILESIZE);
				
				if (map.getType(j, i) == EMPTY_TILE)
					g.drawImage(img1, j * TILESIZE, i * TILESIZE, null);
				else
					g.drawImage(img2, j * TILESIZE, i * TILESIZE, null);
			}
		}
	}

}
