package graphics.objects;

import game_objects.Map;

import java.awt.Graphics;

import static constants.Constants.*;

public class MapGraphics implements Drawable {

	private Map map;

	public MapGraphics(Map map) {
		this.map = map;
	}
	
	@Override
	public void draw(Graphics g) {
		for (int i = 0; i < map.getHeight(); i++) {
			for (int j = 0; j < map.getWidth(); j++) {
				g.drawRect(i * TILESIZE, j * TILESIZE, TILESIZE, TILESIZE);
			}
		}
	}

}
