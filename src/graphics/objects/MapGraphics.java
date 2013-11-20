package graphics.objects;

import game_objects.Map;
import game_objects.Player;

import java.awt.Graphics;

public class MapGraphics extends GameObjectGraphics<Map> {

	private static final int TILESIZE = 32;
	
	private Map map;

	public MapGraphics(Map map) {
		super(map);
		this.map = super.getObject();
	}
	
	@Override
	public void draw(Graphics g) {
		for (int i = 0; i < map.getyLimit(); i++) {
			for (int j = 0; j < map.getxLimit(); j++) {
				g.drawRect(i * TILESIZE, j * TILESIZE, TILESIZE, TILESIZE);
				
				if (map.objAt(i, j) instanceof Player) {
					PlayerGraphics playerGraphics = new PlayerGraphics((Player) map.objAt(i, j));
					playerGraphics.draw(g);
				}
			}
		}
	}

}
