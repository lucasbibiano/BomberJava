package loader;

import java.awt.image.BufferedImage;

public class Spritesheet {
	BufferedImage spritesheet = null;
	int numberOfSprites;
	int tileWidth;
	int tileHeight;
	
	public BufferedImage loadSprite(int position){
		BufferedImage sprite = spritesheet.getSubimage(
			tileWidth*position, 
			tileHeight,
			tileWidth-1, 
			tileHeight
		);
		return sprite;
	}
}
