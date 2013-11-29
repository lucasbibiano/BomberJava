package graphics.objects;

import static constants.Constants.TILESIZE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import game_objects.Bomb;
import game_objects.GameObject;

public class BombGraphics extends GameObjectGraphics {
	
	private Bomb bomb;
	
	public BombGraphics(GameObject bomb) {
		super(bomb);
		this.bomb = (Bomb) bomb;
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(bomb.isExploded() ? Color.YELLOW : Color.black);
		
		g2d.fillOval(bomb.getX() * TILESIZE + 4, bomb.getY() * TILESIZE + 4, 32 - 8, 32 - 8);
	}
}
