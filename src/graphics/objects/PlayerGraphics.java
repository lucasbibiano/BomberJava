package graphics.objects;


import game_objects.Player;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class PlayerGraphics implements Drawable {

	private Player player;
	
	public PlayerGraphics(Player player) {
		this.player = player;
	}
	
	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.fillRect(player.getX(), player.getY(), 50, 50);
	}

}
